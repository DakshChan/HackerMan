import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	private int x;
	private int y;
	private int facing;
	private Rectangle hitbox;
	public static int Size;
	/**
	 * Constructor
	 * @param x, the x position of the entity in a 2D array
	 * @param y, the y position of the entity in a 2D array
	 * @param facing, the direction the entity faces
	 */
	Entity(int x, int y, int facing){
		this.setX(x);
		this.setY(y);
		this.setFacing(facing);
		setHitbox(new Rectangle(x*Size, y*Size, Size, Size));
	}
	/**
	 * Standard getter for x
	 * @return x position in a 2D array
	 */
	public int getX() {
		return x;
	}
	/**
	 * Standard setter for x
	 * @param x, the new x position
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Standard getter for y
	 * @return y position in a 2D array
	 */
	public int getY() {
		return y;
	}
	/**
	 * Standard setter for y
	 * @param y, the new y position
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Standard getter for facing
	 * @return the facing direction, 1 for up, 2 for right, 3 for down, 4 for left, 0 for no direction
	 */
	public int getFacing() {
		return facing;
	}
	/**
	 * Standard setter for x
	 * @param facing, the new facing direction
	 */
	public void setFacing(int facing) {
		this.facing = facing;
	}
	/**
	 * Standard getter for hitbox
	 * @return a rectangle object hitbox
	 */
	public Rectangle getHitbox() {
		return hitbox;
	}
	/**
	 * Standard setter for x
	 * @param hitbox, a new rectangle object for hitbox
	 */
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	/**
	 * prevents the player from standing on top of the current entity
	 * @param p, the player
	 */
	public void rejectPlayer(Player p) {
		if(this.getHitbox().intersects(p.getHitbox())) {
			p.setxSpeed(0);
			p.setySpeed(0);
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
	/**
	 * abstract method all entities have
	 * allows them to display themselves in paintComponent
	 * @param g
	 */
	public abstract void drawSelf(Graphics g);
	
}