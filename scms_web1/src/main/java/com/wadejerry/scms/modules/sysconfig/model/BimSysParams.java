package com.wadejerry.scms.modules.sysconfig.model;

public class BimSysParams {
    private Integer bimSysParamsId;

    private String paramId;

    private String paramName;

    private Integer valueType;

    private Integer intValue;

    private String strValue;

    private String remark;

    private Integer configType;

    private Integer configOrder;

    public Integer getBimSysParamsId() {
        return bimSysParamsId;
    }

    public void setBimSysParamsId(Integer bimSysParamsId) {
        this.bimSysParamsId = bimSysParamsId;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId == null ? null : paramId.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue == null ? null : strValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public Integer getConfigOrder() {
        return configOrder;
    }

    public void setConfigOrder(Integer configOrder) {
        this.configOrder = configOrder;
    }
}