package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

/**
* 岗亭DTO类
* @ClassName: BoothDto
* @Description: TODO
* @author zhanying
* @date 2017年2月8日 下午2:46:03
*
 */
public class BoothDto {
	
	private int boothId;	//岗亭ID
 	private int pfmTimeId;  //通行时段ID
	private String boothCode; //岗亭编号
	private String boothName; //岗亭名称
	private String clientIp; //客户端IP
	private int consumeConfirm; //是否启用消费确认键
	private int centerCharge; //是否中央收费岗亭
	private int intervalSecond; //通道组摄像头间隔秒数,同一车牌间隔抓拍秒数 
	private int recogniseMode; 	//识别模式
	private int inChannel1;	//进通道1绑定设备
	private int inChannel2;	//进通道2绑定设备
	private int inChannel3;	//进通道3绑定设备
	private int inChannel4;	//进通道4绑定设备
	private int outChannel1;	//出通道1绑定设备
	private int outChannel2;	//出通道2绑定设备
	private int outChannel3;	//出通道3绑定设备
	private int outChannel4;	//出通道4绑定设备
	private long updateTime;
	private int pfmEntranceId; 
	
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime.getTime();
	}
	public int getPfmEntranceId() {
		return pfmEntranceId;
	}
	public void setPfmEntranceId(int pfmEntranceId) {
		this.pfmEntranceId = pfmEntranceId;
	}

	
	public int getBoothId() {
		return boothId;
	}
	public void setBoothId(int boothId) {
		this.boothId = boothId;
	}
	public int getPfmTimeId() {
		return pfmTimeId;
	}
	public void setPfmTimeId(int pfmTimeId) {
		this.pfmTimeId = pfmTimeId;
	}
	public String getBoothCode() {
		return boothCode;
	}
	public void setBoothCode(String boothCode) {
		this.boothCode = boothCode;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public int getConsumeConfirm() {
		return consumeConfirm;
	}
	public void setConsumeConfirm(int consumeConfirm) {
		this.consumeConfirm = consumeConfirm;
	}
	public int getCenterCharge() {
		return centerCharge;
	}
	public void setCenterCharge(int centerCharge) {
		this.centerCharge = centerCharge;
	}
	public int getIntervalSecond() {
		return intervalSecond;
	}
	public void setIntervalSecond(int intervalSecond) {
		this.intervalSecond = intervalSecond;
	}
	public int getRecogniseMode() {
		return recogniseMode;
	}
	public void setRecogniseMode(int recogniseMode) {
		this.recogniseMode = recogniseMode;
	}
	public int getInChannel1() {
		return inChannel1;
	}
	public void setInChannel1(int inChannel1) {
		this.inChannel1 = inChannel1;
	}
	public int getInChannel2() {
		return inChannel2;
	}
	public void setInChannel2(int inChannel2) {
		this.inChannel2 = inChannel2;
	}
	public int getInChannel3() {
		return inChannel3;
	}
	public void setInChannel3(int inChannel3) {
		this.inChannel3 = inChannel3;
	}
	public int getInChannel4() {
		return inChannel4;
	}
	public void setInChannel4(int inChannel4) {
		this.inChannel4 = inChannel4;
	}
	public int getOutChannel1() {
		return outChannel1;
	}
	public void setOutChannel1(int outChannel1) {
		this.outChannel1 = outChannel1;
	}
	public int getOutChannel2() {
		return outChannel2;
	}
	public void setOutChannel2(int outChannel2) {
		this.outChannel2 = outChannel2;
	}
	public int getOutChannel3() {
		return outChannel3;
	}
	public void setOutChannel3(int outChannel3) {
		this.outChannel3 = outChannel3;
	}
	public int getOutChannel4() {
		return outChannel4;
	}
	public void setOutChannel4(int outChannel4) {
		this.outChannel4 = outChannel4;
	}

}
