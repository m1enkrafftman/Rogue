package com.m1enkrafftman.rogue.entity;

public class Step {

	private boolean left;
	
	public Step(boolean s) {
		this.left = s;
	}
	
	public boolean isLeft() {
		return this.left;
	}
	
	public void updateStep() {
		this.left = !this.left;
	}
	
}
