package com.cst.wap.weixin.oauth2.pojo;

import java.util.Date;
import java.util.List;

/**
* 类名: SNSUserInfo </br>
* 描述: 通过网页授权获取的用户信息 </br>
 */
public class SNSUserInfo {
	// 用户标识
    private String openId;
    // 用户昵称
    private String nickname;
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headimgUrl;
    // 用户特权信息
    private List<String> privilegeList;
    
    private String oauthStatus;
	private Date oauthTime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public List<String> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }

	public String getOauthStatus() {
		return oauthStatus;
	}

	public void setOauthStatus(String oauthStatus) {
		this.oauthStatus = oauthStatus;
	}

	public Date getOauthTime() {
		return oauthTime;
	}

	public void setOauthTime(Date oauthTime) {
		this.oauthTime = oauthTime;
	}

	/**
	 * @param openId
	 * @param nickname
	 * @param sex
	 * @param country
	 * @param province
	 * @param city
	 * @param headimgUrl
	 * @param privilegeList
	 * @param oauthStatus
	 * @param oauthTime
	 */
	public SNSUserInfo(String openId, String nickname, int sex, String country,
			String province, String city, String headimgUrl,
			List<String> privilegeList, String oauthStatus, Date oauthTime) {
		super();
		this.openId = openId;
		this.nickname = nickname;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.headimgUrl = headimgUrl;
		this.privilegeList = privilegeList;
		this.oauthStatus = oauthStatus;
		this.oauthTime = oauthTime;
	}
	
	public SNSUserInfo(SNSUserInfo s) {
		super();
		this.openId = s.getOpenId();
		this.nickname = s.getNickname();
		this.sex = s.getSex();
		this.country = s.getCountry();
		this.province = s.getProvince();
		this.city = s.getCity();
		this.headimgUrl = s.getHeadimgUrl();
		this.privilegeList = s.getPrivilegeList();
		this.oauthStatus = s.getOauthStatus();
		this.oauthTime = s.getOauthTime();
	}

	public SNSUserInfo() {
		super();
	}

}
