import java.awt.Rectangle;

public class Entity {
	int x;
	int y;
	int facing;
	static int Size;
	Rectangle hitbox;
	Entity(int x, int y, int facing){
		this.x = x;
		this.y = y;
		this.facing = facing;
		hitbox = new Rectangle(x, y, Size, Size);
	}
}
