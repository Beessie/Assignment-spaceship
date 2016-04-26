package com;


//ไว้ดึงข้อมูล gethp , getscore โดยคลาส reporter เป็นตัวกลางในการนำข้อมูลจาก gameengine มาแสดงใน gamepanel
public interface GameReporter {

	long getScore();
	long getHp();

}
