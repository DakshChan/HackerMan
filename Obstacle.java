package src;

import java.awt.Graphics;

public class Obstacle extends Entity{
	private boolean hacked;
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 */
	Obstacle(int x, int y, int facing) {
		super(x, y, facing);	
		setHacked(false);
	}
	/**
	 * Standard getter for hacked
	 * @return true if obstacle is hacked, false if not
	 */
	public boolean isHacked() {
		return hacked;
	}
	/**
	 * Standard setter for hacked
	 * @param hacked, new value for hacked
	 */
	public void setHacked(boolean hacked) {
		this.hacked = hacked;
	}
	/* (non-Javadoc)
	 * @see src.Entity#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		
	}
	/**
	 * Ends the game if the player hitbox touches the obstacle hitbox
	 * @param p
	 * @param map
	 */
	public void killPlayer(Player p, MapPanel map) {
		if(this.getHitbox().intersects(p.getHitbox())) {
			map.setIngame(false);
		}
	}
}
