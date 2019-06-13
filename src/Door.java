import java.awt.Graphics;
import java.awt.Image;

public class Door extends Entity{
	static Image[] doorTex;
	boolean locked;

	Door(int x, int y, int facing) {
		super(x, y, facing);
		this.locked = false;
	}
	Door(int x, int y, int facing, boolean locked) {
		super(x, y, facing);
		this.locked = locked;
	}
	@Override
	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
