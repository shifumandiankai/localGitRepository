package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.MessageDto;
import com.wadejerry.scms.modules.pfm.model.PfmTaskMessage;


public interface PfmTaskMessageMapper {
    int deleteByPrimaryKey(Integer pfmTaskMessageId);

    int insert(PfmTaskMessage record);

    int insertSelective(PfmTaskMessage record);

    PfmTaskMessage selectByPrimaryKey(Integer pfmTaskMessageId);

    int updateByPrimaryKeySelective(PfmTaskMessage record);

    int updateByPrimaryKey(PfmTaskMessage record);
    
    int selectCountByParams(PageParamsDto ppd);
    
    List<MessageDto> selectDtoByParams(PageParamsDto ppd);
}