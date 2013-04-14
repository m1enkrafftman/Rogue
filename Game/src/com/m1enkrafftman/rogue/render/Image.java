package com.m1enkrafftman.rogue.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.m1enkrafftman.rogue.Rogue;

public class Image {
	
	private Texture tex;
	private String name;
	
	public Image(String name) {
		this.tex = this.loadImage(name);
		this.name = name;
	}
	
	private Texture loadImage(String type){
    	try {
    		System.out.printf("%sTexture loaded: %s\n", Rogue.loggingPrefix, type);
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+ type +".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public Texture getTexture() {
		return this.tex;
	}
	
	public String getName() {
		return this.name;
	}

}
