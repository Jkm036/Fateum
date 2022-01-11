package com.fourTen.Mob;

import java.util.Random;

import com.fourTen.Level;
import com.fourTen.Ogre;

public class RandomEnemySpawner {
int xRange;
int yRange;
int xStart;
int yStart;
int x=0;
int y=0;
 Random randomX;
 Random randomY;
 int numberOfEnemies;
 Level level;
 public RandomEnemySpawner(int xRange,int yRange,int xStart,int yStart, int numberOfEnemies) {
	 randomX= new Random();
	 randomY= new Random();
	  this.xRange= xRange;
	  this.yRange= yRange;
	  this.xStart=xStart;
	  this.yStart= yStart;
	 this.numberOfEnemies=numberOfEnemies;
 }
 public void init(Level level) {
	 this.level=level;
 }
 
 public void spawn(String  m) {
	 for(int i=0;i<numberOfEnemies;i++) {
		  x= randomX.nextInt(xRange)+xStart;
		  y=randomY.nextInt(yRange)+yStart;
		 if(level.getBuilding(x, y).solid) {
			i--;
			continue;
		 }else if(!(level.getBuilding(x,y)).solid) {
			 if (m.equals("Ogre")) {
				 level.addMob(new Ogre(x*16, y*16));
			 }else {
			 level.addMob(new Enemy(x*16, y*16));
			 }
		 }
	 }
 }
}
