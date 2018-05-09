package com.wadejerry.scms.modules.auth.model;

import java.util.Date;

public class BimUserRole {
    private Integer bimUseRoleId;

    private Integer bimRoleId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private Integer custom1;

    private Integer custom2;

    private String custom3;

    private String custom4;

    public Integer getBimUseRoleId() {
        return bimUseRoleId;
    }

    public void setBimUseRoleId(Integer bimUseRoleId) {
        this.bimUseRoleId = bimUseRoleId;
    }

    public Integer getBimRoleId() {
        return bimRoleId;
    }

    public void setBimRoleId(Integer bimRoleId) {
        this.bimRoleId = bimRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCustom1() {
        return custom1;
    }

    public void setCustom1(Integer custom1) {
        this.custom1 = custom1;
    }

    public Integer getCustom2() {
        return custom2;
    }

    public void setCustom2(Integer custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3 == null ? null : custom3.trim();
    }

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4 == null ? null : custom4.trim();
    }
}