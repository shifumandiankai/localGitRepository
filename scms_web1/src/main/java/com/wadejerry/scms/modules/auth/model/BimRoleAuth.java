package com.wadejerry.scms.modules.auth.model;

import java.util.Date;

public class BimRoleAuth {
    private Integer bimRoleAuthId;

    private Integer roleId;

    private Integer bimAuthorizationId;

    private Date createTime;

    public Integer getBimRoleAuthId() {
        return bimRoleAuthId;
    }

    public void setBimRoleAuthId(Integer bimRoleAuthId) {
        this.bimRoleAuthId = bimRoleAuthId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getBimAuthorizationId() {
        return bimAuthorizationId;
    }

    public void setBimAuthorizationId(Integer bimAuthorizationId) {
        this.bimAuthorizationId = bimAuthorizationId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}