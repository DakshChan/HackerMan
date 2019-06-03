import java.awt.Color;
import java.awt.Graphics;
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
		if(index >= path.size()-1) {
			reverse = true;
		}
		else if(index <= 0){
			reverse = false;
		}
		Pair dist = findDist(path.get(index));
		if (dist.x > 0) {
			this.x += 1;
			this.facing = 2;
		} 
		else if (dist.x < 0) {
			this.x -= 1;
			this.facing = 4;
		} 
		else if (dist.y > 0) {
			this.y += 1;
			this.facing = 3;
		} 
		else if (dist.y < 0) {
			this.y -= 1;
			this.facing = 1;
		}
		if(dist.x == 0 && dist.y == 0) {
			if(reverse == true) {
				index -= 1;
			}
			else {
				index += 1;
			}
		}
		 
	}
	public void setSightLine(Entity[][] grid) {
		if (this.facing == 1) {
			for (int c = this.y; c < grid[this.y].length; c++) {
				
			}
		} 
		else if (this.facing == 2) {
			for (int c = this.x; c < grid.length; c++) {
				
			}
		} 
		else if (this.facing == 3) {
			for (int c = 0; c < this.y; c++) {
				
			}
		} 
		else if (this.facing == 4) {
			for (int c = 0; c < this.x; c++) {
				
			}
		}
	}
	public void drawSelf(Graphics g) {
		this.followPath();
		g.setColor(Color.RED);
		g.fillRect(this.x, this.y, Entity.Size, Entity.Size);
	}
}
