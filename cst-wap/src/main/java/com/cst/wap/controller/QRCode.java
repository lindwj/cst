package com.cst.wap.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Manager;
import com.cst.wap.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping("/code")
public class QRCode {
	
	@RequestMapping("/getQRCode.do")
	@ResponseBody
	public String createQRCode(String managerId,String url,String openid,HttpServletResponse response,HttpServletRequest request) throws Exception { 
		Subject subject = SecurityUtils.getSubject();
        int width = 300;  
        int height = 300;  
        //二维码的图片格式  
        String format = "png";  
        Hashtable hints = new Hashtable();  
        //内容所使用编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url+managerId,  
                BarcodeFormat.QR_CODE, width, height, hints);  
        //生成二维码  
        String ctxPath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/wechat/qrcodeimg/";
        String img="/resource/wechat/qrcodeimg/"+openid+".png";
        File outputFile = new File(ctxPath+openid+".png");  
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
        return img;
    }  
}
