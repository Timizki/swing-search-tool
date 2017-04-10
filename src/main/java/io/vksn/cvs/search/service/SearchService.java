package io.vksn.cvs.search.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchService {
	
	public Map<String, List<String>> search(List<File> from, List<String> patterns) {
		Map<String, List<String>> result = new HashMap<>();
		for(File file : from) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				Map<String, List<String>> subset = hasMatches(reader, patterns);
				for(String p : subset.keySet()) {
					if(result.containsKey(p)) {
						result.get(p).addAll(subset.get(p));
					}
					else {
						result.put(p, subset.get(p));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		return result;
	}
	
	private Map<String, List<String>> hasMatches(BufferedReader reader, List<String> patterns) throws IOException {
		Map<String, List<String>> matches = new HashMap<>();
		String line = null;
		
		while((line = reader.readLine()) != null) {
			if("".equals(line.trim())) {
				continue;
			}
			for(String pattern : patterns) {
				if("".equals(pattern.trim())) {
					continue;
				}
				if(line.toLowerCase().contains(pattern.toLowerCase())) {
					if(!matches.containsKey(pattern)) {
						matches.put(pattern, new ArrayList<String>());
					}
					matches.get(pattern).add(line);
				}
			}
		}
		return matches;
	}

}
