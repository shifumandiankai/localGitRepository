package com.wadejerry.scms.webservice.server.support.pfm.dto;


/**
* 按次收费 
* @ClassName: PertimeDto
* @Description: TODO
* @author zhanying
* @date 2017年2月8日 下午4:16:37
*
 */
public class PertimeDto {
	 private Integer pertimeId;

	    private double chargeMoney;

	    private Integer custom1;

	    private Integer custom2;

	    private String custom3;

	    private String custom4;

	    private Integer ruleId;

	    public Integer getPertimeId() {
	        return pertimeId;
	    }

	    public void setPertimeId(Integer pertimeId) {
	        this.pertimeId = pertimeId;
	    }

	    public double getChargeMoney() {
	        return chargeMoney;
	    }

	    public void setChargeMoney(double chargeMoney) {
	        this.chargeMoney = chargeMoney;
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

	    public Integer getRuleId() {
	        return ruleId;
	    }

	    public void setRuleId(Integer ruleId) {
	        this.ruleId = ruleId;
	    }
}
