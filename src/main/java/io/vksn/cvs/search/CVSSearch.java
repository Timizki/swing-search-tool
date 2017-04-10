package io.vksn.cvs.search;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.vksn.cvs.search.component.DefaultButton;
import io.vksn.cvs.search.component.FilesPanel;
import io.vksn.cvs.search.component.ResultsDialog;
import io.vksn.cvs.search.component.SearchPatternsPanel;
import io.vksn.cvs.search.component.WaitDialog;
import io.vksn.cvs.search.listeners.WindowClosingListener;
import io.vksn.cvs.search.service.SearchService;

public class CVSSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClassPathXmlApplicationContext context;
	private FilesPanel filesPanel;
	private final SearchPatternsPanel patternsPanel;

	public CVSSearch() {
		super("CVS search");
		setSize(625, 600);
		context = new ClassPathXmlApplicationContext("config/application-context.xml");
		filesPanel = new FilesPanel();
		patternsPanel = new SearchPatternsPanel();
		addWindowListener(new WindowClosingListener());
		JPanel root  =new JPanel();
		root.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		JButton searchButton = DefaultButton.withWidth("Search", 85);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				SearchService service = context.getBean(SearchService.class);
				
				WaitDialog waitDialog = new WaitDialog(CVSSearch.this);
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						waitDialog.run();
					}
				});
				
				thread.run();
				Map<String,List<String>> results = service.search(filesPanel.getFileList(), patternsPanel.getPatterns());
				waitDialog.setVisible(false);
				
				ResultsDialog diag = new ResultsDialog(results);
				diag.setAlwaysOnTop(true);
				diag.setVisible(true);
			}
		});

		mainPanel.add(filesPanel);
		mainPanel.add(patternsPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 50));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(searchButton);
		root.add(mainPanel);
		root.add(buttonPanel);
		this.add(root);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CVSSearch();
	}

}
