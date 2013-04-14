package com.m1enkrafftman.rogue.backend;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

import com.m1enkrafftman.rogue.Rogue;

public class DisplayFactory {
	/** The title of the display. */
	private String title;
	
	/** The display mode of the display. */
	private DisplayMode displayMode;
	
	/** The width of the display. */
	private int width;
	
	/** The height of the display. */
	private int height;
	
	/** Whether the display is fullscreen. */
	private boolean fullscreen;
	
	/** Whether the display is sizeable. */
	private boolean sizeable;
	
	/** Whether the display mode is setup for analygraph rendering. */
	private boolean analygraph;
	
	/** Whether the display is in wireframe mode. */
	private boolean wireframe;
	
	/** Whether the display is in VSync mode. */
	private boolean vsync;
	
	/** Construct a new DisplayFactory instance. */
	public DisplayFactory(String title, DisplayMode displayMode, boolean fullscreen, boolean sizeable, boolean analygraph) {
		this.title = title;
		this.displayMode = displayMode;
		this.width = displayMode.getWidth();
		this.height = displayMode.getHeight();
		this.fullscreen = fullscreen;
		this.sizeable = sizeable;
		this.analygraph = analygraph;
		
		this.init();
	}
	
	/** Initiate the Display. */
	public void init() {
		
		// TODO: Currently setup for openGL 2.1 and defaulted to 1.0 on failure. Entire application would need redone for openGL 3.2 (due to Matrix manipulations being removed).
		
		try {

			ContextAttribs context;
			
			Display.create(); // Fake context to poll openGL capabilities.
			
			if (GLContext.getCapabilities().OpenGL21) {
				context = new ContextAttribs(2,1);
				context.withForwardCompatible(true);
			} else {
				context = new ContextAttribs(1,1);
				context.withForwardCompatible(true);
			}
			
			Display.destroy();
			
			System.out.printf("%sUsing OpenGL version %d.%d\n", Rogue.loggingPrefix, context.getMajorVersion(), context.getMinorVersion());
			
			Display.setFullscreen(this.fullscreen);
			Display.setDisplayMode(this.displayMode);
			Display.setTitle(this.title);
			Display.setResizable(this.sizeable);
			Display.create(new PixelFormat().withDepthBits(24), context);
			
		} catch (LWJGLException e) {
			System.err.printf("%sError creating display.\n", Rogue.loggingPrefix);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/** Returns the display mode of the display. */
	public DisplayMode getDisplayMode() {
		return displayMode;
	}
	
	/** Change the display mode of the display. */
	public void changeDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
		this.width = displayMode.getWidth();
		this.height = displayMode.getHeight();
		
		this.resize(this.width, this.height);
	}
	
	/** Returns the width of the display. */
	public final int getWidth() {
		return width;
	}
	
	/** Returns the height of the display. */
	public final int getHeight() {
		return height;
	}
	
	/** Called to resize the display. */
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/** Returns whether the display is fullscreen. */
	public final boolean isFullscreenEnabled() {
		return fullscreen;
	}
	
	/** Toggle the fullscreen state of the display. */
	public void toggleFullscreen() {
		
		// TODO: changeDisplayMode() on toggle otherwise 'render area' will be smaller than the fullscreen!
		
		this.fullscreen = !this.fullscreen;
		
		try {
			Display.setFullscreen(this.fullscreen);
		} catch (LWJGLException e) {
			System.err.printf("%sError toggling fullscreen mode.\n", Rogue.loggingPrefix);
			e.printStackTrace();
		}
	}
	
	/** Returns whether the display is in analygraph mode. */
	public final boolean isAnalygraph() {
		return analygraph;
	}
	
	/** Toggle the analygraph mode of the display. */
	public void toggleAnalygraph() {
		this.analygraph = !this.analygraph;
		
		// TODO: Maybe later implement analygraph.
	}
	
	/** Returns the wireframe state of the display. */
	public final boolean isWireframeEnabled() {
		return wireframe;
	}
	
	/** Toggle the wireframe mode of the display. */
	public void toggleWireframe() {
		this.wireframe = !this.wireframe;
		
		if (this.wireframe){
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		} else {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		}
	}
	
	/** Returns the VSync state of the display. */
	public final boolean isVsyncEnabled() {
		return vsync;
	}
	
	/** Toggle the VSync mode of the display. */
	public void toggleVSync() {
		this.vsync = !this.vsync;
		
		Display.setVSyncEnabled(this.vsync);
	}
	
	/** Update the display after rendering. */
	public void update() {
		Display.update();
	}
	
	/** The end is nigh! */
	public void cleanUp() {
		Display.destroy();
	}
}
