package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.device.dao.PfmDeviceScreenMapper;
import com.wadejerry.scms.modules.device.model.PfmDeviceScreen;
import com.wadejerry.scms.modules.pfm.dao.PfmTaskMessageMapper;
import com.wadejerry.scms.modules.pfm.dto.MessageDto;
import com.wadejerry.scms.modules.pfm.model.PfmTaskMessage;
import com.wadejerry.scms.modules.pfm.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private PfmTaskMessageMapper messageMapper;
	@Autowired
	private PfmDeviceScreenMapper deviceMapper;
	@Autowired
	private LogRecord logRecord;
	@Override
	public int selectMessageCount(PageParamsDto param) {
		// TODO Auto-generated method stub
		return messageMapper.selectCountByParams(param);
	}

	@Override
	public List<MessageDto> selectMessageList(PageParamsDto param) {
		// TODO Auto-generated method stub
		List<MessageDto> dto = messageMapper.selectDtoByParams(param);
		for(MessageDto temp:dto){
			StringBuilder name = new StringBuilder("");
			String screenid = temp.getScreenids();
			if(screenid!=null&&!"".equals(screenid)){
				String[] screenids =  screenid.split(",");
				for(String id:screenids){
				PfmDeviceScreen screen = deviceMapper.selectByPrimaryKey(Integer.parseInt(id));
				if(screen!=null){
					name.append(screen.getDeviceName()+"  ");
				}
				}
			}
			temp.setDeviceScreenIdArr(name.toString());
		}
		return dto;
	}

	@Override
	public List<SelectDataDto> selectScreenData() {
		// TODO Auto-generated method stub
		List<PfmDeviceScreen> list= deviceMapper.selectByCompanyId(LoginInfo.getCompanyId());
		List<SelectDataDto> dtos = new ArrayList<>();
		for(PfmDeviceScreen screen:list){
			SelectDataDto temp = new SelectDataDto();
			temp.setId(screen.getPfmDeviceScreenId());
			temp.setText(screen.getDeviceName());
			dtos.add(temp);
		}
		return dtos;
	}

	@Override
	@Transactional
	public OperateResult delMessage(List<MessageDto> dto) {
		// TODO Auto-generated method stub
		OperateResult result = new OperateResult();
		StringBuilder messageName = new StringBuilder();
		for(MessageDto dto1:dto){
			messageMapper.deleteByPrimaryKey(dto1.getPfmTaskMessageId());
			messageName.append(dto1.getMessage()+";");
		}
		logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_MESSAGE, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, messageName.toString());

		result.setResult(true);
		result.setMsg("删除成功");
		return result;
	}

	@Override
	public void addMessage(PfmTaskMessage message) {
		// TODO Auto-generated method stub
	
		try{
		messageMapper.insertSelective(message);
		}
		catch(RuntimeException e){
			
			throw e;
		}
		
	}

	@Override
	public void editMessage(PfmTaskMessage message) {
		try{
			messageMapper.updateByPrimaryKeySelective(message);
			}
			catch(RuntimeException e){
				
				throw e;
			}

}
}