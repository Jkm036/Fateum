package com.fourTen.Mob;

import java.awt.Rectangle;


import com.fourTen.Level;
import com.fourTen.Screen;
import com.fourTen.Sprite;
import com.fourTen.Event.Action;
import com.fourTen.Event.KeyPressed;
import com.fourTen.Event.KeyReleased;
import com.fourTen.Event.MousePressed;
import com.fourTen.UIManager.UIHealthBar;
import com.fourTen.Vector.Vector;

public class MainPlayer extends Mob{
	
	public MainPlayer(int x, int y) {
		super(x, y);
		stunEffect=5;
		damage=4;
		healthsize=new Vector(98,6);
		attackSlowness=.5;
		speed=1;
		
		//hitbox= new Rectangle(((this.x+2)-level.xScroll)*game.scale,((this.y+2)-level.yScroll)*game.scale,12*game.scale,11*game.scale);
		// TODO Auto-generated constructor stub
	}
	public void initHitBox() {
		this.hitbox= new Rectangle((int)this.x+4-8,(int)this.y+2-8,6,11);
		this.midx= hitbox.x+((hitbox.width)/2);
		this.midy=hitbox.y+((hitbox.height)/2);
	}
	public void init(Level level) {
		super.init(level);
		initHitBox();
		if(healthbar==null) {
		healthbar = new UIHealthBar( healthsize, this);
		}
		level.uimanager.addComponent(healthbar);
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
	///////
	
	
	public void tick() {
		walkingCycle++;
		attackingCycle++;
		if(walkingCycle>=60)walkingCycle=0;
		if(attackingCycle>=7) {
			attackingCycle=0;
			attacking=false;
		}
		if(attackCoolDown>0) {
			attackCoolDown--;
		}
		double xx=0;
		double yy=0;
		walking=false;
		
		if(!stunned) {
		if(right) {
			cardDir=1;
			if(!collideRightLeft()) {
			xx=speed;
			}
			walking=true;
		}else if(left) {
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
		} else if(down) {
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
	public void render(Screen screen) {
		if(!stunned) {
		sprite=Sprite.playerDown;
		if(cardDir==0) {
			sprite=Sprite.playerUp;
			//
			if(!attacking) {
			if(walking) {
				if(walkingCycle%20<10) {
					sprite=Sprite.playerUpWalkOne;
				}
				else sprite=Sprite.playerUpWalkTwo;
			}
			}else {
				attackAnim(cardDir);
			}
			//
				}
		if(cardDir==1) {
			sprite=Sprite.playerRight;
			//
			if(!attacking) {
			if(walking) {
				if(walkingCycle%20<10) {
					sprite=Sprite.playerRightWalkOne;
				}
				else sprite=Sprite.playerRightWalkTwo;
			}
			}else {
				attackAnim(cardDir);
							}
			//
					}
		if(cardDir==2) {
			sprite=Sprite.playerDown;
			//
			if(!attacking) {
			if(walking) {
				if(walkingCycle%20<10) {
					sprite=Sprite.playerDownWalkOne;
				}
				else sprite=Sprite.playerDownWalkTwo;
			}
			}else {
				attackAnim(cardDir);
			}
			//
				}
		if(cardDir==3) {
		//
		sprite=Sprite.playerLeft;
		if(!attacking) {
		if(walking) {
			if(walkingCycle%20<10) {
				sprite=Sprite.playerLeftWalkOne;
			}
			else sprite=Sprite.playerLeftWalkTwo;
		}
		}else {
			attackAnim(cardDir);
		}
		//
				}
		
		screen.renderSprite((int)x-8,(int)y-8,sprite.width,sprite.height,sprite);
		}else {
			screen.renderStunnedSprite((int)x-8,(int)y-8,sprite.width,sprite.height,sprite);
		}
		
	}
	
	public void listenToEvent(Action action) {
		if(action instanceof KeyPressed) {
		//if W is pressed
			if(((KeyPressed)action).getKeyCode()==0x57) {
				up=true;
				//if A is pressed
								}
			if(((KeyPressed)action).getKeyCode()==0x41) {
				left=true;
				//if S is pressed
							}
			if(((KeyPressed)action).getKeyCode()==0x53) {
			down=true;
						}
			if(((KeyPressed)action).getKeyCode()==0x44) {
				right=true;
							}
		}
		if(action instanceof KeyReleased) {
			//if W is pressed
				if(((KeyReleased)action).getKeyCode()==0x57) {
					up=false;
					//if A is pressed
									}
				if(((KeyReleased)action).getKeyCode()==0x41) {
					left=false;
					//if S is pressed
								}
				if(((KeyReleased)action).getKeyCode()==0x53) {
				down=false;
							}
				if(((KeyReleased)action).getKeyCode()==0x44) {
					right=false;
								}
			}
		
		if(action instanceof MousePressed) {
			attack();
		}
	}

}
