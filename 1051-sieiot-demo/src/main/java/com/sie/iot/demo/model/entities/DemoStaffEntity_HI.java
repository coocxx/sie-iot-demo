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
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * DemoDeptEntity_HI Entity Object
 * Mon Jul 06 11:20:16 CST 2020  Auto Generate
 */
@Entity
@Table(name="demo_user")
public class DemoStaffEntity_HI implements Serializable{
    private Long userId; //主键
    private String userName; //使用者名称
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String userBirthdate;//使用者生日
    private String userAddress; //使用者地址
    private String userMobile; //使用者联系电话
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



    public void setUserId(Long staffId) {
        this.userId = staffId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, length=20)
    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="user_name", nullable=true, length=64)
    public String getUserName() {
        return userName;
    }

    @Column(name="user_birthdate",nullable = true,length=64)
    public String getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(String userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Column(name="user_address", nullable=true, length=255)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserMobile(String userMoblie) {
        this.userMobile = userMoblie;
    }

    @Column(name="user_mobile", nullable=true, length=20)
    public String getUserMobile() {
        return userMobile;
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