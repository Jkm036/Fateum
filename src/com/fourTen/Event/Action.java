package com.fourTen.Event;

public class Action {
	public static enum Type{
			MOUSEPRESSED,
			MOUSERELEASED,
			MOUSEMOVED,
			UP,
			LEFT,
			DOWN,
			RIGHT
			
	}
	public Type type;
	public Action(Type type) {
		this.type=type;
	}
	

}
