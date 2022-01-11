package com.fourTen.Mob;

import java.awt.Rectangle;

import com.fourTen.Entity;
import com.fourTen.Level;
import com.fourTen.Screen;
import com.fourTen.Sprite;
import com.fourTen.Event.Action;
import com.fourTen.Event.ActionListener;
import com.fourTen.UIManager.UIHealthBar;
import com.fourTen.Vector.Vector;

public class Mob extends Entity implements ActionListener{
	public boolean walking;
	public int walkingCycle;
	public boolean attacking=false;
	public int attackingCycle=0;
	public int cardDir=0;
	protected boolean up,down,left,right;
	public Rectangle hitbox;
	protected Sprite sprite;
	protected int initialhealth=100;
	protected int health=100;
	protected int midx;
	protected int midy;
	protected int damage;
	protected boolean stunned=false;
	public int stunTimer;
	public int stunEffect;
	public double percentHealthLoss;
	public boolean healthVisible=false;
	protected Vector healthsize;
	UIHealthBar healthbar;
	protected double attackCoolDown=0;
	protected double attackSlowness;
	protected double speed;
	
	Mob( int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

		public int getMidX() {
	return midx;
	}
		public int getMidY() {
	return midy;
	}
		public void attack() {
			if(attackCoolDown==0) {
				attackCoolDown=attackSlowness*60;
			attacking=true;
			attackingCycle=0;
			if(attacking) {
				for(int i=0;i<getLevel().getMobs().size();i++) {
					if(getLevel().getMobs().get(i).equals(this))continue;   
					if(mobInRange(getLevel().getMobs().get(i), 12)) {
						getLevel().getMobs().get(i).hurt(this.damage,stunEffect);
						break;
					}
				}
			}
			}
			
		}
		public void die() {
			
		}
		
		public boolean equals(Object m) {
			if(!(m instanceof Mob))return false;
			if(m.hashCode()==this.hashCode()){
				return true;
			}
			return false;
		}
	public void hurt(int damage,int stun) {
			health-=damage;
			this.percentHealthLoss+= (damage*100/initialhealth);
			stun(stun);
			this.healthVisible=true;
		}
		public void stun(int stunTimer) {
			stunned=true;
			sprite=Sprite.playerDown;
			this.stunTimer=stunTimer;
		}
		public void unstun() {
			stunned=false;
		}

	public boolean collideRightLeft() {
		
		if(left) {
			if(getLevel().getBuilding((hitbox.x-1)/16,(hitbox.y)/16).solid==true||getLevel().getBuilding((hitbox.x-1)/16,(hitbox.y+hitbox.height)/16).solid==true) {
				return true;
			}
		}
		if(right) {
			if(getLevel().getBuilding((hitbox.x+hitbox.width+1)/16,(hitbox.y)/16).solid==true||getLevel().getBuilding((hitbox.x+hitbox.width+1)/16,(hitbox.y+hitbox.height)/16).solid==true) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collideUpDown() {
		if(up) {
			if(getLevel().getBuilding((hitbox.x)/16,(hitbox.y-1)/16).solid==true||getLevel().getBuilding((hitbox.x+hitbox.width)/16,(hitbox.y-1)/16).solid==true) {
				return true;
			}
		}
		if(down) {
			if(getLevel().getBuilding((hitbox.x)/16,(hitbox.y+hitbox.height+1)/16).solid==true||getLevel().getBuilding((hitbox.x+hitbox.width)/16,(hitbox.y+hitbox.height+1)/16).solid==true) {
				return true;
			}	
				}
		
		return false;
	}
	
	public boolean mobInRange(Mob m,int range) {
		double dist= Math.sqrt((Math.abs((m.getMidX())-this.midx)*Math.abs((m.getMidX())-this.midx))+(Math.abs(m.getMidY()-this.midy)*Math.abs(m.getMidY()-this.midy)));
		if(dist<=range) {
			return true;
		}else
		return false;
		
	}
	
	@Override
	public void listenToEvent(Action action) {
		// TODO Auto-generated method stub
		
	}

}
