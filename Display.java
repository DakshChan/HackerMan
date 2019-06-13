package src;

import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {
	
	public static void spilt3D(Entity[][][] input, Entity[][] mapOut, Entity[][] guardsOut) {
		mapOut = input[0];
		guardsOut = input[1];
	}
	
	public static void main(String[] args) throws Exception {
		GameWindow window = new GameWindow();
		CardLayout savedLayout = new CardLayout();
		StackPanel cardStack = new StackPanel(savedLayout);
		Entity.Size = Toolkit.getDefaultToolkit().getScreenSize().height/16;
		Player.bound = Toolkit.getDefaultToolkit().getScreenSize().height;
		for(int i = 0; i < 2; i ++) {
			String mapName = "map" + (i + "");
			Entity[][] map = new Entity[16][16];
			Entity[][] guards = new Entity[16][16];
			spilt3D(MapLoader.loadMap(mapName), map, guards);
			MapPanel m = new MapPanel(mapName, map, guards, cardStack);
			cardStack.addNamedComponent(m, mapName);
		}
		SnakePanel minigame = new SnakePanel();
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

