package io.vksn.cvs.search.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import io.vksn.cvs.search.service.PdfService;

public class ResultsDialog extends JDialog {

	private Map<String, List<String>> data = new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultsDialog(Map<String, List<String>> results) {
		this.data = results;
		setSize(new Dimension(600, 600));

		JPanel rootPanel = new JPanel();
		BoxLayout rootLayout = new BoxLayout(rootPanel, BoxLayout.X_AXIS);
		rootPanel.setLayout(rootLayout);
		JPanel patternPanel = new JPanel();
		patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.Y_AXIS));
		patternPanel.add(new JLabel("Matched patterns"));

		JList<String> patternList = new JList<>();
		DefaultListModel<String> patternModel = new DefaultListModel<>();
		JScrollPane patternScroll = new JScrollPane(patternList);
		for (String key : data.keySet()) {
			patternModel.addElement(key);
		}
		patternList.setModel(patternModel);
		patternPanel.add(patternScroll);
		rootPanel.add(patternPanel);

		JPanel hitsPanel = new JPanel();
		hitsPanel.setLayout(new BoxLayout(hitsPanel, BoxLayout.Y_AXIS));
		hitsPanel.add(new JLabel("Matched lines"));
		JList<String> hitsList = new JList<>();
		final DefaultListModel<String> hitsModel = new DefaultListModel<>();
		JScrollPane hitsScroll = new JScrollPane(hitsList);
		hitsPanel.add(hitsScroll);
		rootPanel.add(hitsPanel);

		patternList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) e.getSource();

				String key = list.getSelectedValue();
				if (key != null) {
					hitsModel.clear();
					for (String hit : data.get(key)) {
						hitsModel.addElement(hit);
					}
					hitsList.setModel(hitsModel);
				}
			}
		});
		JButton exportButton = new JButton("Export PDF to Desktop");
		exportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PdfService().generatePDF(data);
			}
		});
		rootPanel.add(exportButton);
		add(rootPanel);
	}

}
