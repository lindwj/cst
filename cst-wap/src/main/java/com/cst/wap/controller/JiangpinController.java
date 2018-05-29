package com.cst.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Jiangpin;
import com.cst.service.model.JiangpinForm;
import com.cst.service.JiangpinService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/jiangpin")
public class JiangpinController {
	
	// 业务逻辑对象
	@Autowired
	private JiangpinService jiangpinService;
	
	// 查询结果
	private List<Jiangpin> jiangpinList;
	
	private JiangpinForm jiangpinForm=new JiangpinForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/jiangpinListPage.do")
	@ResponseBody
	public List<Jiangpin> jiangpinListPage(Jiangpin jiangpin) throws Exception{
		//jiangpinForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		Jiangpin jiangpin=jiangpinForm.getJiangpin();
		jiangpinList = jiangpinService.getJiangpinListPage(jiangpin);
		if(jiangpinList==null) {
			return null;
		}
//		jiangpinForm.setJiangpin(jiangpin);
//		request.setAttribute("page", jiangpinForm.getPage());
		if(jiangpinList!=null&&jiangpinList.size()>0) {
		jiangpinList.get(0).setPage(jiangpin.getPage());
		}
		return jiangpinList;
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/jiangpinAddEditIni.do")
	@ResponseBody
	public String jiangpinAddEditIni() throws Exception{
		Jiangpin jiangpin=jiangpinForm.getJiangpin();
		if ("edit".equals(doWhat)) {
			jiangpin = this.jiangpinService.getJiangpinById(jiangpin.getId());
			jiangpinForm.setJiangpin(jiangpin);
		}		
		return "addedit";
	}
	
	/** 查看对象*/
	@RequestMapping("/jiangpinDetail.do")
	@ResponseBody
	public String jiangpinDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Jiangpin jiangpin=jiangpinForm.getJiangpin();
		jiangpin = this.jiangpinService.getJiangpinById(jiangpin.getId());
		jiangpinForm.setJiangpin(jiangpin);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Jiangpin jiangpin=jiangpinForm.getJiangpin();
		jiangpin = this.jiangpinService.getJiangpinById(jiangpin.getId());
		jiangpinForm.setJiangpin(jiangpin);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/jiangpinAddEdit.do")
	@ResponseBody
	public String jiangpinAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //jiangpinForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Jiangpin jiangpin=jiangpinForm.getJiangpin();
		this.jiangpinService.saveJiangpin(jiangpin, this.doWhat);
		jiangpinForm.setJiangpin(jiangpin);
		return "detail";
	}
	/**删除对象*/
	@RequestMapping("/jiangpinDelete.do")
	@ResponseBody
	public String jiangpinDelete() throws Exception {	
		Jiangpin jiangpin=jiangpinForm.getJiangpin();
		this.jiangpinService.deleteJiangpin(jiangpin.getId());
		return "list";
	}	

	public JiangpinForm getModel() {
		return jiangpinForm;
	}
	
	public List<Jiangpin> getJiangpinList() {
		return this.jiangpinList;
	}

	public void setJiangpinList(List<Jiangpin> jiangpinList) {
		this.jiangpinList = jiangpinList;
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
