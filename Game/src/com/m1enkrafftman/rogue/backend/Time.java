package com.m1enkrafftman.rogue.backend;

import org.lwjgl.Sys;

public enum Time {
	
	MINUTES			(60000000000L),
	SECONDS			(1000000000L),
	MILLISECONDS	(1000000L),
	NANOSECONDS		(1L),
	HRMILLISECONDS	(1L);
	
	private long resolution;
	
	private Time(long resolution) {
		this.resolution = resolution;
	}
	
	public long getCurrent() {
		if (this != HRMILLISECONDS) {
			return System.nanoTime() / resolution;	
		} else {
			return Sys.getTime() * 1000L / Sys.getTimerResolution();
		}
	}
}

