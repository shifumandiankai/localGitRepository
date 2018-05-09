package com.wadejerry.scms.modules.onecard.dao;

import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonPhotoDto;
import com.wadejerry.scms.modules.onecard.model.BimPersonPhoto;

public interface BimPersonPhotoMapper {
	public void insertInfo(BimPersonPhoto bimPersonPhoto);

	public void updateInfo(Integer personId);

	public void updateFinger(int fingerprint1Num);

	public void updateAllFinger();

	public void updatePhoto(BimPersonDto bpDto);

	public BimPersonPhoto selectByPersonId(Integer personId);

	public BimPersonPhotoDto selectFingerSq(Integer personId);
}
