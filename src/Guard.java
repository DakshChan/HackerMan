package src;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Guard extends Obstacle{
	int index;
	boolean reverse;
	Rectangle sightLine;
	ArrayList<Pair> path;
	static Image[] guardTex;
	Guard(int x, int y, int facing, ArrayList<Pair> path) {
		super(x, y, facing);
		this.path = path;
		this.index = 0;
		this.reverse = false;
	}
	@Override
	public void killPlayer(){
		//both the guard hitbox and sight hitbow will kill player
	}
	public Pair findDist(Pair target) {
		return new Pair(target.x - this.x, target.y - this.y);
	}
	public void followPath() {
		if(index >= path.size()) {
			reverse = true;
		}
		else if(index <= 0) {
			reverse = false;
		}
		Pair dist = findDist(path.get(index));
		if(dist.x > 0) {
			this.x += 1;
		}
		else if (dist.x < 0) {
			this.x -= 1;
		}
		else if (dist.y > 0) {
			this.y += 1;
		}
		else if( dist.y < 0) {
			this.y -= 1;
		}
		else {
			if(reverse = true) {
				index -= 1;
			}
			else {
				index += 1;
			}
		}
	}
	public void setSightLine() {
		
	}
}
