package com.m1enkrafftman.rogue.entity;

import com.m1enkrafftman.rogue.render.MiscRender;

public class EntityWall extends Entity{
	
	public EntityWall(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
	}
	
	public void onUpdate(int deltaX, int deltaY, int deltaHealth) {
		
	}
	
	public void renderWall() {
		MiscRender.renderBasicCube(this.getMinX(), this.getMinY(), this.getWidth(), this.getColor());
	}
}
