package io.vksn.cvs.search.component;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SmallResultDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmallResultDialog(Map<String, List<String>> results) {		
		setSize(new Dimension(300, 600));
		JPanel resultPanel = new JPanel();
		BoxLayout layout = new BoxLayout(resultPanel, BoxLayout.Y_AXIS);
		resultPanel.setLayout(layout);
		JPanel lists = new JPanel();

		BoxLayout layoutB = new BoxLayout(lists, BoxLayout.X_AXIS);
		lists.setLayout(layoutB);

		if (results.keySet().isEmpty()) {
			resultPanel.add(new JLabel("No Matches found!"));
		} else {
			resultPanel.add(new JLabel(String.format("Found matches for %s patterns", results.keySet().size())));
		}
		
		for(String pattern : results.keySet()) {
			JPanel resultset = new JPanel();
			BoxLayout boxl = new BoxLayout(resultset, BoxLayout.Y_AXIS);
			resultset.setLayout(boxl);
			
			resultset.add(new JLabel(pattern));
			DefaultListModel<String> resultModel = new DefaultListModel<>();
			JList<String> resultList = new JList<>();
			JScrollPane resultScroll = new JScrollPane(resultList);
			resultScroll.setSize(new Dimension(200, 500));
			for(String match : results.get(pattern)) {
				resultModel.addElement(match);
			}
			resultList.setModel(resultModel);
			resultset.add(resultScroll);
			
			lists.add(resultset);
		}	

		resultPanel.add(lists);
		add(resultPanel);
	}

}
