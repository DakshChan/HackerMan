package src;

import java.awt.CardLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardTest {

	public static final String MAINPANEL = "Main Panel";
	public static final String SECONDPANEL = "Secondary Panel";

	public static void addThings(JFrame frame, JPanel cardStack) {
		JPanel mainCard = new JPanel();
		JPanel secondCard = new JPanel();
		mainCard.add(new JButton("Yeet"));
		secondCard.add(new TextArea());
		cardStack.add(mainCard, MAINPANEL);
		cardStack.add(secondCard, SECONDPANEL);
		frame.add(cardStack);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout savedLayout = new CardLayout();
		JPanel cardStack = new JPanel(savedLayout);
		addThings(frame, cardStack);
		frame.pack();
		frame.setVisible(true);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			savedLayout.next(cardStack);
		}
	}

}
