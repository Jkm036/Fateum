package com.fourTen;

public class Screen {
public int width,height;
public int[] pixels;
int col;
int xOffset,yOffset;
public Screen(int width, int height) {
	
	
	this.width=width;
	this.height=height;
	pixels=new int[width*height];
	
}
public void setOffset(int xOffset,int yOffset) {
	this.xOffset=xOffset;
	this.yOffset=yOffset;
	
}
public void renderSprite(int xp, int yp, int width, int height, Sprite sprite) {
	//counteracts the offset so that pixel first xp and yp =0;
	xp-=xOffset;
	yp-=yOffset;
	for(int y=0;y<height;y++) {
		for (int x=0;x<width;x++) {
			int xx= xp+x;
			int yy=yp+y;
			if(xx<0||xx>=this.width||yy<0||yy>=this.height)continue;
			int col=sprite.pixels[x+y*sprite.width];
			if(col==0xffff00ff)continue;
			pixels[xx+yy*this.width]=col;
		}
	}
}
public void renderEnemySprite(int xp, int yp, int width, int height, Sprite sprite) {
	xp-=xOffset;
	yp-=yOffset;
	for(int y=0;y<height;y++) {
		for (int x=0;x<width;x++) {
			int xx= xp+x;
			int yy=yp+y;
			if(xx<0||xx>=this.width||yy<0||yy>=this.height)continue;
			int col=sprite.pixels[x+y*sprite.width];
			if(col==0xffff00ff)continue;
			if(col==0xffb1b1b1) {
				col=0xffe76969;
			}
			pixels[xx+yy*this.width]=col;
		}
	}
}

public void renderStunnedSprite(int xp, int yp, int width, int height, Sprite sprite) {
	//counteracts the offset so that pixel first xp and yp =0;
	xp-=xOffset;
	yp-=yOffset;
	for(int y=0;y<height;y++) {
		for (int x=0;x<width;x++) {
			int xx= xp+x;
			int yy=yp+y;
			if(xx<0||xx>=this.width||yy<0||yy>=this.height)continue;
			int col=sprite.pixels[x+y*sprite.width];
			if(col==0xffff00ff)continue;
			
			
			col=col+0xffdd0000;
			pixels[xx+yy*this.width]=col;
		}
	}
}
}

