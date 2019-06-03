import javax.swing.*;
import java.awt.*;

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
	Entity[][] entities;
	String[] entityTypes;
	public EntityDisplay(Entity[][] entities, String[] entityTypes) {
		this.entities = entities;
		this.entityTypes = entityTypes;
		setLayout(new GridLayout(30,30));

	}
}

class EntitySelector extends JPanel {
	Entity[][] entities;
	public EntitySelector(Entity[][] entities,String[] ) {
		this.entities = entities;
		setLayout(new FlowLayout());

	}
}