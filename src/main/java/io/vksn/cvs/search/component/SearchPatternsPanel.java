package io.vksn.cvs.search.component;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import io.vksn.cvs.search.listeners.FileChooserActionListener;
import io.vksn.cvs.search.model.PatternModel;

public class SearchPatternsPanel extends JPanel {
	private JList<String> searchPatterns = new JList<String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchPatternsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(300, 500));
		searchPatterns.setModel(new PatternModel<String>(new ArrayList<String>()));
		searchPatterns.setPreferredSize(new Dimension(300, 400));
		JButton patternsButton = DefaultButton.withWidth("Import patterns", 200);
		JLabel patternsLabel = new JLabel("Patterns to search");
		
		JPanel addPatternPanel = new JPanel();
		addPatternPanel.setLayout(new GridLayout(1,2));
		final JTextField singlePattern = new JTextField();
		addPatternPanel.setSize(new Dimension(250, 50));
		JButton addButton = new JButton("Add pattern");
		addPatternPanel.add(singlePattern);
		addPatternPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				((PatternModel<String>)searchPatterns.getModel()).addElement(singlePattern.getText());
			}
		});
		
		patternsButton.addActionListener(
				new FileChooserActionListener<String>(null, new FileChooser("Import patterns", false)) {
					public void handleSelection() {
						File selected = getFileChooser().getSelectedFile();
						BufferedReader reader = null;
						try {
							reader = new BufferedReader(new FileReader(selected));
							String line = null;
							while ((line = reader.readLine()) != null) {
								((PatternModel<String>)searchPatterns.getModel()).addElement(line);
							}
							

						} catch (FileNotFoundException e) {
							throw new RuntimeException();
						} catch (IOException e) {
							throw new RuntimeException();
						} finally {
							if (reader != null) {
								try {
									reader.close();
								} catch (IOException e) {
								}
							}
						}
					}

				});
		
		add(patternsLabel);
		add(addPatternPanel);
		add(new JScrollPane(searchPatterns));
		add(patternsButton);
	}
	
	public List<String> getPatterns() {
		PatternModel<String> patterns  = (PatternModel<String>)searchPatterns.getModel();
		return patterns.getAll();
	}
}
