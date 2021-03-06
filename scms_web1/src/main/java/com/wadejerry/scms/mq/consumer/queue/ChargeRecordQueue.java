/*package com.wadejerry.scms.mq.consumer.queue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.modules.basic.dao.BimCardAppMapper;
import com.wadejerry.scms.modules.basic.model.BimCardApp;
import com.wadejerry.scms.modules.pfm.dao.PfmChargeRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.WebServiceMapper;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.mq.dto.ChargeRecordDto;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Component
@Transactional
public class ChargeRecordQueue implements MessageListener {

	@Autowired
	private PfmChargeRecordMapper chargeRecordMapper;
	@Autowired
	private BimCardAppMapper bimCardAppMapper;
	@Autowired
	private WebServiceMapper webservicemapper;
	@Override
	public void onMessage(Message message) {
		try {
			String jsonStr = ((TextMessage) message).getText();
			ChargeRecordDto chargeRecordDto = JacksonUtil.toObject(jsonStr, ChargeRecordDto.class);
			LogManager.logInfo(chargeRecordDto.carNumber);
			//TODO 如果 carId 不等于零 扣除账户余额 
			PfmChargeRecord record = new PfmChargeRecord();
			record.setBalance(chargeRecordDto.getBalance());
			record.setCardId(chargeRecordDto.getCardId());
			record.setCarId(chargeRecordDto.getCarId());
			record.setCarNumber(chargeRecordDto.getCarNumber());
			record.setCarTypeId(chargeRecordDto.getCarTypeId());
			record.setCarTypeName(chargeRecordDto.getCarTypeName());
			record.setChargeFee(chargeRecordDto.getChargefee());
			//record.setChargeRecordId(chargeRecordDto.getChargeRecordId());
			record.setCustom1(chargeRecordDto.getCustom1());
			record.setCustom2(chargeRecordDto.getCustom2());
			record.setCustom3(chargeRecordDto.getCustom3());
			record.setCustom4(chargeRecordDto.getCustom4());
			record.setDisableTime(DateUtil.df.parse(chargeRecordDto.getStrDisableTime()));
			record.setInsertTime(DateUtil.df.parse(chargeRecordDto.getStrInsertTime()));
			record.setInTime(DateUtil.df.parse(chargeRecordDto.getStrInTime()));
			record.setOutTime(DateUtil.df.parse(chargeRecordDto.getStrOutTime()));
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
			if(webservicemapper.selectCountFromPfmchargeRecordByCarNumAndinsertTime(record.getCarNumber(), record.getOutTime())>0){
				LogManager.logDebug("chargeRecord重复添加信息"+chargeRecordDto);
				return;
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
			
		} catch (JMSException e) {
			LogManager.logException(e);
		}
		catch (ParseException e) {
			LogManager.logException(e);
		}
	}

}
*/