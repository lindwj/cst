package com.cst.manager.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.IndexBanner;
import com.cst.service.model.IndexBannerForm;
import com.cst.service.model.Manager;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductPic;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.IndexBannerService;
import com.cst.service.ParameterService;
import com.cst.service.ProductService;

import java.io.BufferedInputStream;    
import java.io.BufferedOutputStream;    
import java.io.File;    
import java.io.FileInputStream;    
import java.io.FileOutputStream;    
import java.io.IOException;    
import java.io.InputStream;    
import java.io.OutputStream;    
import java.net.URLEncoder;    
    
import javax.servlet.http.HttpServletResponse;    
    
import org.apache.log4j.Logger;    
import org.apache.tools.zip.ZipEntry;    
import org.apache.tools.zip.ZipOutputStream;    
    
    
/**   
 * 批量下载文件：   
 *   使用ant.jar包中的org.apache.tools.zip.*完成压缩，   
 * java原生也有java.util.zip.*但是测试了下无法搞定压缩   
 * 文件内文件名的中文问题     
 * @author yangcong   
 *    
 */    

/**
 * @author lind
 */

@Controller
@RequestMapping("/batchDownload")
public class BatchDownloadController{
	
	private Logger Log = Logger.getLogger(BatchDownloadController.class);    
    private static final String FilePath = "D:\\";    
    
    private static final long serialVersionUID = -8694640030455344419L;    
    
    
    @RequestMapping("/execute.do")
    @ResponseBody
    public String execute(HttpServletResponse response,HttpServletRequest request,String files) {    
        //生成的ZIP文件名为Demo.zip    
    	String FilePath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/productIndex/";
    	
        String tmpFileName = "Demo.zip";
        byte[] buffer = new byte[1024];    
        String strZipPath = FilePath + tmpFileName;    
        try {    
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(    
                    strZipPath));    
            // 需要同时下载的两个文件result.txt ，source.txt  
            String[] filesss=files.split(",");
//            File[] file1 = { new File(FilePath+"test1.txt"),    
//                    new File(FilePath+"测试2.docx") };    
            for (int i = 0; i < filesss.length; i++) {
            	if(filesss[i]==null ||filesss[i].length()<3 ) {
            		continue;
            	}
            	
                FileInputStream fis = new FileInputStream(new File(FilePath+filesss[i]));    
                out.putNextEntry(new ZipEntry(filesss[i]));    
                //设置压缩文件内的字符编码，不然会变成乱码    
                out.setEncoding("GBK");    
                int len;    
                // 读入需要下载的文件的内容，打包到zip文件    
                while ((len = fis.read(buffer)) > 0) {    
                    out.write(buffer, 0, len);    
                }    
                out.closeEntry();    
                fis.close();    
            }    
            out.close();    
            this.downFile(response,request, tmpFileName);    
        } catch (Exception e) {    
            Log.error("文件下载出错", e);    
        }    
        return null;    
    }    
    
    
    /**   
     * 文件下载   
     * @param response   
     * @param str   
     */    
    private void downFile(HttpServletResponse response,HttpServletRequest request, String str) {    
        try {    
        	String FilePath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/productIndex/";
            String path = FilePath + str;    
            File file = new File(path);    
            if (file.exists()) {    
                InputStream ins = new FileInputStream(path);    
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面    
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流    
                BufferedOutputStream bouts = new BufferedOutputStream(outs);    
                response.setContentType("application/x-download");// 设置response内容的类型    
                response.setHeader(    
                        "Content-disposition",    
                        "attachment;filename="    
                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息    
                int bytesRead = 0;    
                byte[] buffer = new byte[8192];    
                // 开始向网络传输文件流    
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {    
                    bouts.write(buffer, 0, bytesRead);    
                }    
                bouts.flush();// 这里一定要调用flush()方法    
                ins.close();    
                bins.close();    
                outs.close();    
                bouts.close();    
            } else {    
                response.sendRedirect("../error.jsp");    
            }    
        } catch (IOException e) {    
            Log.error("文件下载出错", e);    
        }    
    }
}
