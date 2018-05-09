package com.wadejerry.scms.modules.log.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.log.dao.ConfigurationLogMapper;
import com.wadejerry.scms.modules.log.dto.ConfigurationLogDto;
import com.wadejerry.scms.modules.log.model.ConfigurationLog;
import com.wadejerry.scms.modules.log.service.ConfigurationLogService;

@Service("ConfigurationService")
public class ConfigurationLogServiceImpl implements ConfigurationLogService{
    @Autowired
    private ConfigurationLogMapper configurationLogMapper;
	
	@Override
	public int getRecordTotal(PageParamsDto paramsDto) {
		
		return configurationLogMapper.getRecordTotal(paramsDto);
	}

	@Override
	public List<ConfigurationLog> getConfigurationLogByPage(PageParamsDto paramsDto) {
		
		return configurationLogMapper.getConfigurationLogByPage(paramsDto);
	}

	@Override
	public List<Map<String, Object>> export(ConfigurationLogDto configurationLogDto) {
		
		return configurationLogMapper.export(configurationLogDto);
	}

}
