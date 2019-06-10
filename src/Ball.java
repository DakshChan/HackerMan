import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	double x;
	double xSpeed;
	double y; 
	double ySpeed;
	int radius;
	Rectangle hitbox;
	
	public Ball() {
		x = 0;
		y = 0;
		xSpeed = 1.0;
		ySpeed = 1.0;
		radius = 50;
		hitbox = new Rectangle(0,0,50,50);
	}
	
	public Ball(int x, int y, double xSpeed, double ySpeed, int radius) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.radius = radius;
		hitbox = new Rectangle(x,y,radius,radius);
	}

	public void update(double time, Ball[]balls) {
		if(x+radius > 800) {
			x = 800 - radius;
			bounceX();
		}
		else if(x < 0) {
			x = 0;
			bounceX();
		}
		if(y+radius > 600) {
			y = 600 - radius;
			bounceY();
		}
		else if( y < 0) {
			y = 0;
			bounceY();
		}
		collideCheck(balls);
		x += xSpeed*time;
		y += ySpeed*time;
		hitbox.x = (int)x;
		hitbox.y = (int)y;
	}
	public void collideCheck(Ball[]balls) {
		for(int c = 0; c < balls.length; c++) {
			if(this.hitbox.intersects(balls[c].hitbox) && balls[c] != this) {
				this.bounceX();
				this.bounceY();
				balls[c].bounceX();
				balls[c].bounceY();
			}
		}
	}
	public void bounceX() {
		xSpeed = -xSpeed;
	}
	public void bounceY() {
		ySpeed = -ySpeed;
	}
	public void drawSelf(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)x, (int)y, radius, radius);
	}
}
