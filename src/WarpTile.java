package src;

import java.awt.Color;
import java.awt.Graphics;

public class WarpTile extends Entity{
	private String connectedMapName;
	private int connectedX;
	private int connectedY;
	private boolean warp;
	
	WarpTile(int x, int y, int facing, String connectedMapName, int connectedX, int connectedY) {
		super(x, y, facing);
		this.setConnectedMapName(connectedMapName);
		this.setConnectedX(connectedX*Size);
		this.setConnectedY(connectedY*Size);
		this.setWarp(false);
	}
	public String getConnectedMapName() {
		return connectedMapName;
	}

	public void setConnectedMapName(String connectedMapName) {
		this.connectedMapName = connectedMapName;
	}

	public int getConnectedX() {
		return connectedX;
	}

	public void setConnectedX(int connectedX) {
		this.connectedX = connectedX;
	}

	public int getConnectedY() {
		return connectedY;
	}

	public void setConnectedY(int connectedY) {
		this.connectedY = connectedY;
	}

	public boolean getWarp() {
		return warp;
	}

	public void setWarp(boolean warp) {
		this.warp = warp;
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(this.getX() *Size, this.getY()*Size, Size, Size);
	}

}
