package com.cst.wap.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
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
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.CartService;
import com.cst.service.ManagerService;
import com.cst.service.UserService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/user")
public class UserController {

	// 业务逻辑对象
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private  HttpServletResponse response;
	
	@Autowired
	private CartService cartService;

	// 查询结果
	private List<User> userList;

	private Map<String, Object> session;
	private String doWhat;
	private int pare_moduleid;

	//是否有用户
	@RequestMapping("/isUser.do")
	@ResponseBody
	public int isUser(User user) {
		User u=userService.getUserByOpenid(user);
		if(u!=null){
			Date now=new Date();
			u.setBindDate(now);
			u.setAgentId(user.getAgentId());
			if(u.getBindState()==0){
				userService.saveUser(u, "edit");
				return 2;//未绑定
			}else{
				return 1;//已经完全绑定过了
			}
		}else{
			return 0;
		}
	}
	
	//登录接口（微代）
	@RequestMapping("/isLoginWd.do")
	@ResponseBody
	public int isLoginWd(User user) throws Exception{
		Manager manager=new Manager();
		manager.setAccount(user.getMobile());
		Manager ma=managerService.getManagerAcc(manager);
		if(ma!=null){
			return -4;
		}else{
			User u=userService.getUserByMobile(user);
			if(u==null){
				return -1;//没有该用户去注册
			}else{
				if(u.getOpenId()==null){
					u.setOpenId(user.getOpenId());
					userService.saveUser(u, "edit");
				}else{
					
				}
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
					Subject sub = SecurityUtils.getSubject();
					user.setPassword(Common.Md5(user.getPassword() + Common.salt));
					UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
	//				token.setRememberMe(true);
					try {
						sub.login(token);
						Date now=new Date();
						Manager m=new Manager();
						m.setRoleId(10);
						m.setAccount(user.getMobile());
						m.setPassword(Common.Md5(user.getPassword() + Common.salt));
						m.setDowhat("add");
						m.setOpenId(user.getOpenId());
						m.setNickname(user.getNicknameWd());
						m.setPicWx(user.getPicWx());
						m.setMobile(user.getMobile());
						m.setStatus(1);
						m.setName(u.getNickname());
						m.setCreateDate(now);
						managerService.saveManager(m, m.getDowhat());
						return 1;
					} catch (AuthenticationException e) {
						e.printStackTrace();
						token.clear();
						//账号密码不匹配
						return -3;//密码错误
					}
				}else{
					Subject sub = SecurityUtils.getSubject();
					user.setPassword(Common.Md5(user.getPassword() + Common.salt));
					UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
	//				token.setRememberMe(true);
					try {
						sub.login(token);
						return -2;//跳转提示页面
					} catch (AuthenticationException e) {
						e.printStackTrace();
						token.clear();
						//账号密码不匹配
						return -3;//密码错误
					}
				}
			}
		}
	}
	
	//用户总粉丝数
	@RequestMapping("/getFans.do")
	@ResponseBody
	public int getFans(User user) {
		List<User> u=userService.getUserByAgents(user);
		return u.size();
	}
	
	//用户当日粉丝
	@RequestMapping("/getFan.do")
	@ResponseBody
	public int getFan(User user) {
		List<User> u=userService.getUserByAgent(user);
		return u.size();
	}
	
	//注册（微代客户）
	@RequestMapping("/registerWd.do")
	@ResponseBody
	public int registerWd(User user) {
		User u=userService.getUserByMobile(user);
		if(u!=null){
			return -3;
		}else{
			// 验证码校验
			List<String> sList = null;
			Subject subject = SecurityUtils.getSubject();
			Object ob = subject.getSession().getAttribute("captcha");
			if (ob != null) {
				sList = (List<String>) ob;
				// 验证不通过
				if (!user.getCaptcha().equals(sList.get(1))) {
					return -1;
				}else{
					subject.getSession().removeAttribute("captcha");
					user.setPassword(Common.Md5(user.getPassword() + Common.salt));
					Date now=new Date();
					user.setBindDate(now);
					user.setStatus(1);
					user.setRegistType(1);
					userService.saveUser(user, "add");
					return 1;
				}
			} else {
				// 未获取验证码
				return -2;
			}
		}
	}
	
	
	//登陆（微代客户）
	@RequestMapping("/loginWd.do")
	@ResponseBody
	public int loginWd(User user) {
		User u=userService.getUserByMobile(user);
		Subject sub = SecurityUtils.getSubject();
		// 密码md5加密
		user.setPassword(Common.Md5(user.getPassword() + Common.salt));
		UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
//		token.setRememberMe(true);
		if(u!=null){
			try {
				sub.login(token);
				Date now=new Date();
				user.setBindDate(now);
				user.setStatus(1);
				user.setRegistType(1);
				userService.saveUser(user, "edit");
				return 1;
			} catch (AuthenticationException e) {
				e.printStackTrace();
				token.clear();
				//账号密码不匹配
				return -1;
			}
		}else{
			return -3;
		}
	}
	
	//退出登录
	@RequestMapping("/logoutWx.do")
	@ResponseBody
	public int logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			return 1;
		}else{
			return 0;
		}
	}
	
	//判断是否登录
	@RequestMapping("/isLoginWx.do")
	@ResponseBody
	public User isLoginSvc() {
		User user=new User();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			user = (User)subject.getPrincipal();
			user.setPassword(null);
			user.setStatus(1);
		}else{
			user.setStatus(0);
		}
		return user;
	}
	
	/**
	 * 微信端用户登录
	 */
	@RequestMapping("/loginWx.do")
	@ResponseBody
	public int loginWx(User user) {
		Subject sub = SecurityUtils.getSubject();
		// 密码md5加密
		user.setPassword(Common.Md5(user.getPassword() + Common.salt));
		UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
//		token.setRememberMe(true);
		try {
			sub.login(token);
			User u=userService.getUserByOpenid(user);
			if(u!=null){
			}else{
				userService.saveUser(user, "edit");
			}
			Subject subject = SecurityUtils.getSubject();
			//是否登陆
			if(subject.isAuthenticated()){
				user = (User)subject.getPrincipal();
				//登陆成功 同步cookie购物车数据
				//cookie 获取数据
				Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
				if(bdhcookie!=null){
					String value= URLDecoder.decode(bdhcookie.getValue());
					//cookie不能是空值
					if(value.length()>5){
						String[] bdnlist = value.split(";");
						// 保存数据库
						Cart ca =new Cart();
						for(String st : bdnlist){
							ca.setNum(Integer.valueOf(st.split(":")[1]));
							ca.setProductUuid(st.split(":")[0]);
							ca.setCreateByUser(user.getUserId());
							Cart temp = this.cartService.getCartById(ca);
							if(temp!=null){
								ca.setNum(temp.getNum()+ca.getNum());
								this.cartService.saveCart(ca, "edit");
							}else{
								this.cartService.saveCart(ca, "add");	
							}
						}
						value= URLEncoder.encode("");
						Common.addCookie( response,"bdhcookie",value);
					}
				}
			}
			//判断登陆前是否有 购买操作，回到之前操作地址
			if(subject.getSession().getAttribute("preUrlFlag") != null){
				String preUrlFlag = String.valueOf(subject.getSession().getAttribute("preUrlFlag"));
				subject.getSession().removeAttribute("preUrlFlag");
				if(preUrlFlag.length()>3){
						//返回商品详情页
						return 1;
				}else{
						//返回购物车
						return 2;
				}
			}
				//首页
				return 3;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			//账号密码不匹配
			return -1;
		}
	}

	/** 微信端忘记密码 */
	@RequestMapping("/forgetWx.do")
	@ResponseBody
	public int forgetWx(User user) throws Exception {
		// 验证码校验
		List<String> sList = null;
		Subject subject = SecurityUtils.getSubject();
		Object ob = subject.getSession().getAttribute("captcha");
		//判断手机是否注册
		User userTemp = this.userService.getUserByMobile(user);
		if(userTemp!=null){
			if (ob != null) {
				sList = (List<String>) ob;
				// 验证不通过
				if (!user.getCaptcha().equals(sList.get(1))) {
					return -1;
				}
				subject.getSession().removeAttribute("captcha");
			} else {
				// 未获取验证码
				return -2;
			}
			// 校验结束
			if ("edit".equals(user.getDowhat())) {
				// 1 表示 激活 ，这里直接赋予激活状态
				user.setStatus(1);
				user.setRegistType(1);
			}else{
				
			}
			// 密码 md5加密
			user.setPassword(Common.Md5(user.getPassword() + Common.salt));
			this.userService.saveUser(user, user.getDowhat());
			// 成功
			return 1;
		}else{
			//账号没有被注册
			return -3;
		}
		
	}

	/** 微信端注册 */
	@RequestMapping("/userSaveWx.do")
	@ResponseBody
	public int userSaveWx(User user) throws Exception {
		// 验证码校验
		List<String> sList = null;
		Subject subject = SecurityUtils.getSubject();
		Object ob = subject.getSession().getAttribute("captcha");
		if (ob != null) {
			sList = (List<String>) ob;
			// 验证不通过
			if (!user.getCaptcha().equals(sList.get(1))) {
				return -1;
			}
			subject.getSession().removeAttribute("captcha");
		} else {
			// 未获取验证码
			return -2;
		}
		// 校验结束
		if ("add".equals(user.getDowhat())) {
			// 1 表示 激活 ，这里直接赋予激活状态
			user.setStatus(1);
			user.setRegistType(1);
		}
		// 密码 md5加密
		user.setPassword(Common.Md5(user.getPassword() + Common.salt));
		try {
				this.userService.saveUser(user, user.getDowhat());
				Subject sub = SecurityUtils.getSubject();
				// 密码md5加密
				user.setPassword(Common.Md5(user.getPassword() + Common.salt));
				UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());
//				token.setRememberMe(true);
				sub.login(token);
				Subject subjects = SecurityUtils.getSubject();
				//是否登陆
				if(subjects.isAuthenticated()){
					user = (User)subjects.getPrincipal();
					//登陆成功 同步cookie购物车数据
					//cookie 获取数据
					Cookie bdhcookie= Common.getCookieByName(request, "bdhcookie");
					if(bdhcookie!=null){
						String value= URLDecoder.decode(bdhcookie.getValue());
						//cookie不能是空值
						if(value.length()>5){
							String[] bdnlist = value.split(";");
							// 保存数据库
							Cart ca =new Cart();
							for(String st : bdnlist){
								ca.setNum(Integer.valueOf(st.split(":")[1]));
								ca.setProductUuid(st.split(":")[0]);
								ca.setCreateByUser(user.getUserId());
								Cart temp = this.cartService.getCartById(ca);
								if(temp!=null){
									ca.setNum(temp.getNum()+ca.getNum());
									this.cartService.saveCart(ca, "edit");
								}else{
									this.cartService.saveCart(ca, "add");	
								}
							}
							value= URLEncoder.encode("");
							Common.addCookie( response,"bdhcookie",value);
						}
					}
				}
			} catch (DuplicateKeyException be) {
				// 重复值手机已被注册
				return -3;
			}
		// 成功
		return 1;
	}

	/** 删除对象 */
	public String userDelete() throws Exception {
		User user = null;
		this.userService.deleteUser(user.getUserId());
		return "list";
	}

	@RequestMapping("/getMobileCaptchaWx.do")
	@ResponseBody
	public int getMobileCaptchaWx(User user) throws Exception {
		int ret = 0;
		//判断手机是否注册
		User userTemp = this.userService.getUserByMobile(user);
		if(userTemp!=null){
			return 2;
		}
		try {
			String rets = Common.getMobileCaptcha(user.getMobile());
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
	
	@RequestMapping("/getMobileCodeWx.do")
	@ResponseBody
	public int getMobileCodeWx(User user) throws Exception {
		int ret = 0;
		try {
			String rets = Common.getMobileCaptcha(user.getMobile());
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

	public List<User> getUserList() {
		return this.userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
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
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
