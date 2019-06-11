import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class MapCreator {
	public static void main(String[] args){
		Panel creator = new Panel();
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e){

			}
			creator.revalidate();
			creator.repaint();
		}
	}
}

class Panel extends JFrame {
	private int maxX,maxY;
	Entity[][][] entities;
	String[] entityTypes;
	String[][] entityMeta;
	Entity[] currentEntity = new Entity[1];
	public Panel(){
		entities = new Entity[3][16][16];
		//this.entityTypes = entityTypes;
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Map Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		setLayout(new BorderLayout());
		entityMeta = new String[3][];
		//Guards
		entityMeta[0] = new String[]{"Facing","Path"};
		//LaserNode
		entityMeta[1] = new String[]{"Facing"};
		//Flooring
		entityMeta[2] = new String[]{"Facing","Image name"};

		entityTypes = new String[]{"guard","lasernode","flooring"};
		add(new EntityDisplay(this.entities,this.currentEntity),BorderLayout.CENTER);
		add(new EntitySelector(this.entityTypes,entityMeta),BorderLayout.EAST);
		setVisible(true);
	}
}

class EntityDisplay extends JPanel implements MouseListener{
	Entity[][][] entities;
	Entity[] currentEntity;
	public EntityDisplay(Entity[][][] entities,Entity[] currentEntity) {
		this.entities = entities;
		this.currentEntity = currentEntity;
		setLayout(new GridLayout(16,16));
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				ImagePanel temp = new ImagePanel(entities, currentEntity, i, j);
				add(temp);
			}
		}
		addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		ImagePanel selected = (ImagePanel) getComponentAt(e.getPoint());
		this.entities[0][selected.x][selected.y] = new GuardPath();
		System.out.println("Clicked on " + selected.x + " " + selected.y + " @ " + selected.getLocation().getX() + " " + selected.getLocation().getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

class ImagePanel extends JPanel{
	Entity[][][] entities;
	Entity[] currentEntity;
	int x;
	int y;
	public ImagePanel(Entity[][][] entities,Entity[] currentEntity,int x,int y){
		this.entities = entities;
		this.currentEntity = currentEntity;
		this.x = x;
		this.y = y;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		requestFocusInWindow();
	}

	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i][this.x][this.y] != null) {
				entities[i][this.x][this.y].drawSelf(g);
			}
		}
	}
}


class EntitySelector extends JPanel implements ActionListener{
	String[] entityTypes;
	String[][] entityMeta;
	public EntitySelector(String[] entityTypes,String[][] entityMeta) {
		this.entityTypes = entityTypes;
		this.entityMeta = entityMeta;
		setLayout(new FlowLayout());
		JComboBox comboBox = new JComboBox(entityTypes);
		comboBox.addActionListener(this);

		JPanel hey = new JPanel();

		add(comboBox);
		add(hey);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox comboBox = (JComboBox) e.getSource();
		System.out.println(comboBox.getSelectedItem());

	}
}

class GuardPath extends Entity {
	GuardPath(){
		super(0,0,0);
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(new java.awt.Color(4, 100, 0));
		System.out.println("x: " + this.x + " y: " + this.y);
		g.fillOval(this.x,this.y,40,40);
	}
}