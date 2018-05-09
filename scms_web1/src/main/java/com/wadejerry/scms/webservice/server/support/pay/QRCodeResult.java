package com.wadejerry.scms.webservice.server.support.pay;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pay.dto.PayInfoDto;

/**
* 
* @ClassName: QRCodeResult
* @Description: 获取二维码返回结果
* @author zhanying
* @date 2017年6月19日 上午8:50:47
*
 */
public class QRCodeResult extends CommonResult {
	//微信支付码
	private byte[] weixinPayCode; 
	//支付宝支付码
	private byte[] alipayPayCode; 
	//订单号
	private String orderId; 
	//支付信息
	private PayInfoDto payInfoDto;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public byte[] getWeixinPayCode() {
		return weixinPayCode;
	}

	public void setWeixinPayCode(byte[] weixinPayCode) {
		this.weixinPayCode = weixinPayCode;
	}

	public byte[] getAlipayPayCode() {
		return alipayPayCode;
	}

	public void setAlipayPayCode(byte[] alipayPayCode) {
		this.alipayPayCode = alipayPayCode;
	}

	public PayInfoDto getPayInfoDto() {
		return payInfoDto;
	}

	public void setPayInfoDto(PayInfoDto payInfoDto) {
		this.payInfoDto = payInfoDto;
	}
}
