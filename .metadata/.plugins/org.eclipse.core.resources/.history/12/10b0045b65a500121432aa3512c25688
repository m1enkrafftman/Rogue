package com.m1enkrafftman.rogue.gui;

import org.lwjgl.opengl.GL11;

import com.m1enkrafftman.rogue.Rogue;

public class GuiButton extends Gui {
	
	private String name;
	private int x, y;
	private int width, height;
	private int color;
	private boolean state;
	
	public GuiButton(String n, int x, int y, int width, int height, int color) {
		this.name = n;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.state = false;
	}
	
	public void handleMouse(int x, int y) {
		y = 600-y;
		if(x > this.x && x < (this.x + this.width) &&
				y > this.y && y < (this.y + this.height)) {
			this.setBoolean(true);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setBoolean(boolean b) {
		this.state = b;
	}
	
	public boolean getButtonState() {
		return this.state;
	}
	
	public void render() {
		GL11.glPushMatrix();
		GL11.glColor4f(0.0f, 0.5f, 1.3f, 1.0f);
		GL11.glRecti(this.x, this.y, this.x+this.width, this.y+2);
		GL11.glRecti(this.x, this.y + (this.height-2), this.x+this.width, this.y+this.height);
		GL11.glRecti(this.x, this.y, this.x+2, this.y+this.height);
		GL11.glRecti(this.x + (this.width-2), this.y, this.x+this.width, this.y+this.height);
		GL11.glColor4f(0.0f, 0.1f, 1.7f, 1.0f);
		GL11.glRecti(this.x + 2, this.y + 2, this.x+(this.width-2), this.y+(this.height-2));
		int drawX = this.x + ((this.width/2) - (FontRenderer.getStringWidth(this.name)/2));
		int drawY = this.y + (((this.height / 2) - 5) + 10);
		FontRenderer.drawStringWithColor(this.name, drawX, drawY, 0xff00ff00);
		GL11.glPopMatrix();
	}

}
