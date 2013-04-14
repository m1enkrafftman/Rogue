package com.m1enkrafftman.rogue.gui;

import org.lwjgl.opengl.GL11;

public class Gui {

	/** Draw a filled rectangle. */
	public void drawRect_Filled(double xPos, double yPos, double width, double height, int color, float zLevel, float... g)
	{
		float f = (float)(color >> 24 & 0xFF) / 255F;
		float f1 = (float)(color >> 16 & 0xFF) / 255F;
		float f2 = (float)(color >> 8 & 0xFF) / 255F;
		float f3 = (float)(color & 0xFF) / 255F;
		
		GL11.glPushMatrix();
		GL11.glTranslated(xPos + (width / 2), yPos + (height / 2), zLevel);
		GL11.glScaled(width, height, 1.0D);
		
		if (g != null){
			if(g.length == 3) {
				GL11.glRotatef(g[0], 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(g[1], 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(g[2], 0.0f, 0.0f, 1.0f);
			}
		}
		
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glVertex2d(-0.5D, -0.5D);
			GL11.glVertex2d(-0.5D,  0.5D);
			GL11.glVertex2d( 0.5D,  0.5D);
			GL11.glVertex2d( 0.5D, -0.5D);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glPopMatrix();
	}
	
}
