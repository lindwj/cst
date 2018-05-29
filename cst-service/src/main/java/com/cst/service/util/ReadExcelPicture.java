package com.cst.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sun.misc.BASE64Encoder;


public class ReadExcelPicture {
    /**
     * 获取图片
     * 
     * @param workbook
     * @return
     */
    public static List<Picture>  getPic(Workbook workbook, int sheetNum) {
        List<Picture> picList = new ArrayList<Picture>();
        if (workbook instanceof XSSFWorkbook) {
            XSSFWorkbook xwb = (XSSFWorkbook) workbook;
            XSSFSheet xsheet = xwb.getSheetAt(sheetNum);
            for (POIXMLDocumentPart dr : xsheet.getRelations()) {
                if (dr instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) dr;
                    List<XSSFShape> shapes = drawing.getShapes();
                    int index=1;
                    for (XSSFShape shape : shapes) {
                        Picture picture = new Picture();
                        XSSFPicture pic = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = pic.getPreferredSize();
                        picture.setX(anchor.getRow1());// 左上角坐标
                        picture.setY(anchor.getCol1());
                        byte[] data = pic.getPictureData().getData();// 图片数据
                        
                        //保存excel里面的图片
                        writePicture("F:/test/"+sheetNum+"_"+index+"_"+anchor.getRow1()+","+anchor.getCol1()+".jpg",data);
                        
                        picture.setPictureData(new BASE64Encoder().encode(data));
                        picList.add(picture);
                        picture = null;
                        
                        index++;
                    }
                }
            }
        } else if (workbook instanceof HSSFWorkbook) {
            HSSFWorkbook hwb = (HSSFWorkbook) workbook;
            HSSFSheet hsheet = hwb.getSheetAt(sheetNum);
            HSSFPatriarch hssfPatriarch= hsheet.getDrawingPatriarch();
            int index=1;
            if(hssfPatriarch!=null)
            for (HSSFShape shape : hssfPatriarch.getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    Picture picture = new Picture();
                    picture.setX(anchor.getRow1());// 左上角坐标
                    picture.setY(anchor.getCol1());
                    HSSFPicture pic = (HSSFPicture) shape;
                    byte[] data = pic.getPictureData().getData();// 图片数据
                    
                  //保存excel里面的图片
                    writePicture("F:/test/"+sheetNum+"_"+index+"_"+anchor.getRow1()+","+anchor.getCol1()+".jpg",data);
                    
                    picture.setPictureData(new BASE64Encoder().encode(data));
                    picList.add(picture);
                }
                
                index++;
            }
        }
        return  picList;
    }

    /**
     * 获取图片
     * 
     * @param filePath
     * @return
     */
    public static List<List<Picture> > readExcelPicture(String filePath) {
        List<List<Picture> > allpic = new ArrayList<List<Picture>>();
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            int sheetNum = workbook.getNumberOfSheets();// sheet的页数
            for (int i = 0; i < sheetNum; i++) {
                allpic.add(getPic(workbook, i));
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return allpic;
    }
    
    
    /**
     * 写图片文件
     * 
     * @param filePath
     * @return
     */
    public static void writePicture(String filePath,byte[] data) {
    	
    FileOutputStream fos=null;
	try {
		fos = new FileOutputStream(new File(filePath));
		fos.write(data);  
		fos.flush(); 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 

    
     
    
    
    }
    
    
}