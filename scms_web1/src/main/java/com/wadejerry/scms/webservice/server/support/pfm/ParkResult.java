package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ParkDto;

public class ParkResult  extends CommonResult {
	private ParkDto[] parkDtoArr;

	public ParkDto[] getParkDtoArr() {
		return parkDtoArr;
	}

	public void setParkDtoArr(ParkDto[] parkDtoArr) {
		this.parkDtoArr = parkDtoArr;
	}
	
}
