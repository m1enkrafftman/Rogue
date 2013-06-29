package com.m1enkrafftman.rogue.gui.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.GameLoop;
import com.m1enkrafftman.rogue.external.level.Level;
import com.m1enkrafftman.rogue.gui.FontRenderer;
import com.m1enkrafftman.rogue.gui.GuiButton;
import com.m1enkrafftman.rogue.gui.GuiScreen;

public class InfoScreen extends GuiScreen {
	
	public InfoScreen() {
		super();
		this.getButtons().add(new GuiButton("Return to Menu", 300, 250, 200, 40, 0xff0000ff));
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
        FontRenderer.drawStringWithColor("Information", 400 - (FontRenderer.getStringWidth("You win")/2), 200, 0xff11ffff);
        this.renderInfo();
		for(GuiButton button : this.getButtons()) {
			button.render();
		}
	}

	@Override
	public void postRender() {
		
	}
	
	private void renderInfo() {
		int x = 400;
		int y = 310;
		for(String s : this.handleInfo()) {
			FontRenderer.drawCenteredString(s, x, y, 0xffffffff);
			y += 12;
		}
	}
	
	private ArrayList<String> handleInfo() {
		ArrayList<String> info = new ArrayList<>();
		info.add("Controls =");
		info.add("W = Walk forward");
		info.add("A = Rotate left");
		info.add("D = Rotate right");
		info.add("Spacebar = attack");
		info.add("_");
		info.add("Objective =");
		info.add("You are a rogue, hired to");
		info.add("storm the king's castle and");
		info.add("slaughter him and all his men.");
		info.add("This is an early-dev game, so");
		info.add("please ignore the bugs.");
		return info;
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
				case("Return to Menu"):
					GameLoop.currentScreen = new MainMenu();
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