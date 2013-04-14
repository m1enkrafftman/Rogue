package com.m1enkrafftman.rogue.misc;

public class Point {
	
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean existsIn(Location c, int i) {
		if(this.x > c.getX() && this.x < c.getX() + i &&
				this.y > c.getY() && this.equals(y < c.getY() + i))
			return true;
		/**
		System.out.println("==========[ Top ]==============");
		System.out.println("" + c.getX() + ", " +c.getY());
		System.out.println("" + this.x + ", " +this.y);
		System.out.println("==========[Bottom]=============");
		*/
		return false;
	}

}
