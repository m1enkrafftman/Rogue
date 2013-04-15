package com.m1enkrafftman.rogue.entity;

import com.m1enkrafftman.rogue.misc.Location;


public abstract class Entity {
	
	private int minX, minY, width, color;
	private double health;
	
	public Entity(int minX, int minY, int width, int color) {
		this.minX = minX;
		this.minY = minY;
		this.width = width;
		this.color = color;
		this.health = 20;
	}
	
	public Location getMid() {
		return (new Location(this.minX + (this.width/2), this.minY + (this.width/2)));
	}
	
	public void setHealth(double d) {
		this.health = d;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public int getMinX() {
		return this.minX;
	}
	
	public int getMinY() {
		return this.minY;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setLocation(Location l) {
		this.minX = l.getX();
		this.minY = l.getY();
	}

}