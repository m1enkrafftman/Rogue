package com.m1enkrafftman.rogue.entity.extensions;

import com.m1enkrafftman.rogue.entity.EntityLiving;

public class Action {
	
	private int x, y;
	private EntityLiving en;
	private float rot;
	private boolean att;
	private int tick;
	
	public Action(EntityLiving e, int delX, int delY, float rot, boolean attack, int tick) {
		this.en = e;
		this.x = delX; this.y = delY;
		this.rot = rot;
		this.att = attack;
		this.tick = tick;
	}
	
	public boolean shouldAttack() {
		return this.att;
	}
	
	public int getDeltaX() {
		return this.x;
	}
	
	public int getDeltaY() {
		return this.y;
	}
	
	public float getRotation() {
		return this.rot;
	}
	
	public EntityLiving getEntity() {
		return this.en;
	}
	
	public int getWorldTick() {
		return this.tick;
	}

}
