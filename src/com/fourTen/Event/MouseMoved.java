package com.fourTen.Event;

public class MouseMoved extends Action {


	int x,y;
	boolean dragged;

	public MouseMoved(int x,int y, boolean dragged) {
		super(Action.Type.MOUSEPRESSED);
		this.x=x;
		this.y=y;
		this.dragged=dragged;
		
	}

}
