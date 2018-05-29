package com.cst.wap.weixin.oauth2;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cst.wap.weixin.oauth2.pojo.SNSUserInfo;
import com.cst.wap.weixin.oauth2.pojo.WeixinOauth2Token;
import com.cst.wap.weixin.weChatpay.utils.CommonUtil;

public class WeixinOauth2Util {

	/**
     * 获取网页授权凭证
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                /*int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");*/
            }
        }
        return wat;
    }
    
    /**
     * 通过网页授权获取用户信息
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings( { "deprecation", "unchecked" })
    public SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo(jsonObject.getString("openid"), jsonObject.getString("nickname"), jsonObject.getInt("sex"), jsonObject.getString("country"),
                		jsonObject.getString("province"), jsonObject.getString("city"), jsonObject.getString("headimgurl"),
            			null, null, null);
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadimgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                //snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
               /* int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");*/
            }
        }
        return snsUserInfo;
    }
 
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//	public static String encodeQrcode(String content, HttpServletResponse response,HttpServletRequest request,String imgpath,int width,int height) {
//		if (StringUtils.isBlank(content)){
//			return "";
//		}
//		String pngName = String.valueOf(System.currentTimeMillis());
//		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//		Map hints = new HashMap();
//		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
//		BitMatrix bitMatrix = null;
//		String format = "png";
//		String fileSeparator = System.getProperty("file.separator");
//		String path =  request.getServletContext().getRealPath(fileSeparator);
// 		path = path + imgpath;	
//		try {
//			bitMatrix = multiFormatWriter.encode(content,BarcodeFormat.QR_CODE, width, height, hints);
//			// 输出二维码图片流
//			try {
//				File outputFile = new File(path+pngName+".png");		
//				MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return pngName;
//	}
    
    /**
     * URL编码（utf-8）
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
