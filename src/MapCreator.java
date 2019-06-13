import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.invoke.ConstantCallSite;

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
	public Panel(){
		entities = new Entity[3][16][16];
		//this.entityTypes = entityTypes;
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Map Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		Entity.Size = maxY/16;
		setResizable(false);
		setLayout(new BorderLayout());
		EntitySelector selector = new EntitySelector();
		add(selector,BorderLayout.EAST);

		String[][][] output = new String[1][16][16];
		for (int i = 0; i < output.length; i++){
			for (int j = 0; j < output.length; j++){
				output[0][i][j] = "null";
			}
		}

		add(new EntityDisplay(this.entities,selector,output),BorderLayout.CENTER);

		setVisible(true);

	}
}

class EntityDisplay extends JPanel implements MouseListener{
	Entity[][][] entities;
	String[][][] output;
	EntitySelector selector;
	public EntityDisplay(Entity[][][] entities,EntitySelector selector,String[][][] output) {
		this.entities = entities;
		this.selector = selector;
		this.output = output;
		setLayout(new GridLayout(16,16));
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				ImagePanel temp = new ImagePanel(entities, i, j);
				add(temp);
			}
		}
		addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		ImagePanel selected = (ImagePanel) getComponentAt(e.getPoint());
		System.out.println("Clicked on " + selected.x + " " + selected.y + " @ " + selected.getLocation().getX() + " " + selected.getLocation().getY());
		if (!selector.getText().equals("guard")) {
			this.entities[0][selected.x][selected.y] = MapLoader.entityParser(selector.getText(), 0, 0);
			this.output[0][selected.x][selected.y] = selector.getText();
		} else {
			this.entities[1][selected.x][selected.y] = new GuardPath();
		}

		System.out.println("\n\n\n\n\n");
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 16; j++){
				System.out.print(output[0][i][j]+" ");
			}
			System.out.println();
		}
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
	int x;
	int y;
	public ImagePanel(Entity[][][] entities,int x,int y){
		this.entities = entities;
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


class EntitySelector extends JPanel{
	JTextField textField;
	JLabel options;
	public EntitySelector() {
		setLayout(new GridLayout(8,1));
		textField = new JTextField(40);
		add(textField);
		options = new JLabel("terminal");
		add(options);
		options = new JLabel("lasernode");
		add(options);
		options = new JLabel("warp/mapname/connected x/connected y");
		add(options);
		options = new JLabel("wall");
		add(options);
		options = new JLabel("null");
		add(options);
		options = new JLabel("guard");
		add(options);
		//add();
	}
	public String getText(){
		return textField.getText();
	}
}


class GuardPath extends Entity {
	GuardPath(){
		super(0,0,0);
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(new java.awt.Color(4, 100, 0));
		g.fillOval(this.getX(),this.getY(),40,40);
	}
}
