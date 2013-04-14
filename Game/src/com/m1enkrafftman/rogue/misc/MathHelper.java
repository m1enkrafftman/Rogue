package com.m1enkrafftman.rogue.misc;

public class MathHelper {

	public static double getDistance(int x1, int y1, int x2, int y2) {
	       double xSquared = (x2 - x1) * (x2 - x1); 
	       double ySquared = (y2 - y1) * (y2 - y1);
	       return Math.sqrt(xSquared + ySquared);
	    }
	
}
