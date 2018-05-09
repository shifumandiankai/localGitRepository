package com.wadejerry.scms.webservice.server.support.pfm.dto;

import com.wadejerry.scms.webservice.server.support.CommonPageRequest;

public class RecordPageRequest extends CommonPageRequest{
	
	private long beginTime;	//开始时间
	
	private long endTime; //结束时间
	
	private int direction; //进出放行 0 不区分进出 1 进 2 出
	
	private int[] entranceIdArr; //出入口id集合，长度0表示查询所有
	
	private int flag ;	//0 正常记录 1 异常记录
	
	private String carNum;	//车牌 空查询不过滤
	
	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int[] getEntranceIdArr() {
		return entranceIdArr;
	}

	public void setEntranceIdArr(int[] entranceIdArr) {
		this.entranceIdArr = entranceIdArr;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}



}
