package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import com.m1enkrafftman.rogue.misc.Point;
import com.m1enkrafftman.rogue.render.MiscRender;

public class EntityLiving extends Entity {
	
	private int speed = 5;
	private int attackTick;
	private boolean dead;
	
	public EntityLiving(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.dead = false;
		this.attackTick = 100;
	}
	
	public void setAttackInt(int i) {
		this.attackTick = i;
	}
	
	public int getAttackInt() {
		return this.attackTick;
	}
	
	public void incrementAttackTick() {
		this.setAttackInt(this.getAttackInt() + 1);
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int i) {
		this.speed = i;
	}
	
	public void onLivingUpdate() {
		
	}
	
	public void renderLiving() {
		MiscRender.renderBasicCube(this.getMinX(), this.getMinY(), this.getWidth(), this.getColor());
	}
	
	public ArrayList<Point> getPoints() {
		ArrayList<Point> a = new ArrayList<>();
		a.add(new Point(this.getMinX(), this.getMinY()));
		a.add(new Point(this.getMinX() + this.getWidth(), this.getMinY()));
		a.add(new Point(this.getMinX() + this.getWidth(), this.getMinY() + this.getWidth()));
		a.add(new Point(this.getMinX(), this.getMinY() + this.getWidth()));
		return a;
	}

	public boolean getIsLiving() {
		return !this.dead;
	}
	
	public void setLifeStatus(boolean b) {
		this.dead = b;
	}
	
}
