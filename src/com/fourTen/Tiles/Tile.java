package com.fourTen.Tiles;

import com.fourTen.Sprite;
import com.fourTen.SpriteSheet;

public class  Tile {
	public int x,y;
	public boolean solid;
	public Sprite sprite;
	public Tile(Sprite sprite) {
		this.sprite=sprite;
	}
	public void render() {
		
	}
public static Tile grass= new Grass(Sprite.grass);
public static Tile lightGrass= new LightGrass(Sprite.lightGrass);
public static Tile sandOne= new SandOne(Sprite.sandOne);
public static Tile homeOne=new HomeOne(Sprite.homeone);
public static Tile voidTile= new Void(Sprite.voidsprite);
public static Tile stonepillar= new StonePillar(Sprite.stonepillar);

public static final int colGrass=0xff00ff00;
public static final int colhomeOne=0xffff0000;
public static final int colVoid=0Xff0000ff;
public static final int collightGrass=0xffc3d657;
public static final int colsandOne=0xffe7d593;
public static final int colstonepillar=0xff908e99;
}
