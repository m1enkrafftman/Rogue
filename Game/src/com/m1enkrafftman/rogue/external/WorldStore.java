package com.m1enkrafftman.rogue.external;

import java.util.ArrayList;

import com.m1enkrafftman.rogue.entity.EntityWall;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.Point;

public class WorldStore {

	private Location location;
	private int x, y;
	private EntityWall wall;
	
	public WorldStore(Location l, EntityWall w) {
		this.location = l;
		this.x = l.getX();
		this.y = l.getY();
		this.wall = w;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public EntityWall getWall() {
		return this.wall;
	}
	
	public boolean isCollideable() {
		return (this.getWall() instanceof Collideable);
	}
	
	public void render() {
		EntityWall wall = this.getWall();
		if(wall instanceof Collideable) {
			((Collideable)wall).renderCollide();
		}else if(wall instanceof BackTile) {
			((BackTile)wall).renderTile();
		}else {
			wall.renderWall();
		}
	}
	
	public boolean canMove(ArrayList<Point> points) {
		for(Point p : points) {
			System.out.println("Passing Location: " + this.x + ", " + this.y);
			if(p.existsIn(new Location(this.x, this.y), 16)) {
				return false;
			}
		}
		return true;
	}
	
}