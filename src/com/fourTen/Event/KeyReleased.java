package com.fourTen.Event;

public class KeyReleased extends Action {

	int keyCode;

	public KeyReleased(int keyCode) {
			super(calcType(keyCode));
			this.keyCode=keyCode;
			
		
		// TODO Auto-generated constructor stub
	}
	public static Type calcType(int keyCode) {
		if(keyCode==0x57) {
			return Action.Type.UP;
		}else if (keyCode==0x41) {
			return Action.Type.LEFT;
		}else if(keyCode==0x53) {
			return Action.Type.DOWN;
		}else if(keyCode==0x44) {
			return Action.Type.RIGHT;
		}
		return null;	
	}

	public int getKeyCode() {
		return keyCode;
	}

}
