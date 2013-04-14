package com.m1enkrafftman.rogue.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.m1enkrafftman.rogue.backend.DisplayFactory;

public abstract class GuiScreen extends Gui {
	
	private ArrayList<GuiButton> buttons;
	
	public GuiScreen() {
		buttons = new ArrayList<GuiButton>();
	}
	
	public ArrayList<GuiButton> getButtons() {
		return this.buttons;
	}
	
	public abstract void preRender();
	
	public abstract void render();
	
	public abstract void postRender();
	
	public abstract void onMouseClick(int key);
	
	public abstract void onKeyPress(int key);
	
	public abstract void handleLogic();
	
	public void handleMouse() {
		while (Mouse.next()) {
			if(!Mouse.getEventButtonState()) continue;
			this.onMouseClick(Mouse.getEventButton());
		}
	}

	public void handleKey() {
		while (Keyboard.next()) {
			if (!Keyboard.getEventKeyState()) continue;
			this.onKeyPress(Keyboard.getEventKey());
		}
	}

}
