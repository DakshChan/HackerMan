package src;

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
		this.interactBox = new Rectangle();
	}
	public void update(Set<Character>pressed, Entity[][]map, Entity[][]guards) {
		this.move(pressed);
		this.setInteractBox();
		if(pressed.contains('e')) {
			this.interact(map, guards);
		}
	}
	public void move(Set<Character>pressed) {
		if(getX()+Size > bound) {
			setX(bound - Size);
			xSpeed = 0;
		}
		else if(getX() < 0) {
			setX(0);
			xSpeed = 0;
		}
		else if(pressed.contains('a')) {
			xSpeed = -Size/8;
			this.setFacing(4);
		}
		else if(pressed.contains('d')) {
			xSpeed = Size/8;
			this.setFacing(2);
		}
		else {
			xSpeed = 0;
		}
		if(getY()+Size > bound) {
			setY(bound - Size);
			ySpeed = 0;
		}
		else if(getY() < 0) {
			setY(0);
			ySpeed = 0;
		}
		else if(pressed.contains('w')) {
			ySpeed = -Size/8;
			this.setFacing(1);
		}
		else if(pressed.contains('s')) {
			ySpeed = Size/8;
			this.setFacing(3);
		}
		else {
			ySpeed = 0;
		}
		setX((int)(getX() + xSpeed));
		setY((int)(getY() + ySpeed));
		getHitbox().x = this.getX();
		getHitbox().y = this.getY();
	}
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
	public void interact(Entity[][] map, Entity[][]guards) {
		for(int vc = 0; vc < map.length; vc ++) {
			for(int hc = 0; hc < map[0].length; hc ++) {
				if (map[hc][vc] instanceof Terminal && ((Terminal) map[hc][vc]).getHacked() == false && this.interactBox.intersects(map[hc][vc].getHitbox())) {
					if (((Terminal) map[hc][vc]).getType() == 2) {
						((Terminal) map[hc][vc]).hack(guards);
					}
					else {
						((Terminal) map[hc][vc]).hack(map);
					}
				}
				if(map[hc][vc] instanceof WarpTile && this.getHitbox().intersects(map[hc][vc].getHitbox())) {
					((WarpTile)map[hc][vc]).setWarp(true);
				}
			}
		}
	}
	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int)getX(), (int)getY(), Size, Size);
		g.setColor(Color.YELLOW);
		g.fillRect(interactBox.x, interactBox.y, interactBox.width, interactBox.height);
	}

}
