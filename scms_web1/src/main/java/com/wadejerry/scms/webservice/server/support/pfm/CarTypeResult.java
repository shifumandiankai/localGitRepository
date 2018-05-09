package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarTypeDto;

public class CarTypeResult extends CommonResult{
	private CarTypeDto[] CarTypeDtoArr;

	public CarTypeDto[] getCarTypeDtoArr() {
		return CarTypeDtoArr;
	}

	public void setCarTypeDtoArr(CarTypeDto[] carTypeDtoArr) {
		CarTypeDtoArr = carTypeDtoArr;
	}
	
}
