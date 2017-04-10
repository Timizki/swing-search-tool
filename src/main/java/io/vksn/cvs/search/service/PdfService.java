package io.vksn.cvs.search.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	public void generatePDF(Map<String, List<String>> data) {
		Document docu = new Document();
		try {
			PdfWriter.getInstance(docu, new FileOutputStream(new File(System.getProperty("user.home"), "desktop/exported-hits.pdf")));
			docu.open();
			Font title = new Font();
			title.setSize(16);
			title.setStyle(Font.UNDERLINE);			
			title.setColor(BaseColor.RED);

			Font defaultFont = new Font();
			defaultFont.setSize(10);
			defaultFont.setStyle(Font.NORMAL);

			for (Entry<String, List<String>> entry : data.entrySet()) {
				Paragraph p = new Paragraph(entry.getKey(),title);
				
				docu.add(p);
				for (String hit : entry.getValue()) {
					Paragraph hitp = new Paragraph(hit, defaultFont);
					hitp.setIndentationLeft(25);
					docu.add(hitp);
				}
			}

		} catch (DocumentException | FileNotFoundException e) {

		}
		docu.close();
	}

}
