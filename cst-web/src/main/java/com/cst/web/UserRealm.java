package com.cst.web;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cst.service.UserService;
import com.cst.service.model.User;

public class UserRealm extends AuthorizingRealm{
	
	
	@Autowired
	private UserService userService;
	
	
	
	
	

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        
        User user =new User();
        user.setMobile(username);
        user.setPassword(password);
        
        user = userService.login(user);

        if (user != null) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password.toCharArray(),"user");
            
            return info;
       
			}
        return null;
    }






	
	/**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//    	Manager mng = (Manager) principals.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(null);
//        authorizationInfo.setStringPermissions(null);  
//        Map<String,Object> mso =managerService.findRoles(mng.getManagerId());
        
//        if(mso.get("set")!=null ){
        	
//        	Set<String> roleset =(Set<String>)mso.get("set");
//        	if(roleset!=null&&roleset.size()>0){
//        		authorizationInfo.setRoles(roleset);
//        	}
//        	
//        	
//        	String roleids = (String)mso.get("roleids");
//        	if(roleids!=null&&roleids.length()>0){
//        		
//        		Set<String> permissions =managerService.findPermissions(roleids);
//        		
//        		if(permissions!=null){
//        		authorizationInfo.setStringPermissions(permissions);    
//        		}
//        	}
        	
//        }
        

        return null;
    }

    
    


}
