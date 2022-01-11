package com.fourTen.UIManager;

import java.awt.Color;
import java.awt.Graphics;

import com.fourTen.Level;
import com.fourTen.game;
import com.fourTen.Mob.Mob;
import com.fourTen.Vector.Vector;

public class UIHealthBar extends UIComponent {
	Vector size;
	int x;
	int y;
	Mob m;
	Level level;
	Color damageCol= new Color(0xff0000);
	Color healthCol= new Color(0x00ff00);
	int xp;
	int yp;
	
double progress=100;//0.0- 1.0
		public UIHealthBar( Vector size,Mob m) {
			super((int)m.getX(),(int)m.getY());
			this.size=size;
			this.m=m;
			this.level=m.getLevel();
		}
		public void setProgress(double progress) {
			this.progress=progress;
		}
		
		public void lowerProgress(double progress) {
			this.progress-=progress;
		}
		public void die() {
			level.uimanager.components.remove(this);
		}
		public void render(Graphics g) {
			if(m.healthVisible) {
				g.setColor(healthCol);
				xp=game.scale*(((int)m.getX())-8);
				yp=game.scale*(((int)m.getY())-8);
				xp-=game.xScroll*game.scale;
				yp-=game.yScroll*game.scale;
				g.fillRect(xp,yp, (int)(size.getX()-m.percentHealthLoss),size.getY());
				g.setColor(damageCol);
				if(((int)(size.getX()-m.percentHealthLoss))>0) {
				g.fillRect(xp+((int)(size.getX()-m.percentHealthLoss)), yp, ((int)(m.percentHealthLoss)), size.getY());
				}else {
					g.fillRect(xp,yp,size.getX(),size.getY());
				}
			}
			
		}
		
}
