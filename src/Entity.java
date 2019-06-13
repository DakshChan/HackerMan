import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	private int x;
	private int y;
	private int facing;
	private Rectangle hitbox;
	public static int Size;
	Entity(int x, int y, int facing){
		this.setX(x);
		this.setY(y);
		this.setFacing(facing);
		setHitbox(new Rectangle(x*Size, y*Size, Size, Size));
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getFacing() {
		return facing;
	}
	public void setFacing(int facing) {
		this.facing = facing;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public void rejectPlayer(Player p) {
		if(this.getHitbox().intersects(p.getHitbox())) {
			p.xSpeed = 0;
			p.ySpeed = 0;
			if(p.getFacing() == 1) {
				p.setY((this.getY()+1)*Size);
			}
			if(p.getFacing() == 2) {
				p.setX((this.getX()-1)*Size);
			}
			if(p.getFacing() == 3) {
				p.setY((this.getY()-1)*Size);
			}
			if(p.getFacing() == 4) {
				p.setX((this.getX()+1)*Size);
			}
		}
	}
	public abstract void drawSelf(Graphics g);
	
}