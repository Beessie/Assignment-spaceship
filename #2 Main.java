package com.spaceship;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("SpaceShip");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(180, 550, 30, 30); //สร้างผู้เล่น ส่วนที่เขียนเพิ่มจากไฟล์ #1 Main
		GamePanel gp = new GamePanel();//gui ของเกมส์ทั้งหมด เช่น สีพื้นหลัง สีอักษรแสดงคะแนน
		GameEngine engine = new GameEngine(gp, v); //ใส่รายละเอียดของเกมส์ทั้งหมด ส่วนที่เขียนเพิ่มจากไฟล์ #1 Main
		frame.addKeyListener(engine); //ส่วนที่เขียนเพิ่มจากไฟล์ #1 Main
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();//ส่วนที่เขียนเพิ่มจากไฟล์ #1 Main
	}
}
