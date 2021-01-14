package com.sie.iot.demo.bean;
import com.sie.iot.common.bean.ReqeustCommonDataBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户")
public class PracUserBean extends ReqeustCommonDataBean {
    @ApiModelProperty(value = "主键id")
    private Long userId;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户地址")
    private String userAddress;
    @ApiModelProperty(value = "联系电话")
    private String userMobile;

    private Long operatorUserId;

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserAddress() { return userAddress; }

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserMobile() { return userMobile; }

    public void setUserMobile(String userMobile) { this.userMobile = userMobile; }

    public Long getOperatorUserId() { return operatorUserId; }

    public void setOperatorUserId(Long operatorUserId) { this.operatorUserId = operatorUserId; }
}
