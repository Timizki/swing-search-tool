package io.vksn.cvs.search.component;

import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.vksn.cvs.search.listeners.FileChooserActionListener;
import io.vksn.cvs.search.model.SelectedFiles;

public class FilesPanel extends JPanel {

	private JList<String> fileList = new JList<String>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FilesPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Files to search patterns"));
		fileList.setModel(new SelectedFiles(new File[0]));
		JButton selectCVS = DefaultButton.withWidth("Import CVS", 150);
		selectCVS.addActionListener(new FileChooserActionListener<String>(null, new FileChooser("Import", true)) {
			public void handleSelection() {
				File[] selectedFiles = getFileChooser().getSelectedFiles();
				fileList.setModel(new SelectedFiles(selectedFiles));
			}

		});
		
		add(new JScrollPane(fileList));
		add(selectCVS);
	}
	public List<File> getFileList() {
		SelectedFiles files = (SelectedFiles)fileList.getModel();
		return files.getFiles();
	}

}
