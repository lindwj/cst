package com.cst.web.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
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
		
	
	@RequestMapping("/logout.do")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			
		}
		return "redirect:/resource/electricBusiness/index.html";
	}
	
	@RequestMapping("/isLoginSvc.do")
	@ResponseBody
	public User isLoginSvc(User user) {
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
	 * 用户登录
	 * 
	 * @param currUser
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(User user) {
		// 后端校验
		String validate = null;

		// 后端校验 账号 密码 等不能为空

		if (user.getMobile() == null || user.getMobile().length() != 11) {
			validate = "101";
		}

		if (user.getPassword() == null || user.getPassword().length() == 0) {
			validate = "101";
		}
		if ("101".equals(validate)) {
			return "redirect:/resource/jsp/login.jsp?errcode=101";
		}

		Subject sub = SecurityUtils.getSubject();

		// 加盐 md5加密
		user.setPassword(Common.Md5(user.getPassword() + Common.salt));

		UsernamePasswordToken token = new UsernamePasswordToken(user.getMobile(), user.getPassword());

//		token.setRememberMe(true);
		try {
			sub.login(token);
			
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
						//返回商品详情页  redirect:/orders/ordersAddEditIni.do?productUuid="+preUrlFlag.split(",")[0]+"&capacity="+preUrlFlag.split(",")[1]
						
//						http://www.beidahuang.com/resource/electricBusiness/orderNow.html?productUuid=111&showCapacity=111&capacity=111
						return "redirect:/resource/electricBusiness/orderNow.html?productUuid="+preUrlFlag.split(",")[0]+"&capacity="+preUrlFlag.split(",")[1];
					}else{
						//返回购物车
						return "redirect:/resource/electricBusiness/shoppingMarket.html";
					}
			
			
			}
			
			
			return "redirect:/resource/electricBusiness/index.html";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/resource/jsp/login.jsp?errcode=001";
		}
	}

	/** 执行搜索 */
	public String userListPage() throws Exception {
		// userForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		User user = null;
		userList = userService.getUserListPage(user);
		return "list";
	}

	/** 编辑前初始化对象 */
	public String userAddEditIni() throws Exception {
		User user = null;
		if ("edit".equals(doWhat)) {
			user = this.userService.getUserById(user.getUserId());
		}
		return "addedit";
	}

	/** 查看对象 */
	public String userDetail() throws Exception {
		// this.setPare_moduleid(14);
		User user = null;
		user = this.userService.getUserById(user.getUserId());
		return "detail";
	}

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		User user = null;
		user = this.userService.getUserById(user.getUserId());
		return "detail";
	}

	/** 保存新增对象 */
	@RequestMapping("/userAddEdit.do")
	public ModelAndView userAddEdit(User user, ModelAndView model) throws Exception {
		String validate = null;

		if (true) {

			// 后端校验 账号 密码 验证码 等不能为空

//			if (user.getNickname() == null || user.getNickname().length() > 40) {
//				validate = "101";
//			}
			
			if (user.getMobile() == null || user.getMobile().length() != 11) {
				validate = "101";
			}

			if (user.getCaptcha() == null || user.getCaptcha().length() != 4) {
				validate = "101";
			}
			if (user.getPassword() == null || user.getPassword().length() == 0) {
				validate = "101";
			}
			if ("101".equals(validate)) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/resource/jsp/signup.jsp");
				return model;
			}

			// 验证码校验
			List<String> sList = null;
			Subject subject = SecurityUtils.getSubject();
			Object ob = subject.getSession().getAttribute("captcha");
			if (ob != null) {
				sList = (List<String>) ob;
				// 验证不通过
				if (!user.getCaptcha().equals(sList.get(1))) {
					model.addObject("errcode", "102");
					model.setViewName("redirect:/resource/jsp/signup.jsp");
					return model;
				}

				subject.getSession().removeAttribute("captcha");

			} else {
				// 未获取验证码
				model.addObject("errcode", "103");
				model.setViewName("redirect:/resource/jsp/signup.jsp");
				return model;

			}

			// 校验结束

			if ("add".equals(user.getDowhat())) {
				// 1 表示 激活 ，这里直接赋予激活状态
				user.setStatus(1);
			}

			// 加盐 md5加密
			user.setPassword(Common.Md5(user.getPassword() + Common.salt));

		}

		try {
			this.userService.saveUser(user, user.getDowhat());
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");
			model.setViewName("redirect:/resource/jsp/signup.jsp");
			return model;

		}
		// 成功
		model.addObject("errcode", "000");
		model.setViewName("redirect:/resource/jsp/login.jsp");
		return model;
	}

	/** 删除对象 */
	public String userDelete() throws Exception {
		User user = null;
		this.userService.deleteUser(user.getUserId());
		return "list";
	}

	@RequestMapping("/getMobileCaptcha.do")
	@ResponseBody
	public int getMobileCaptcha(User user) throws Exception {
		int ret = 0;
		//只有注册用户时 进入判断
		if(user.getRegistType()==1){
		//判断手机是否注册
		User userTemp = this.userService.getUserByMobile(user);
		
		if(userTemp!=null){
			return 2;
		}
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
