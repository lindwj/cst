package com.cst.wap.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Shualiwu;
import com.cst.service.model.ShualiwuForm;
import com.cst.service.model.Star;
import com.cst.service.util.Common;
import com.cst.service.ShualiwuService;
import com.cst.service.StarService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/shualiwu")
public class ShualiwuController {
	
	// 业务逻辑对象
	@Autowired
	private ShualiwuService shualiwuService;
	
	@Autowired
	private StarService starService;
	
	
	// 查询结果
	private List<Shualiwu> shualiwuList;
	
	private ShualiwuForm shualiwuForm=new ShualiwuForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/shualiwuListPage.do")
	@ResponseBody
	public List<Shualiwu> shualiwuListPage(Shualiwu shualiwu) throws Exception{
		//shualiwuForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Shualiwu shualiwu=shualiwuForm.getShualiwu();
		shualiwuList = shualiwuService.getShualiwuListPage(shualiwu);
//		shualiwuForm.setShualiwu(shualiwu);
//		request.setAttribute("page", shualiwuForm.getPage());
		if(shualiwuList==null) {
			return null;	
		}
		if(shualiwuList!=null&&shualiwuList.size()>0) {
		shualiwuList.get(0).setPage(shualiwu.getPage());
		}
		return shualiwuList;
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/shualiwuAddEditIni.do")
	@ResponseBody
	public String shualiwuAddEditIni() throws Exception{
		Shualiwu shualiwu=shualiwuForm.getShualiwu();
		if ("edit".equals(doWhat)) {
			shualiwu = this.shualiwuService.getShualiwuById(shualiwu.getId());
			shualiwuForm.setShualiwu(shualiwu);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	@RequestMapping("/shualiwuDetail.do")
	@ResponseBody
	public String shualiwuDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Shualiwu shualiwu=shualiwuForm.getShualiwu();
		shualiwu = this.shualiwuService.getShualiwuById(shualiwu.getId());
		shualiwuForm.setShualiwu(shualiwu);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Shualiwu shualiwu=shualiwuForm.getShualiwu();
		shualiwu = this.shualiwuService.getShualiwuById(shualiwu.getId());
		shualiwuForm.setShualiwu(shualiwu);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/shualiwuAddEdit.do")
	@ResponseBody
	public String shualiwuAddEdit(Shualiwu shualiwu,HttpServletRequest request,HttpServletResponse response) throws Exception {		
		//this.setPare_moduleid(14);
        //shualiwuForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//        Shualiwu shualiwu=shualiwuForm.getShualiwu();
		String openid=null;
		String name=null;
		String headurl=null;
		
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
			
			this.shualiwuService.saveShualiwu(shualiwu, "add");
			
			
		}else {
			return null;
		}
		
		
//		shualiwuForm.setShualiwu(shualiwu);
		return String.valueOf(shualiwu.getId());
	}
	
	
	//zhifuwancheng xiugai zhuangtai
	@RequestMapping("/shualiwuup.do")
	@ResponseBody
	public String shualiwuup(Shualiwu shualiwu,HttpServletRequest request,HttpServletResponse response) throws Exception {		
		this.shualiwuService.saveShualiwu(shualiwu, "edit");
		
		Star star =new Star();
		star.setId(shualiwu.getStarId());
		//暂存礼物id
		star.setAdmid(shualiwu.getLiwuId());;
		
		this.starService.updateStarredu(star);
//		shualiwuForm.setShualiwu(shualiwu);
		return "";
	}
	
	
	
	/**删除对象*/
	@RequestMapping("/shualiwuDelete.do")
	@ResponseBody
	public String shualiwuDelete() throws Exception {	
		Shualiwu shualiwu=shualiwuForm.getShualiwu();
		this.shualiwuService.deleteShualiwu(shualiwu.getId());
		return "list";
	}	

	public ShualiwuForm getModel() {
		return shualiwuForm;
	}
	
	public List<Shualiwu> getShualiwuList() {
		return this.shualiwuList;
	}

	public void setShualiwuList(List<Shualiwu> shualiwuList) {
		this.shualiwuList = shualiwuList;
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
