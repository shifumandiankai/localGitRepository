package com.wadejerry.scms.modules.auth.model;

import java.util.Date;

public class BimRoleAreaAuth {
    private Integer id;

    private Integer bimRoleId;

    private Integer acmAreaId;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBimRoleId() {
        return bimRoleId;
    }

    public void setBimRoleId(Integer bimRoleId) {
        this.bimRoleId = bimRoleId;
    }

    public Integer getAcmAreaId() {
        return acmAreaId;
    }

    public void setAcmAreaId(Integer acmAreaId) {
        this.acmAreaId = acmAreaId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}