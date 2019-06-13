package src;

import java.awt.Color;
import java.awt.Graphics;

public class WarpTile extends Entity{
	private String connectedMapName;
	private int connectedX;
	private int connectedY;
	private boolean warp;
	
	//public static Image warpTex;
	
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 * @param connectedMapName, a string with the name of a connected map
	 * @param connectedX, the x position the player will appear in on the connected map
	 * @param connectedY, the y position the player will appear in on the connected map
	 */
	WarpTile(int x, int y, int facing, String connectedMapName, int connectedX, int connectedY) {
		super(x, y, facing);
		this.setConnectedMapName(connectedMapName);
		this.setConnectedX(connectedX*Size);
		this.setConnectedY(connectedY*Size);
		this.setWarp(false);
	}
	/**
	 * Standard getter for connectedMapName
	 * @return, connectedMapName
	 */
	public String getConnectedMapName() {
		return connectedMapName;
	}

	/**
	 * Standard setter for connectedMapName
	 * @param connectedMapName, new connectedMapName
	 */
	public void setConnectedMapName(String connectedMapName) {
		this.connectedMapName = connectedMapName;
	}

	/**
	 * Standard getter for connectedX
	 * @return connectedX
	 */
	public int getConnectedX() {
		return connectedX;
	}

	/**
	 * Standard setter for connectedX
	 * @param connectedX, new connectedX
	 */
	public void setConnectedX(int connectedX) {
		this.connectedX = connectedX;
	}

	/**
	 * Standard getter for connectedY
	 * @return connectedY
	 */
	public int getConnectedY() {
		return connectedY;
	}

	/**
	 * Standard setter for connectedX
	 * @param connectedY
	 */
	public void setConnectedY(int connectedY) {
		this.connectedY = connectedY;
	}

	/**
	 * Standard getter for warp
	 * @return
	 */
	public boolean getWarp() {
		return warp;
	}

	/**
	 * Standard setter for warp
	 * @param warp
	 */
	public void setWarp(boolean warp) {
		this.warp = warp;
	}

	/* (non-Javadoc)
	 * @see src.Entity#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(this.getX() *Size, this.getY()*Size, Size, Size);
	}

}
