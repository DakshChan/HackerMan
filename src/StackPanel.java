package src;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;

public class StackPanel extends JPanel{
	private HashMap<String, Component> componentMap;
	public StackPanel(LayoutManager layout) {
		this.setLayout(layout);
		componentMap = new HashMap<String, Component>();
	}
	public void addNamedComponent(Component c, String name) {
		this.add(c, name);
		componentMap.put(name,c);
	}

	public Component getComponentByName(String name) {
		if (componentMap.containsKey(name)) {
			return (Component) componentMap.get(name);
		} else
			return null;
	}
}
