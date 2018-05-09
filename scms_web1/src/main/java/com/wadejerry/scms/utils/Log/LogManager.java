package com.wadejerry.scms.utils.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;


/**
 * 
* @ClassName: LogManager
* @Description: 日志管理类
* @author zhanying
* @date 2016年9月6日 下午4:53:40
*
 */
public class LogManager {
	private static Logger logger = null;
	
	public static void logException(Exception e){
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		String newClass = stacks[1].getClassName();
		if(logger == null || !newClass.equals(logger.getName())){
			logger = Logger.getLogger(newClass);
		}
		StringBuffer logInfo = new StringBuffer(100);
		logInfo.append("[");
		logInfo.append(stacks[1].getLineNumber());
		logInfo.append("]");
		logInfo.append(stacks[1].getMethodName());
		StringWriter trace = new StringWriter();
		e.printStackTrace(new PrintWriter(trace));
		logInfo.append(" Exception:");
		logInfo.append(trace.toString());
		logger.error(logInfo.toString());
	}
	
	public static void logInfo(String message){
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		String newClass = stacks[1].getClassName();
		if(logger == null || !newClass.equals(logger.getName())){
			logger = Logger.getLogger(newClass);
		}
		StringBuffer logInfo = new StringBuffer(100);
		logInfo.append("[");
		logInfo.append(stacks[1].getLineNumber());
		logInfo.append("]");
		logInfo.append(stacks[1].getMethodName());
		logInfo.append(" INFO:");
		logInfo.append(message);
		logger.info(logInfo);
	}
	
	public static void logDebug(String message){
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		String newClass = stacks[1].getClassName();
		if(logger == null || !newClass.equals(logger.getName())){
			logger = Logger.getLogger(newClass);
		}
		StringBuffer logInfo = new StringBuffer(100);
		logInfo.append("[");
		logInfo.append(stacks[1].getLineNumber());
		logInfo.append("]");
		logInfo.append(stacks[1].getMethodName());
		logInfo.append(" DEBUG:");
		logInfo.append(message);
		logger.debug(logInfo);
	}
}
