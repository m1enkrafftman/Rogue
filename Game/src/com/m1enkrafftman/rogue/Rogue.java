package com.m1enkrafftman.rogue;

public class Rogue {
	
	public static GameLoop instance = new GameLoop();
	public static final String loggingPrefix = "Rogue | ";
	
	public static void main(String[] args) {
		if(instance == null) {
			instance = new GameLoop();
		}
	}

}
