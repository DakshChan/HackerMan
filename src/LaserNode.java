package src;

import java.awt.Image;
import java.util.ArrayList;

public class LaserNode extends Obstacle{
	ArrayList<Pair> connected;
	static Image LaserNTex;
	LaserNode(int x, int y, int facing) {
		super(x, y, facing);
	}
	public void connect(Entity[][] grid) {
//		for(int hc = 0; hc < grid.length; hc++) {
//			if(grid[hc][this.y] instanceof LaserNode && ((Obstacle)grid[hc][this.y]).hacked == false && grid[hc][this.y] != this) {
//				connected.add(new Pair(hc, this.y));
//			}
//		}
//		for(int vc = 0; vc < grid[y].length; vc++) {
//			if(grid[this.x][vc] instanceof LaserNode && ((Obstacle)grid[this.x][vc]).hacked == false && grid[this.x][vc] != this) {
//				connected.add(new Pair(this.x, vc));
//			}
//		}
//		for(int c = 0; c < connected.size(); c++) {
//			Pair target = connected.get(c);
//			if(target.x - this.x > 1) {
//				for(int i = target.x + 1; i < this.x; i++) {
//					grid[i][this.y] = new LaserBeam(i, this.y, 0, Size);
//				}
//			}
//			else if(target.y - this.y > 1) {
//				
//			}
//			else if(target.x - this.x < 1) {
//				
//			}
//			else if(target.y - this.y < 1) {
//				
//			}
//		}
	}
}
