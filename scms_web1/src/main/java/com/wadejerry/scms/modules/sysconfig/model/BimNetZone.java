package com.wadejerry.scms.modules.sysconfig.model;

public class BimNetZone {
    private Integer netZoneId;

    private Integer bimCompanyId;

    private String name;

    private String url;

    private Integer port;

    public Integer getNetZoneId() {
        return netZoneId;
    }

    public void setNetZoneId(Integer netZoneId) {
        this.netZoneId = netZoneId;
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}