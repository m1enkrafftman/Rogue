package com.m1enkrafftman.rogue.external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.m1enkrafftman.rogue.entity.EntityWall;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.Point;

public class World {
	
	private int width, height;
	
	private ArrayList<WorldStore> storage;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.initWorld();
	}
	
	public World(ArrayList<WorldStore> storage) {
		this.storage = storage;
	}
	
	private void initWorld() {
		this.storage = new ArrayList<>();
		Random rand = new Random();
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				int i = rand.nextInt(100);
				if(i == 0) {
					this.storage.add(new WorldStore(new Location(x*32, y*32), new Collideable(x*32, y*32, 32, 0xffffffff)));
				}else {
					this.storage.add(new WorldStore(new Location(x*32, y*32), new BackTile(x*32, y*32, 32, 0xffffffff)));
				}
			}
		}
	}
	
	public void renderWorld() {
		for(WorldStore ws : this.storage) {
			ws.render();
		}
	}
	
	public boolean canMove(ArrayList<Point> points) {
		for(WorldStore w : this.storage) {
			if(w.canMove(points)) {
				return true;
			}
		}
		return false;
	}

}
