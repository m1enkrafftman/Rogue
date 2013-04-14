package com.m1enkrafftman.rogue.entity;

import java.util.ArrayList;

import com.m1enkrafftman.rogue.misc.Point;
import com.m1enkrafftman.rogue.render.MiscRender;

public class EntityLiving extends Entity {
	
	public EntityLiving(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
	}
	
	public void onLivingUpdate(int deltaX, int deltaY, int deltaHealth) {
		
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

}
