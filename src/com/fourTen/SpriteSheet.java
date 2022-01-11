package com.fourTen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
int[]pixels;
int width,height;
String path;
BufferedImage image;

public SpriteSheet(int width, int height, String path) {
	this.width=width;
	this.height=height;
	this.path=path;
	pixels=new int[width*height];
	load();
}
public void load() {
	try {
		image=ImageIO.read(SpriteSheet.class.getResource(path));
		width=image.getWidth();
		height=image.getHeight();
		pixels=new int[width*height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.print("SpriteSheet Error you Ape...");
	}
	
}

public static SpriteSheet baseSheet= new SpriteSheet(512,288,"/AllAssetsPink.png");
public static SpriteSheet newSheet= new SpriteSheet(864,304,"/NewAssetsPink.png");
}
