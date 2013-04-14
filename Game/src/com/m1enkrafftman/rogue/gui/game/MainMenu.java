package com.m1enkrafftman.rogue.gui.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.GameLoop;
import com.m1enkrafftman.rogue.gui.GuiButton;
import com.m1enkrafftman.rogue.gui.GuiScreen;
import com.m1enkrafftman.rogue.misc.Cache;

public class MainMenu extends GuiScreen {
	
	public MainMenu(GameLoop loop) {
		super();
		this.getButtons().add(new GuiButton("Singleplayer", 300, 280, 200, 40, 0xff0000ff));
	}

	public void preRender() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glLoadIdentity();
	}

	private void renderBackground(int objectColor) {
		GL11.glPushMatrix();
		float f  = (float)(objectColor >> 24 & 0xff) / 255F;
		float f1 = (float)(objectColor >> 16 & 0xff) / 255F;
		float f2 = (float)(objectColor >> 8  & 0xff) / 255F;
		float f3 = (float)(objectColor       & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glRecti(0, 0, 800, 600);
		GL11.glPopMatrix();
	}

	@Override
	public void render() {
        this.renderBackground(0xff444444);
        Cache.IMAGE_HANDLER.renderImage256(Cache.IMAGE_HANDLER.getTextureByName("title"), 400 - (256/2), 15);
		for(GuiButton button : this.getButtons()) {
			button.render();
		}
	}

	@Override
	public void postRender() {
		
	}

	@Override
	public void onMouseClick(int key) {
		for(GuiButton button : this.getButtons()) {
			button.handleMouse(Mouse.getX(), Mouse.getY());
		}
		this.check();
	}
	
	public void check() {
		for(GuiButton b : this.getButtons()) {
			if(b.getButtonState()) {
				switch(b.getName()) {
				case("Singleplayer"):
					GameLoop.currentScreen = new InGame();
					break;
				}
			}
		}
	}

	@Override
	public void onKeyPress(int key) {
		switch(key) {
		case Keyboard.KEY_ESCAPE:
			Display.destroy();
		}
	}

	@Override
	public void handleLogic() {
	}

}
