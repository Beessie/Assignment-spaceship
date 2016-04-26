package com;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.text.StyledEditorKit.BoldAction;

public class SpaceShip extends Sprite{

	int step = 20;
	private int hp = 3; //สามารถมีชีวิตได้ 3ครั้ง
	private Boolean alive = true;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
		
	}
    
    //เคลื่นที่ไปทางซ้าย-ขวา ตามขนาดความกว้างของเฟรมในคลาส Main
	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	
	public int getHp(){
		return hp;
	}
	
	public void decreaseHp(){	
		hp--;
		if(hp == 0) die(); //ถ้า hp = 0ก็ให้ผู้เล่นตาย
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void die(){
		alive = false;
	}

}
