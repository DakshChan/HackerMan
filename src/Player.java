package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Set;

public class Player {
	double x;
	double xSpeed;
	double y; 
	double ySpeed;
	int radius;
	Rectangle hitbox;
	
	public Player() {
		x = 500;
		y = 400;
		xSpeed = 0.0;
		ySpeed = 0.0;
		radius = 20;
		hitbox = new Rectangle(0,0,20,20);
	}
	public void update(double time, Ball[]balls, Set<Character>pressed) {
		collideCheck(balls,pressed);
		if(x+radius > 800) {
			x = 800 - radius;
			this.xSpeed = 0;
		}
		else if(x < 0) {
			x = 0;
			this.xSpeed = 0;
		}
		else if(pressed.contains('a')) {
			xSpeed = -0.3;
		}
		else if(pressed.contains('d')) {
			xSpeed = 0.3;
		}
		else {
			this.xSpeed = 0;
		}
		if(y+radius > 600) {
			y = 600 - radius;
			this.ySpeed = 0;
		}
		else if(y < 0) {
			y = 0;
			this.ySpeed = 0;
		}
		else if(pressed.contains('w')) {
			ySpeed = -0.3;
		}
		else if(pressed.contains('s')) {
			ySpeed = 0.3;
		}
		else {
			this.ySpeed = 0;
		}
		
		x += xSpeed*time;
		y += ySpeed*time;
		hitbox.x = (int)x;
		hitbox.y = (int)y;
	}
	//ignore this part
//	public void collideCheck(Ball[]balls, Set<Character>pressed) {
//		for(int c = 0; c < balls.length; c++) {
//			if(this.hitbox.intersects(balls[c].hitbox)) {
//				pressed.clear();
//				balls[c].bounceX();
//				balls[c].bounceY();
//			}
//		}
//	}
	public void drawSelf(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval((int)x, (int)y, radius, radius);
	}

}
