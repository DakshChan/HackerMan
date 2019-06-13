package src;

import java.awt.Color;
import java.awt.Graphics;

public class LaserBeam extends Obstacle{
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 */
	LaserBeam(int x, int y, int facing) {
		super(x, y, facing);
	}
	/* (non-Javadoc)
	 * @see src.Obstacle#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX()*Size, getY()*Size, Size, Size);
	}
}
