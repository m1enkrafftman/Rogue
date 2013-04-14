package com.m1enkrafftman.rogue.external.level;

import java.io.IOException;
import java.util.ArrayList;

import com.m1enkrafftman.rogue.external.BackTile;
import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.IOFileReader;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.external.WorldStore;
import com.m1enkrafftman.rogue.misc.Location;

public class Level {
	
	private String level;
	private int num;
	
	public Level(int lvl) {
		this.level = "level" + lvl;
		this.num = lvl;
	}
	
	public String getLevelName() {
		return this.level;
	}
	
	public int getLevelNumber() {
		return this.num;
	}
	
	public ArrayList<WorldStore> getWorldFromLevel() {
		ArrayList<String> array = null;
		try {
			array = IOFileReader.readFile(this.level);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		int y = 0;
		ArrayList<WorldStore> storage = new ArrayList<>();
		try {
			for(String s : array) {
				for(int x1 = 0; x1 < 25; x1++) {
					char c = s.charAt(x1);
					if(c == '=') {
						storage.add(new WorldStore(new Location(x1*32, y*32), new BackTile(x1*32, y*32, 32, 0xffffffff)));
					}else if(c == '#') {
						storage.add(new WorldStore(new Location(x1*32, y*32), new Collideable(x1*32, y*32, 32, 0xffffffff)));
					}
				}
				y+=1;
			}
		} catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return storage;
	}

}
