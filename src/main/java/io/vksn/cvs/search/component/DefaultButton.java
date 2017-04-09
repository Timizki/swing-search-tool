package io.vksn.cvs.search.component;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;

public class DefaultButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultButton(String name) {
		this(name, 100,30);
	}
	
	public DefaultButton(String name, int width, int height) {
		super(name);
		setLayout(new GridLayout());
		setPreferredSize(new Dimension(width,height));
	}
	
	
	public static DefaultButton withWidth(String name, int width) {
		return new DefaultButton(name, width, 30);
	}
}
