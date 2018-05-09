package com.wadejerry.scms.webservice.server.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.modules.basic.dao.BimCardAppMapper;
import com.wadejerry.scms.modules.basic.model.BimCardApp;
import com.wadejerry.scms.modules.device.dao.PfmServerMapper;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.pay.model.PfmPayOrderFinish;
import com.wadejerry.scms.modules.pay.service.PfmPayOrderService;
import com.wadejerry.scms.modules.pfm.dao.PfmChargeRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.WebServiceMapper;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.model.PfmRecord;
import com.wadejerry.scms.mq.dto.ChargeRecordDto;
import com.wadejerry.scms.mq.dto.InRecordDto;
import com.wadejerry.scms.mq.dto.OutRecordDto;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PayOrderDto;

@Component
@Transactional
public class TransPfmService {
	@Autowired
	private WebServiceMapper pfmbm;  //获取记录mapper
	@Autowired
	private PfmChargeRecordMapper chargeRecordMapper; //收费记录mapper
	@Autowired
	private BimCardAppMapper bimCardAppMapper; //账户余额mapper
	@Autowired
	private PfmRecordMapper recordMapper; //收费记录mapper
	@Autowired
	private PfmServerMapper serverMapper; //停车场服务mapper
	@Autowired
	private PfmPayOrderService pfmPayOrderService; //移动支付Service
	
	/**
	* 上传出场事件
	* @author zhanying 2017年7月18日 下午5:15:43
	* @param  @param serverId
	* @param  @param outRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public CommonResult uploadOutRecord(int serverId,OutRecordDto outRecordDto) {	
		CommonResult result = new CommonResult();
		result.setiRet(true);
		PfmServer server = serverMapper.selectByPrimaryKey(serverId); 
		if(server != null) {
			PfmRecord record = new PfmRecord();
			record.setBoothId(outRecordDto.getOutBoothId());
			record.setBoothName(outRecordDto.getOutBoothName());
			record.setCardId(outRecordDto.getCardId());
			record.setCarId(outRecordDto.getCarId());
			record.setCarNumber(outRecordDto.getCarNumber());
			record.setCarriagewayId(outRecordDto.getOutCarriagewayId());
			record.setCarriagewayName(outRecordDto.getOutCarriagewayName());
			record.setCarTypeId(outRecordDto.getCarTypeId());
			record.setCarTypeName(outRecordDto.getCarTypeName());
			record.setDeviceId(outRecordDto.getOutDeviceId());
			record.setDeviceName(outRecordDto.getOutDeviceName());
			record.setDirection(2);
			record.setEntranceId(outRecordDto.getOutEntranceId());
			record.setEntranceName(outRecordDto.getOutEntranceName());
			try {
				record.setEventTime(DateUtil.df.parse(outRecordDto.getStrOutTime()));
			} catch (ParseException e) {
				LogManager.logException(e);
			}
			record.setFlag(outRecordDto.getFlag());
			record.setInRecordId(outRecordDto.getInRecordId());
			try {
				record.setInsertTime(DateUtil.df.parse(outRecordDto.getStrInsertTime()));
			} catch (ParseException e) {
				LogManager.logException(e);
			}
			record.setNote(outRecordDto.getNote());
			record.setParkId(outRecordDto.getParkId());
			record.setParkName(outRecordDto.getParkName());
			record.setPersonName(outRecordDto.getPersonName());
			record.setPhoto(outRecordDto.getOutPhoto());
			//record.setRecordId(outRecordDto.getInRecordId());
			record.setUserId(outRecordDto.getUserId());
			record.setUserName(outRecordDto.getUserName());
			if(pfmbm.selectCountFromPfmRecordByCarNumAndinsertTime(record.getCarNumber(), record.getEventTime())>0){
				LogManager.logDebug("出场Record重复添加信息"+record);
			}
			recordMapper.insertSelective(record);
		} else {
			result.setiRet(false);
		    LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}
	
	/**
	* 上传收费记录
	* @author zhanying 2017年6月14日 上午10:37:58
	* @param  @param serverId
	* @param  @param chargeRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */	
	public CommonResult uploadChargerRecord(int serverId,ChargeRecordDto chargeRecordDto) {
		CommonResult result = new CommonResult();
		result.setiRet(true);	
			PfmServer server = serverMapper.selectByPrimaryKey(serverId);
			if(server != null) {
				PfmChargeRecord record = new PfmChargeRecord();
				record.setBalance(chargeRecordDto.getBalance());
				record.setCardId(chargeRecordDto.getCardId());
				record.setCarId(chargeRecordDto.getCarId());
				record.setCarNumber(chargeRecordDto.getCarNumber());
				record.setCarTypeId(chargeRecordDto.getCarTypeId());
				record.setCarTypeName(chargeRecordDto.getCarTypeName());
				record.setChargeFee(chargeRecordDto.getChargefee());
				record.setCustom1(chargeRecordDto.getCustom1());
				record.setCustom2(chargeRecordDto.getCustom2());
				record.setCustom3(chargeRecordDto.getCustom3());
				record.setCustom4(chargeRecordDto.getCustom4());
				try {
					record.setInsertTime(DateUtil.df.parse(chargeRecordDto.getStrInsertTime()));
					record.setInTime(DateUtil.df.parse(chargeRecordDto.getStrInTime()));
					record.setOutTime(DateUtil.df.parse(chargeRecordDto.getStrOutTime()));
					record.setDisableTime(DateUtil.df.parse(chargeRecordDto.getStrDisableTime()));
				} catch (ParseException e) {
					LogManager.logException(e);
				}	
				record.setFlag(chargeRecordDto.getFlag());
				record.setInBoothId(chargeRecordDto.getInBoothId());
				record.setInBoothName(chargeRecordDto.getInBoothName());
				record.setInCarriagewayId(chargeRecordDto.getInCarriagewayId());
				record.setInCarriagewayName(chargeRecordDto.getInCarriagewayName());
				record.setInDeviceId(chargeRecordDto.getInDeviceId());
				record.setInDeviceName(chargeRecordDto.getInDeviceName());
				record.setInPhoto(chargeRecordDto.getInPhoto());
				record.setInEntranceId(chargeRecordDto.getInEntranceId());
				record.setInEntranceName(chargeRecordDto.getInEntranceName());
				record.setOutEntranceId(chargeRecordDto.getOutEntranceId());
				record.setOutEntranceName(chargeRecordDto.getOutEntranceName());
				record.setInUserId(chargeRecordDto.getInUserId());
				record.setInUserName(chargeRecordDto.getInUserName());
				record.setJianmianFee(chargeRecordDto.getJianmianfee());
				record.setJianmianRuleName(chargeRecordDto.getJianmianrulename());
				record.setNote(chargeRecordDto.getNote());
				record.setOutBoothId(chargeRecordDto.getOutBoothId());
				record.setOutBoothName(chargeRecordDto.getOutBoothName());
				record.setOutCarriagewayId(chargeRecordDto.getOutCarriagewayId());
				record.setOutCarriagewayName(chargeRecordDto.getOutCarriagewayName());
				record.setOutDeviceId(chargeRecordDto.getOutDeviceId());
				record.setOutDeviceName(chargeRecordDto.getOutDeviceName());
				record.setOutPhoto(chargeRecordDto.getOutPhoto());
				record.setParkId(chargeRecordDto.getParkId());
				record.setParkName(chargeRecordDto.getParkName());
				record.setPaymethod(chargeRecordDto.getPaymethod());
				record.setPersonName(chargeRecordDto.getPersonName());
				record.setParkingDuration(chargeRecordDto.getParkingduration());
				record.setRuleId(chargeRecordDto.getRuleId());
				record.setRuleName(chargeRecordDto.getRuleName());
				record.setRuleType(chargeRecordDto.getRuleType());
				record.setUserid(chargeRecordDto.getUserId());
				record.setUserName(chargeRecordDto.getUserName());
				if(pfmbm.selectCountFromPfmchargeRecordByCarNumAndinsertTime(record.getCarNumber(), record.getOutTime())>0){
					LogManager.logDebug("chargeRecord重复添加信息"+chargeRecordDto);
				}
				if(chargeRecordDto.getCarId()==0){
					chargeRecordMapper.insertSelective(record);
				}
				else{
					BimCardApp cardapp=bimCardAppMapper.selectByCarID(chargeRecordDto.getCarId());
					if(cardapp!=null){
						BigDecimal yue = cardapp.getValue1().subtract(chargeRecordDto.getChargefee());
						cardapp.setUpdateTime(new Date());
						cardapp.setValue1(yue);
						bimCardAppMapper.updateByPrimaryKey(cardapp);
						chargeRecordMapper.insertSelective(record);
					}
				}
			} else{
				result.setiRet(false);
				LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
			}
		return result;
	}
	
	/**
	* 上传入场记录
	* @author zhanying 2017年6月14日 上午10:40:31
	* @param  @param serverId
	* @param  @param chargeRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	
	public CommonResult uploadInRecord(int serverId,InRecordDto inRecordDto){
		CommonResult result = new CommonResult(); 
		result.setiRet(true);
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if(server != null){
			PfmRecord record = new PfmRecord();
			record.setAdvance(inRecordDto.getAdvance());
			record.setAdvanceType(inRecordDto.getAdvancetype());
			record.setBoothId(inRecordDto.getInBoothId());
			record.setBoothName(inRecordDto.getInBoothName());
			record.setCardId(inRecordDto.getCardId());
			record.setCarId(inRecordDto.getCarId());
			record.setCarNumber(inRecordDto.getCarNumber());
			record.setCarriagewayId(inRecordDto.getInCarriagewayId());
			record.setCarriagewayName(inRecordDto.getInCarriagewayName());
			record.setCarTypeId(inRecordDto.getCarTypeId());
			record.setCarTypeName(inRecordDto.getCarTypeName());
			record.setDeviceId(inRecordDto.getInDeviceId());
			record.setDeviceName(inRecordDto.getInDeviceName());
			record.setDirection(1);
			record.setEntranceId(inRecordDto.getInEntranceId());
			record.setInRecordId(inRecordDto.getInRecordId());
			record.setEntranceName(inRecordDto.getInEntranceName());
			try {
				record.setEventTime(DateUtil.df.parse(inRecordDto.getStrInTime()));
				record.setInsertTime(DateUtil.df.parse(inRecordDto.getStrInsertTime()));
			} catch (ParseException e) {
				LogManager.logException(e);
			}
			record.setFlag(inRecordDto.getFlag());
			record.setInRecordId(inRecordDto.getInRecordId());
			record.setNote(inRecordDto.getNote());
			record.setParkId(inRecordDto.getParkId());
			record.setParkName(inRecordDto.getParkName());
			record.setPersonName(inRecordDto.getPersonName());
			record.setPhoto(inRecordDto.getInPhoto());
			record.setUserId(inRecordDto.getUserId());
			record.setUserName(inRecordDto.getUserName());
			if(pfmbm.selectCountFromPfmRecordByCarNumAndinsertTime(record.getCarNumber(), record.getEventTime())>0){
				LogManager.logInfo("进场Record重复添加信息"+inRecordDto);
				return result;
			}
			recordMapper.insertSelective(record);
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}
	
	/**
	* 上传移动支付数据 
	* @author zhanying 2017年7月18日 下午4:35:11
	* @param  @param serverId
	* @param  @param payOrderDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public CommonResult uploadPayOrder(int serverId, PayOrderDto payOrderDto) {
		CommonResult result = new CommonResult(); 
		result.setiRet(true);
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if(server != null) {
			PfmPayOrderFinish payOrder = new PfmPayOrderFinish();
			payOrder.setCarNum(payOrderDto.getCarNum());
			payOrder.setClientName(payOrderDto.getClientName());
			payOrder.setGenTime(new Date(payOrderDto.getGenTime()));
			payOrder.setInTime(new Date(payOrderDto.getInTime()));
			payOrder.setNote(payOrderDto.getNote());
			//payOrderDto.setOrderId(orderId);
			payOrder.setOrderMoney(payOrderDto.getOrderMoney());
			payOrder.setOrderNo(payOrderDto.getOrderNo());
			payOrder.setOutTime(new Date(payOrderDto.getOutTime()));
			payOrder.setParkSerial(payOrderDto.getParkSerial());
			payOrder.setPayTime(new Date(payOrderDto.getPayTime()));
			payOrder.setStatus(payOrderDto.getStatus());
			payOrder.setUpdateTime(new Date(payOrderDto.getUpdateTime()));
			pfmPayOrderService.insertPayOrder(payOrder);
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}
}
