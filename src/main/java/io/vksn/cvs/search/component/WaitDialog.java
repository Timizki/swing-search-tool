package io.vksn.cvs.search.component;

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaitDialog extends JDialog implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WaitDialog(Frame owner) {
		super(owner, "Please wait", false);
		setSize(300, 150);	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(new JLabel("Running search, please wait"));
		add(panel);
	
	}

	@Override
	public void run() {
		setVisible(true);
	}
	
	

}
