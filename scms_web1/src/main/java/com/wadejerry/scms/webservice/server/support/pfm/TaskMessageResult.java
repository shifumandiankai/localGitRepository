package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TaskMessageDto;

public class TaskMessageResult extends CommonResult {

	private  TaskMessageDto[] TaskMessageDtoArr;

	public TaskMessageDto[] getTaskMessageDtoArr() {
		return TaskMessageDtoArr;
	}

	public void setTaskMessageDtoArr(TaskMessageDto[] taskMessageDtoArr) {
		TaskMessageDtoArr = taskMessageDtoArr;
	}
}
