package com.cst.web.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Manager;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.GoodsAddressService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/goodsAddress")
public class GoodsAddressController{
	
	// 业务逻辑对象
	@Autowired
	private GoodsAddressService goodsAddressService;
	
	// 查询结果
	private List<GoodsAddress> goodsAddressList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/goodsAddressListPage.do")
	public String goodsAddressListPage(GoodsAddress goodsAddress, Model model) throws Exception{
		//goodsAddressForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		goodsAddress.setCreateByUser(user.getUserId());
		
		goodsAddressList = goodsAddressService.getGoodsAddressListPage(goodsAddress);
		
		//判断是否没有地址
		if(goodsAddressList==null||goodsAddressList.size()==0){
			model.addAttribute("isDefault",1 );
		}else{
			model.addAttribute("isDefault",0 );
		}
		
		model.addAttribute("goodsAddressList", goodsAddressList);
		model.addAttribute("page", goodsAddress.getPage());
		model.addAttribute("errcode", goodsAddress.getErrcode());
		
		return "/jsp/goodsAddress/goodsAddress";
	}
	
	
	@RequestMapping("/goodsAddressListPageSvc.do")
	@ResponseBody
	public GoodsAddress goodsAddressListPageSvc(GoodsAddress goodsAddress) throws Exception{
		//goodsAddressForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		goodsAddress.setCreateByUser(user.getUserId());
		
		goodsAddressList = goodsAddressService.getGoodsAddressListPage(goodsAddress);
		
//		//判断是否没有地址
//		if(goodsAddressList==null||goodsAddressList.size()==0){
//			model.addAttribute("isDefault",1 );
//		}else{
//			model.addAttribute("isDefault",0 );
//		}
		
//		model.addAttribute("goodsAddressList", goodsAddressList);
		goodsAddress.setGoodsAddressList(goodsAddressList);
		
		return goodsAddress;
	}
	
	
	
	/** 编辑前初始化对象*/
	@RequestMapping("/goodsAddressAddEditIni.do")
	public String goodsAddressAddEditIni(GoodsAddress goodsAddress,Model model) throws Exception{
		
		model.addAttribute("errcode", goodsAddress.getErrcode());
		goodsAddress = this.goodsAddressService.getGoodsAddressById(goodsAddress.getGoodsAddressUuid());
		
		model.addAttribute("goodsAddress", goodsAddress);
		return "/jsp/goodsAddress/goodsAddressEdit";
	}
	
	
	/** 编辑前初始化对象*/
	@RequestMapping("/goodsAddressAddEditIniSvc.do")
	@ResponseBody
	public GoodsAddress goodsAddressAddEditIniSvc(GoodsAddress goodsAddress) throws Exception{
		
		goodsAddress = this.goodsAddressService.getGoodsAddressById(goodsAddress.getGoodsAddressUuid());
		
		return goodsAddress;
	}
	
	
	/** 查看对象*/
	public String goodsAddressDetail() throws Exception {
//		this.setPare_moduleid(14);	
		GoodsAddress goodsAddress=null;
//		goodsAddress = this.goodsAddressService.getGoodsAddressById(goodsAddress.getGoodsAddressId());
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		GoodsAddress goodsAddress=null;
//		goodsAddress = this.goodsAddressService.getGoodsAddressById(goodsAddress.getGoodsAddressId());
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/goodsAddressAddEdit.do")
	public ModelAndView goodsAddressAddEdit (GoodsAddress goodsAddress, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //goodsAddressForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		String validate = null;
		// add 新增 edit 修改
		if ("add".equals(goodsAddress.getDowhat())) {

			// 后端校验 101 省市县 街道 名称 编号 等不能为空

			if (goodsAddress.getProvince() == null || goodsAddress.getProvince() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getCity() == null || goodsAddress.getCity() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getDistrict() == null || goodsAddress.getDistrict() <= 0) {
				validate = "101";
			}
//			if (goodsAddress.getStreet() == null || goodsAddress.getStreet() <= 0) {
//				validate = "101";
//			}

			if (goodsAddress.getAddress() == null || goodsAddress.getAddress().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getName() == null || goodsAddress.getName().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getMobile() == null || goodsAddress.getMobile().length() == 0) {
				validate = "101";
			}
			
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/goodsAddress/goodsAddressListPage.do");
				return model;
			}

			// 校验结束

			// 1  ，这里直接赋予 激活状态
			goodsAddress.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			User u = (User) subject.getPrincipal();

			goodsAddress.setCreateByUser(u.getUserId());
			
			
			goodsAddress.setGoodsAddressUuid(Common.getUUID());

		} 
		else if ("edit".equals(goodsAddress.getDowhat())) {
			if (goodsAddress.getAddress() == null || goodsAddress.getAddress().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getName() == null || goodsAddress.getName().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getMobile() == null || goodsAddress.getMobile().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getProvince() == null || goodsAddress.getProvince() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getCity() == null || goodsAddress.getCity() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getDistrict() == null || goodsAddress.getDistrict() <= 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.addObject("goodsAddressUuid", goodsAddress.getGoodsAddressUuid());
				model.setViewName("redirect:/goodsAddress/goodsAddressAddEditIni.do");
				return model;
			}
			
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				Subject subject = SecurityUtils.getSubject();
				User u = (User) subject.getPrincipal();

				goodsAddress.setCreateByUser(u.getUserId());
			}
		}

		try {
			this.goodsAddressService.saveGoodsAddress(goodsAddress, goodsAddress.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			
			if("edit".equals(goodsAddress.getDowhat())){
				model.addObject("goodsAddressUuid", goodsAddress.getGoodsAddressUuid());
				model.setViewName("redirect:/goodsAddress/goodsAddressAddEditIni.do");
				return model;
			}

		}
		
		

		model.setViewName("redirect:/goodsAddress/goodsAddressListPage.do");
		return model;
	}
	
	
	
	/** 保存新增对象 */
	@RequestMapping("/goodsAddressEditSvc.do")
	@ResponseBody
	public String goodsAddressEditSvc (GoodsAddress goodsAddress) throws Exception {		
		//this.setPare_moduleid(14);
        //goodsAddressForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		String validate = null;
		// add 新增 edit 修改
			if (goodsAddress.getAddress() == null || goodsAddress.getAddress().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getName() == null || goodsAddress.getName().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getMobile() == null || goodsAddress.getMobile().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getProvince() == null || goodsAddress.getProvince() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getCity() == null || goodsAddress.getCity() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getDistrict() == null || goodsAddress.getDistrict() <= 0) {
				validate = "101";
			}

			if ("101".equals(validate)) {
				return "101";
			}
			
			//设为默认 
			if(goodsAddress.getIsDefault()==1){
				Subject subject = SecurityUtils.getSubject();
				User u = (User) subject.getPrincipal();

				goodsAddress.setCreateByUser(u.getUserId());
			}

		try {
			this.goodsAddressService.saveGoodsAddress(goodsAddress, "edit");
		} catch (DuplicateKeyException be) {
			// 重复值
			
				return "100";

		}
		
		

		return "000";
	}
	
	
	
	@RequestMapping("/goodsAddressAdd.do")
	@ResponseBody
	public String goodsAddressAdd (GoodsAddress goodsAddress) throws Exception {		
		
		String validate = null;
		// add 新增 edit 修改

			// 后端校验 101 省市县 街道 名称 编号 等不能为空

			if (goodsAddress.getProvince() == null || goodsAddress.getProvince() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getCity() == null || goodsAddress.getCity() <= 0) {
				validate = "101";
			}
			if (goodsAddress.getDistrict() == null || goodsAddress.getDistrict() <= 0) {
				validate = "101";
			}
//			if (goodsAddress.getStreet() == null || goodsAddress.getStreet() <= 0) {
//				validate = "101";
//			}

			if (goodsAddress.getAddress() == null || goodsAddress.getAddress().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getName() == null || goodsAddress.getName().length() == 0) {
				validate = "101";
			}
			
			if (goodsAddress.getMobile() == null || goodsAddress.getMobile().length() == 0) {
				validate = "101";
			}
			
			if ("101".equals(validate)) {
				return validate;
			}

			// 校验结束

			// 1  ，这里直接赋予 激活状态
			goodsAddress.setStatus(1);

			Subject subject = SecurityUtils.getSubject();
			User u = (User) subject.getPrincipal();

			goodsAddress.setCreateByUser(u.getUserId());
			
			
			goodsAddress.setGoodsAddressUuid(Common.getUUID());


		try {
			this.goodsAddressService.saveGoodsAddress(goodsAddress, "add");
		} catch (DuplicateKeyException be) {
			// 重复值
				return "100";

		}
		
		

		return goodsAddress.getGoodsAddressUuid();
	}
	
	
	
	@RequestMapping("/goodsAddressDefault.do")
	@ResponseBody
	public int goodsAddressDefault(GoodsAddress goodsAddress) throws Exception {		
		

		try {
			Subject subject = SecurityUtils.getSubject();
			User u = (User) subject.getPrincipal();

			goodsAddress.setCreateByUser(u.getUserId());
			goodsAddress.setIsDefault(1);
			this.goodsAddressService.saveGoodsAddress(goodsAddress,"edit");
		} catch (Exception be) {
			
			return 0;
		}

		return 1;
	}
	
	
	
	/**删除对象*/
	@RequestMapping("/goodsAddressDeleteSvc.do")
	@ResponseBody
	public String goodsAddressDeleteSvc(GoodsAddress goodsAddress) throws Exception {	
		this.goodsAddressService.deleteGoodsAddress(goodsAddress.getGoodsAddressUuid());
		return "000";
	}
	
	
	
	/**删除对象*/
	@RequestMapping("/goodsAddressDelete.do")
	public String goodsAddressDelete(GoodsAddress goodsAddress) throws Exception {	
		this.goodsAddressService.deleteGoodsAddress(goodsAddress.getGoodsAddressUuid());
		return "redirect:/goodsAddress/goodsAddressListPage.do";
	}	
	public List<GoodsAddress> getGoodsAddressList() {
		return this.goodsAddressList;
	}

	public void setGoodsAddressList(List<GoodsAddress> goodsAddressList) {
		this.goodsAddressList = goodsAddressList;
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
