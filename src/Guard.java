import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Guard extends Obstacle{
	private int index;
	private int xPixels;
	private int yPixels;
	private int sightRange;
	private boolean reverse;
	private Rectangle sightLine;
	private Pair[] path;
	
	public static Image[] guardTex = new Image[4];
	
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 * @param path, an arraylist of the path the guard will follow
	 */
	Guard(int x, int y, int facing, Pair[] path) {
		super(x, y, facing);
		this.index = 0;
		this.sightRange = 3;
		this.xPixels = x*Size;
		this.yPixels = y*Size;
		this.reverse = false;
		this.sightLine = new Rectangle();
		this.path = path;
	}
	/**
	 * @param target, the x and y coordinates of a position on the map
	 * @return x and y distance of guard from target
	 */
	public Pair findDist(Pair target) {
		return new Pair(target.x*Size - this.xPixels, target.y*Size - this.yPixels);
	}
	/**
	 * Makes guard follow its path
	 */
	public void followPath() {
		if(index >= path.length-1) {
			reverse = true;
		}
		else if(index <= 0){
			reverse = false;
		}
		Pair dist = findDist(path[index]);
		if (dist.x > 0) {
			this.xPixels += Size/10;
			this.setFacing(2);
		} 
		else if (dist.x < 0) {
			this.xPixels -= Size/10;
			this.setFacing(4);
		} 
		else if (dist.y > 0) {
			this.yPixels += Size/10;
			this.setFacing(3);
		} 
		else if (dist.y < 0) {
			this.yPixels -= Size/10;
			this.setFacing(1);
		}
		if(Math.abs(dist.x) <= 5 && Math.abs(dist.y) <= 5) {
			this.xPixels = (path[index].x)*Size;
			this.yPixels = (path[index].y)*Size;
			this.setFacing(0);
		}
		if(dist.x == 0 && dist.y == 0) {
			if(reverse == true) {
				index -= 1;
			}
			else {
				index += 1;
			}
		}
		this.setX(this.xPixels / Size);
		this.setY(this.yPixels / Size);

		getHitbox().x = this.xPixels;
		getHitbox().y = this.yPixels;
	}
	/**
	 * sets the guard's line of sight
	 * line of sight is blocked by other entities
	 * line of sight is reduced when hacked
	 * @param grid, an entity array, represents the map the guard is in
	 */
	public void setSightLine(Entity[][]grid) {
		int xStop; 
		int yStop;
		if (this.getFacing() == 1) {
			if(this.isHacked()) {
				yStop = Math.max(-1, (this.yPixels/Size) - sightRange);
			}
			else {
				yStop = -1;
			}
			for (int c = this.getY(); c > yStop ; c--) {
				if(grid[this.getX()][c] != null) {
					yStop = c;
				}
			}
			this.sightLine.x = this.xPixels;
			this.sightLine.y = (yStop+1)*Size;
			this.sightLine.width = Size;
			this.sightLine.height = this.yPixels - (yStop+1)*Size;
		} 
		else if (this.getFacing() == 2) {
			if(this.isHacked()) {
				xStop = Math.min(grid.length, (this.xPixels/Size) + (sightRange + 1));
			}
			else {
				xStop = grid.length;
			}
			for (int c = this.getX(); c < xStop; c++) {
				if(grid[c][this.getY()] != null) {
					xStop = c;
				}
			}
			this.sightLine.x = this.xPixels + Size;
			this.sightLine.y = this.yPixels;
			this.sightLine.width = xStop*Size - (this.xPixels + Size);
			this.sightLine.height = Size;
			 
		} 
		else if (this.getFacing() == 3) {
			if(this.isHacked()) {
				yStop = Math.min(grid[this.getY()].length, (this.yPixels/Size) + (sightRange + 1));
			}
			else {
				yStop = grid[this.getY()].length;
			}
			for (int c = this.getY(); c < yStop; c++) {
				if(grid[this.getX()][c] != null) {
					yStop = c;
				}
			}
			this.sightLine.x = this.xPixels;
			this.sightLine.y = this.yPixels + Size;
			this.sightLine.width = Size;
			this.sightLine.height = yStop*Size - (this.yPixels + Size);
		} 
		else if (this.getFacing() == 4) {
			if(this.isHacked()) {
				xStop = Math.max(-1, (this.xPixels/Size) - (sightRange));
			}
			else {
				xStop = -1;
			}
			for (int c = this.getX(); c >= 0 && xStop == -1; c--) {
				if(grid[c][this.getY()] != null) {
					xStop = c;
				}
			}
			this.sightLine.x = (xStop + 1)*Size;
			this.sightLine.y = this.yPixels;
			this.sightLine.width = this.xPixels - (xStop + 1)*Size;
			this.sightLine.height = Size;
		}
		else {
			this.sightLine.x = this.xPixels;
			this.sightLine.y = this.yPixels;
			this.sightLine.width = Size;
			this.sightLine.height = Size;
		}
	}
	/* (non-Javadoc)
	 * @see src.Obstacle#killPlayer(src.Player, src.MapPanel)
	 */
	@Override
	public void killPlayer(Player p, MapPanel map){
		if(this.getHitbox().intersects(p.getHitbox()) || this.sightLine.intersects(p.getHitbox())) {
			map.setIngame(false);
		}
	}
	/* (non-Javadoc)
	 * @see src.Obstacle#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		Color overlayColor = new Color(255, 255, 5, 96);
		g.setColor(overlayColor);
		g.fillRect(this.sightLine.x, this.sightLine.y, this.sightLine.width, this.sightLine.height);
		/*
		 * switch(getFacing()) { case 1: g.drawImage(guardTex[0], this.xPixels,
		 * this.yPixels, Entity.Size, Entity.Size, null); break; case 2:
		 * g.drawImage(guardTex[1], this.xPixels, this.yPixels, Entity.Size,
		 * Entity.Size, null); break; case 3: g.drawImage(guardTex[2], this.xPixels,
		 * this.yPixels, Entity.Size, Entity.Size, null); break; case 4:
		 * g.drawImage(guardTex[3], this.xPixels, this.yPixels, Entity.Size,
		 * Entity.Size, null); break; default: g.drawImage(guardTex[0], this.xPixels,
		 * this.yPixels, Entity.Size, Entity.Size, null); break; }
		 */
		g.setColor(Color.RED);
		g.fillRect(this.xPixels, this.yPixels, Entity.Size, Entity.Size);
	}
}
