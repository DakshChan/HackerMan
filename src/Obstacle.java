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
	@Override
	public void drawSelf(Graphics g) {
		
	}
	public void killPlayer(Player p, MapPanel map) {
		if(this.hitbox.intersects(p.hitbox)) {
			map.ingame = false;
		}
	}
}
