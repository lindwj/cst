package com.cst.wap.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cst.service.model.Shualiwu;
import com.cst.service.model.Star;
import com.cst.service.model.StarForm;
import com.cst.service.util.Common;
import com.cst.service.ShualiwuService;
import com.cst.service.StarService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/star")
public class StarController {
	
	// 业务逻辑对象
	@Autowired
	private StarService starService;
	
	@Autowired
	private ShualiwuService shualiwuService;
	
	// 查询结果
	private List<Star> starList;
	
	private StarForm starForm=new StarForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	
	//jQuery form插件的使用   后台
//    @RequestMapping(value = "getParamFromFileForAjax",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> getParamFromFileForAjax(HttpServletRequest request,String fileName) {
//        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
//        FileInputStream inputStream = null;
//         
//        //把Request强转成多部件请求对象
//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//        //根据文件名称获取文件对象
//        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile(fileName);
//         
//        
//        try{
//            String filename = commonsMultipartFile.getName();
//            String originalFilename = commonsMultipartFile.getOriginalFilename();
//             
//            System.out.println("FileName = " + filename);
//            System.out.println("originalFilename = " + originalFilename);
//             
//            inputStream = (FileInputStream) commonsMultipartFile.getInputStream();
//        } catch (IOException e1) {
//            paramMap.put("message", "上传文件错误");
//            return paramMap;
//        }
//         
//        //String filePath = "E:\\work\\svn\\openeap\\code\\openeap\\src\\main\\webapp\\template\\北京市房屋租赁合同5.xml";    
//        //paramMap = ImportFile.getParamFromFile(filePath);
//        paramMap = ImportFile.getParamFromStream(inputStream);
//         
//        Iterator it = paramMap.entrySet().iterator();
//        while (it.hasNext()) {
//         Map.Entry e = (Map.Entry) it.next();
//         System.out.println("参数 Key: " + e.getKey() + ";  参数 Value: "
//           + e.getValue());
//        }
//         
//        System.out.println("生成的json为：  " + JsonMapper.getInstance().toJson(paramMap));
//         
//        return paramMap;
//    }
    
	
	/** 执行搜索 */
	@RequestMapping("/starListPage.do")
	@ResponseBody
	public List<Star> starListPage(Star star) throws Exception{
		//starForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Star star=starForm.getStar();
		starList = starService.getStarListPage(star);
		if(starList==null) {
			return null;	
		}
//		starForm.setStar(star);
//		request.setAttribute("page", starForm.getPage());
		if(starList!=null&&starList.size()>0) {
		starList.get(0).setPage(star.getPage());
		}
		return starList;
	}
	
	
	@RequestMapping("/getStarList.do")
	@ResponseBody
	public List<Star> getStarList(Star star) throws Exception{
		//starForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Star star=starForm.getStar();
		starList = starService.getStarList(star);
		if(starList==null) {
			return null;	
		}
//		starForm.setStar(star);
//		request.setAttribute("page", starForm.getPage());
		return starList;
	}
	
	
	@RequestMapping("/starListInfo.do")
	@ResponseBody
	public Star starListInfo(Star star) throws Exception{
		//starForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Star star=starForm.getStar();
		star = starService.getstarListInfo(star);
//		starForm.setStar(star);
//		request.setAttribute("page", starForm.getPage());
//		starList.get(0).setPage(star.getPage());
		return star;
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/starAddEditIni.do")
	@ResponseBody
	public Star starAddEditIni(Star star) throws Exception{
//		Star star=starForm.getStar();
//		if ("edit".equals(doWhat)) {
//			star = this.starService.getStarById(star.getId());
//			starForm.setStar(star);
//		}		
		
		star = this.starService.getStarById(star.getId());
		
		return star;
	}
	
	@RequestMapping("/toupiao.do")
	@ResponseBody
	public String toupiao(Star star,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		Star star=starForm.getStar();
//		if ("edit".equals(doWhat)) {
//			star = this.starService.getStarById(star.getId());
//			starForm.setStar(star);
//		}		
		
		
		String openid=null;
		String name=null;
		String headurl=null;
		Shualiwu shualiwu =new Shualiwu();
		Shualiwu st=null;
		
		Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
		if (bdhcookie != null) {
			// cookie 获取数据
			String value = URLDecoder.decode(bdhcookie.getValue());
			if (value.length() > 5) {
				openid=value.split(":")[0];
				name=value.split(":")[1];
				headurl=value.split(":")[2];
			} else {
				return null;
			}
			
			shualiwu.setOpenid(openid);
			shualiwu.setName(name);
			shualiwu.setHeadurl(headurl);
			shualiwu.setEventid(star.getEventid());
			shualiwu.setStarId(star.getId());
//			shualiwu.setOpenid(star.getName());
			shualiwu.setType(1);
			
			Shualiwu s= this.shualiwuService.getShualiwu(shualiwu);
			
			st= this.shualiwuService.getShualiwunum(shualiwu);
			//已投票
			if(s != null) {
				return "2,"+(3-st.getId());
			}
			
			
			//每天3票
			if(st != null&&st.getId()>=3) {
				return "3";
			}
			
			this.shualiwuService.saveShualiwu(shualiwu, "add");
			
			this.starService.updateStarPiaoShu(star);
			
		}else {
			return null;
		}
		
		
		
		
		
		
//		Shualiwu shualiwu =new Shualiwu();
//		shualiwu.setEventid(star.getEventid());
//		shualiwu.setStarId(star.getId());
//		shualiwu.setOpenid(star.getName());
//		shualiwu.setType(1);//toupiao
//		this.shualiwuService.saveShualiwu(shualiwu, "add");
		
		return "1,"+(2-st.getId());
	}
	
	
	
	
	/** 查看对象*/
	@RequestMapping("/starDetail.do")
	@ResponseBody
	public String starDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Star star=starForm.getStar();
		star = this.starService.getStarById(star.getId());
		starForm.setStar(star);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Star star=starForm.getStar();
		star = this.starService.getStarById(star.getId());
		starForm.setStar(star);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/starAddEdit.do")
	@ResponseBody
	public String starAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //starForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Star star=starForm.getStar();
		this.starService.saveStar(star, this.doWhat);
		starForm.setStar(star);
		return "detail";
	}
	/**删除对象*/
	@RequestMapping("/starDelete.do")
	@ResponseBody
	public String starDelete() throws Exception {	
		Star star=starForm.getStar();
		this.starService.deleteStar(star.getId());
		return "list";
	}	

	public StarForm getModel() {
		return starForm;
	}
	
	public List<Star> getStarList() {
		return this.starList;
	}

	public void setStarList(List<Star> starList) {
		this.starList = starList;
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
