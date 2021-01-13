package com.sie.iot.demo.bean;
import com.alibaba.fastjson.annotation.JSONField;
import com.sie.iot.common.bean.ReqeustCommonDataBean;

import java.util.Date;

public class DemoUserBean extends ReqeustCommonDataBean{

    private Long userId; //主键
    private String userName; //名称
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date userBirthdate; //生日
    private String userAddress; //地址
    private String userMobile; //联系方式

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

}
