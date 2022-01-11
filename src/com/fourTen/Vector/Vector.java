package com.fourTen.Vector;

import java.util.List;

import AStar.Node;

public class Vector {
int x;
int y;
	public Vector(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public double getDist(Vector vector) {
		double dist= Math.sqrt(((Math.abs((vector.getX())-this.x)*Math.abs((vector.getX())-this.x)))+((Math.abs(vector.getY()-this.y)*Math.abs(vector.getY()-this.y))));
		return dist;
	}
	public boolean inNodeList(List<Node> l) {
		for(int i=0;i<l.size();i++) {
			if(l.get(i).location.equals(this)) {
				return true;
			}
		}
		return false;
		
	}
	public boolean equals(Object o) {
		if(!(o instanceof Vector))return false;
		if(((Vector)(o)).getX()==this.getX()&&((Vector)(o)).getY()==this.getY()){
			return true;
		}
		return false;
		
	}
}
