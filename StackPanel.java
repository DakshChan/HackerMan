package src;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;

public class StackPanel extends JPanel{
	private HashMap<String, Component> componentMap;
	/**
	 * @param layout, layout of the panel
	 */
	public StackPanel(LayoutManager layout) {
		this.setLayout(layout);
		componentMap = new HashMap<String, Component>();
	}
	/**
	 * adds a component to the panel and also stores a string associated with it
	 * @param c, component to be added
	 * @param name, name associated with component
	 */
	public void addNamedComponent(Component c, String name) {
		this.add(c, name);
		componentMap.put(name,c);
	}

	/**
	 * Takes a string and returns a component with that name
	 * @param name, a string of the name associated with a component
	 * @return, a component with the associated name if possible, null if not
	 */
	public Component getComponentByName(String name) {
		if (componentMap.containsKey(name)) {
			return (Component) componentMap.get(name);
		} else
			return null;
	}
}
