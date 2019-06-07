package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class LaserBeam extends Obstacle{
	static Image laserBTex;
	LaserBeam(int x, int y, int facing) {
		super(x, y, facing);
	}
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(x*Size, y*Size, Size, Size);
	}
}
