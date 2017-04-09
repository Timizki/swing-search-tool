package io.vksn.cvs.search.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import io.vksn.cvs.search.component.FileChooser;

public abstract class FileChooserActionListener<T> implements ActionListener {

	private Component parent;
	private FileChooser chooser;

	public FileChooserActionListener(Component parent, FileChooser chooser) {
		this.parent = parent;
		this.chooser = chooser;
	}

	public void actionPerformed(ActionEvent e) {
		int result = chooser.showDialog(parent, chooser.getApproveButtonText());
		if (result == JFileChooser.APPROVE_OPTION) {
			handleSelection();
		}

	}

	public FileChooser getFileChooser() {
		return this.chooser;
	}

	public abstract void handleSelection();
}
