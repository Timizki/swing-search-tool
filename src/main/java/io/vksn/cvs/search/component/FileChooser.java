package io.vksn.cvs.search.component;

import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String approveButtonText = "Approve";
	
	public FileChooser(String approveText, boolean useMultiSelect) {
		this.approveButtonText = approveText;
		setMultiSelectionEnabled(useMultiSelect);
	}
	
	public String getApproveText() {
		return this.approveButtonText;
	}

}
