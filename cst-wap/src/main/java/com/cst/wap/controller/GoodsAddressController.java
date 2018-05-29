package com.cst.wap.controller;

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
import com.cst.service.model.Nation;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.GoodsAddressService;
import com.cst.service.NationService;
import com.cst.service.dao.GoodsAddressMapper;



/**
 * @author lind
 */

@Controller
@RequestMapping("/goodsAddress")
public class GoodsAddressController{
	
	// 业务逻辑对象
	@Autowired
	private GoodsAddressService goodsAddressService;
	@Autowired
	private NationService nationService;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	
	// 查询结果
	private List<GoodsAddress> goodsAddressList;
	
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	/**判断是否有地址*/
	@RequestMapping("/isGoodsAddressWx.do")
	@ResponseBody
	public int isGoodsAddressWx() throws Exception{
		GoodsAddress goodsAddress=new GoodsAddress();
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			User user = (User)subject.getPrincipal();
			goodsAddress.setCreateByUser(user.getUserId());
			goodsAddressList=goodsAddressService.getGoodsAddressListPageWx(goodsAddress);
			if(goodsAddressList.size()==0||goodsAddressList==null){
				return 0;
			}else{
				return 1;
			}
		}else{
			return -1;
		}
	}
	
	/**获取默认地址或者第一个地址*/
	@RequestMapping("/getDefaultAddressWx.do")
	@ResponseBody
	public GoodsAddress getDefaultAddressWx() throws Exception{
		GoodsAddress goodsAddress=new GoodsAddress();
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		goodsAddress.setCreateByUser(user.getUserId());
		goodsAddressList=goodsAddressService.getGoodsAddressListPageWx(goodsAddress);
		GoodsAddress g=null;
		for(int i=0; i<goodsAddressList.size();i++){
			if(goodsAddressList.get(i).getIsDefault()==1){
				g = goodsAddressList.get(i);
			}
		}
		if(g==null){
			g=goodsAddressList.get(0);
		}
		return g;
	}
	/**获取默认地址或者第一个地址(未登录)*/
	@RequestMapping("/getDefaultAddressWxOpen.do")
	@ResponseBody
	public GoodsAddress getDefaultAddressWxOpen(GoodsAddress goodsAddress) throws Exception{
		goodsAddressList=goodsAddressService.getGoodsAddressListPageWxOpen(goodsAddress);
		GoodsAddress g=new GoodsAddress();
		if(goodsAddressList.size()==0||goodsAddressList == null){
			g.setGoodsAddressUuid(null);
		}else{
			for(int i=0; i<goodsAddressList.size();i++){
				if(goodsAddressList.get(i).getIsDefault()==1){
					g = goodsAddressList.get(i);
				}
			}
			if(g==null){
				g=goodsAddressList.get(0);
			}
		}
		return g;
	}
	
	/**根据区取街道*/
	@RequestMapping("/getStreetWx.do")
	@ResponseBody
	public List<Nation> getStreetWx(Nation nation) throws Exception{
		Nation n=nationService.getNationByCode(nation.getCode());
		List<Nation> nations=nationService.getNationByParentId(n.getId());
		return nations;
	}
	
	/**微信地址管理分页(登录) */
	@RequestMapping("/addressListPageWx.do")
	@ResponseBody
	public List<GoodsAddress> addressListPageWx(GoodsAddress goodsAddress) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		goodsAddress.setCreateByUser(user.getUserId());
		goodsAddressList = goodsAddressService.getGoodsAddressListPageWx(goodsAddress);
		//判断是否没有地址
		return goodsAddressList;
	}
	
	/**微信地址管理分页(未登录) */
	@RequestMapping("/addressListPageWxOpen.do")
	@ResponseBody
	public List<GoodsAddress> addressListPageWxOpen(GoodsAddress goodsAddress) throws Exception{
		goodsAddressList = goodsAddressService.getGoodsAddressListPageWxOpen(goodsAddress);
		//判断是否没有地址
		return goodsAddressList;
	}
	
	/** 微信地址回填 */
	@RequestMapping("/addressGetWx.do")
	@ResponseBody
	public GoodsAddress goodsAddressAddEdit (GoodsAddress goodsAddress) throws Exception {		
		GoodsAddress g=new GoodsAddress();
		if(goodsAddress.getGoodsAddressUuid()!=null){
			g=goodsAddressService.getGoodsAddressByIdWx(goodsAddress.getGoodsAddressUuid());
			Nation n=nationService.getNationById(g.getProvince());
			Nation na=nationService.getNationById(g.getCity());
			Nation nat=nationService.getNationById(g.getDistrict());
			g.setProvincestr(n.getProvince());
			g.setCitystr(na.getCity());
			g.setDistrictstr(nat.getDistrict());
			g.setProvinceCode(n.getCode());
			g.setCityCode(na.getCode());
			g.setDistrictCode(nat.getCode());
			return g;
		}else{
			return goodsAddress;
		}
	}
	
	
	
	/** 微信地址添加 */
	@RequestMapping("/addressSaveWx.do")
	@ResponseBody
	public int addressSaveWx (GoodsAddress goodsAddress) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		User u = (User) subject.getPrincipal();
		if(goodsAddress.getGoodsAddressUuid()==null||goodsAddress.getGoodsAddressUuid()==""||goodsAddress.getGoodsAddressUuid()=="undefined"){
			List<Nation> n=nationService.getNationByCodeWx(goodsAddress.getProvince().toString());
			List<Nation> na=nationService.getNationByCodeWx(goodsAddress.getCity().toString());
			List<Nation> nat=nationService.getNationByCodeWx(goodsAddress.getDistrict().toString());
			goodsAddress.setProvince(n.get(0).getId());
			goodsAddress.setCity(na.get(0).getId());
			goodsAddress.setDistrict(nat.get(0).getId());
			goodsAddress.setStatus(2);
			goodsAddress.setCreateByUser(u.getUserId());
			goodsAddress.setGoodsAddressUuid(Common.getUUID());
			goodsAddressService.saveGoodsAddress(goodsAddress, "add");
			//添加
			return 0;
		}else{
			List<Nation> n=nationService.getNationByCodeWx(goodsAddress.getProvince().toString());
			List<Nation> na=nationService.getNationByCodeWx(goodsAddress.getCity().toString());
			List<Nation> nat=nationService.getNationByCodeWx(goodsAddress.getDistrict().toString());
			goodsAddress.setProvince(n.get(0).getId());
			goodsAddress.setCity(na.get(0).getId());
			goodsAddress.setDistrict(nat.get(0).getId());
			goodsAddress.setCreateByUser(u.getUserId());
			goodsAddressService.saveGoodsAddress(goodsAddress, "edit");
			//修改
			return 1;
		}
	}
	/** 微信地址添加（未登录） */
	@RequestMapping("/addressSaveWxOpen.do")
	@ResponseBody
	public int addressSaveWxOpen (GoodsAddress goodsAddress) throws Exception {
		if(goodsAddress.getGoodsAddressUuid()==null||goodsAddress.getGoodsAddressUuid()==""||goodsAddress.getGoodsAddressUuid()=="undefined"){
			List<Nation> n=nationService.getNationByCodeWx(goodsAddress.getProvince().toString());
			List<Nation> na=nationService.getNationByCodeWx(goodsAddress.getCity().toString());
			List<Nation> nat=nationService.getNationByCodeWx(goodsAddress.getDistrict().toString());
			goodsAddress.setProvince(n.get(0).getId());
			goodsAddress.setCity(na.get(0).getId());
			goodsAddress.setDistrict(nat.get(0).getId());
			goodsAddress.setStatus(2);
			goodsAddress.setGoodsAddressUuid(Common.getUUID());
			goodsAddressService.saveGoodsAddressOpen(goodsAddress, "add");
			//添加
			return 0;
		}else{
			List<Nation> n=nationService.getNationByCodeWx(goodsAddress.getProvince().toString());
			List<Nation> na=nationService.getNationByCodeWx(goodsAddress.getCity().toString());
			List<Nation> nat=nationService.getNationByCodeWx(goodsAddress.getDistrict().toString());
			goodsAddress.setProvince(n.get(0).getId());
			goodsAddress.setCity(na.get(0).getId());
			goodsAddress.setDistrict(nat.get(0).getId());
			goodsAddressService.saveGoodsAddressOpen(goodsAddress, "edit");
			//修改
			return 1;
		}
	}
	
	//选取默认地址
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
	
	//选取默认地址(未登录)
	@RequestMapping("/goodsAddressDefaultOpen.do")
	@ResponseBody
	public int goodsAddressDefaultOpen(GoodsAddress goodsAddress) throws Exception {		
		try {
			goodsAddress.setIsDefault(1);
			this.goodsAddressService.saveGoodsAddressOpen(goodsAddress,"edit");
		} catch (Exception be) {
			return 0;
		}
		
		return 1;
	}
	
	
	/**删除对象*/
	@RequestMapping("/goodsAddressDeleteWx.do")
	@ResponseBody
	public int goodsAddressDeleteWx(GoodsAddress goodsAddress) throws Exception {
		if(goodsAddress.getGoodsAddressUuid()==null||goodsAddress.getGoodsAddressUuid()==""||goodsAddress.getGoodsAddressUuid()=="undefined"){
			return 0;
		}else{
			goodsAddressService.deleteGoodsAddress(goodsAddress.getGoodsAddressUuid());
			return 1;
		}
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
