package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.AnshiDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.BaoqiDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRuleDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.DaynightDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.JianmianReleaseDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodTimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PertimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeTimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodTimeDto;

/**
 * webService规则数据
* @ClassName: ChargeRuleResult
* @Description: TODO
* @author zhanying
* @date 2017年2月9日 上午9:26:13
*
 */
public class ChargeRuleResult extends CommonResult{
	
	private ChargeRuleDto[] ChargeRuleDtoArr; //规则
	private AnshiDto[] AnshiDtoArr; //按时规则
	private BaoqiDto[] BaoqiDtoArr; //包期规则
	private DaynightDto[] DaynightDtoArr; //日夜分时按次
	private JianmianReleaseDto[] JianmianReleaseDtoArr; //减免和异常放行规则
	private PeriodDto[] PeriodDtoArr; //分时段收费规则
	private PeriodTimeDto[] periodTimeArr; //分时收费的时间段
	private PertimeDto[] PertimeDtoArr; //按次收费规则
	private TimeChargeDto[] TimeChargeDtoArr; //分时收费
	private TimeChargeTimeDto[] TimeChargeTimeDtoArr; //分时段收费时段
	private UnitPeriodDto[] UnitPeriodDtoArr; //按单位时段收费规则
	private UnitPeriodTimeDto[]  UnitPeriodTimeDtoArr; //单位时段收费的时间段
	
	public ChargeRuleDto[] getChargeRuleDtoArr() {
		return ChargeRuleDtoArr;
	}
	public void setChargeRuleDtoArr(ChargeRuleDto[] chargeRuleDtoArr) {
		ChargeRuleDtoArr = chargeRuleDtoArr;
	}
	public AnshiDto[] getAnshiDtoArr() {
		return AnshiDtoArr;
	}
	public void setAnshiDtoArr(AnshiDto[] anshiDtoArr) {
		AnshiDtoArr = anshiDtoArr;
	}
	public BaoqiDto[] getBaoqiDtoArr() {
		return BaoqiDtoArr;
	}
	public void setBaoqiDtoArr(BaoqiDto[] baoqiDtoArr) {
		BaoqiDtoArr = baoqiDtoArr;
	}
	public DaynightDto[] getDaynightDtoArr() {
		return DaynightDtoArr;
	}
	public void setDaynightDtoArr(DaynightDto[] daynightDtoArr) {
		DaynightDtoArr = daynightDtoArr;
	}
	public JianmianReleaseDto[] getJianmianReleaseDtoArr() {
		return JianmianReleaseDtoArr;
	}
	public void setJianmianReleaseDtoArr(JianmianReleaseDto[] jianmianReleaseDtoArr) {
		JianmianReleaseDtoArr = jianmianReleaseDtoArr;
	}
	public PeriodDto[] getPeriodDtoArr() {
		return PeriodDtoArr;
	}
	public void setPeriodDtoArr(PeriodDto[] periodDtoArr) {
		PeriodDtoArr = periodDtoArr;
	}
	public PeriodTimeDto[] getPeriodTimeArr() {
		return periodTimeArr;
	}
	public void setPeriodTimeArr(PeriodTimeDto[] periodTimeArr) {
		this.periodTimeArr = periodTimeArr;
	}
	public PertimeDto[] getPertimeDtoArr() {
		return PertimeDtoArr;
	}
	public void setPertimeDtoArr(PertimeDto[] pertimeDtoArr) {
		PertimeDtoArr = pertimeDtoArr;
	}
	public TimeChargeDto[] getTimeChargeDtoArr() {
		return TimeChargeDtoArr;
	}
	public void setTimeChargeDtoArr(TimeChargeDto[] timeChargeDtoArr) {
		TimeChargeDtoArr = timeChargeDtoArr;
	}
	public TimeChargeTimeDto[] getTimeChargeTimeDtoArr() {
		return TimeChargeTimeDtoArr;
	}
	public void setTimeChargeTimeDtoArr(TimeChargeTimeDto[] timeChargeTimeDtoArr) {
		TimeChargeTimeDtoArr = timeChargeTimeDtoArr;
	}
	public UnitPeriodDto[] getUnitPeriodDtoArr() {
		return UnitPeriodDtoArr;
	}
	public void setUnitPeriodDtoArr(UnitPeriodDto[] unitPeriodDtoArr) {
		UnitPeriodDtoArr = unitPeriodDtoArr;
	}
	public UnitPeriodTimeDto[] getUnitPeriodTimeDtoArr() {
		return UnitPeriodTimeDtoArr;
	}
	public void setUnitPeriodTimeDtoArr(UnitPeriodTimeDto[] unitPeriodTimeDtoArr) {
		UnitPeriodTimeDtoArr = unitPeriodTimeDtoArr;
	}
}
