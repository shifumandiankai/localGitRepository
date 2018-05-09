package com.wadejerry.scms.modules.sysconfig.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.sysconfig.dao.BimSysParamsMapper;
import com.wadejerry.scms.modules.sysconfig.model.BimSysParams;
import com.wadejerry.scms.utils.Log.LogManager;
@Service
public class SysConfigService {
	
	
	@Autowired
	private BimSysParamsMapper bimSysParamsMapper;
/*	@Autowired(required = false)
	private SysInfoParam sysInfoParam;
	@Autowired(required = false)
	private ISysConfigDao sysConfigDao;
	@Autowired(required = false)
	private ILogRecord logRecord;
	@Override
	public List<SysConfig> findAllByConfigType(Integer configType){
		return sysConfigDao.findAllByConfigType(configType);
	}
	
	@Override
	public void updateAll(UserLoginPojo user,List<SysConfig> sysConfigList){
		sysConfigDao.updateAll(sysConfigList);
		logRecord.recordLog(user, sysConfigList, ConstParamLog.LOG_UPDATE_STR, ConstParamLog.CONFIG);
	}

	@Override
	public SysConfig findByConfigTypeAndKey(Integer configType,Integer noValue) {
		return sysConfigDao.findAllByType(configType, noValue);
	}
	
	*//**
	 * 根据类型获取系统参数
	 * @author jinbenbin 2013-10-15 上午09:23:23
	 */
	public HashMap<String, Object> findAllSysConfigParamByConfigType(Integer configType) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<BimSysParams> sysConfigList = bimSysParamsMapper.select(configType);
		if (sysConfigList != null) {
			for (BimSysParams sysConfig : sysConfigList) {
				if (sysConfig.getValueType()==0) {
					paramMap.put(sysConfig.getParamId(), sysConfig.getIntValue());
				} else {
					paramMap.put(sysConfig.getParamId(), sysConfig.getStrValue());
				}
			}
		}
		return paramMap;
	}
	
	/**
	 * 更新系统参数
	 * @author jinbenbin 2013-10-15 上午09:40:50
	 *//*
	public void updateSysConfig(UserLoginPojo user, HashMap<String, Object> paramMap) {
		if (paramMap != null && !paramMap.isEmpty()) {
			List<SysConfig> sysConfigList = sysConfigDao.findAllByParameterName(paramMap.keySet());
			if (sysConfigList != null && !sysConfigList.isEmpty()) {
				for (SysConfig sysConfig : sysConfigList) {
					if (ConstParamSysConfig.TYPE_INT.equals(sysConfig.getValueType())) {
						Integer intValue = (Integer)paramMap.get(sysConfig.getParameterName());
						if (intValue != null) {
							sysConfig.setIntValue(intValue);
						}
					} else {
						String stringValue = (String)paramMap.get(sysConfig.getParameterName());
						if (stringValue != null) {
							sysConfig.setStrValue(stringValue);
						}
					}
				}
				updateAll(user, sysConfigList);
			}
		}
	}
	
	*//**
	 * 获取设备、服务器自动校时任务触发条件
	 * @author jinbenbin 2013-11-20 下午08:15:55
	 *//*
	public String getTimingCronExpression() {
		StringBuffer expression = new StringBuffer();
		Pattern p = Pattern.compile("([0-9]|([0-1][0-9])|(2[0-3])):[0-5]?[0-9]:[0-5]?[0-9]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(sysInfoParam.getAutoSetTimeTime());
		if (matcher.matches()) {
			String group = matcher.group();
			String[] array = group.split("\\:");
			expression.append(array[2]).append(" ").append(array[1]).append(" ").append(array[0]).append(" ");
		} else {
			expression.append("0 0 0 ");
		}
		if (sysInfoParam.getAutoSetTimeStyle() == 0) {
			expression.append("* * ?");
		} else {
			switch (sysInfoParam.getAutoSetTimeStyle()) {
				case 1:
					expression.append("? * MON");
					break;
				case 2:
					expression.append("? * TUE");
					break;
				case 3:
					expression.append("? * WED");
					break;
				case 4:
					expression.append("? * THU");
					break;
				case 5:
					expression.append("? * FRI");
					break;
				case 6:
					expression.append("? * SAT");
					break;
				case 7:
					expression.append("? * SUN");
					break;
				default:
					expression.append("? * MON");
					break;
			}
		}
		String exp = expression.toString().replace("00", "0");
		LogManager.logInfo("自动校时任务触发条件：" + exp);
		return exp;
	}
	
    *//**
     * 记录日志
     * @author jinbenbin 2014-1-15 上午08:58:28
     * @param operateString 操作字符串
     * @modificationHistory=========================逻辑或功能性重大变更记录
     * @modify by user: {修改人} 2014-1-15
     * @modify by reason:{原因}
     *//*
    public void recordLog(String operateString) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("operateString", operateString);
        logRecord.recordLog(ConstParamLog.LOG_OP_TYPE_SYSCONFIG, null, ConstParamSysConfig.LOG_SYSTEM_OPERATE, map,
                ConstParamLog.LOG_TYPE_CONFIG);
    }*/
}
