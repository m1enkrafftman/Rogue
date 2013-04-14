package com.m1enkrafftman.rogue.render;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class ImageHandler {
	
	private ArrayList<Image> textures = new ArrayList<>();
	
	public ImageHandler() {
		this.loadTextures();
	}
	
	public void loadTextures() {
		textures.add(new Image("player"));
		textures.add(new Image("floor"));
		textures.add(new Image("title"));
	}
	
	public Texture getTextureByName(String s) {
		Texture t = null;
		for(Image tex : this.textures) {
			if(s.equals(tex.getName())) {
				t = tex.getTexture();
			}
		}
		return t;
	}
	
	public void renderImage(Texture t, int x, int y) {
		int z = 0;
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
    	GL11.glTexCoord3f(0, 0, 0);
    	GL11.glVertex3f(x, y, z);
    	GL11.glTexCoord3f(1, 0, 0);
    	GL11.glVertex3f(x + 16, y, z);
    	GL11.glTexCoord3f(1, 1, 0);
    	GL11.glVertex3f(x + 16, y + 16, z);
    	GL11.glTexCoord3f(0, 1, 0);
    	GL11.glVertex3f(x, y + 16, z);
    	GL11.glEnd();
	}
	
	public void renderImage32(Texture t, int x, int y) {
		int z = 0;
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
    	GL11.glTexCoord3f(0, 0, 0);
    	GL11.glVertex3f(x, y, z);
    	GL11.glTexCoord3f(1, 0, 0);
    	GL11.glVertex3f(x + 32, y, z);
    	GL11.glTexCoord3f(1, 1, 0);
    	GL11.glVertex3f(x + 32, y + 32, z);
    	GL11.glTexCoord3f(0, 1, 0);
    	GL11.glVertex3f(x, y + 32, z);
    	GL11.glEnd();
	}
	
	public void renderImage256(Texture t, int x, int y) {
		int z = 0;
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
    	GL11.glTexCoord3f(0, 0, 0);
    	GL11.glVertex3f(x, y, z);
    	GL11.glTexCoord3f(1, 0, 0);
    	GL11.glVertex3f(x + 256, y, z);
    	GL11.glTexCoord3f(1, 1, 0);
    	GL11.glVertex3f(x + 256, y + 256, z);
    	GL11.glTexCoord3f(0, 1, 0);
    	GL11.glVertex3f(x, y + 256, z);
    	GL11.glEnd();
	}

}
