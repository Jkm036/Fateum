package com.fourTen.Mob;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.fourTen.Level;
import com.fourTen.Screen;
import com.fourTen.Sprite;
import com.fourTen.UIManager.UIHealthBar;
import com.fourTen.Vector.Vector;

import AStar.Node;

public class Enemy extends Mob {
	Random random=new Random();
	List<Mob> playersInRange = new ArrayList<Mob>();
	List<Node> path = new ArrayList<Node>();
	public boolean chasing;
	

	public Enemy( int x, int y) {
		super( x, y);
		stunEffect=5;
		damage=4;
	 healthsize = new Vector(98,6);
	 attackSlowness=1;
	 speed=.7;
		

		 
}
	public void initHitBox() {
		this.hitbox= new Rectangle((((int)this.x+4-8)),(((int)this.y+2-8)),6,11);
		this.midx= hitbox.x+((hitbox.width)/2);
		this.midy=hitbox.y+((hitbox.height)/2);
	}
	public void init(Level level) {
		super.init(level);
		initHitBox();
		healthbar = new UIHealthBar(healthsize, this);
		level.uimanager.addComponent(healthbar);
	}
	public List<Mob> playerInRange(){
			double dist= Math.sqrt((Math.abs((level.players.get(0).getX())-this.x)*Math.abs((level.players.get(0).getX())-this.x))+(Math.abs(level.players.get(0).getY()-this.y)*Math.abs(level.players.get(0).getY()-this.y)));
			if(dist<50) {
				playersInRange.add(getLevel().players.get(0));
		}
		return playersInRange;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public List<Node> findPath(Vector start, Vector goal){
		List<Node> unchecked = new ArrayList<Node>();
		List<Node> checked = new ArrayList<Node>();
		Node current = new Node(0,start.getDist(goal),start,null);
		unchecked.add(current);
		while(unchecked.size()>0) {
			Collections.sort(unchecked);
			current=unchecked.get(0);
			if(current.location.equals(goal)) {
				List<Node> path= new ArrayList<Node>();
				while(current.parent!=null) {
					path.add(current);
					current=current.parent;
				}
				checked.clear();
				unchecked.clear();
				return path;
			}else {
				unchecked.remove(current);
				checked.add(current);
				for(int i=0;i<9;i++) {
					int tileX=current.location.getX();
					int tileY=current.location.getY();
					int xi=(i%3)-1;
					int yi=(i/3)-1;
					if((xi==0&&yi==0)||level.getBuilding((tileX+xi),(tileY+yi)).solid) {
						continue;
					}
					Vector atLocation= new Vector(tileX+xi,tileY+yi);
					Node at= new Node(current.gCost+current.location.getDist(atLocation),atLocation.getDist(goal),atLocation, current);
					if(atLocation.inNodeList(checked)&&at.gCost>=at.gCost)continue;
					unchecked.add(at);
				}
				
			}
		}
		unchecked.clear();
		checked.clear();
		return null;
	}
	
	public void chase() {
			int px;
			int py;
			if(playersInRange.get(0).getX()<16&&playersInRange.get(0).getX()>-16) {
				px=0;
			}else {
			 px= (int) (playersInRange.get(0).getX()/16);
			}
			
			if(playersInRange.get(0).getY()<16&&playersInRange.get(0).getY()>-16) {
				py=0;
			}else {
			 py= (int) (playersInRange.get(0).getY()/16);
			}
			
			right=false;
			left=false;
			up=false;
			down=false;
			double dist= Math.sqrt((Math.abs((level.players.get(0).getX())-this.x)*Math.abs((level.players.get(0).getX())-this.x))+(Math.abs(level.players.get(0).getY()-this.y)*Math.abs(level.players.get(0).getY()-this.y)));
			
			if(dist<=12) {
				attack();
			}
			if(dist<=Math.sqrt(2)*16&&dist>=8) {
				if ((int)playersInRange.get(0).getX()<(int)this.x) {
					this.left=true;
				}else
				if ((int)playersInRange.get(0).getX()>(int)this.x) {
					this.right=true;
				}
				if ((int)playersInRange.get(0).getY()<(int)this.y) {
					this.up=true;
				}else
				if ((int)playersInRange.get(0).getY()>(int)this.y) {
					this.down=true;
				}
				
			}else {
			Vector start = new Vector(((int)getX())/16,((int)getY()/16));
			Vector goal = new Vector(px,py);
				if(time%3==0)path= findPath(start, goal);
			
			if (path != null) {
				right=false;
				left=false;
				up=false;
				down=false;
				if (path.size() > 0) {
					
					Vector step = path.get(path.size() - 1).location;
					
					if ((int)this.x/16 < step.getX()) {
						
						right=true;
					
						
					}else
					if ((int)this.x/16> step.getX()) {
						
						left=true;
						
					
						
					}
					if ((int)this.y/16 < step.getY() ) {
						
						down=true;
					
						
					}else
					if ((int)this.y/16 > step.getY() ) {
						
						up=true;
						
					}
					}else {
						up=false;
						right=false;
						down=false;
						left=false;
					}
			}
			}
		}
	
	public void die() {
		level.getEntities().remove(this);
		level.getMobs().remove(this);
		healthbar.die();
	}
	//start tick
	public void tick() {
		double xx=0;
		double yy=0;
		attackingCycle++;
		time++;
		if(attackCoolDown>0) {
			attackCoolDown--;
		}
		
		if(health<=0) {
			die();
		}
		if(attackingCycle>=7) {
			attackingCycle=0;
			attacking=false;
		}
		if(time>=2000) {
			time=0;
		}		
		playersInRange=playerInRange();
		if(playersInRange.size()>1) {
		chase();
		}else {
			//random movement
		if(time%(random.nextInt(50) + 30) == 0) {
			int movement=random.nextInt(2);
			if(movement==0) {
			up=random.nextBoolean();
			right=random.nextBoolean();
			}
			if(!up) {
			down=random.nextBoolean();
			}
			if(!right) {
			left=random.nextBoolean();
			}
					if (random.nextInt(3) == 0) {
						up=false;
						down=false;
						left=false;
						right=false;
					}
		}
		//end of random movement
		}
		
		walking=false;
		if(!stunned) {
			
			
		if(right) {
			cardDir=1;
			if(!collideRightLeft()) {
			xx=speed;
			}
			walking=true;
		} else if(left) {
			cardDir=3;
			if(!collideRightLeft()) {
			xx=-speed;
			}
			walking=true;
		}
		
		
		if(up) {
			cardDir=0;
			if(!collideUpDown()) {
			yy=-speed;
			}
			walking=true;
		}else if(down) {
			cardDir=2;
			if(!collideUpDown()) {
			yy=speed;
			}
			walking=true;
		}
		
		
		}else {
			if(stunTimer>0) {
				stunned=true;
				stunTimer--;	
			}else {
				stunned=false;
			}
		}
		this.x+=xx;
		hitbox.x=((int)this.x+4-8);
		this.y+=yy;
		hitbox.y=((int)this.y+2-8);
		this.midx= hitbox.x+((hitbox.width)/2);
		this.midy=hitbox.y+((hitbox.height)/2);
	}
	
	//end tick
	public void render(Screen screen) {
		if(!stunned) {
		sprite=Sprite.playerDown;
		if(cardDir==0) {
			sprite=Sprite.playerUp;
			//
			if(!attacking) {
			if(walking) {
				if(time%20<10) {
					sprite=Sprite.playerUpWalkOne;
				}
				else sprite=Sprite.playerUpWalkTwo;
			}
			}else {
				attackAnim(cardDir);
			}
			//
		}else if(cardDir==1) {
			sprite=Sprite.playerRight;
			//
			if(!attacking) {
			if(walking) {
				if(time%20<10) {
					sprite=Sprite.playerRightWalkOne;
				}
				else sprite=Sprite.playerRightWalkTwo;
			}
			}else {
				attackAnim(cardDir);
							}
			//
		}else if(cardDir==2) {
			sprite=Sprite.playerDown;
			//
			if(!attacking) {
			if(walking) {
				if(time%20<10) {
					sprite=Sprite.playerDownWalkOne;
				}
				else sprite=Sprite.playerDownWalkTwo;
			}
			}else {
				attackAnim(cardDir);
			}
			//
	}else if(cardDir==3) {
		//
		sprite=Sprite.playerLeft;
		if(!attacking) {
		if(walking) {
			if(time%20<10) {
				sprite=Sprite.playerLeftWalkOne;
			}
			else sprite=Sprite.playerLeftWalkTwo;
		}
		}else {
			attackAnim(cardDir);
		}
		//
	}
		screen.renderEnemySprite((int)x-8,(int)y-8,sprite.width,sprite.height,sprite);
			}else {
		
			screen.renderStunnedSprite((int)x-8,(int)y-8,sprite.width,sprite.height,sprite);
			}
	}
	
	
public void attackAnim(int cardDir){
	
	if(cardDir==0) {
		if(attackingCycle%4==0) {
			sprite= Sprite.playerUpAttackOne;
		}
		if(attackingCycle%4==1) {
			sprite= Sprite.playerUpAttackTwo;
		}
		if(attackingCycle%4==2) {
			sprite= Sprite.playerUpAttackThree;
		}
		if(attackingCycle%3==3) {
			sprite= Sprite.playerUpAttackFour;
		}

	}
	if(cardDir==1) {
		if(attackingCycle%4==0) {
			sprite= Sprite.playerRightAttackOne;
		}
		if(attackingCycle%4==1) {
			sprite= Sprite.playerRightAttackTwo;
		}
		if(attackingCycle%4==2) {
			sprite= Sprite.playerRightAttackThree;
		}
		if(attackingCycle%3==3) {
			sprite= Sprite.playerRightAttackFour;
		}

	}
	if(cardDir==2) {
		if(attackingCycle%4==0) {
			sprite= Sprite.playerDownAttackOne;
		}
		if(attackingCycle%4==1) {
			sprite= Sprite.playerDownAttackTwo;
		}
		if(attackingCycle%4==2) {
			sprite= Sprite.playerDownAttackThree;
		}
		if(attackingCycle%3==3) {
			sprite= Sprite.playerDownAttackFour;
		}
	}
	if(cardDir==3) {
		if(attackingCycle%4==0) {
			sprite= Sprite.playerLeftAttackOne;
		}
		if(attackingCycle%4==1) {
			sprite= Sprite.playerLeftAttackTwo;
		}
		if(attackingCycle%4==2) {
			sprite= Sprite.playerLeftAttackThree;
		}
		if(attackingCycle%3==3) {
			sprite= Sprite.playerLeftAttackFour;
		}
	}
}
	
}
