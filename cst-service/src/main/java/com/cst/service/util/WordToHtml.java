package com.cst.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 * word ת����html
 */
public class WordToHtml {
	
	public static void main(String[] args) { 

//		 Word2003ToHtml("C:/Users/Administrator/Downloads/","内蒙古国税局.doc","F:/test/","内蒙古国税局-130.doc.html");
		 
		 Word2007ToHtml("C:/Users/Administrator/Downloads/","全套报表模板-word版本.docx","F:/test/","全套报表模板-word版本-130.docx.html");
  }
    
	
	//2007word 转 html
    public static void Word2007ToHtml(String sourceFilePath,String sourceFileName,String targetFilePath,String targetFileName) {
    	InputStream in=null;
    	OutputStream out=null;
    	
    	try {
    	
    	File f = new File(sourceFilePath + sourceFileName);  
        if (!f.exists()) {  
            System.out.println("Sorry File does not Exists!");  
        } else {  
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {  
                  
                //document
                in = new FileInputStream(f);  
                XWPFDocument document = new XWPFDocument(in);  
  
                //图片 
                File imageFolderFile = new File(targetFilePath);  
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));  
                options.setExtractor(new FileImageExtractor(imageFolderFile));  
                options.setIgnoreStylesIfUnused(false);  
                options.setFragment(true);  
                  
                //写目标文件 
                out = new FileOutputStream(new File(targetFilePath + targetFileName));  
                XHTMLConverter.getInstance().convert(document, out, options);  
                
                //Ҳ����ʹ���ַ���������ȡ����������
//                ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
//                XHTMLConverter.getInstance().convert(document, baos, options);  
//                String content = baos.toString();
//                System.out.println(content);
//                 baos.close();
            } else {  
                System.out.println("Enter only MS Office 2007+ files");  
            }  
        }  
        
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {  
            try {  
                if (in != null)  
                    in.close();  
                if (out != null)  
                    out.close();  
            } catch (IOException ie) {  
            }  
        }  
    }  
    
    //2003 word转 html
    public static void Word2003ToHtml(String sourceFilePath,String sourceFileName,final String targetFilePath,String targetFileName){
    	InputStream input=null;
    	OutputStream outStream=null;
    	
    	try {
    	
        input = new FileInputStream(new File(sourceFilePath+sourceFileName));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //图片处理
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(targetFilePath);
                if(!imgPath.exists()){
                    imgPath.mkdirs();
                }
                File file = new File(targetFilePath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return targetFilePath + suggestedName;
            }
        });
        
        //����word�ĵ�
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        
        File htmlFile = new File(targetFilePath + targetFileName);
        outStream = new FileOutputStream(htmlFile);
        
        //Ҳ����ʹ���ַ���������ȡ����������
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
//        OutputStream outStream = new BufferedOutputStream(baos);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        
        serializer.transform(domSource, streamResult);

        //Ҳ����ʹ���ַ���������ȡ����������
//        String content = baos.toString();
//        System.out.println(content);
//        baos.close();
        outStream.close();
    
    }catch(Exception e) {
    	e.printStackTrace();
    }finally {  
        try {  
            if (input != null)  
            	input.close();  
            if (outStream != null)  
            	outStream.close();  
        } catch (IOException ie) {  
        }  
    }  
    }
    	
}
