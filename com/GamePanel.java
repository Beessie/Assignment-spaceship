package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK); //สีพื้นหลังของเกมส์
	}
    //ส่วนที่เขียนเพิ่มจาก commit #01 GamePanel
	public void updateGameUI(GameReporter reporter){ //แสดงคะแนนบนหน้าจอ
		big.clearRect(0, 0, 400, 600);
		
        if(reporter.getHp() >0){
            big.setColor(Color.WHITE);	 //คะแนน
            big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
            big.setColor(Color.YELLOW);	//จำนวนชีิวิตปัจุบัน
            big.drawString(String.format("HP : %d", reporter.getHp()), 10, 20);
        }
        
        else{
            big.setColor(Color.RED); //แสดงข้อความ Game Over เมื่อเกมส์จบ
            big.drawString(String.format("GAME OVER", reporter.getHP()), 10, 20);
        }
		
        for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
