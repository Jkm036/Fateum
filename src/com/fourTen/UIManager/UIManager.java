package com.fourTen.UIManager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIManager {
 List<UIComponent> components= new ArrayList<UIComponent>();
 
 	public UIManager() {
	 
 	}
 	public void render(Graphics g) {
 		for(int i=0;i<components.size();i++) {
 			components.get(i).render(g);
 		}
 	}
 	public void addComponent(UIComponent c) {
 		components.add(c);
 	}
}
