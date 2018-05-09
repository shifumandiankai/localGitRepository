package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.pfm.dto.MessageDto;
import com.wadejerry.scms.modules.pfm.model.PfmTaskMessage;

public interface MessageService {
	
	int selectMessageCount(PageParamsDto param);
	List<MessageDto> selectMessageList(PageParamsDto param);
	List<SelectDataDto> selectScreenData();
	OperateResult delMessage(List<MessageDto> dto);
	
	void addMessage(PfmTaskMessage message);
	void editMessage(PfmTaskMessage message);

}
