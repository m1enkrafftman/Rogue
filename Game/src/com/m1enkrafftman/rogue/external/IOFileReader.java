package com.m1enkrafftman.rogue.external;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOFileReader {
	
	public static ArrayList<String> readFile(String lvlname) throws NumberFormatException, IOException {
		File directory = new File("res/lvl/" + lvlname + ".txt");
		ArrayList<String> st = new ArrayList<>();
		if(directory.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(directory));
			for(String s = ""; (s = reader.readLine()) != null;) {
				st.add(s);
			}
			reader.close();
		}else {
			st.add("");
		}
		return st;
	}
}
