package com.fourTen;

public class Sprite {
	SpriteSheet sheet;
	public int width;
	public int height;
	public int xCoordinate,yCoordinate;
	public int[]pixels;
	public int col;
	
	
	
	public Sprite(int xCoordinate, int yCoordinate,int width,int height, SpriteSheet sheet) {
		this.width=width;
		this.height=height;
		this.sheet=sheet;
		this.xCoordinate=xCoordinate*width;
		this.yCoordinate=yCoordinate*height;
		pixels= new int[width*height];
		load();
	
	}
	public Sprite(int xCoordinate, int yCoordinate,int width,int height, SpriteSheet sheet,int shiftDown, int shiftRight) {
		this.width=width;
		this.height=height;
		this.sheet=sheet;
		this.xCoordinate=(xCoordinate*width)+shiftRight;
		this.yCoordinate=(yCoordinate*height)+shiftDown;
		pixels= new int[width*height];
		load();
	
	}
	public Sprite(int size, int col) {
		this.width=size;
		this.height=size;
		this.col=0x0000ff;
		pixels= new int[width*height];
		loadvoid();
	}
	/*public int[] rotate(int[] pixels, double angle, int width, int height) {
		int[]results= new int[width*height];
		int x0= -width/2;
		int y0= -height/2;
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				results[rotX(x,y,angle)+rotY(x,y,angle)*width]=pixels[x+y*width];
			}
		}
		
	}*/
	public double rotX(int x, int y, double angle) {
		double cos=Math.cos(angle);
		double sin = Math.sin(angle);
		return x*cos +y*-sin;
	}
	public double rotY(int x, int y, double angle) {
		double cos=Math.cos(angle);
		double sin = Math.sin(angle);
		return y*sin +x*cos;
	}
	
	public void loadvoid() {
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				pixels[x+y*width]=col;
			}
			
		}
		
	}
	
	public void load() {
		for(int y=0;y<height;y++) {
			for (int x=0;x<width;x++){
				pixels[x+y*width]
						=sheet.pixels[(xCoordinate+x)+(yCoordinate+y)*sheet.width];
			}
		}
		
	}
	public static Sprite grass= new Sprite(11,15,16,16,SpriteSheet.baseSheet);
	public static Sprite lightGrass= new Sprite(10,15,16,16,SpriteSheet.baseSheet);
	public static Sprite sandOne= new Sprite(11,16,16,16,SpriteSheet.baseSheet);
	public static Sprite sandTwo= new Sprite(10,16,16,16,SpriteSheet.baseSheet);
	public static Sprite sandThree= new Sprite(9,16,16,16,SpriteSheet.baseSheet);
	public static Sprite homeone= new Sprite(6,1,16,16,SpriteSheet.baseSheet);
	public static Sprite voidsprite= new Sprite(16,0xFF0000ff);
	public static Sprite stonepillar = new Sprite(4,10,16,16,SpriteSheet.baseSheet);
	//player sprites
		//face downward
	public static Sprite playerDown= new Sprite(13,0,16,16,SpriteSheet.baseSheet);
	public static Sprite playerDownWalkOne = new Sprite(14,0,16,16,SpriteSheet.baseSheet);
	public static Sprite playerDownWalkTwo = new Sprite(16,0,16,16,SpriteSheet.baseSheet);
		//face upward
	public static Sprite playerUp = new Sprite(13,1,16,16,SpriteSheet.baseSheet);
	public static Sprite playerUpWalkOne = new Sprite(14,1,16,16,SpriteSheet.baseSheet);
	public static Sprite playerUpWalkTwo = new Sprite(16,1,16,16,SpriteSheet.baseSheet);
		//face left
	public static Sprite playerLeft = new Sprite(13,3,16,16,SpriteSheet.baseSheet);
	public static Sprite playerLeftWalkOne = new Sprite(14,3,16,16,SpriteSheet.baseSheet);
	public static Sprite playerLeftWalkTwo = new Sprite(16,3,16,16,SpriteSheet.baseSheet);
		//face right
	public static Sprite playerRight = new Sprite(13,2,16,16,SpriteSheet.baseSheet);
	public static Sprite playerRightWalkOne = new Sprite(14,2,16,16,SpriteSheet.baseSheet);
	public static Sprite playerRightWalkTwo = new Sprite(16,2,16,16,SpriteSheet.baseSheet);
	//attack
	//down attack
	public static Sprite playerDownAttackOne = new Sprite(12,4,16,16,SpriteSheet.baseSheet);
	public static Sprite playerDownAttackTwo = new Sprite(13,4,16,16,SpriteSheet.baseSheet);
	public static Sprite playerDownAttackThree = new Sprite(14,4,16,16,SpriteSheet.baseSheet);
	public static Sprite playerDownAttackFour = new Sprite(15,4,16,16,SpriteSheet.baseSheet);
	//up attack
	public static Sprite playerUpAttackOne = new Sprite(12,5,16,16,SpriteSheet.baseSheet);
	public static Sprite playerUpAttackTwo = new Sprite(13,5,16,16,SpriteSheet.baseSheet);
	public static Sprite playerUpAttackThree = new Sprite(14,5,16,16,SpriteSheet.baseSheet);
	public static Sprite playerUpAttackFour = new Sprite(15,5,16,16,SpriteSheet.baseSheet);
	//right attack
	public static Sprite playerRightAttackOne = new Sprite(12,6,16,16,SpriteSheet.baseSheet);
	public static Sprite playerRightAttackTwo = new Sprite(13,6,16,16,SpriteSheet.baseSheet);
	public static Sprite playerRightAttackThree = new Sprite(14,6,16,16,SpriteSheet.baseSheet);
	public static Sprite playerRightAttackFour = new Sprite(15,6,16,16,SpriteSheet.baseSheet);

	//left attck
	public static Sprite playerLeftAttackOne = new Sprite(12,7,16,16,SpriteSheet.baseSheet);
	public static Sprite playerLeftAttackTwo = new Sprite(13,7,16,16,SpriteSheet.baseSheet);
	public static Sprite playerLeftAttackThree = new Sprite(14,7,16,16,SpriteSheet.baseSheet);
	public static Sprite playerLeftAttackFour = new Sprite(15,7,16,16,SpriteSheet.baseSheet);
	//Ogre
	//down
	public static Sprite ogreDown = new Sprite(49,0,16,16,SpriteSheet.newSheet);
	public static Sprite ogreDownWalkOne = new Sprite(50,0,16,16,SpriteSheet.newSheet,1,0);
	public static Sprite ogreDownWalkTwo = new Sprite(52,0,16,16,SpriteSheet.newSheet,1,0);
	//up
	public static Sprite ogreUp = new Sprite(49,1,16,16,SpriteSheet.newSheet);
	public static Sprite ogreUpWalkOne = new Sprite(50,1,16,16,SpriteSheet.newSheet,1,0);
	public static Sprite ogreUpWalkTwo = new Sprite(52,1,16,16,SpriteSheet.newSheet,1,0);
	//right
	public static Sprite ogreRight = new Sprite(49,2,16,16,SpriteSheet.newSheet);
	public static Sprite ogreRightWalkOne = new Sprite(50,2,16,16,SpriteSheet.newSheet,1,0);
	public static Sprite ogreRightWalkTwo = new Sprite(52,2,16,16,SpriteSheet.newSheet,1,0);
	//left
	public static Sprite ogreLeft = new Sprite(49,3,16,16,SpriteSheet.newSheet);
	public static Sprite ogreLeftWalkOne = new Sprite(50,3,16,16,SpriteSheet.newSheet);
	public static Sprite ogreLeftWalkTwo = new Sprite(52,3,16,16,SpriteSheet.newSheet);
	//ogre downattck
	public static Sprite ogreDownAttackOne = new Sprite(48,4,16,16,SpriteSheet.newSheet);
	public static Sprite ogreDownAttackTwo = new Sprite(49,4,16,16,SpriteSheet.newSheet);
	public static Sprite ogreDownAttackThree = new Sprite(50,4,16,16,SpriteSheet.newSheet);
	public static Sprite ogreDownAttackFour = new Sprite(51,4,16,16,SpriteSheet.newSheet);
	//oge upattck
	public static Sprite ogreUpAttackOne = new Sprite(48,5,16,16,SpriteSheet.newSheet);
	public static Sprite ogreUpAttackTwo = new Sprite(49,5,16,16,SpriteSheet.newSheet);
	public static Sprite ogreUpAttackThree = new Sprite(50,5,16,16,SpriteSheet.newSheet);
	public static Sprite ogreUpAttackFour = new Sprite(51,5,16,16,SpriteSheet.newSheet);
	//right attck
	public static Sprite ogreRightAttackOne = new Sprite(48,6,16,16,SpriteSheet.newSheet);
	public static Sprite ogreRightAttackTwo = new Sprite(49,6,16,16,SpriteSheet.newSheet);
	public static Sprite ogreRightAttackThree = new Sprite(50,6,16,16,SpriteSheet.newSheet);
	public static Sprite ogreRightAttackFour = new Sprite(51,6,16,16,SpriteSheet.newSheet);
	//left attck
	public static Sprite ogreLeftAttackOne = new Sprite(48,7,16,16,SpriteSheet.newSheet);
	public static Sprite ogreLeftAttackTwo = new Sprite(49,7,16,16,SpriteSheet.newSheet);
	public static Sprite ogreLeftAttackThree = new Sprite(50,7,16,16,SpriteSheet.newSheet);
	public static Sprite ogreLeftAttackFour = new Sprite(51,7,16,16,SpriteSheet.newSheet);
}
