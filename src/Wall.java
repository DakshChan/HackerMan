import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Entity{

	/**
	 * @param x
	 * @param y
	 * @param facing
	 */
	Wall(int x, int y, int facing) {
		super(x, y, facing);
	}

	/* (non-Javadoc)
	 * @see src.Entity#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getX()*Size, getY()*Size, Size, Size);
	}

}
