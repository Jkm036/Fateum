package AStar;

import com.fourTen.Vector.Vector;

public class Node implements Comparable<Node> {
double hCost;
public double gCost;
double fCost;
public Node parent;
public Vector location;

public Node(double gCost, double hCost,Vector location, Node parent) {
	this.hCost=hCost;
	this.gCost=gCost;
	this.fCost=gCost+hCost;
	this.parent=parent;
	this.location=location;
};

@Override
public int compareTo(Node o) {
	if(this.fCost<o.fCost) {
		return -1;
	}
	if(this.fCost>o.fCost) {
		return 1;
	}
	return 0;
}

}
