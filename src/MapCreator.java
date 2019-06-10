import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapCreator {
	public static void main(String[] args){
		Panel creator = new Panel();
		while(true){
			try {
				Thread.sleep(1000);
			} catch (Exception e){

			}
		}
	}
}

class Panel extends JFrame {
	private int maxX,maxY;
	Entity[][][] entities;
	String[] entityTypes;
	String[] entityMeta;
	public Panel(){
		entities = new Entity[3][30][30];
		//this.entityTypes = entityTypes;
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Map Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		setLayout(new BorderLayout());
		add(new EntityDisplay(this.entities,entityMeta),BorderLayout.CENTER);
		add(new EntitySelector(this.entityTypes,entityMeta),BorderLayout.SOUTH);
		setVisible(true);
	}
}

class EntityDisplay extends JPanel {
	Entity[][][] entities;
	String[] entityMeta;
	public EntityDisplay(Entity[][][] entities,String[] entityMeta) {
		this.entities = entities;
		setLayout(new GridLayout(30,30));
		for (int i = 0; i < 30; i++){
			for (int j = 0; j < 30; j++){
				add(new testImagePanel(new Entity[]{this.entities[0][i][j],this.entities[1][i][j],this.entities[2][i][j]},this.entityMeta));
			}
		}
	}
}

class testImagePanel extends JPanel implements ActionListener, MouseListener {
	Entity[] entities;
	String[] entityMeta;
	public testImagePanel(Entity[] entities,String[] entityMeta){
		this.entities = entities;
		this.entityMeta = entityMeta;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Entity ent:entities) {
			ent.drawSelf(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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


class EntitySelector extends JPanel {
	String[] entityTypes;
	String[] entityMeta;
	public EntitySelector(String[] entityTypes,String[] entityMeta) {
		this.entityTypes = entityTypes;
		this.entityMeta = entityMeta;
		setLayout(new FlowLayout());
	}
}

class GuardPath extends Entity {
	GuardPath(){
		super(0,0,0);
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(new java.awt.Color(4, 100, 0));
		g.fillOval(0,0,40,40);
	}
}