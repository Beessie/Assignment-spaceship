package com.spaceship;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("SpaceShip");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		GamePanel gp = new GamePanel();//gui ของเกมส์ เช่น สีพื้นหลัง
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
}
