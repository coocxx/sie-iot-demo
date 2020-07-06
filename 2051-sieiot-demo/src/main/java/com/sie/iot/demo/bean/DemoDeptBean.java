package com.sie.iot.demo.bean;
import com.sie.iot.common.bean.ReqeustCommonDataBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("部门")
public class DemoDeptBean extends ReqeustCommonDataBean{
    @ApiModelProperty(value = "主键id")
    private Long deptId; //主键
    @ApiModelProperty(value = "部门名称")
    private String deptName; //部门名称
    @ApiModelProperty(value = "部门编码")
    private String deptCode; //部门编码
    @ApiModelProperty(value = "部门地址")
    private String deptAddress; //部门地址
    @ApiModelProperty(value = "部门联系人")
    private String deptContactName; //部门联系人
    @ApiModelProperty(value = "部门联系人ID")
    private Long deptContactId; //部门联系人ID
    @ApiModelProperty(value = "联系电话")
    private String deptContactMoblie; //联系电话

    private Long operatorUserId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getDeptContactName() {
        return deptContactName;
    }

    public void setDeptContactName(String deptContactName) {
        this.deptContactName = deptContactName;
    }

    public Long getDeptContactId() {
        return deptContactId;
    }

    public void setDeptContactId(Long deptContactId) {
        this.deptContactId = deptContactId;
    }

    public String getDeptContactMoblie() {
        return deptContactMoblie;
    }

    public void setDeptContactMoblie(String deptContactMoblie) {
        this.deptContactMoblie = deptContactMoblie;
    }

    public Long getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Long operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
