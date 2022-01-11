package com.fourTen;

import java.util.ArrayList;
import java.util.List;

import com.fourTen.Event.Action;
import com.fourTen.Event.Layer;
import com.fourTen.Mob.Mob;
import com.fourTen.Tiles.Tile;
import com.fourTen.UIManager.UIManager;

public class Level extends Layer {
	protected int[] backgroundMap;
	protected int[] foregroundMap;
	protected int[] buildings;
	
	protected int mapwidth;
	protected int mapheight;
	String backgroundPath;
	String foregroundPath;
	List<Mob> mobs= new ArrayList<Mob>();
	List<Entity>entities= new ArrayList<Entity>();
	public List<Mob> players = new ArrayList<Mob>();
	public Screen screen;
	public UIManager uimanager = new UIManager();
	//Level spawnlvl=new SpawnLevel("/map");
	
	//public int xScroll;
	//public int yScroll;
	
	
public void tick() {
	for(int i=0;i<entities.size();i++) {
		entities.get(i).tick();
	}
}

public void init() {
	/*for(int i=0;i<mobs.size();i++) {
		mobs.get(i).init(this);
	}*/
	for(int i=0;i<entities.size();i++) {
		entities.get(i).init(this);
	}
}
public List<Mob> getMobs(){
	return mobs;
}

public List<Entity> getEntities(){
	return entities;
}

public Mob getMob(int i){
	return  mobs.get(i);
}

public void addPlayer(Mob m) {
	m.setLevel(this);
	mobs.add(m);
	entities.add(m);
	players.add(m);
}
public void addMob(Mob m) {
	m.setLevel(this);
	mobs.add(m);
	entities.add(m);
}

public void addEntity(Entity e) {
	entities.add(e);
}

public void render(Screen screen, int xScroll, int yScroll) {
	//if xScroll=16, it takes tile 1 and renders it at 16 on the screen. Thats why we xp-xoffset so that it renders at 0
	screen.setOffset(xScroll, yScroll);
	int x0=xScroll>>4;
	int x1=(xScroll+screen.width)+16>>4;
	int y0=yScroll>>4;
	int y1=(yScroll+screen.height)+16>>4;
	//render background map
	for(int y=y0;y<y1;y++) {
		for(int x=x0;x<x1;x++) {
			screen.renderSprite(x*16,y*16 , (getTile(x,y).sprite.width), (getTile(x,y).sprite.height), getTile(x,y).sprite);
		}
	}
	for(int y=y0;y<y1;y++) {
		for(int x=x0;x<x1;x++) {
			screen.renderSprite(x*16, y*16, getBuilding(x,y).sprite.width, getBuilding(x,y).sprite.height, getBuilding(x,y).sprite);
		}
	}
	//render buildings and other stable entities
	
	for(int i=0;i<entities.size();i++) {
		entities.get(i).render(screen);
	}
	
}
//tile precision
public Tile getTile(int x,int y) {
	if(x<0||x>=mapwidth||y<0||y>=mapheight) return Tile.voidTile;
if(backgroundMap[x+y*mapwidth]== Tile.colGrass) {
	return Tile.grass;
}else 
if(backgroundMap[x+y*mapwidth]== Tile.colhomeOne) {
	return Tile.homeOne;
}else
if(backgroundMap[x+y*mapwidth]== Tile.collightGrass) {
		return Tile.lightGrass;
	}else
if(backgroundMap[x+y*mapwidth]== Tile.colsandOne) {
			return Tile.sandOne;
		}else
return Tile.voidTile;
}

public Tile getBuilding(int x, int y) {
	if(x<0||x>=mapwidth||y<0||y>=mapheight) return getTile(x,y);
	if(foregroundMap[x+y*mapwidth]==Tile.colhomeOne) {
		return Tile.homeOne;
	}else if(foregroundMap[x+y*mapwidth]==Tile.colstonepillar){
		return Tile.stonepillar;
	}else
		return getTile(x,y);
	}

public void listenToEvent(Action action){
	for(int i=0;i<mobs.size();i++) {
		mobs.get(i).listenToEvent(action);
	}
	
}


}
