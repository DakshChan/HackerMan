import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Guard extends Obstacle{
	Rectangle sightLine;
	ArrayList<Pair> path;
	static Image[] guardTex;
	Guard(int x, int y, int facing, int size, ArrayList<Pair> path) {
		super(x, y, facing, size);
		this.path = path;
	}
	@Override
	public void killPlayer(){
		//both the guard hitbox and sight hitbow will kill player
	}
	public Pair findDist(Pair target) {
		return new Pair(target.x - this.x, target.y - this.y);
	}
	public void followPath() {
		
	}
	public void setSightLine() {
		
	}
}
