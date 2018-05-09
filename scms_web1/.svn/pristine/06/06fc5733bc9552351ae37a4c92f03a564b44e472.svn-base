package com.wadejerry.scms.frame.log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.utils.Log.LogManager;

/**
*
* @ClassName: LogPropertiesUtils
* @Description:  日志资源文件操作类型 
* @author zhanying
* @date 2017年6月16日 上午11:34:39
*
 */
public class LogPropertiesUtils {
	private static Properties properties = new Properties();
	
	static{
		InputStream in = LogPropertiesUtils.class.getResourceAsStream("log.properties");
		loadProerties(in);
	}
	private static void loadProerties(InputStream in){
		try {
			//properties.load(in);
			properties.load(new InputStreamReader(in, "UTF-8"));

		} catch (IOException e) {
			LogManager.logException(e);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				LogManager.logException(e);
			}
		}
	}
	
	public static String getResourceByCode(int logType,int moduleId,int operType){
		int systemId = ConstParamLog.moduleIdAndSystemIdMap.get(moduleId); //获取模块对应系统id		
		String logTypeName = ConstParamLog.logTypeAndNameMap.get(logType); //获取日志类型名称
		String systemName = ConstParamLog.systemIdAndNameMap.get(systemId); //获取系统名称
		String moduleName = ConstParamLog.moduleIdAndNameMap.get(moduleId); //模块名称
		String operName = ConstParamLog.logOperIdAndNameMap.get(operType); //获取操作类型名称
		/*资源 名称定义方式  日志类型名称+系统名称+模块名称+操作类型名称*/
		String result = properties.getProperty(logTypeName+"."+systemName+"."+moduleName+"."+operName);
		return result;
	}
}
