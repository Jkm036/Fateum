package com.fourTen.Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.fourTen.Event.ActionListener;
import com.fourTen.Event.MouseMoved;
import com.fourTen.Event.MousePressed;
import com.fourTen.Event.MouseReleased;

public class Mouse implements MouseListener, MouseMotionListener {
ActionListener listener;

public Mouse(ActionListener listener) {
	this.listener=listener;
}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseMoved moved = new MouseMoved(e.getX(),e.getY(),true);
		listener.listenToEvent(moved);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseMoved moved = new MouseMoved(e.getX(),e.getY(),false);
		listener.listenToEvent(moved);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		MousePressed pressed = new MousePressed(e.getX(),e.getY());
		listener.listenToEvent(pressed);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MouseReleased released = new MouseReleased(e.getX(),e.getY());
		listener.listenToEvent(released);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
