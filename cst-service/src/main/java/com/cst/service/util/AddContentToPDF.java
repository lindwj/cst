package com.cst.service.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AddContentToPDF {

	public static void addPicToPdf(String sourceFile, String targetFile) {

		PdfReader reader=null;
		PdfStamper stamper=null;
		try {
		URL fileUrl = AddContentToPDF.class.getResource("");
		Image image = Image.getInstance(fileUrl.getPath()+"/head.png");
			
		reader = new PdfReader(sourceFile);
		stamper = new PdfStamper(reader,
				new FileOutputStream(targetFile));

//		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // set font
//		Font font = new Font(bf, 10);
//		font.setStyle(Font.BOLD);
//		font.getBaseFont();
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {

			PdfContentByte over = stamper.getOverContent(i);
			PdfDictionary p = reader.getPageN(i);
			PdfObject po = p.get(new PdfName("MediaBox"));
			PdfArray pa = (PdfArray) po;

//			Rectangle rtl = over.getPdfDocument().getPageSize();
			

			if (pa.getAsNumber(pa.size() - 1).floatValue() < 600) {

				image.setAbsolutePosition(over.getPdfDocument().left() - 25, over.getPdfDocument().top() - 250);// 0, 0,
																												// 841.92,
																												// 595.32
			}else {
				image.setAbsolutePosition(over.getPdfDocument().left() - 25, over.getPdfDocument().top());// 0, 0, 841.92,
			}

			over.addImage(image);

		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stamper!=null) {
				stamper.close();
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(reader!=null) {
			reader.close();	
			}
		}
		
	}

	public static void main(String[] args) throws IOException, DocumentException {

		PdfReader reader=null;
		PdfStamper stamper=null;
		try {
			
		reader = new PdfReader("F:/test/aspose中华人民共和国企业所得税年度纳税申报表（A类，2014年版）-xls.pdf");
		stamper = new PdfStamper(reader,
				new FileOutputStream("F:/test/aspose中华人民共和国企业所得税年度纳税申报表（A类，2014年版）-xls-head.pdf"));

//		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // set font
//		Font font = new Font(bf, 10);
//		font.setStyle(Font.BOLD);
//		font.getBaseFont();
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {

			PdfContentByte over = stamper.getOverContent(i);
			PdfDictionary p = reader.getPageN(i);
			PdfObject po = p.get(new PdfName("MediaBox"));
			PdfArray pa = (PdfArray) po;

//			Rectangle rtl = over.getPdfDocument().getPageSize();
			Image image = Image.getInstance("./head.png");
																										// 595.32

			if (pa.getAsNumber(pa.size() - 1).floatValue() < 600) {

				image.setAbsolutePosition(over.getPdfDocument().left() - 25, over.getPdfDocument().top() - 250);// 0, 0,
																												// 841.92,
																												// 595.32
			}else {
				image.setAbsolutePosition(over.getPdfDocument().left() - 25, over.getPdfDocument().top());// 0, 0, 841.92,
			}

			over.addImage(image);

		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(stamper!=null) {
			stamper.close();
			}
			if(reader!=null) {
			reader.close();	
			}
		}

		

	}
}