package com.m1enkrafftman.rogue.misc;

public class MathHelper {

	/**
	 * Returns the distance between objects
	 */
	public static double getDistance(int x1, int y1, int x2, int y2) {
		double xSquared = (x2 - x1) * (x2 - x1); 
		double ySquared = (y2 - y1) * (y2 - y1);
		return Math.sqrt(xSquared + ySquared);
	}

	/**
	 * Returns the distance between objects
	 */
	public static double getDistance(Location one, Location two) {
		int x1 = one.getX();
		int y1 = one.getY();
		int x2 = two.getX();
		int y2 = two.getY();
		double xSquared = (x2 - x1) * (x2 - x1); 
		double ySquared = (y2 - y1) * (y2 - y1);
		return Math.sqrt(xSquared + ySquared);
	}
	
	/**
     * Returns the greatest integer less than or equal to the double argument
     */
    public static int floor_double(double par0)
    {
        int var2 = (int)par0;
        return par0 < (double)var2 ? var2 - 1 : var2;
    }
    
    public static final float sqrt_double(double par0)
    {
        return (float)Math.sqrt(par0);
    }
	
}
