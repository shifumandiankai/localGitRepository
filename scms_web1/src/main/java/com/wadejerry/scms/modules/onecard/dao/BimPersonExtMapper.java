package com.wadejerry.scms.modules.onecard.dao;

import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonExtDto;

public interface BimPersonExtMapper {
	
	void saveInfo(BimPersonExtDto bimPersonExtDto);

	//void updateInfo(BimPersonExtDto bimPersonExtDto);

	void deletePersonById(Integer personId);

	void updateInfo(BimPersonDto bpDto);
}
