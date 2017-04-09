package io.vksn.cvs.search.component;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ResultDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResultDialog(List<String> results) {
		setSize(new Dimension(300,600));
		add(new JLabel("asdf"));
	}

}
