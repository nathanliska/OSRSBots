package hoWoodcutter;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.widgets.message.Message;

import hoWoodcutter.core.Locations;
import hoWoodcutter.core.Settings;
import hoWoodcutter.core.Status;
import hoWoodcutter.task.GExchange;
import hoWoodcutter.task.Node;
import hoWoodcutter.task.antiban.Antiban;
import hoWoodcutter.task.bank.Bank;
import hoWoodcutter.task.bank.BankWalk;
import hoWoodcutter.task.chop.Chop;
import hoWoodcutter.task.chop.ChopWalk;
import hoWoodcutter.task.flee.Flee;
import hoWoodcutter.task.hop.WorldHop;
import hoWoodcutter.task.powerChop.DropAll;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 0.3)
public class hoWoodcutter extends AbstractScript {

	private boolean shouldStart;
	private boolean levelUp;
	private boolean worldHop;
	private boolean antiban;

	private int logsCut;
	private int logsHr;
	private int logsPrice;

	private String status;

	private hoWoodcutterGUI gui;
	private Status state;
	private Node[] nodeArray;
	private Settings settings;

	private BufferedImage mainPaint;
	private Timer timeRan;
	private SkillTracker tracker;
	private Tile deathSpot;

	private Frame dreambotFrame = findDreambotFrame();

	@Override
	public void onStart() {
		gui = new hoWoodcutterGUI(this);
		timeRan = new Timer();
		tracker = new SkillTracker(getClient());
		nodeArray = new Node[] { new Antiban(this), new WorldHop(this), new Flee(this), new DropAll(this),
				new Bank(this), new BankWalk(this), new Chop(this), new ChopWalk(this) };
		status = "Starting script.";
		antiban = false;

		mainPaint = getImageFromURL("http://i.imgur.com/TUk704K.jpg");

		if (mainPaint == null) {
			log("Error loading paint, will retry shortly.");
		}

		gui.setVisible(true);
		tracker.start(Skill.WOODCUTTING);
		log("Hello, you have started hoWoodcutter version " + getVersion() + " by HeatSlinger & Opoz, enjoy!");
	}

	@Override
	public void onMessage(Message message) {
		if (message.getMessage().contains("You get some")) { // ayyy ;)
			logsCut++;
		} else if (message.getMessage().contains("Congratulations, you just advanced")) {
			levelUp = true;
			sleep(Calculations.random(300, 900));
			getMouse().click(new Rectangle(210, 440, 150, 9));
			if (getDialogues().inDialogue()) {
				getDialogues().spaceToContinue();
			}
			levelUp = false;
		}
	}

	@Override
	public int onLoop() {
		if (mainPaint == null) {
			mainPaint = getImageFromURL("http://i.imgur.com/TUk704K.jpg");
		}

		try {
			if (shouldStart) {
				if (settings == null) {
					settings = new Settings();
					settings.setLocations(new Locations(gui.getTreeType(), gui.getTreeArea(), gui.getBankArea()));
					settings.setWorldHopIfAttacked(gui.getWorldHopIfAttacked());
					settings.setWorldHopIfNoTrees(gui.getWorldHopIfNoTrees());
					settings.setPowerChop(gui.getPowerChop());

					logsPrice = GExchange.getPrice(gui.getTreeType().getItemID());
				} else if (getClient().isLoggedIn()) {
					for (final Node node : nodeArray) {
						if (node.validate()) {
							status = node.status();
							node.execute();
							return node.delay();
						}
					}
				}
			}
		} catch (Exception e) {
			log("Error at: " + status);
			e.printStackTrace();
			return 0;
		}

		return Calculations.random(300, 500);
	}

	@Override
	public void onExit() {
		log("Goodbye!");
	}

	@Override
	public void onPaint(Graphics g) {
		if (mainPaint != null) {
			g.drawImage(mainPaint, 316, 3, null);
		}

		logsHr = (int) (logsCut * 3600000D / timeRan.elapsed());

		Font font = new Font("Arial", Font.BOLD, 13);
		g.setFont(font);
		g.setColor(Color.BLACK);
		// Time Elapsed
		g.drawString("" + timeRan.formatTime(), 435, 17);
		// Logs Cut
		g.drawString("" + logsCut, 435, 34);
		// Logs/hr
		g.drawString("" + logsHr, 435, 50);
		// Xp Gained
		g.drawString("" + tracker.getGainedExperience(Skill.WOODCUTTING), 435, 66);
		// Xp Gain/hr
		g.drawString("" + tracker.getGainedExperiencePerHour(Skill.WOODCUTTING), 435, 82);
		// Gp/hr
		g.drawString("" + logsPrice * logsHr, 435, 98);
		// Current Status
		g.setColor(Color.WHITE);
		g.drawString("Status: " + status, 185, 333);

	}

	public void setShouldStart(boolean shouldStart) {
		this.shouldStart = shouldStart;
	}

	public void setDeathSpot(Tile spot) {
		deathSpot = spot;
	}

	public void setWorldHop(boolean hop) {
		worldHop = hop;
	}

	public void setAntiban(int time) {
		antiban = true;
	}

	public hoWoodcutterGUI getGUI() {
		return gui;
	}

	public Settings getSettings() {
		return settings;
	}

	public Tile getDeathSpot() {
		return deathSpot;
	}

	public boolean getLevelUp() {
		return levelUp;
	}

	public boolean getWorldHop() {
		return worldHop;
	}

	public boolean getAntiban() {
		return antiban;
	}

	@SuppressWarnings("unused")
	private Image getImage(String fileName) {

		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(("temp2.png"));
				log("outside if");
				if (!myFile.exists()) {
					log("inside if");
					// In JAR
					URL url = getClass().getResource(fileName);

					log(url.getPath());

					url.openConnection().getInputStream();

					InputStream inputStream = getClass().getResourceAsStream(fileName);

					// Copy file
					OutputStream outputStream = new FileOutputStream(myFile);
					byte[] buffer = new byte[1024];
					int length;
					if (inputStream == null) {
						log("input stream null");
					}
					while ((length = inputStream.read()) > 0) {
						outputStream.write(length);
						log("Ran this");
					}
					// while ((length = inputStream.read(buffer)) > 0) {
					// outputStream.write(buffer, 0, length);
					// log("ran this");
					// }
					outputStream.close();
					inputStream.close();
				}
				// Desktop.getDesktop().open(myFile);
				log("" + (ImageIO.read(myFile) == null));
				return ImageIO.read(myFile);
			} catch (IOException ex) {
				log(ex.getMessage());
				log("IOException");

			}
		}
		return null;
		/*
		 * URL url = hoWoodcutter.class.getResource(fileName);
		 * 
		 * BufferedImage image = null;
		 * 
		 * if(url == null) { log("url is null"); }
		 * 
		 * log(url.getPath()); // returning
		 * file:/C:/Users/Me/DreamBot/Scripts/MyJar.jar!/package/paint.png
		 * log(url.toString()); // To make sure I have the correct file //
		 * returning
		 * jar:file:/C:/Users/Me/DreamBot/Scripts/MyJar.jar!/package/paint.png
		 * 
		 * try { image = ImageIO.read(url); } catch (IOException e1) { log(
		 * "Error converting url to image."); }
		 * 
		 * if (image == null) { log("Image is null."); } else {
		 * 
		 * }
		 */

		// return image;
	}

	private BufferedImage getImageFromURL(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public static Frame findDreambotFrame() {
		for (Frame frame : Frame.getFrames()) {
			if (frame.getTitle().toLowerCase().contains("dreambot")) {
				return frame;
			}
		}
		return null;
	}

	public Frame getDreambotFrame() {
		return dreambotFrame;
	}
}