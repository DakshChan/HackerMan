import java.awt.Color;
import java.awt.Graphics;

public class WarpTile extends Entity{
	String connectedMapName;
	String componentID;
	int connectedX;
	int connectedY;
	boolean warp;
	
	WarpTile(int x, int y, int facing, String connectedMapName, int connectedX, int connectedY) {
		super(x, y, facing);
		this.connectedMapName = connectedMapName;
		this.connectedX = connectedX*Size;
		this.connectedY = connectedY*Size;
		this.warp = false;
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(this.x *Size, this.y*Size, Size, Size);
	}
	
}
