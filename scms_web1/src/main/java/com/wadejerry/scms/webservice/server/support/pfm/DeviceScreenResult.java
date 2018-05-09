package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.DeviceScreenDto;

public class DeviceScreenResult extends CommonResult{
	
	private  DeviceScreenDto[] DeviceScreenDtoArr;

	public DeviceScreenDto[] getDeviceScreenDtoArr() {
		return DeviceScreenDtoArr;
	}

	public void setDeviceScreenDtoArr(DeviceScreenDto[] deviceScreenDtoArr) {
		DeviceScreenDtoArr = deviceScreenDtoArr;
	}

}
