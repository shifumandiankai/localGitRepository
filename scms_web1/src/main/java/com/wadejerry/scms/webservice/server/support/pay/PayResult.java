package com.wadejerry.scms.webservice.server.support.pay;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pay.dto.PayInfoDto;

/**
 * 
* @ClassName: PayResult
* @Description: 支付结果
* @author zhanying
* @date 2017年6月19日 上午8:47:08
*
 */
public class PayResult extends CommonResult {
	private PayInfoDto payInfoDto; //支付信息
	
	private PayInfoDto[] payInfoDtoArr; //支付信息
	
	private int paySuccessOffTime; //支付成功等待出场时间

	public PayInfoDto getPayInfoDto() {
		return payInfoDto;
	}

	public void setPayInfoDto(PayInfoDto payInfoDto) {
		this.payInfoDto = payInfoDto;
	}

	public PayInfoDto[] getPayInfoDtoArr() {
		return payInfoDtoArr;
	}

	public void setPayInfoDtoArr(PayInfoDto[] payInfoDtoArr) {
		this.payInfoDtoArr = payInfoDtoArr;
	}

	public int getPaySuccessOffTime() {
		return paySuccessOffTime;
	}

	public void setPaySuccessOffTime(int paySuccessOffTime) {
		this.paySuccessOffTime = paySuccessOffTime;
	}
	
	
}


