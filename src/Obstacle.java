package src;

import java.awt.Graphics;

public class Obstacle extends Entity{
	private boolean hacked;
	Obstacle(int x, int y, int facing) {
		super(x, y, facing);	
		setHacked(false);
	}
	Obstacle(int x, int y, int facing, boolean hacked) {
		super(x, y, facing);	
		this.setHacked(hacked);
	}
	public boolean isHacked() {
		return hacked;
	}
	public void setHacked(boolean hacked) {
		this.hacked = hacked;
	}
	@Override
	public void drawSelf(Graphics g) {
		
	}
	public void killPlayer(Player p, MapPanel map) {
		if(this.getHitbox().intersects(p.getHitbox())) {
			map.ingame = false;
		}
	}
}
