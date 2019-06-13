import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {
	
	public static void spilt3D(Entity[][][] input, Entity[][] mapOut, Entity[][] guardsOut) {
		for (int j = 0; j < 16; j++){
			for (int l = 0; l < 16; l++){
				mapOut[j][l] = input[0][j][l];
				guardsOut[j][l] = input[1][j][l];
			}
		}
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
			Entity[][][] entities = MapLoader.loadMap(mapName);
			spilt3D(entities, map, guards);
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

