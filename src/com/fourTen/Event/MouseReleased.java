package com.fourTen.Event;

public class MouseReleased extends Action{
int x,y;

	public MouseReleased(int x,int y) {
		super(Action.Type.MOUSERELEASED);
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}

}
