import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Set;

public class Player extends Entity{
	double xSpeed;
	double ySpeed;
	static int bound;
	static Image[] playerTex;
	Rectangle interactBox;
	
	public Player(int x, int y, int facing) {
		super(x, y, facing);
		//this.setInteractBox();
	}
	public void update(Set<Character>pressed) {
		if(x+Size > bound) {
			x = bound - Size;
			xSpeed = 0;
		}
		else if(x < 0) {
			x = 0;
			xSpeed = 0;
		}
		else if(pressed.contains('a')) {
			xSpeed = -1;
			this.facing = 4;
		}
		else if(pressed.contains('d')) {
			xSpeed = 1;
			this.facing = 2;
		}
		else {
			xSpeed = 0;
		}
		if(y+Size > bound) {
			y = bound - Size;
			ySpeed = 0;
		}
		else if(y < 0) {
			y = 0;
			ySpeed = 0;
		}
		else if(pressed.contains('w')) {
			ySpeed = -1;
			this.facing = 1;
		}
		else if(pressed.contains('s')) {
			ySpeed = 1;
			this.facing = 3;
		}
		else {
			ySpeed = 0;
		}
		
		x += xSpeed;
		y += ySpeed;
		hitbox.x = this.x;
		hitbox.y = this.y;
	}
	public void setInteractBox() {
		if (this.facing == 1) {
			
		}
		else if (this.facing == 2) {
			
		}
		else if (this.facing == 3) {
			
		}
		else if (this.facing == 4) {
			
		}
		
	}
	public void interact(Entity[][] map) {
		for(int hc = 0; hc < map.length; hc ++) {
			for(int vc = 0; vc < map[0].length; vc ++) {
				if(map[hc][vc] instanceof Terminal && ((Terminal)map[hc][vc]).hacked == false && this.interactBox.intersects(map[hc][vc].hitbox)) {
					if(((Terminal)map[hc][vc]).type == 2) {
						((Terminal)map[hc][vc]).hack(map);
					}
					else {
						((Terminal)map[hc][vc]).hack(map, 3);
					}
				}
				if(map[hc][vc] instanceof WarpTile && this.hitbox.intersects(map[hc][vc].hitbox)) {
					((WarpTile)map[hc][vc]).warp = true;
				}
			}
		}
	}
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, Size, Size);
	}

}
