package com;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

//abstract class ไว้กำหนดค่าเบื้องต้น เช่น ความสูง ความกว้าง ตำแหน่ง x,y  โดยให้ object ที่เป็นวัตถุในเกมใช้ extend
//เช่น spaceship enemy bullet จะเรียกใช้คลาสที่ในการกำหนดความสูง ความกว้าง ทิศทาง
public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void draw(Graphics2D g);
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
