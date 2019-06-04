import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapCreator {
	public static void main(String[] args){
		Panel creator = new Panel();
	}
}

class Panel extends JFrame {
	private int maxX,maxY;
	Entity[][] entities;
	String[] entityTypes;
	public Panel(Entity[][] entities,String[] entityTypes){
		this.entities = entities;
		this.entityTypes = entityTypes;
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Map Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		setLayout(new BorderLayout());
		//add(button,BorderLayout.CENTER);
		add(new EntitySelector(this.entities),BorderLayout.SOUTH);
	}
}

class EntityDisplay extends JFrame {
	Entity[][][] entities = new Entity[3][30][30];
	String[] entityTypes;
	public EntityDisplay(String[][][] entities) {
		setLayout(new GridLayout(30,30));
		for (int i = 0; i < 30; i++){
			for (int j = 0; j < 30; j++){
				Image[] temp = new Image[0];
				for (int k = 0; k < 3; k++) {
					this.entities[k][i][j] = MapLoader.entityParser(entities[i][j][k],0,0);

					add(new ImagePanel());
				}
			}
		}
	}
}

class ImagePanel extends JPanel {
	Image[] images;
	public ImagePanel(Image[] images){
		this.images = images;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.images.length; i++){
			g.drawImage(images[i], 0, 0, this);
		}
	}
}

class EntitySelector extends JPanel {
	String[] entityTypes;
	public EntitySelector(String[] entityTypes) {
		this.entityTypes = entityTypes;
		setLayout(new FlowLayout());

	}
}

class GuardPath extends Entity {
	GuardPath(){
		super(0,0,0);
	}
}