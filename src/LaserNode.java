import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class LaserNode extends Obstacle{
	ArrayList<Pair> connected;
	static Image LaserNTex;
	LaserNode(int x, int y, int facing) {
		super(x, y, facing);
		this.connected = new ArrayList<Pair>();
	}
	LaserNode(int x, int y, int facing, boolean hacked) {
		super(x, y, facing, hacked);
		this.connected = new ArrayList<Pair>();
	}
	public void connect(Entity[][] grid, Entity[][] laser) {
		int xUpper = grid.length;
		int yUpper = grid[0].length;
		int xLower = -1;
		int yLower = -1;
		for (int c = this.y - 1; c >= 0 && yLower == -1; c--) {
			if(grid[this.x][c] != null) {
				if(grid[this.x][c] instanceof LaserNode && ((LaserNode)grid[this.x][c]).hacked == false) {
					connected.add(new Pair(this.x,c));
				}
				yLower = c;
			}
		}
		for (int c = this.y + 1; c < yUpper; c++) {
			if(grid[this.x][c] != null) {
				if(grid[this.x][c] instanceof LaserNode && ((LaserNode)grid[this.x][c]).hacked == false) {
					connected.add(new Pair(this.x,c));
				}
				yUpper = c;
			}
		}
		for (int c = this.x - 1; c >= 0 && xLower == -1; c--) {
			if(grid[c][this.y] != null) {
				if(grid[c][this.y] instanceof LaserNode && ((LaserNode)grid[c][this.y]).hacked == false) {
					connected.add(new Pair(c, this.y));
				}
				xLower = c;
			}
		}
		for (int c = this.x + 1; c < xUpper; c++) {
			if(grid[c][this.y] != null) {
				if(grid[c][this.y] instanceof LaserNode && ((LaserNode)grid[c][this.y]).hacked == false) {
					connected.add(new Pair(c, this.y));
				}
				xUpper = c;
			}
		}
		for(int c = 0; c < connected.size(); c++) {
			Pair target = connected.get(c);
			if(target.x - this.x < 0) {
				for(int i = target.x + 1; i < this.x; i++) {
					if(laser[i][this.y] == null) {
						laser[i][this.y] = new LaserBeam(i, this.y, 0);
					}
				}
			}
			else if(target.y - this.y < 0) {
				for(int i = target.y + 1; i < this.y; i++) {
					if(laser[this.x][i] == null) {
						laser[this.x][i] = new LaserBeam(this.x, i, 0);
					}
				}
			}
			else if(target.x - this.x > 0) {
				for(int i = this.x + 1; i < target.x; i++) {
					if(laser[i][this.y] == null) {
						laser[i][this.y] = new LaserBeam(i, this.y, 0);
					}
				}
			}
			else if(target.y - this.y > 0) {
				for(int i = this.y + 1; i < target.y; i++) {
					if(laser[this.x][i] == null) {
						laser[this.x][i] = new LaserBeam(this.x, i, 0);
					}
				}
			}
		}
	}
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x*Size, y*Size, Size, Size);
	}
}
