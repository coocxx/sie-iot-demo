package com.sie.iot.demo.bean;
import com.sie.iot.common.bean.ReqeustCommonDataBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("使用者")
public class DemoStaffBean extends ReqeustCommonDataBean{
    @ApiModelProperty(value = "主键id")
    private Long userId; //主键
    @ApiModelProperty(value = "使用者名称")
    private String userName; //部门名称
    @ApiModelProperty(value = "使用者地址")
    private String userAddress; //部门地址
    @ApiModelProperty(value = "使用者联系电话")
    private String userMobile; //联系电话

    private Long operatorUserId;

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

    public Long getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Long operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
