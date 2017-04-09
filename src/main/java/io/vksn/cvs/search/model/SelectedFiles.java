package io.vksn.cvs.search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class SelectedFiles extends DefaultListModel<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<File> filesToWorkWith = new ArrayList<File>();
	
	public SelectedFiles(File[] files) {
			for(File file : files) {
				filesToWorkWith.add(file);
				super.addElement(file.getName());
			}
	}

	@Override
	public String remove(int index) {
		filesToWorkWith.remove(index);
		return super.remove(index);
	}
	
	public List<File> getFiles() {
		return this.filesToWorkWith;
	}

}
