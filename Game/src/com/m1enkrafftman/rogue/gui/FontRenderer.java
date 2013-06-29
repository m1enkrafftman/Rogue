package com.m1enkrafftman.rogue.gui;

import org.lwjgl.opengl.GL11;

public class FontRenderer {
	
	public static int getStringWidth(String s) {
		return (s.length() * 8);
	}
	
	public static void drawCenteredString(String s, int x, int y, int color) {
		int negX = (int)(getStringWidth(s)/2);
		drawStringWithColor(s, x - negX, y, color);
	}
	
	public static void drawStringWithColor(String s, int x, int y, int objectColor) {
		GL11.glPushMatrix();
		float f  = (float)(objectColor >> 24 & 0xff) / 255F;
		float f1 = (float)(objectColor >> 16 & 0xff) / 255F;
		float f2 = (float)(objectColor >> 8  & 0xff) / 255F;
		float f3 = (float)(objectColor       & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		drawString(s, x, y);
		GL11.glPopMatrix();
	}

	public static void drawString(String s, int x, int y){
		int startX = x;
		GL11.glBegin(GL11.GL_POINTS);
		for(char c : s.toLowerCase().toCharArray()){
			if(c == 'a'){
				for(int i=0;i<8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'b'){
				for(int i=0;i<8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+7, y-5);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-6);
				
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				x+=8;
			}else if(c == 'c'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'd'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-3);
				GL11.glVertex2f(x+6, y-4);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'e'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y-0);
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'f'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'g'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-3);
				GL11.glVertex2f(x+5, y-3);
				GL11.glVertex2f(x+7, y-3);
				
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'h'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'i'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+3, y-i);
				}
				for(int i=1;i<=5;i++){
					GL11.glVertex2f(x+i, y-0);
					GL11.glVertex2f(x+i, y-8);
				}
				x+=7;
			}else if(c == 'j'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+6, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-0);
				}
				GL11.glVertex2f(x+1, y-3);
				GL11.glVertex2f(x+1, y-2);
				GL11.glVertex2f(x+1, y-1);
				x+=8;
			}else if(c == 'k'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				GL11.glVertex2f(x+6, y-8);
				GL11.glVertex2f(x+5, y-7);
				GL11.glVertex2f(x+4, y-6);
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+2, y-4);
				GL11.glVertex2f(x+2, y-3);
				GL11.glVertex2f(x+3, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 'l'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				x+=7;
			}else if(c == 'm'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+3, y-6);
				GL11.glVertex2f(x+2, y-7);
				GL11.glVertex2f(x+4, y-5);
				
				GL11.glVertex2f(x+5, y-6);
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+4, y-5);
				x+=8;
			}else if(c == 'n'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+2, y-7);
				GL11.glVertex2f(x+2, y-6);
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+4, y-4);
				GL11.glVertex2f(x+5, y-3);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-1);
				x+=8;
			}else if(c == 'o' || c == '0'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				x+=8;
			}else if(c == 'p'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				x+=8;
			}else if(c == 'q'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					if(i != 1) GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					if(i != 6) GL11.glVertex2f(x+i, y-0);
				}
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 'r'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 's'){
				for(int i=2;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-6);
				GL11.glVertex2f(x+1, y-5);
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y);
				}
				GL11.glVertex2f(x+7, y-3);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+1, y-1);
				GL11.glVertex2f(x+1, y-2);
				x+=8;
			}else if(c == 't'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				x+=7;
			}else if(c == 'u'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-0);
				}
				x+=8;
			}else if(c == 'v'){
				for(int i=2;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+6, y-i);
				}
				GL11.glVertex2f(x+2, y-1);
				GL11.glVertex2f(x+5, y-1);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+4, y);
				x+=7;
			}else if(c == 'w'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+2, y);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+5, y);
				GL11.glVertex2f(x+6, y);
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				x+=8;
			}else if(c == 'x'){
				for(int i=1;i<=7;i++)
					GL11.glVertex2f(x+i, y-i);
				for(int i=7;i>=1;i--)
					GL11.glVertex2f(x+i, y-8+i);
				x+=8;
			}else if(c == 'y'){
				GL11.glVertex2f(x+4, y);
				GL11.glVertex2f(x+4, y-1);
				GL11.glVertex2f(x+4, y-2);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+4, y-4);
				
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+2, y-6);
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-8);
				
				GL11.glVertex2f(x+5, y-5);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-8);
				x+=8;
			}else if(c == 'z'){
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-i);
				}
				GL11.glVertex2f(x+6, y-7);
				x += 8;
			}else if(c == '1'){
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				GL11.glVertex2f(x+3, y-7);
				x += 8;
			}else if(c == '2'){
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-6);
				
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+3, y-2);
				GL11.glVertex2f(x+2, y-1);
				x += 8;
			}else if(c == '3'){
				for(int i=1;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y);
				}
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+6, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x += 8;
			}else if(c == '4'){
				for(int i=2;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=7;i++){
					GL11.glVertex2f(x+i, y-1);
				}
				for(int i=0;i<=4;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				x+=8;
			}else if(c == '5'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=4;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				GL11.glVertex2f(x+1, y-1);
				GL11.glVertex2f(x+2, y);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+4, y);
				GL11.glVertex2f(x+5, y);
				GL11.glVertex2f(x+6, y);
				
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				
				GL11.glVertex2f(x+6, y-4);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-4);
				GL11.glVertex2f(x+3, y-4);
				GL11.glVertex2f(x+2, y-4);
				x += 8;
			}else if(c == '6'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				GL11.glVertex2f(x+6, y-4);
				x+=8;
			}else if(c == '7'){
				for(int i=0;i<=7;i++)
					GL11.glVertex2f(x+i, y-8);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-6);
				
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+3, y-2);
				GL11.glVertex2f(x+2, y-1);
				GL11.glVertex2f(x+1, y);
				x+=8;
			}else if(c == '8'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x += 8;
			}else if(c == '9'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=5;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+1, y-0);
				x += 8;
			}else if(c == '.'){
				GL11.glVertex2f(x+1, y);
				x+=2;
			}else if(c == ','){
				GL11.glVertex2f(x+1, y);
				GL11.glVertex2f(x+1, y-1);
				GL11.glVertex2f(x+1, y-2);
				x+=2;
			}else if(c == '\n'){
				y-=10;
				x = startX;
			}else if(c == ' '){
				x += 8;
			}else if (c == '-'){
				GL11.glVertex2f(x, y-4);
				GL11.glVertex2f(x+1, y-4);
				GL11.glVertex2f(x-1, y-4);
				GL11.glVertex2f(x-2, y-4);
				GL11.glVertex2f(x-3, y-4);
				GL11.glVertex2f(x-4, y-4);
				x+=6;
			}else if (c == '=') {
				GL11.glVertex2f(x, y-2);
				GL11.glVertex2f(x+1, y-2);
				GL11.glVertex2f(x-1, y-2);
				GL11.glVertex2f(x-2, y-2);
				GL11.glVertex2f(x-3, y-2);
				GL11.glVertex2f(x-4, y-2);
				GL11.glVertex2f(x, y-4);
				GL11.glVertex2f(x+1, y-4);
				GL11.glVertex2f(x-1, y-4);
				GL11.glVertex2f(x-2, y-4);
				GL11.glVertex2f(x-3, y-4);
				GL11.glVertex2f(x-4, y-4);
				x+=6;
			}
		}
		GL11.glEnd();
	}

}
