package hoWoodcutter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import hoWoodcutter.task.Node;
import hoWoodcutter.task.bank.Bank;
import hoWoodcutter.task.bank.BankWalk;
import hoWoodcutter.task.chop.Chop;
import hoWoodcutter.task.chop.ChopWalk;
import hoWoodcutter.task.flee.Flee;
import hoWoodcutter.task.powerChop.DropAll;

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 0.285)
public class hoWoodcutter extends AbstractScript {

	private boolean shouldStart;
	private boolean levelUp;
	private int logsCut;
	private int logsHr;

	private String status;

	private hoWoodcutterGUI gui;
	private Node[] nodeArray;
	private Settings settings;

	private Image mainPaint;// getImage("http://i.imgur.com/TUk704K.jpg");
	private Timer timeRan;
	private SkillTracker tracker;
	private Tile deathSpot;

	@Override
	public void onStart() {
		gui = new hoWoodcutterGUI(this);
		timeRan = new Timer();
		tracker = new SkillTracker(getClient());
		nodeArray = new Node[] { new Flee(this), new DropAll(this), new Bank(this), new BankWalk(this), new Chop(this),
				new ChopWalk(this) };

		mainPaint = getImage("paint.png");

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

		try {
			if (shouldStart) {
				if (settings == null) {
					settings = new Settings();
				}
				if (settings.getLocations() == null) {
					settings.setLocations(new Locations(gui.getTreeType(), gui.getTreeArea(), gui.getBankArea()));
					settings.setWorldHop(gui.getWorldHop());
					settings.setPowerChop(gui.getPowerChop());
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
			log("Error at :" + status);
			return 0;
		}

		return Calculations.random(300, 500);
	}

	@Override
	public void onExit() {
		log("Goodbye!");
	}

	@Override
	public void onPaint(Graphics2D g) {
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
		g.drawString("" + logsCut * (int) 3600000D / timeRan.elapsed(), 435, 50);
		// Xp Gained
		g.drawString("" + tracker.getGainedExperience(Skill.WOODCUTTING), 435, 66);
		// Xp Gain/hr
		g.drawString("" + tracker.getGainedExperiencePerHour(Skill.WOODCUTTING), 435, 82);
		// Gp/hr
		g.drawString("" + settings.getLocations().getTree().getLogPrice() * logsHr, 435, 98);
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

	private Image getImage(String url) {
		/*
		 * int counter = 0; while (counter < 20) { try { log("ran this"); return
		 * ImageIO.read(new URL(url)); } catch (IOException e) { log(
		 * "Issue loading paint"); } sleep(200); counter++; } return null;
		 */
		/*
		 * try { PrintWriter writer = new PrintWriter("the-file-name.txt",
		 * "UTF-8"); } catch (FileNotFoundException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); } catch
		 * (UnsupportedEncodingException e1) { // TODO Auto-generated catch
		 * block e1.printStackTrace(); }
		 */
		URL image = getClass().getResource(url);
		log(image.toString());
		while (image == null) {
			log("Searching for paint...");
			image = getClass().getResource(url);
		}
		Image temp = null;
		//while (temp == null) {
			try {
				temp = ImageIO.read(image);
			} catch (IOException e1) {
				log("error converting url to image");
				for(int i = 0; i < e1.getStackTrace().length; i++) {
					log(e1.getStackTrace()[i].toString());
				}
			}
		//}
			if(temp == null) {
				log("Image is null");
			}
		return temp;
	}
}