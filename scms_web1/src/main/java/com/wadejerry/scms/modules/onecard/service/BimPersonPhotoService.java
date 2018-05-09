package com.wadejerry.scms.modules.onecard.service;

import com.wadejerry.scms.modules.onecard.dto.BimPersonPhotoDto;
import com.wadejerry.scms.modules.onecard.model.BimPersonPhoto;

public interface BimPersonPhotoService {
	public void insertInfo(BimPersonPhoto bimPersonPhoto);

	public void updateFinger(int fingerprint1Num);

	public void updateAllFinger();

	public BimPersonPhoto selectByPersonId(Integer valueOf);

	public BimPersonPhotoDto selectFingerSq(Integer personId);
}
