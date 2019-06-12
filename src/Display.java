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
		Guard[] guards = new Guard[8];
		Pair[] path = new Pair[4];
		path[0] = new Pair(x,y);
		path[1] = new Pair(x,y-5);
		path[2] = new Pair(x-5,y-5);
		path[3] = new Pair(x-5,y-1);
		guards[0] = new Guard(x,y,0,path);
		map[7][0] = new LaserNode(7, 0, 0);
		map[0][0] = new LaserNode(0, 0, 0);
		map[0][7] = new LaserNode(0, 7, 0);
		map[7][13] = new LaserNode(7, 13, 0);
		map[4][7] = new Terminal(4, 7, 0, 0);
		map[7][7] = new WarpTile(7, 7, 0, str2, 1,1);
		MapPanel mainMap = new MapPanel(map, guards,cardStack);
		mainMap.player.x = 2*Entity.Size;
		mainMap.player.y = Entity.Size;
		MapPanel second = new MapPanel(empty,guards,cardStack);
		cardStack.addNamedComponent(mainMap, str1);
		cardStack.addNamedComponent(second, str2);
		window.add(cardStack);
		window.pack();
		window.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((WarpTile) map[7][7]).warp = true;
		mainMap.updateMap(map);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//savedLayout.show(cardStack, str1);
		//mainMap.requestFocusInWindow();
		//((LaserNode)map[7][0]).hacked = true;
		//mainMap.updateMap(map);
		 
	}
}

class GameWindow extends JFrame {
	GameWindow() {
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
	}
}

