package com.cst.wap.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

import com.cst.service.model.Cart;
import com.cst.service.model.Manager;
import com.cst.service.model.ManagerForm;
import com.cst.service.model.Nation;
import com.cst.service.model.Shop;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.ManagerService;
import com.cst.service.UserService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/manager")
public class ManagerController{
	
	// 业务逻辑对象
	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	
	// 查询结果
	private List<Manager> managerList;
	
	private ManagerForm managerForm=new ManagerForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	//获取验证码
	@RequestMapping("/getMobileCaptcha.do")
	@ResponseBody
	public int getMobileCaptcha(Manager manager) throws Exception {
		Manager m=this.managerService.getManager(manager);
		if(m!=null){
			int ret = 0;
			try {
				String rets = Common.getMobileCaptcha(manager.getMobile());
	
				if (rets.contains("ok")) {
					ret = 0;
				} else {
					ret = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				ret = 1;
			}
			return ret;
		}else{
			return -1;
		}
	}
	
	//获取验证码
	@RequestMapping("/getMobileCaptchaWd.do")
	@ResponseBody
	public int getMobileCaptchaWd(Manager manager) throws Exception {
		Manager m=this.managerService.getManagerAcc(manager);
		if(m!=null){
			return -1;
		}else{
			int ret = 0;
			try {
				String rets = Common.getMobileCaptcha(manager.getAccount());
				if (rets.contains("ok")) {
					ret = 0;
				} else {
					ret = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				ret = 1;
			}
			return ret;
		}
	}
	
	
	//判断是否有账号
	@RequestMapping("/isManager.do")
	@ResponseBody
	public int isManager(Manager manager) throws Exception {
		Manager m = managerService.getManagerId(manager);
		if(m!=null){
			return 1;
		}else{
			return 0;
		}
	}
	
	//判断是否有账号
	@RequestMapping("/getManagerOpenid.do")
	@ResponseBody
	public Manager getManagerOpenid(Manager manager) throws Exception {
		Manager m = managerService.getManagerId(manager);
		return m;
	}
		
	
	/** 微信代理注册 */
	@RequestMapping("/agencySaveWx.do")
	@ResponseBody
	public int agencySaveWx(Manager manager) throws Exception {
		Manager ma=managerService.getManagerAcc(manager);
		if(ma!=null){
			return -4;
		}else{
			// 验证码校验
			User user=new User();
			user.setMobile(manager.getMobile());
			manager.setRoleId(10);
			User u=userService.getUserByMobile(user);
			if(u!=null){
				User user2 = userService.getMoneys(u);
				User user3 = userService.getMoneysOpenid(u);
				double a;
				double b;
				if(user2==null){
					a=0;
				}else{
					a=user2.getPrice();
				}
				if(user3==null){
					b=0;
				}else{
					b=user3.getPrice();
				}
				if((a+b)>3200||(a+b)==3200){
					Date now=new Date();
					Manager m=new Manager();
					m.setAccount(manager.getMobile());
					m.setPassword(Common.Md5(manager.getPassword() + Common.salt));
					m.setDowhat("add");
					m.setRoleId(10);
					m.setOpenId(manager.getOpenId());
					m.setNickname(URLDecoder.decode(manager.getNickname(), "UTF-8"));
					m.setPicWx(manager.getPicWx());
					m.setMobile(manager.getMobile());
					m.setStatus(1);
					m.setName(manager.getName());
					m.setCreateDate(now);
					managerService.saveManager(m, m.getDowhat());
					return 1;
				}else{
					return -3;
				}
			}else{
				List<String> sList = null;
				Subject subject = SecurityUtils.getSubject();
				Object ob = subject.getSession().getAttribute("captcha");
				if (ob != null) {
					sList = (List<String>) ob;
					// 验证不通过
					if (!manager.getCaptcha().equals(sList.get(1))) {
						return -1;
					}else{
						subject.getSession().removeAttribute("captcha");
						User u1=new User();
						u1.setMobile(manager.getMobile());
						u1.setPassword(Common.Md5(manager.getPassword() + Common.salt));
						u1.setNickname(manager.getName());
						u1.setOpenId(manager.getOpenId());
						u1.setRegistType(1);
						u1.setStatus(1);
						u1.setDowhat("add");
						userService.saveUser(u1, u1.getDowhat());
						User u2=new User();
						u2.setOpenId(manager.getOpenId());
						User u3 = userService.getMoneysOpenid(u2);
						double c;
						if(u3==null){
							c=0;
						}else{
							c=u3.getPrice();
						}
						if(c>3200||c==3200){
							Date now=new Date();
							Manager m=new Manager();
							m.setAccount(manager.getMobile());
							m.setPassword(Common.Md5(manager.getPassword() + Common.salt));
							m.setDowhat("add");
							m.setRoleId(10);
							m.setOpenId(manager.getOpenId());
							m.setNickname(manager.getNickname());
							m.setPicWx(manager.getPicWx());
							m.setMobile(manager.getMobile());
							m.setStatus(1);
							m.setName(manager.getName());
							m.setCreateDate(now);
							managerService.saveManager(m, m.getDowhat());
							return 1;
						}else{
							return -3;
						}
					}
				} else {
					// 未获取验证码
					return -2;
				}
			}
		}
	}

	public ManagerForm getModel() {
		return managerForm;
	}
	
	public List<Manager> getManagerList() {
		return this.managerList;
	}

	public void setManagerList(List<Manager> managerList) {
		this.managerList = managerList;
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
