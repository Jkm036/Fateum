package com.fourTen;

public class Entity {
protected double  x,y;
protected int time =0;
protected Level level;




public Entity(int x, int y) {
	this.x=x;
	this.y=y;
}
public void tick() {
	
}
public void render(Screen screen) {
	
}
public void init(Level level) {
	this.setLevel(level);
}
public double  getX() {
	return x;
}
public double  getY() {
	return y;
}
public Level getLevel() {
	return level;
}
public void setLevel(Level level) {
	this.level = level;
}
}
