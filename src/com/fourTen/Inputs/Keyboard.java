package com.fourTen.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.fourTen.Event.ActionListener;
import com.fourTen.Event.KeyPressed;
import com.fourTen.Event.KeyReleased;

public class Keyboard implements KeyListener{
	public boolean up,left,right,down;

	ActionListener listener;
	
	public Keyboard(ActionListener listener) {
		this.listener=listener;
	}
	@Override
	public void keyTyped(KeyEvent e) {


		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W)up=true;
		if(e.getKeyCode()==KeyEvent.VK_A)left=true;
		if(e.getKeyCode()==KeyEvent.VK_D)right=true;
		if(e.getKeyCode()==KeyEvent.VK_S)down=true;
		KeyPressed pressed= new KeyPressed(e.getKeyCode());
		listener.listenToEvent(pressed);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W)up=false;
		if(e.getKeyCode()==KeyEvent.VK_A)left=false;
		if(e.getKeyCode()==KeyEvent.VK_D)right=false;
		if(e.getKeyCode()==KeyEvent.VK_S)down=false;
		KeyReleased released= new KeyReleased(e.getKeyCode());
		listener.listenToEvent(released);
		
	}



}
