package com.wadejerry.scms.modules.auth.model;

public class BimAuthorization {
    private Integer bimAuthorizationId;

    private Integer pid;

    private String authName;

    private String identity;

    public Integer getBimAuthorizationId() {
        return bimAuthorizationId;
    }

    public void setBimAuthorizationId(Integer bimAuthorizationId) {
        this.bimAuthorizationId = bimAuthorizationId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }
}