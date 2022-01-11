package com.fourTen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fourTen.Mob.RandomEnemySpawner;

public class SpawnLevel extends Level{
	BufferedImage backgroundImage;
	BufferedImage foregroundImage;
	RandomEnemySpawner spawner= new RandomEnemySpawner(1,1,1,8,1);
	
	public SpawnLevel(Screen screen, String backgroundPath, String foregroundPath) {
		this.backgroundPath=backgroundPath;
		this.foregroundPath=foregroundPath;
		load();
	}
	public void init() {
		spawner.init(this);
		spawner.spawn("Ogre");
		super.init();
	}
	public void load() {
		try {
			backgroundImage=ImageIO.read(SpawnLevel.class.getResource(backgroundPath));
			foregroundImage=ImageIO.read(SpawnLevel.class.getResource(foregroundPath));
			mapwidth=backgroundImage.getWidth();
			mapheight=backgroundImage.getHeight();
			backgroundMap= new int[mapwidth*mapheight];
			foregroundMap= new int[mapwidth*mapheight];
			backgroundImage.getRGB(0, 0, mapwidth, mapheight, backgroundMap, 0, mapwidth);
			foregroundImage.getRGB(0, 0, mapwidth, mapheight, foregroundMap, 0, mapwidth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Level map could not load");
		}
	}

}
