import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class LaserNode extends Obstacle{
	ArrayList<Pair> connected;
	static Image LaserNTex;
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 */
	LaserNode(int x, int y, int facing) {
		super(x, y, facing);
		this.connected = new ArrayList<Pair>();
	}
	/**
	 * checks for other laserNodes in cardinal directions and created laserBeams between them
	 * laserBeams are blocked by other entities
	 * @param grid, a 2D array, represents the map the laserNode is in
	 * @param laser, a separate 2D array that laserBeams are created in
	 */
	public void connect(Entity[][] grid, Entity[][] laser) {
		int xUpper = grid.length;
		int yUpper = grid[0].length;
		int xLower = -1;
		int yLower = -1;
		for (int c = this.getY() - 1; c > yLower; c--) {
			if(grid[this.getX()][c] != null) {
				if(grid[this.getX()][c] instanceof LaserNode && ((LaserNode)grid[this.getX()][c]).isHacked() == false) {
					connected.add(new Pair(this.getX(),c));
				}
				yLower = c;
			}
		}
		for (int c = this.getY() + 1; c < yUpper; c++) {
			if(grid[this.getX()][c] != null) {
				if(grid[this.getX()][c] instanceof LaserNode && ((LaserNode)grid[this.getX()][c]).isHacked() == false) {
					connected.add(new Pair(this.getX(),c));
				}
				yUpper = c;
			}
		}
		for (int c = this.getX() - 1; c > xLower; c--) {
			if(grid[c][this.getY()] != null) {
				if(grid[c][this.getY()] instanceof LaserNode && ((LaserNode)grid[c][this.getY()]).isHacked() == false) {
					connected.add(new Pair(c, this.getY()));
				}
				xLower = c;
			}
		}
		for (int c = this.getX() + 1; c < xUpper; c++) {
			if(grid[c][this.getY()] != null) {
				if(grid[c][this.getY()] instanceof LaserNode && ((LaserNode)grid[c][this.getY()]).isHacked() == false) {
					connected.add(new Pair(c, this.getY()));
				}
				xUpper = c;
			}
		}
		for(int c = 0; c < connected.size(); c++) {
			Pair target = connected.get(c);
			if(target.x - this.getX() < 0) {
				for(int i = target.x + 1; i < this.getX(); i++) {
					if(laser[i][this.getY()] == null) {
						laser[i][this.getY()] = new LaserBeam(i, this.getY(), 0);
					}
				}
			}
			else if(target.y - this.getY() < 0) {
				for(int i = target.y + 1; i < this.getY(); i++) {
					if(laser[this.getX()][i] == null) {
						laser[this.getX()][i] = new LaserBeam(this.getX(), i, 0);
					}
				}
			}
			else if(target.x - this.getX() > 0) {
				for(int i = this.getX() + 1; i < target.x; i++) {
					if(laser[i][this.getY()] == null) {
						laser[i][this.getY()] = new LaserBeam(i, this.getY(), 0);
					}
				}
			}
			else if(target.y - this.getY() > 0) {
				for(int i = this.getY() + 1; i < target.y; i++) {
					if(laser[this.getX()][i] == null) {
						laser[this.getX()][i] = new LaserBeam(this.getX(), i, 0);
					}
				}
			}
		}
	}
	/**
	 * Disconnects laserNode from others when hacked
	 * Removes laserBeams connected to the laserNode
	 * @param grid, a 2D array, represents the map the laserNode is in
	 * @param laser, a separate 2D array that laserBeams exist in
	 */
	public void disconnect(Entity[][] grid, Entity[][] laser) {
		for(int c = 0; c < connected.size(); c++) {
			Pair target = connected.get(c);
			((LaserNode)grid[target.x][target.y]).connected.clear();
			if(target.x - this.getX() < 0) {
				for(int i = target.x + 1; i < this.getX(); i++) {
					if(laser[i][this.getY()] != null) {
						laser[i][this.getY()] = null;
					}
				}
			}
			else if(target.y - this.getY() < 0) {
				for(int i = target.y + 1; i < this.getY(); i++) {
					if(laser[this.getX()][i] != null) {
						laser[this.getX()][i] = null;
					}
				}
			}
			else if(target.x - this.getX() > 0) {
				for(int i = this.getX() + 1; i < target.x; i++) {
					if(laser[i][this.getY()] != null) {
						laser[i][this.getY()] = null;
					}
				}
			}
			else if(target.y - this.getY() > 0) {
				for(int i = this.getY() + 1; i < target.y; i++) {
					if(laser[this.getX()][i] != null) {
						laser[this.getX()][i] = null;
					}
				}
			}
		}
		connected.clear();
	}
	/* (non-Javadoc)
	 * @see src.Obstacle#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(getX()*Size, getY()*Size, Size, Size);
	}
}
