package com.fourTen;

import com.fourTen.Mob.Enemy;
import com.fourTen.Vector.Vector;

public class Ogre extends Enemy {

	public Ogre(int x, int y) {
		super(x, y);
		stunEffect=5;
		damage=9;
	 healthsize = new Vector(98,6);
	 attackSlowness=1;
	 speed=.5;
	}
	public void attackAnim(int cardDir){
		
		if(cardDir==0) {
			if(attackingCycle%4==0) {
				sprite= Sprite.ogreUpAttackOne;
			}
			if(attackingCycle%4==1) {
				sprite= Sprite.ogreUpAttackTwo;
			}
			if(attackingCycle%4==2) {
				sprite= Sprite.ogreUpAttackThree;
			}
			if(attackingCycle%3==3) {
				sprite= Sprite.ogreUpAttackFour;
			}

		}
		if(cardDir==1) {
			if(attackingCycle%4==0) {
				sprite= Sprite.ogreRightAttackOne;
			}
			if(attackingCycle%4==1) {
				sprite= Sprite.ogreRightAttackTwo;
			}
			if(attackingCycle%4==2) {
				sprite= Sprite.ogreRightAttackThree;
			}
			if(attackingCycle%3==3) {
				sprite= Sprite.ogreRightAttackFour;
			}

		}
		if(cardDir==2) {
			if(attackingCycle%4==0) {
				sprite= Sprite.ogreDownAttackOne;
			}
			if(attackingCycle%4==1) {
				sprite= Sprite.ogreDownAttackTwo;
			}
			if(attackingCycle%4==2) {
				sprite= Sprite.ogreDownAttackThree;
			}
			if(attackingCycle%3==3) {
				sprite= Sprite.ogreDownAttackFour;
			}
		}
		if(cardDir==3) {
			if(attackingCycle%4==0) {
				sprite= Sprite.ogreLeftAttackOne;
			}
			if(attackingCycle%4==1) {
				sprite= Sprite.ogreLeftAttackTwo;
			}
			if(attackingCycle%4==2) {
				sprite= Sprite.ogreLeftAttackThree;
			}
			if(attackingCycle%3==3) {
				sprite= Sprite.ogreLeftAttackFour;
			}
		}
	}
	public void render(Screen screen) {
		sprite=Sprite.ogreDown;
		if(!stunned) {	
			if(cardDir==0) {
				sprite=Sprite.ogreUp;
				//
				if(!attacking) {
				if(walking) {
					if(time%20<10) {
						sprite=Sprite.ogreUpWalkOne;
					}
					else sprite=Sprite.ogreUpWalkTwo;
				}
				}else {
					attackAnim(cardDir);
				}
				//
			}else if(cardDir==1) {
				sprite=Sprite.ogreRight;
				//
				if(!attacking) {
				if(walking) {
					if(time%20<10) {
						sprite=Sprite.ogreRightWalkOne;
					}
					else sprite=Sprite.ogreRightWalkTwo;
				}
				}else {
					attackAnim(cardDir);
								}
				//
			}else if(cardDir==2) {
				sprite=Sprite.ogreDown;
				//
				if(!attacking) {
				if(walking) {
					if(time%20<10) {
						sprite=Sprite.ogreDownWalkOne;
					}
					else sprite=Sprite.ogreDownWalkTwo;
				}
				}else {
					attackAnim(cardDir);
				}
				//
		}else if(cardDir==3) {
			//
			sprite=Sprite.ogreLeft;
			if(!attacking) {
			if(walking) {
				if(time%20<10) {
					sprite=Sprite.ogreLeftWalkOne;
				}
				else sprite=Sprite.ogreLeftWalkTwo;
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

}
