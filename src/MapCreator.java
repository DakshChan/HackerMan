import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;
import java.util.Map;

public class MapCreator {
	public static void main(String[] args){
		Panel creator = new Panel();
	}
}

class Panel extends JFrame {
	private int maxX,maxY;
	Entity[][][] entities;
	String[] entityTypes;
	public Panel(String[] entityTypes){
		entities = new Entity[3][30][30];
		this.entityTypes = entityTypes;
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Map Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		setLayout(new BorderLayout());
		add(new EntityDisplay(this.entities),BorderLayout.CENTER);
		add(new EntitySelector(this.entityTypes),BorderLayout.SOUTH);
	}
}

class EntityDisplay extends JFrame {
	Entity[][][] entities;
	public EntityDisplay(Entity[][][] entities) {
		this.entities = entities;
		setLayout(new GridLayout(30,30));
		for (int i = 0; i < 30; i++){
			for (int j = 0; j < 30; j++){
				//Image[] temp = new Image[0];
				add(new testImagePanel(new Entity[]{this.entities[0][i][j],this.entities[1][i][j],this.entities[2][i][j]}));
			}
		}
	}
}

class testImagePanel extends JPanel implements EventListener {
	Entity[] entities;
	public testImagePanel(Entity[] entities){
		this.entities = entities;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.entities[0] instanceof LaserBeam){
			setBackground(new java.awt.Color(103,7,0));
		} else if (this.entities[0] instanceof LaserNode){
			setBackground(new java.awt.Color(103, 0, 81));
		}
		if (this.entities[1] != null){
			g.setColor(new java.awt.Color(0,0,100));
			g.fillRect(0,0,40,40);
		}
		if (this.entities[2] != null){
			g.setColor(new java.awt.Color(4, 100, 0));
			g.fillOval(0,0,40,40);
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