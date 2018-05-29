package com.cst.service.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.License;

public class Office2pdf {
	
	
	public static void main(String[] args) {
		//ok 1月30号测试 		无水印
//       doc2pdf("C:/Users/Administrator/Downloads/全套报表模板-word版本.docx","F:/test/aspose全套报表模板-word版本-docx.pdf");  
       
//       //ok 		无水印
//       doc2pdf("C:/Users/Administrator/Downloads/内蒙古国税局.doc","F:/test/aspose内蒙古国税局-doc.pdf");
//		
//       //ok			有水印
		excel2pdf("C:/Users/Administrator/Downloads/资产负债表.xlsx","F:/test/aspose资产负债表-xlsx.pdf");
		
		//ok		有水印
		excel2pdf("C:/Users/Administrator/Downloads/中华人民共和国企业所得税年度纳税申报表（A类，2014年版）.xls","F:/test/aspose中华人民共和国企业所得税年度纳税申报表（A类，2014年版）-xls.pdf");
		
		//ok			无水印   word转html代码officeDocumentToHtml，必须接在doc2pdf之后执行，才不会有水印
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/全套报表模板-word版本.docx","F:/test/aspose全套报表模板-word版本-docx.html",".docx");
//		
//		//ok			无水印  加入license方法后无水印
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/内蒙古国税局.doc","F:/test/aspose内蒙古国税局-doc.html",".doc");
		
//		//ok			有水印
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/资产负债表.xlsx","F:/test/aspose资产负债表-xlsx.html",".xlsx");
//		
//		//ok			有水印
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/中华人民共和国企业所得税年度纳税申报表（A类，2014年版）.xls","F:/test/aspose中华人民共和国企业所得税年度纳税申报表（A类，2014年版）-xls.html",".xls");
		
//		excel2pdf("C:/Users/Administrator/Downloads/资产负债表.xlsx","F:/test/aspose资产负债表.pdf");
//		
//		excel2pdf("C:/Users/Administrator/Downloads/昌源9月报表.xls","F:/test/aspose昌源9月报表.pdf");
//		
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/全套报表模板-word版本.docx","F:/test/aspose全套报表模板-wordhtml版本.html",".docx");
//		
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/企业会计准则第30号.docx","F:/test/aspose企业会计准则第30号-wordhtml版本.html",".docx");
//		
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/内蒙古国税局.doc","F:/test/aspose内蒙古国税局-wordhtml版本.html",".doc");
//		
//		officeDocumentToHtml("C:/Users/Administrator/Downloads/资产负债表.xlsx","F:/test/aspose资产负债表.xlsx.html",".xlsx");
		
    } 
	
	
	private static Boolean officeDocumentToHtml(String sourceDoc, String saveDoc,String docExtendName)  
	{  
		if (!getLicense()) {
            return false;
        }
		
		Boolean result = false;  
	  
	    //��ȡ�ļ���չ��  
	    switch (docExtendName)  
	    {  
	        case ".doc":  
	        case ".docx":  
			Document doc=null;
			try {
				doc = new Document(sourceDoc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				doc.save(saveDoc, com.aspose.words.SaveFormat.HTML_FIXED) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
	            result = true;  
	            break;  
	        case ".xls":  
	        case ".xlsx":  
			Workbook workbook=null;
			try {
				workbook = new Workbook(sourceDoc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				workbook.save(saveDoc, com.aspose.cells.SaveFormat.HTML);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	  
	            result = true;  
	            break;  
	        case ".ppt":  
	        case ".pptx":  
	            //templateFile = templateFile.Replace("/", "\\");  
	            //string templateFile = sourceDoc;  
	            //templateFile = templateFile.Replace("/", "\\");  
//	            PresentationEx pres = new PresentationEx(sourceDoc);  
//	            pres.Save(saveDoc, Aspose.Slides.Export.SaveFormat.Html);  
	  
	            result = true;  
	            break;  
	        default:  
	            break;  
	    }  
	  
	    return result;  
	}

    private static boolean getLicense() {
        boolean result = false;
        InputStream is=null;
        try {
            is= Office2pdf.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xmlӦ����..\WebRoot\WEB-INF\classes·����
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(is!=null) {
        		try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        return result;
    }

    public static void doc2pdf(String wordPath, String pdfPath) {
        if (!getLicense()) {
            return;
        }
        
        FileOutputStream os=null;
        try {
            File file = new File(pdfPath);  //�½�һ��pdf�ĵ�
            os = new FileOutputStream(file);
            Document doc = new Document(wordPath);                    //Address�ǽ�Ҫ��ת����word�ĵ�
            doc.save(os, com.aspose.words.SaveFormat.PDF);//ȫ��֧��DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF �໥ת��
//            System.out.println("����ʱ��" + ((now - old) / 1000.0) + "��");  //ת����ʱ
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	try {
        		if(os!=null) {
				os.close();
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

    /**
     * @param excelPath
     * @param pdfPath
     */
    public static void excel2pdf(String excelPath, String pdfPath) {
        if (!getLicense()) {
            return;
        }
        FileOutputStream fileOS=null;
        try {
            Workbook wb = new Workbook(excelPath);
            
            //临时pdf文件 
            String pdfPathTemp=pdfPath.split("\\.")[0]+"temp.pdf";
            
            fileOS = new FileOutputStream(new File(pdfPathTemp));
            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
            
            //去除水印
            
            AddContentToPDF.addPicToPdf(pdfPathTemp, pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	try {
        		if(fileOS!=null) {
				fileOS.close();
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}