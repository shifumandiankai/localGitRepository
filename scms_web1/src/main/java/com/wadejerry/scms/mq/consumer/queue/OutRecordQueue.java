/*package com.wadejerry.scms.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.modules.pfm.dao.PfmRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.WebServiceMapper;
import com.wadejerry.scms.modules.pfm.model.PfmRecord;
import com.wadejerry.scms.mq.dto.OutRecordDto;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

*//**
 * 
* @ClassName: OutRecordQueue
* @Description: 转存mq车辆出的记录
* @author zhanying
* @date 2017年3月31日 上午8:36:07
*
 *//*
@Component
@Transactional
public class OutRecordQueue implements MessageListener {
	@Autowired
	private PfmRecordMapper recordMapper;
	@Autowired
	private WebServiceMapper webservicemapper;
	@Override
	public void onMessage(Message message) {
		try {
			String jsonStr = ((TextMessage) message).getText();
			OutRecordDto outRecordDto = JacksonUtil.toObject(jsonStr, OutRecordDto.class);
			LogManager.logInfo(outRecordDto.carNumber);
			PfmRecord record = new PfmRecord();
//			record.setAdvance(outRecordDto.);
//			record.setAdvanceType(outRecordDto.getAdvancetype());
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
			record.setEventTime(DateUtil.df.parse(outRecordDto.getStrOutTime()));
			record.setFlag(outRecordDto.getFlag());
			record.setInRecordId(outRecordDto.getInRecordId());
			record.setInsertTime(DateUtil.df.parse(outRecordDto.getStrInsertTime()));
			record.setNote(outRecordDto.getNote());
			record.setParkId(outRecordDto.getParkId());
			record.setParkName(outRecordDto.getParkName());
			record.setPersonName(outRecordDto.getPersonName());
			record.setPhoto(outRecordDto.getOutPhoto());
			//record.setRecordId(outRecordDto.getInRecordId());
			record.setUserId(outRecordDto.getUserId());
			record.setUserName(outRecordDto.getUserName());
			if(webservicemapper.selectCountFromPfmRecordByCarNumAndinsertTime(record.getCarNumber(), record.getEventTime())>0){
				LogManager.logDebug("出场Record重复添加信息"+record);
				return;
			}
			recordMapper.insertSelective(record);
			
		} catch (JMSException e) {
			LogManager.logException(e);
		} 
		catch(Exception e){
			LogManager.logException(e);
		}
	}

}
*/