package com.wadejerry.scms.modules.log.dao;

import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.log.dto.ConfigurationLogDto;
import com.wadejerry.scms.modules.log.model.ConfigurationLog;

public interface ConfigurationLogMapper {

	int getRecordTotal(PageParamsDto paramsDto);

	List<ConfigurationLog> getConfigurationLogByPage(PageParamsDto paramsDto);

	List<Map<String, Object>> export(ConfigurationLogDto configurationLogDto);

}
