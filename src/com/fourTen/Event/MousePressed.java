package com.fourTen.Event;

public class MousePressed extends Action{
int x,y;
	public MousePressed(int x, int y) {
		super(Action.Type.MOUSEPRESSED);
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}

}
