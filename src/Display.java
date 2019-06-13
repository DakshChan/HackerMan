import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {
	
	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		CardLayout savedLayout = new CardLayout();
		StackPanel cardStack = new StackPanel(savedLayout);
		Entity.Size = Toolkit.getDefaultToolkit().getScreenSize().height/16;
		Player.bound = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = 6;
		int y = 6;
		String str1 = "map1";
		String str2 = "map2";
		Entity[][] map = new Entity[16][16];
		Entity[][] empty = new Entity[16][16];
		Entity[][] actuallyempty = new Entity[16][16];
		Entity[][] guards = new Entity[16][16];
		Pair[] path = new Pair[4];
		path[0] = new Pair(x,y);
		path[1] = new Pair(x,y-5);
		path[2] = new Pair(x-5,y-5);
		path[3] = new Pair(x-5,y-1);
		guards[0][0] = new Guard(x,y,0,path);
		map[7][0] = new LaserNode(7, 0, 0);
		map[0][0] = new LaserNode(0, 0, 0);
		map[0][7] = new LaserNode(0, 7, 0);
		map[7][13] = new LaserNode(7, 13, 0);
		map[9][0] = new Terminal(9, 0, 0, 2, 1);
		map[7][7] = new WarpTile(7, 7, 0, str2, 1,1);
		empty[12][11] = new WarpTile(12, 11, 0, str1, 7,7);
		empty[9][0] = new Terminal(9, 0, 0, 2, 3);
		MapPanel mainMap = new MapPanel(str1, map, guards,cardStack);
		mainMap.getPlayer().setX(2*Entity.Size);
		mainMap.getPlayer().setY(Entity.Size);
		MapPanel second = new MapPanel(str2, empty, actuallyempty,cardStack);
		SnakePanel minigame = new SnakePanel();
		cardStack.addNamedComponent(mainMap, str1);
		cardStack.addNamedComponent(second, str2);
		cardStack.addNamedComponent(minigame, "minigame");
		window.add(cardStack);
		window.pack();
		window.setVisible(true);
	}
}

class GameWindow extends JFrame {
	GameWindow() {
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
	}
}

