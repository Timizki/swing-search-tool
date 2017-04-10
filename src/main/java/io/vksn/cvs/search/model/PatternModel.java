package io.vksn.cvs.search.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class PatternModel<T> extends DefaultListModel<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PatternModel(List<T> data) {
		for(T t : data) {
			super.addElement(t);
		}
	}
	
	public List<T> getAll() {
		List<T> data = new ArrayList<T>();
		for(int i = 0; i < super.getSize(); i++) { 
			data.add(super.getElementAt(i));
		}
		return data;
		
	}
}
