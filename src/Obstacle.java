package src;

import java.awt.Graphics;

public class Obstacle extends Entity{
	boolean hacked;
	Obstacle(int x, int y, int facing) {
		super(x, y, facing);	
		hacked = false;
	}
	Obstacle(int x, int y, int facing, boolean hacked) {
		super(x, y, facing);	
		this.hacked = hacked;
	}
	public void killPlayer() {
		//kills player if hitbox intersects
	}
	@Override
	public void drawSelf(Graphics g) {
		
	}
}
