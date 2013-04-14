package com.m1enkrafftman.rogue;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import com.m1enkrafftman.rogue.backend.Time;
import com.m1enkrafftman.rogue.backend.Timer;
import com.m1enkrafftman.rogue.gui.GuiScreen;
import com.m1enkrafftman.rogue.gui.game.MainMenu;
import com.m1enkrafftman.rogue.misc.Cache;
import com.m1enkrafftman.rogue.render.ImageHandler;

public class GameLoop {

	/** Whether the game is running. */
	public volatile boolean running = true;
	
	public final String title = "Rogue";
	
	private long  timeDelta;
	
	/** FPS calculations. */
	private long timeLastLoop;
	private long timeLastFPS;
	private int fps;
	private int fpsCurrent;
	
	/** How many ticks per second our game will run. */
	public Timer timer = new Timer(20.0F);
	
	public static GuiScreen currentScreen;
	
	public static GameLoop instance;
	
	public GameLoop() {
		this.init();
		this.runGame();
		this.cleanUp();
		GameLoop.instance = this;
	}
	
	/** Initiate Rogue. */
	public void init() {
		
		System.out.printf("%sRogue starting!\n", Rogue.loggingPrefix);
		System.out.printf("%sLWJGL Version " + Sys.getVersion() + "\n", Rogue.loggingPrefix);
		
		this.initDisplay();
		this.initOpenGL();
		this.initOpenAL();
		this.setupTextures();
	}
	
	public void setupTextures() {
		Cache.IMAGE_HANDLER = new ImageHandler();
	}
	
	/** Create the openGL context. */
	public void initOpenGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Cache.width, Cache.height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void initOpenAL() {
		// TODO: Implement PaulsCode for sound.
		// TODO: Will have support for ingame voice + play wav files.
		try {
			AL.create();
		} catch (LWJGLException e) {
			System.err.printf("%sError initiating OpenAL. Sound will be disabled!\n", Rogue.loggingPrefix);
			e.printStackTrace();
		}
	}
	
	/** Clean up the game. */
	public void cleanUp() {
		AL.destroy();
		System.out.printf("%sSomebody closed me :(", Rogue.loggingPrefix);
		System.exit(0);
	}
	
	private void initDisplay() {
		try {

			Display.setDisplayMode(new DisplayMode(Cache.width, Cache.height));
			Display.setTitle("Rogue");
			Display.create();
			System.out.printf("%sDisplay created: [%s ,%s]\n", Rogue.loggingPrefix, ""+Cache.width, Cache.height);

		} catch (LWJGLException e) {
			System.err.printf("%sError retrieving display modes.\n", Rogue.loggingPrefix);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void runGame() {
		GameLoop.currentScreen = new MainMenu(GameLoop.instance);
		while(this.running) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			if(Display.isCloseRequested()) this.running = false;
			this.logic();
			this.render();
			this.calculateFPS();
			Cache.fps = this.getFPS();
			Display.update();
			Display.sync(20);
		}
	}
	
	public void logic() {
		this.timer.update();
		GameLoop.currentScreen.handleMouse();
		GameLoop.currentScreen.handleKey();
		GameLoop.currentScreen.handleLogic();
	}
	
	public void render() {
		GameLoop.currentScreen.render();
	}
	
	/** Returns the current FPS. */
	public int getFPS() {
		return fpsCurrent;
	}
	
	/** Returns delta time. */
	public long getDeltaTime() {
		return timeDelta;
	}
	
	/** Calculate the current FPS. */
	public void calculateFPS() {
		
		this.timeDelta = Time.MILLISECONDS.getCurrent() - this.timeLastLoop;
		this.timeLastLoop = Time.MILLISECONDS.getCurrent();
		this.timeLastFPS += this.timeDelta;
		this.fps++;
		
		if (this.timeLastFPS >= 1000) {
			this.fpsCurrent = this.fps;
			this.timeLastFPS = 0;
			this.fps = 0;
		}
	}
	
}