public class Obstacle extends Entity{
	boolean hacked;
	Obstacle(int x, int y, int facing, int size) {
		super(x, y, facing, size);	
		hacked = false;
	}
	public void killPlayer() {
		//kills player if hitbox intersects
	}
}
