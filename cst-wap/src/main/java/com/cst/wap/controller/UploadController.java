package com.cst.wap.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cst.service.model.Manager;
import com.cst.service.model.Product;
import com.cst.service.model.ProductForm;
import com.cst.service.model.Star;
import com.cst.service.ProductService;
import com.cst.service.StarService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/upload")
public class UploadController{
	
	// 业务逻辑对象
	@Autowired
	private ProductService productService;
	@Autowired
	private StarService starService;
	
	// 查询结果
	private List<Product> productList;
	
	private ProductForm productForm=new ProductForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	
	@RequestMapping("/headUpload.do")
	@ResponseBody
	public Map<String,Object> headUpload(HttpServletRequest request,Star star) {  
		  
		Map<String,Object> map =new HashMap<String, Object>();
		
		//把Request强转成多部件请求对象
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //根据文件名称获取文件对象
        CommonsMultipartFile file = (CommonsMultipartFile) multipartHttpServletRequest.getFile(star.getPicName());
         
		
        String path = request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/";  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        star.setHeadurl(request.getContextPath()+"/resource/upload/"+fileName);
        this.starService.saveStar(star, "add");
  
        map.put("state", "SUCCESS");
        map.put("url", request.getContextPath()+"/resource/upload/"+fileName);
        map.put("size", file.getSize());
        map.put("original", file.getOriginalFilename());
        map.put("title", file.getName());
        map.put("type", file.getContentType());
        return map;  
    }
	
	
	
	/** 执行搜索 */
	@RequestMapping("/pUpload.do")
	@ResponseBody
	public Map<String,Object> pupload(@RequestParam(value = "upfile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {  
		  
		Map<String,Object> map =new HashMap<String, Object>();
		
        String path = request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/description";  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        
  
        map.put("state", "SUCCESS");
        map.put("url", request.getContextPath()+"/resource/upload/description/"+fileName);
        map.put("size", file.getSize());
        map.put("original", file.getOriginalFilename());
        map.put("title", file.getName());
        map.put("type", file.getContentType());
        return map;  
    }
	
	
	@RequestMapping("/uploadActiontest.do")
	@ResponseBody
	public String uploadAction(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {  
		  
		Map<String,Object> map =new HashMap<String, Object>();
		
        String path = request.getSession().getServletContext().getRealPath("/resource/upload/product");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return fileName;  
    }
	
	
	
	/**
	* 处理文件上传
	* @param response
	* @param request
	* @return
	* @throws IOException
	*/
	@RequestMapping(value="/uploadAction.do")  
	@ResponseBody
	public Map<String,Object> upload(HttpServletResponse response,HttpServletRequest request) throws IOException{  
	Map<String,Object> map =new HashMap<String, Object>();	
		
	String responseStr="";
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
	//String savePath = this.getServletConfig().getServletContext().getRealPath("");
	        //savePath = savePath + "/uploads/";
	// 文件保存路径  ctxPath本地路径
	String ctxPath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/product/";
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
//	String ymd = sdf.format(new Date());  
//	ctxPath += File.separator + ymd + File.separator;  
//	System.out.println("ctxpath="+ctxPath);
	// 创建文件夹  
	File file = new File(ctxPath);    
	if (!file.exists()) {    
	file.mkdirs();    
	}
	String fileName = null;    
	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
	// 上传文件   
	MultipartFile mf = entity.getValue();  
	fileName = mf.getOriginalFilename();//获取原文件名  
	System.out.println("filename="+fileName);
	File uploadFile = new File(ctxPath + fileName);
	try {  
	FileCopyUtils.copy(mf.getBytes(), uploadFile); 
	map.put("fileName", fileName);
//	   responseStr=fileName;  
	   } catch (IOException e) {  
//	   	responseStr="err";  
	   	map.put("fileName", "err");
	       e.printStackTrace();  
	       }
	}   
    return map;   
	}
	
	
	@RequestMapping(value="/uploadPicAction.do")  
	@ResponseBody
	public Map<String,Object> uploadPic(HttpServletResponse response,HttpServletRequest request) throws IOException{  
	Map<String,Object> map =new HashMap<String, Object>();	
		
	String responseStr="";
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
	//String savePath = this.getServletConfig().getServletContext().getRealPath("");
	        //savePath = savePath + "/uploads/";
	// 文件保存路径  ctxPath本地路径
	String ctxPath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/productPic/";
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
//	String ymd = sdf.format(new Date());  
//	ctxPath += File.separator + ymd + File.separator;  
//	System.out.println("ctxpath="+ctxPath);
	// 创建文件夹  
	File file = new File(ctxPath);    
	if (!file.exists()) {    
	file.mkdirs();    
	}
	String fileName = null;    
	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
	// 上传文件   
	MultipartFile mf = entity.getValue();  
	fileName = mf.getOriginalFilename();//获取原文件名  
	System.out.println("filename="+fileName);
	File uploadFile = new File(ctxPath + fileName);
	try {  
	FileCopyUtils.copy(mf.getBytes(), uploadFile); 
	map.put("fileName", fileName);
//	   responseStr=fileName;  
	   } catch (IOException e) {  
//	   	responseStr="err";  
	   	map.put("fileName", "err");
	       e.printStackTrace();  
	       }
	}   
    return map;   
	}
	
	
	
	@RequestMapping(value="/uploadIndexAction.do")  
	@ResponseBody
	public Map<String,Object> uploadIndex(HttpServletResponse response,HttpServletRequest request) throws IOException{  
	Map<String,Object> map =new HashMap<String, Object>();	
		
	String responseStr="";
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
	//String savePath = this.getServletConfig().getServletContext().getRealPath("");
	        //savePath = savePath + "/uploads/";
	// 文件保存路径  ctxPath本地路径
	String ctxPath=request.getSession().getServletContext().getRealPath("/")+File.separator+"resource/upload/productIndex/";
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
//	String ymd = sdf.format(new Date());  
//	ctxPath += File.separator + ymd + File.separator;  
//	System.out.println("ctxpath="+ctxPath);
	// 创建文件夹  
	File file = new File(ctxPath);    
	if (!file.exists()) {    
	file.mkdirs();    
	}
	String fileName = null;    
	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
	// 上传文件   
	MultipartFile mf = entity.getValue();  
	fileName = mf.getOriginalFilename();//获取原文件名  
	System.out.println("filename="+fileName);
	File uploadFile = new File(ctxPath + fileName);
	try {  
	FileCopyUtils.copy(mf.getBytes(), uploadFile); 
	map.put("fileName", fileName);
//	   responseStr=fileName;  
	   } catch (IOException e) {  
//	   	responseStr="err";  
	   	map.put("fileName", "err");
	       e.printStackTrace();  
	       }
	}   
    return map;   
	}
	
	/** 编辑前初始化对象*/
	public String productAddEditIni() throws Exception{
		Product product=null;
		if ("edit".equals(doWhat)) {
//			product = this.productService.getProductById(product.getProductId());
			productForm.setProduct(product);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	public String productDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Product product=null;
//		product = this.productService.getProductById(product.getProductId());
		productForm.setProduct(product);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Product product=null;
//		product = this.productService.getProductById(product.getProductId());
		productForm.setProduct(product);
		return "detail";
	}
	/** 保存新增对象 */
	public String productAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //productForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Product product=null;
		this.productService.saveProduct(product, this.doWhat);
		productForm.setProduct(product);
		return "detail";
	}
	/**删除对象*/
	public String productDelete() throws Exception {	
		Product product=null;
//		this.productService.deleteProduct(product.getProductId());
		return "list";
	}	

	public ProductForm getModel() {
		return productForm;
	}
	
	public List<Product> getProductList() {
		return this.productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public String getDoWhat() {
		return doWhat;
	}

	public void setDoWhat(String doWhat) {
		this.doWhat = doWhat;
	}
	
	public void setPare_moduleid(int pareModuleid) {
		pare_moduleid = pareModuleid;
	}
	
	
}
