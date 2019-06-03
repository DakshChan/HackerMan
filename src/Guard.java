package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Guard extends Obstacle{
	int index;
	int xPixels;
	int yPixels;
	boolean reverse;
	Rectangle sightLine;
	ArrayList<Pair> path;
	static Image[] guardTex;
	Guard(int x, int y, int facing, ArrayList<Pair> path) {
		super(x, y, facing);
		this.xPixels = x*Size;
		this.yPixels = y*Size;
		this.sightLine = new Rectangle();
		this.path = path;
		this.index = 0;
		this.reverse = false;
	}
	@Override
	public void killPlayer(){
		//both the guard hitbox and sight hitbow will kill player
	}
	public Pair findDist(Pair target) {
		return new Pair(target.x*Size - this.xPixels, target.y*Size - this.yPixels);
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
			this.xPixels += 1;
			this.facing = 2;
		} 
		else if (dist.x < 0) {
			this.xPixels -= 1;
			this.facing = 4;
		} 
		else if (dist.y > 0) {
			this.yPixels += 1;
			this.facing = 3;
		} 
		else if (dist.y < 0) {
			this.yPixels -= 1;
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
		
		this.x = this.xPixels / Size;
		this.y = this.yPixels / Size;

		hitbox.x = this.xPixels;
		hitbox.y = this.yPixels;
	}
	public void setSightLine(Entity[][]grid) {
		if (this.facing == 1) {
			int yStop = -1;
			for (int c = this.y; c >= 0 && yStop == -1; c--) {
				if(grid[this.x][c] != null) {
					yStop = c;
				}
			}
			this.sightLine.x = this.xPixels;
			this.sightLine.y = (yStop+1)*Size;
			this.sightLine.width = Size;
			this.sightLine.height = this.yPixels - (yStop+1)*Size;
		} 
		else if (this.facing == 2) {
			int xStop = grid.length;
			for (int c = this.x; c < xStop; c++) {
				if(grid[c][this.y] != null) {
					xStop = c;
				}
			}
			this.sightLine.x = this.xPixels + Size;
			this.sightLine.y = this.yPixels;
			this.sightLine.width = xStop*Size - (this.xPixels + Size);
			this.sightLine.height = Size;
			 
		} 
		else if (this.facing == 3) {
			int yStop = grid.length;
			for (int c = this.y; c < yStop; c++) {
				if(grid[this.x][c] != null) {
					yStop = c;
				}
			}
			this.sightLine.x = this.xPixels;
			this.sightLine.y = this.yPixels + Size;
			this.sightLine.width = Size;
			this.sightLine.height = yStop*Size - (this.yPixels + Size);
			//System.out.println(yStop);
		} 
		else if (this.facing == 4) {
			int xStop = -1;
			for (int c = this.x; c >= 0 && xStop == -1; c--) {
				if(grid[c][this.y] != null) {
					xStop = c;
				}
			}
			this.sightLine.x = (xStop + 1)*Size;
			this.sightLine.y = this.yPixels;
			this.sightLine.width = this.xPixels - (xStop + 1)*Size;
			this.sightLine.height = Size;
		}
	}
	public void drawSelf(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this.xPixels, this.yPixels, Entity.Size, Entity.Size);
		g.setColor(Color.YELLOW);
		g.fillRect(this.sightLine.x, this.sightLine.y, this.sightLine.width, this.sightLine.height);
	}
}
