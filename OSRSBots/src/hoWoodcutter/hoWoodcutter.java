package hoWoodcutter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

@ScriptManifest(category = Category.WOODCUTTING, name = "hoWoodcutter", description = "Gets the wood, ya dummy.", author = "HeatSlinger & Opoz", version = 0.1)
public class hoWoodcutter extends AbstractScript {

	private boolean shouldStart;
	private hoWoodcutterGUI gui;
	private Node[] nodeArray;
	private Settings settings;

	private BufferedImage mainPaint = getImage("http://i.imgur.com/TUk704K.jpg");
	private int logsCut;
	private int logsHr;
	private String status;
	private Timer timeRan;
	private SkillTracker tracker;
	private Tile deathSpot;

	@Override
	public void onStart() {
		gui = new hoWoodcutterGUI(this);
		timeRan = new Timer();
		tracker = new SkillTracker(getClient());
		nodeArray = new Node[] {/*new RetrieveItems(this), new RetrieveItemsWalk(this),*/ new Flee(this), new Bank(this), new BankWalk(this), new Chop(this), new ChopWalk(this) };
		
		gui.setVisible(true);
		tracker.start(Skill.WOODCUTTING);

		log("Hello, you have started hoWoodcutter by HeatSlinger & Opoz, enjoy!");
	}

	@Override
	public void onMessage(Message message) {
		if (message.getMessage().contains("You get some")) { // ayyy ;)
			logsCut++;
		}
	}

	@Override
	public int onLoop() {
		
		try {
			if (shouldStart) {
				if (settings == null) {
					settings = new Settings();
				} else if (settings.getLocations() == null) {
					settings.setLocations(new Locations(gui.getTreeType(), gui.getTreeArea(), gui.getBankArea()));
					settings.setWorldHop(gui.getWorldHop());
				} else {
					for (final Node node : nodeArray) {
						if (node.validate()) {
							status = node.status();
							node.execute();
							node.delay();
							return 0;
						}
						sleep(500);
					}
				}
			}
		} catch (Exception e){
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
		g.drawImage(mainPaint, 316, 3, null);
		
		logsHr =  (int) (logsCut * 3600000D / timeRan.elapsed());

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
		g.drawString("Status: " + status, 185, 333);
		

	}

	private BufferedImage getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public void setShouldStart(boolean shouldStart) {
		this.shouldStart = shouldStart;
	}

	public hoWoodcutterGUI getGUI() {
		return gui;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setDeathSpot(Tile spot) {
		deathSpot = spot;
	}

	public Tile getDeathSpot() {
		return deathSpot;
	}
}