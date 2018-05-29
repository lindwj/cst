package com.cst.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.Manager;
import com.cst.service.model.Shop;
import com.cst.service.model.Shuidian;
import com.cst.service.model.ShuidianForm;
import com.cst.service.ShuidianService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/shuidian")
public class ShuidianController {
	
	// 业务逻辑对象
	@Autowired
	private ShuidianService shuidianService;
	
	// 查询结果
	private List<Shuidian> shuidianList;
	
	private ShuidianForm shuidianForm=new ShuidianForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/shuidianListPage.do")
	public String shuidianListPage(Shuidian shuidian, Model model) throws Exception {
		//shuidianForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

		shuidian.setCreateByAdm(mg.getManagerId());
//		Shuidian shuidian=shuidianForm.getShuidian();
		shuidianList = shuidianService.getShuidianListPage(shuidian);
//		shuidianForm.setShuidian(shuidian);
//		request.setAttribute("page", shuidianForm.getPage());
		
		model.addAttribute("shuidianList", shuidianList);
		model.addAttribute("page", shuidian.getPage());
		model.addAttribute("errcode", shuidian.getErrcode());
		
		if(shuidian.getMenuId()!=null&&shuidian.getMenuId()>0){
		subject.getSession().setAttribute("menuId", shuidian.getMenuId());
		}

		// request.setAttribute("page", shopForm.getPage());
		return "/jsp/shuidian/shuidianIndex";
//		return "list";
	}
	
	/** 报表 */
	@RequestMapping("/shuidianbbList.do")
	public String shuidianbbList(Shuidian shuidian, Model model) throws Exception {
		//shuidianForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		shuidianList=null;
//		Manager mg = (Manager) subject.getPrincipal();
//
//		shuidian.setCreateByAdm(mg.getManagerId());
//		Shuidian shuidian=shuidianForm.getShuidian();
//		if(shuidian.getNian()==null||shuidian.getNian()<1) {
//		shuidian.setNian(-1);
//		shuidian.setYue(-1);
//		shuidian.setShuidianId(-1);
//		shuidian.setFengsid(-1);
//		}
		if(shuidian.getNian()!=null&&shuidian.getNian()>0) {
		shuidianList = shuidianService.getShuidianbbList(shuidian);
		
		shuidian.setNian(shuidian.getNian()-1);
		List<Shuidian> shuidianListtemp = shuidianService.getShuidianbbList(shuidian);
		
		if(shuidianList!=null&&shuidianListtemp!=null) {
			for(Shuidian shuidiana:shuidianList) {
				for(Shuidian shuidianb:shuidianListtemp) {
					if(shuidiana.getXmid() == shuidianb.getXmid()) {
						shuidiana.setShuidian(shuidianb);
						break;
					}
				}
			}
		}
		}
//		shuidianForm.setShuidian(shuidian);
//		request.setAttribute("page", shuidianForm.getPage());
		
		model.addAttribute("shuidianList", shuidianList);
//		model.addAttribute("page", shuidian.getPage());
		model.addAttribute("errcode", shuidian.getErrcode());
		
		if(shuidian.getMenuId()!=null&&shuidian.getMenuId()>0){
		subject.getSession().setAttribute("menuId", shuidian.getMenuId());
		}

		// request.setAttribute("page", shopForm.getPage());
		return "/jsp/shuidian/shuidianbb";
//		return "list";
	}
	
	
	/** 编辑前初始化对象*/
	@RequestMapping("/shuidianAddEditIni.do")
	public String shuidianAddEditIni(Shuidian shuidian,Model model) throws Exception{
//		Shuidian shuidian=shuidianForm.getShuidian();
//		if ("edit".equals(doWhat)) {
//			shuidian = this.shuidianService.getShuidianById(shuidian.getShuidianId());
//			shuidianForm.setShuidian(shuidian);
//		}		
//		return "addedit";
		
model.addAttribute("errcode", shuidian.getErrcode());
		
shuidian = this.shuidianService.getShuidianById(shuidian.getShuidianId());
		
		model.addAttribute("shuidian", shuidian);
		return "/jsp/shuidian/shuidianEdit";
	}
	
	@RequestMapping("/shuidianAdd.do")
	public String shuidianAdd(Shuidian shuidian, Model model) throws Exception {
		
		model.addAttribute("errcode", shuidian.getErrcode());
		
		model.addAttribute("shuidian", shuidian);
		return "/jsp/shuidian/shuidianAdd";
	}
	
	/** 查看对象*/
	@RequestMapping("/shuidianDetail.do")
	@ResponseBody
	public String shuidianDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Shuidian shuidian=shuidianForm.getShuidian();
		shuidian = this.shuidianService.getShuidianById(shuidian.getShuidianId());
		shuidianForm.setShuidian(shuidian);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Shuidian shuidian=shuidianForm.getShuidian();
		shuidian = this.shuidianService.getShuidianById(shuidian.getShuidianId());
		shuidianForm.setShuidian(shuidian);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/shuidianAddEdit.do")
	@ResponseBody
	public String shuidianAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //shuidianForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Shuidian shuidian=shuidianForm.getShuidian();
		this.shuidianService.saveShuidian(shuidian, this.doWhat);
		shuidianForm.setShuidian(shuidian);
		return "detail";
	}
	
	/** 保存新增对象 */
	@RequestMapping("/saveShuidian.do")
	public ModelAndView shuidianAddEdit(Shuidian shuidian, ModelAndView model) throws Exception {
		// this.setPare_moduleid(14);
		// shopForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(shuidian.getDowhat())) {

			// 后端校验 101 省市县 街道 名称 编号 等不能为空

//			if (shop.getProvince() == null || shop.getProvince() <= 0) {
//				validate = "101";
//			}
//			if (shop.getCity() == null || shop.getCity() <= 0) {
//				validate = "101";
//			}
//			if (shop.getDistrict() == null || shop.getDistrict() <= 0) {
//				validate = "101";
//			}
//			if (shop.getStreet() == null || shop.getStreet() <= 0) {
//				validate = "101";
//			}

//			if (shop.getName() == null || shop.getName().length() == 0) {
//				validate = "101";
//			}
//
//			if (shop.getCode() == null || shop.getCode().length() == 0) {
//				validate = "101";
//			}
//			if (shop.getAddress() == null || shop.getAddress().length() == 0) {
//				validate = "101";
//			}
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/shuidian/shuidianAdd.do");
				return model;
			}

			// 校验结束

			// 1 表示 上架 ，这里直接赋予 上架状态
//			shop.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			Manager mg = (Manager) subject.getPrincipal();

			shuidian.setShuidianfei(shuidian.getDianfei()+shuidian.getShuifei());
			shuidian.setCreateByAdm(mg.getManagerId());

		} 
		else if ("edit".equals(shuidian.getDowhat())) {
//			if (shop.getName() == null || shop.getName().length() == 0) {
//				validate = "101";
//			}
//
//			if (shop.getCode() == null || shop.getCode().length() == 0) {
//				validate = "101";
//			}
//			if (shop.getAddress() == null || shop.getAddress().length() == 0) {
//				validate = "101";
//			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("shuidianId", shuidian.getShuidianId());
				model.setViewName("redirect:/shuidian/shuidianAddEditIni.do");
				return model;
			}
		}

		try {
			this.shuidianService.saveShuidian(shuidian, shuidian.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			
			if("edit".equals(shuidian.getDowhat())){
				model.addObject("shuidianId", shuidian.getShuidianId());
				model.setViewName("redirect:/shuidian/shuidianEditIni.do");
				return model;
			}else{
				
				model.setViewName("redirect:/shuidian/shuidianAdd.do");
				return model;
			}

		}
		
		

		model.setViewName("redirect:/shuidian/shuidianListPage.do");
		return model;
	}
	
	/**删除对象*/
	@RequestMapping("/shuidianDelete.do")
	public String shuidianDelete(Shuidian shuidian, Model model) throws Exception {	
//		Shuidian shuidian=shuidianForm.getShuidian();
		this.shuidianService.deleteShuidian(shuidian.getShuidianId());
		return "redirect:/shuidian/shuidianListPage.do";
	}	

	public ShuidianForm getModel() {
		return shuidianForm;
	}
	
	public List<Shuidian> getShuidianList() {
		return this.shuidianList;
	}

	public void setShuidianList(List<Shuidian> shuidianList) {
		this.shuidianList = shuidianList;
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
