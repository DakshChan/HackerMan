package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Set;

public class Player extends Entity{
	private double xSpeed;
	private double ySpeed;
	private Rectangle interactBox;
	
	public static int bound;
	public static Image[] playerTex = new Image[4];
	
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 */
	public Player(int x, int y, int facing) {
		super(x, y, facing);
		this.interactBox = new Rectangle();
	}
	/**
	 * Standard getter for xSpeed
	 * @return xSpeed, speed player moves horizontally
	 */
	public double getxSpeed() {
		return xSpeed;
	}
	/**
	 * Standard setter for xSpeed
	 * @param xSpeed, new xSpeed
	 */
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}
	/**
	 * Standard getter for ySpeed
	 * @return ySpeed, speed player moves vertically
	 */
	public double getySpeed() {
		return ySpeed;
	}
	/**
	 * Standard setter for ySpeed
	 * @param ySpeed, new ySpeed
	 */
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
	/**
	 * Runs all other player actions from a single method
	 * @param pressed, a list of keys that are pressed
	 * @param map, a 2D entity array representing the map the player is in
	 * @param guards, a 2D entity array containing guards
	 */
	public void update(Set<Character>pressed, Entity[][]map, Entity[][]guards) {
		this.move(pressed);
		this.setInteractBox();
		if(pressed.contains('e')) {
			this.interact(map, guards);
		}
	}
	/**
	 * Moves the player
	 * @param pressed, a list of keys that are pressed
	 */
	public void move(Set<Character>pressed) {
		if(getX()+Size > bound) {
			setX(bound - Size);
			setxSpeed(0);
		}
		else if(getX() < 0) {
			setX(0);
			setxSpeed(0);
		}
		else if(pressed.contains('a')) {
			setxSpeed(-Size/10);
			this.setFacing(4);
		}
		else if(pressed.contains('d')) {
			setxSpeed(Size/10);
			this.setFacing(2);
		}
		else {
			setxSpeed(0);
		}
		if(getY()+Size > bound) {
			setY(bound - Size);
			setySpeed(0);
		}
		else if(getY() < 0) {
			setY(0);
			setySpeed(0);
		}
		else if(pressed.contains('w')) {
			setySpeed(-Size/10);
			this.setFacing(1);
		}
		else if(pressed.contains('s')) {
			setySpeed(Size/10);
			this.setFacing(3);
		}
		else {
			setySpeed(0);
		}
		setX((int)(getX() + getxSpeed()));
		setY((int)(getY() + getySpeed()));
		getHitbox().x = this.getX();
		getHitbox().y = this.getY();
	}
	/**
	 * Sets the interaction hitbox of the player
	 * Interaction hitbox allows player to interact with other Entities
	 */
	public void setInteractBox() {
		if (this.getFacing() == 1) {
			this.interactBox.x = this.getX();
			this.interactBox.y = Math.max(0, this.getY() - (Size/2));
			this.interactBox.width = Size;
			this.interactBox.height = Size/2;
		}
		else if (this.getFacing() == 2) {
			this.interactBox.x = Math.min(bound, this.getX() + Size);
			this.interactBox.y = this.getY();
			this.interactBox.width = Size/2;
			this.interactBox.height = Size;
		}
		else if (this.getFacing() == 3) {
			this.interactBox.x = this.getX();
			this.interactBox.y = Math.min(bound, this.getY() + Size);
			this.interactBox.width = Size;
			this.interactBox.height = Size/2;
		}
		else if (this.getFacing() == 4) {
			this.interactBox.x = Math.max(0, this.getX() - (Size/2));
			this.interactBox.y = this.getY();
			this.interactBox.width = Size/2;
			this.interactBox.height = Size;
		}
		
	}
	/**
	 * Allows the player to interact with other entities on the map
	 * Player can hack Terminals and activate WarpTiles to enter other maps
	 * @param map, a 2D entity array representing the map the player is in
	 * @param guards, a 2D entity array containing guards
	 */
	public void interact(Entity[][] map, Entity[][]guards) {
		for(int vc = 0; vc < map.length; vc ++) {
			for(int hc = 0; hc < map[0].length; hc ++) {
				if (map[hc][vc] instanceof Terminal && ((Terminal) map[hc][vc]).getHacked() == false && this.interactBox.intersects(map[hc][vc].getHitbox())) {
					if (((Terminal) map[hc][vc]).getType() == 2) {
						((Terminal) map[hc][vc]).hack(guards);
					} else {
						((Terminal) map[hc][vc]).hack(map);
					}
					((Terminal) map[hc][vc]).setMinigameStart(true);
				}
				if(map[hc][vc] instanceof WarpTile && this.getHitbox().intersects(map[hc][vc].getHitbox())) {
					((WarpTile)map[hc][vc]).setWarp(true);
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see src.Entity#drawSelf(java.awt.Graphics)
	 */
	@Override
	public void drawSelf(Graphics g) {
		/*
		 * switch(getFacing()) { case 1: g.drawImage(playerTex[0], this.getX(),
		 * this.getY(), Entity.Size, Entity.Size, null); break; case 2:
		 * g.drawImage(playerTex[1], this.getX(), this.getY(), Entity.Size, Entity.Size,
		 * null); break; case 3: g.drawImage(playerTex[2], this.getX(), this.getY(),
		 * Entity.Size, Entity.Size, null); break; case 4: g.drawImage(playerTex[3],
		 * this.getX(), this.getY(), Entity.Size, Entity.Size, null); break; default:
		 * g.drawImage(playerTex[0], this.getX(), this.getY(), Entity.Size, Entity.Size,
		 * null); break; }
		 */
		g.setColor(Color.MAGENTA);
		g.fillRect(getX(), getY(), Size, Size);
	}
}
