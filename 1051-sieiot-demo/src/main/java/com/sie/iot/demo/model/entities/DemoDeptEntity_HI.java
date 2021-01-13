package com.sie.iot.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * DemoDeptEntity_HI Entity Object
 * Mon Jul 06 11:20:16 CST 2020  Auto Generate
 */
@Entity
@Table(name="demo_dept")
public class DemoDeptEntity_HI implements Serializable{
    private Long deptId; //主键
    private String deptName; //部门名称
    private String deptCode; //部门编码
    private String deptAddress; //部门地址
    private String deptContactName; //部门联系人
    private Long deptContactId; //部门联系人ID
    private String deptContactMoblie; //联系电话
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate; //创建时间
    private Long createdBy; //创建人
    private Long lastUpdatedBy; //更新人
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate; //更新时间
    private Long lastUpdateLogin; //最后登录ID
    private Integer deleteFlag = 0; //删除标识 0表示未删除 1表示删除
    private Integer versionNum; //版本号
    private Long operatorUserId;

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Id
	@GenericGenerator(name = "redisGenerationId", strategy = "com.sie.iot.common.idgenerate.RedisGenerationId")
	@GeneratedValue(generator = "redisGenerationId")
	@Column(name="dept_id", nullable=false, length=20)	
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name="dept_name", nullable=true, length=64)	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Column(name="dept_code", nullable=true, length=64)	
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	@Column(name="dept_address", nullable=true, length=255)	
	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptContactName(String deptContactName) {
		this.deptContactName = deptContactName;
	}

	@Column(name="dept_contact_name", nullable=true, length=32)	
	public String getDeptContactName() {
		return deptContactName;
	}

	public void setDeptContactId(Long deptContactId) {
		this.deptContactId = deptContactId;
	}

	@Column(name="dept_contact_id", nullable=true, length=20)	
	public Long getDeptContactId() {
		return deptContactId;
	}

	public void setDeptContactMoblie(String deptContactMoblie) {
		this.deptContactMoblie = deptContactMoblie;
	}

	@Column(name="dept_contact_moblie", nullable=true, length=20)	
	public String getDeptContactMoblie() {
		return deptContactMoblie;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="creation_date", nullable=true, length=0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_by", nullable=true, length=19)	
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="last_updated_by", nullable=true, length=19)	
	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name="last_update_date", nullable=true, length=0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name="last_update_login", nullable=true, length=19)	
	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="delete_flag", nullable=true, length=10)	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name="version_num", nullable=true, length=10)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setOperatorUserId(Long operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Long getOperatorUserId() {
		return operatorUserId;
	}
}
