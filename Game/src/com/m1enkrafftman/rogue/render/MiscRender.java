package com.m1enkrafftman.rogue.render;

import org.lwjgl.opengl.GL11;

public class MiscRender {
	
	public static void renderBasicCube(int x, int y, int width, int objectColor) {
		GL11.glPushMatrix();
		float f  = (float)(objectColor >> 24 & 0xff) / 255F;
		float f1 = (float)(objectColor >> 16 & 0xff) / 255F;
		float f2 = (float)(objectColor >> 8  & 0xff) / 255F;
		float f3 = (float)(objectColor       & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glRecti(x, y, x + width, y + width);
		GL11.glPopMatrix();
	}
	
	public static void renderBasicWeirdCube(int x, int y, int width, int height, int objectColor) {
		GL11.glPushMatrix();
		float f  = (float)(objectColor >> 24 & 0xff) / 255F;
		float f1 = (float)(objectColor >> 16 & 0xff) / 255F;
		float f2 = (float)(objectColor >> 8  & 0xff) / 255F;
		float f3 = (float)(objectColor       & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glRecti(x, y, x + width, y + height);
		GL11.glPopMatrix();
	}

}
