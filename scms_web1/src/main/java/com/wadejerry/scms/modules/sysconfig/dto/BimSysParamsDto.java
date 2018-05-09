package com.wadejerry.scms.modules.sysconfig.dto;

import com.wadejerry.scms.frame.entity.OperateResult;

/**
* 系统参数DTO
* @ClassName: BimSysParamsDto
* @Description: TODO
* @author zhanying
* @date 2017年6月7日 上午8:43:23
*
 */
public class BimSysParamsDto {
	private Integer autoSetTimeStyleWeek;
	private String  autoSetTimeStyle;
	private Integer alarmLogPreserveMonths;
	private Integer configLogPreserveMonths;
	private Integer commonLogPreserveMonths;
	private Integer workLogPreserveMonths;
	private Integer monitorLogPreserveMonths;
	private Integer pfmPhotoSaveTime;
	private String scmsPayUrl;
	private Integer SuccessfulPayOffTime;
	public Integer getAutoSetTimeStyleWeek() {
		return autoSetTimeStyleWeek;
	}
	public void setAutoSetTimeStyleWeek(Integer autoSetTimeStyleWeek) {
		this.autoSetTimeStyleWeek = autoSetTimeStyleWeek;
	}
	public String getAutoSetTimeStyle() {
		return autoSetTimeStyle;
	}
	public void setAutoSetTimeStyle(String autoSetTimeStyle) {
		this.autoSetTimeStyle = autoSetTimeStyle;
	}
	public Integer getAlarmLogPreserveMonths() {
		return alarmLogPreserveMonths;
	}
	public void setAlarmLogPreserveMonths(Integer alarmLogPreserveMonths) {
		this.alarmLogPreserveMonths = alarmLogPreserveMonths;
	}
	public Integer getConfigLogPreserveMonths() {
		return configLogPreserveMonths;
	}
	public void setConfigLogPreserveMonths(Integer configLogPreserveMonths) {
		this.configLogPreserveMonths = configLogPreserveMonths;
	}
	public Integer getCommonLogPreserveMonths() {
		return commonLogPreserveMonths;
	}
	public void setCommonLogPreserveMonths(Integer commonLogPreserveMonths) {
		this.commonLogPreserveMonths = commonLogPreserveMonths;
	}
	public Integer getWorkLogPreserveMonths() {
		return workLogPreserveMonths;
	}
	public void setWorkLogPreserveMonths(Integer workLogPreserveMonths) {
		this.workLogPreserveMonths = workLogPreserveMonths;
	}
	public Integer getMonitorLogPreserveMonths() {
		return monitorLogPreserveMonths;
	}
	public void setMonitorLogPreserveMonths(Integer monitorLogPreserveMonths) {
		this.monitorLogPreserveMonths = monitorLogPreserveMonths;
	}
	public Integer getPfmPhotoSaveTime() {
		return pfmPhotoSaveTime;
	}
	public void setPfmPhotoSaveTime(Integer pfmPhotoSaveTime) {
		this.pfmPhotoSaveTime = pfmPhotoSaveTime;
	}
	public String getScmsPayUrl() {
		return scmsPayUrl;
	}
	public void setScmsPayUrl(String scmsPayUrl) {
		this.scmsPayUrl = scmsPayUrl;
	}
	public Integer getSuccessfulPayOffTime() {
		return SuccessfulPayOffTime;
	}
	public void setSuccessfulPayOffTime(Integer successfulPayOffTime) {
		SuccessfulPayOffTime = successfulPayOffTime;
	}
	
	
}
