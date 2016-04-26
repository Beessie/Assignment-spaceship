package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private long hp;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){ //เรียกคลาสศัสตรู
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateBullet(){ //เรียกคลาสกระสุน
		Bullet b = new Bullet(v.x, v.y);
		gp.sprites.add(b);
		bullets.add(b);
	}
	//เงื่อนไขของเกมส์
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
         //ถ้าผู้เล่นยิงโดนศัตรู ศัตรูก็จะตาย
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Bullet> b_iter = bullets.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}
		
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}
		}
		//ถ้าผู้เล่นโดนนตัวศัตรู ผู้เล่นชีวิตก็จะลดลง
		hp = v.getHp();
		gp.updateGameUI(this);//ให้ไปอัพเดทเพื่อแสดงจำนวนชีวิตปัจจุบันที่เหลืออยู่ในคลาส GamePanel
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				v.decreaseHp();
				if( !v.isAlive() ) {
					hp = v.getHp();
					gp.updateGameUI(this);
					die();
				}
				e.die();
				return;
			}
		}
		for(Bullet b : bullets){ // ถ้าลูกกระสุนไปโดนศัตรูคะแนนก็จะเพิ่มครั้งละ 50คะแนน
			for(Enemy e : enemies){
			er = e.getRectangle(); 	
			br = b.getRectangle();
			if(br.intersects(er)){
				e.die();
				b.die();				
				score += 50;
				return;
			}
			}
		}
		
		
	}
	
	public void die(){ //เมื่อผู้เล่นตายครบ 3ครั้งตามเงื่อนไขในคลาสของผู้เล่นเวลาก็จะหยุดลง
		timer.stop();
        
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			generateBullet();
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	public long getHp(){
		return hp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
