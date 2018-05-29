package com.cst.service.util;

import java.io.BufferedOutputStream;  
import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.util.List;  
  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.apache.commons.io.output.ByteArrayOutputStream;  
import org.apache.log4j.Logger;  
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;  
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;  
  
/** 
 * java将excel转换为HTML 
 * @author wu.85@163.com 
 */  
public class Excel2Html {  
    private static final Logger logger = Logger.getLogger(Excel2Html.class.getName());  
    private static final String DEFAULT_PICTURE_FOLDER = "pictures";  
    private static final String DEFAULT_HTML_TYPE = ".html";// 默认转换的HTML文件后缀  
    private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";
  
    public static void main(String[] args) {  
    	
    	//测试excel转换html
//        File outputFolder = new File("F:/test");  
//        File outputPictureFolder = null;  
//        try {  
//            // 被转换的excel文件  
//            File convertedWordFile = new File(
//                    "C:/Users/Administrator/Downloads/中华人民共和国企业所得税年度纳税申报表（A类，2014年版）.xls");  
//            convert2Html(convertedWordFile, outputFolder, outputPictureFolder);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
        
//        try {  
//            // 被转换的excel文件  
//            File convertedWordFile = new File(
//                    "C:/Users/Administrator/Downloads/昌源9月报表.xls");  
//            convert2Html(convertedWordFile, outputFolder, outputPictureFolder);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
        
//        测试  数据写入excel
        try {
        	updateExcelValue("C:/Users/Administrator/Downloads/中华人民共和国企业所得税年度纳税申报表（A类，2014年版）.xls",1,42,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }  
    
    
 // 写入，往指定sheet表的单元格
    public static void updateExcelValue(String filePath,int sheetIndex,int rowIndex,int cellIndex) throws Exception {
    	FileOutputStream fo =null;
    	FileInputStream is=null;
    	Workbook wb = null;
    	Sheet sheet = null;
    	try {
    		
    		is=new FileInputStream(new File(filePath));
    		if(filePath.endsWith(EXCEL_XLS)){  //Excel 2003  
                wb = new HSSFWorkbook(is);  
            }else if(filePath.endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
                wb = new XSSFWorkbook(is);  
            }
        
        sheet = wb.getSheetAt(sheetIndex);
 
        Row row = sheet.getRow(rowIndex); // 获取指定的行对象，无数据则为空，需要创建
//        if (row == null) {
//            row = sheet.createRow(rowIndex); // 该行无数据，创建行对象
//        }
 
        Cell cell =row.getCell(cellIndex);
//        Cell cell = row.createCell(0); // 创建指定单元格对象。如本身有数据会替换掉
        cell.setCellValue("1001"); // 设置内容
 
        fo = new FileOutputStream(filePath); // 输出到文件
        wb.write(fo);
        
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	if(fo != null) {
        	fo.close();
        	}
        	
        	if(is != null) {
            	is.close();
            }
        }
        
 
    }
    
    
  
    public static void writeFile(String content, String path) {  
        FileOutputStream fos = null;  
        BufferedWriter bw = null;  
        try {  
            File file = new File(path);  
            fos = new FileOutputStream(file);  
            bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));  
            bw.write(content);  
        } catch (FileNotFoundException fnfe) {  
            fnfe.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        } finally {  
            try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (IOException ie) {  
            }  
        }  
    }  
  
    public static Workbook getWorkbook(File file) {  
        Workbook workbook = null;  
        try {  
            if (null != file && file.exists()) {  
                workbook = WorkbookFactory.create(file);  
            }  
        } catch (IOException e) {  
            logger.error("IOException in getWorkbook:", e);  
        } catch (InvalidFormatException  e) {  
            logger.error("InvalidFormatException in getWorkbook:", e);  
        }  
        return workbook;  
    }  
  
    /** 
     * @param excelFile 被转换的word文件 
     * @param outputFolder 转换后HTML文件存放位置 
     * @param outputPictureFolder 转换后原word中图片存放位置 
     * @throws TransformerException 
     * @throws IOException 
     * @throws ParserConfigurationException 
     */  
    public static void convert2Html(File excelFile, File outputFolder,  
            final File outputPictureFolder) throws TransformerException,  
            IOException, ParserConfigurationException {  
        // 创建excel ExcelToHtmlConverter对象  
        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(  
                DocumentBuilderFactory.newInstance().newDocumentBuilder()  
                        .newDocument());  
        excelToHtmlConverter.setOutputColumnHeaders(false);  
        excelToHtmlConverter.setOutputRowNumbers(false);  
          
        // 创建POI工作薄对象  
        HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook(excelFile);  
        excelToHtmlConverter.processWorkbook(workbook);  
  
        Document htmlDocument = excelToHtmlConverter.getDocument();  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        DOMSource domSource = new DOMSource(htmlDocument);  
        StreamResult streamResult = new StreamResult(out);  
  
        TransformerFactory tf = TransformerFactory.newInstance();  
        Transformer serializer = tf.newTransformer();  
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");  
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
        serializer.setOutputProperty(OutputKeys.METHOD, "html");  
        serializer.transform(domSource, streamResult);  
  
//        writePicures(workbook.getAllPictures(), outputPictureFolder.getAbsolutePath()+ File.separator );  
        writeFile(new String(out.toByteArray()), outputFolder.getAbsolutePath()  
                + File.separator + excelFile.getName() + DEFAULT_HTML_TYPE);  
        out.close();  
    }  
  
    public static void writePicures(List<HSSFPictureData> pics,String picturesFolder)  
            throws IOException {  
        if (pics != null) {  
            int count = 0;  
            for (int i = 0; i < pics.size(); i++) {  
                HSSFPictureData picData = pics.get(i);  
                if (null == picData) {  
                    continue;  
                }  
                byte[] bytes = picData.getData();  
                FileOutputStream output = new FileOutputStream(picturesFolder + count  
                        + "." + picData.suggestFileExtension());  
                BufferedOutputStream writer = new BufferedOutputStream(output);  
                writer.write(bytes);  
                writer.flush();  
                writer.close();  
                output.close();  
                count++;  
            }  
        }  
    }  
}  