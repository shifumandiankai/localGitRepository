package com.wadejerry.scms.frame.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @ClassName: ConstParamLog
* @Description: 日志常量
* @author zhanying
* @date 2017年6月15日 下午3:50:45
*
 */
public class ConstParamLog {
	//start 操作类型
	public final static Integer LOG_OPER_ADD =1 ; //添加
	public final static Integer LOG_OPER_UPDATE = 2; //更新
	public final static Integer LOG_OPER_DELETE = 3; //删除
	public final static Integer LOG_OPER_QUERY = 4; //查询
	public final static Integer LOG_OPER_EXPORT = 5; //导出
	public final static Integer LOG_OPER_IMPORT = 6; //导入
	public final static Integer LOG_OPER_OTHER = 7; //其他操作
	
	public final static String STR_LOG_OPER_ADD ="添加" ; //添加
	public final static String STR_LOG_OPER_UPDATE = "更新"; //更新
	public final static String STR_LOG_OPER_DELETE = "删除"; //删除
	public final static String STR_LOG_OPER_QUERY = "查询"; //查询
	public final static String STR_LOG_OPER_EXPORT = "导出"; //导出
	public final static String STR_LOG_OPER_IMPORT = "导入"; //导入
	public final static String STR_LOG_OPER_OTHER = "其他操作"; //其他操作
	
	
	public final static Map<Integer,String> logOperIdAndNameMap = new LinkedHashMap<Integer,String>();
	static {
		logOperIdAndNameMap.put(LOG_OPER_ADD, "add");
		logOperIdAndNameMap.put(LOG_OPER_UPDATE, "update");
		logOperIdAndNameMap.put(LOG_OPER_DELETE, "delete");
		logOperIdAndNameMap.put(LOG_OPER_QUERY, "query");
		logOperIdAndNameMap.put(LOG_OPER_EXPORT, "export");
		logOperIdAndNameMap.put(LOG_OPER_IMPORT, "import");
		logOperIdAndNameMap.put(LOG_OPER_OTHER, "other");
	}
	
	public final static Map<Integer,String> logOperIdAndStrNameMap = new LinkedHashMap<Integer,String>();
	static {
		logOperIdAndStrNameMap.put(LOG_OPER_ADD, STR_LOG_OPER_ADD);
		logOperIdAndStrNameMap.put(LOG_OPER_UPDATE, STR_LOG_OPER_UPDATE);
		logOperIdAndStrNameMap.put(LOG_OPER_DELETE, STR_LOG_OPER_DELETE);
		logOperIdAndStrNameMap.put(LOG_OPER_QUERY, STR_LOG_OPER_QUERY);
		logOperIdAndStrNameMap.put(LOG_OPER_EXPORT, STR_LOG_OPER_EXPORT);
		logOperIdAndStrNameMap.put(LOG_OPER_IMPORT, STR_LOG_OPER_IMPORT);
		logOperIdAndStrNameMap.put(LOG_OPER_OTHER, STR_LOG_OPER_OTHER);
	}
	
	
	
	//end 操作类型
	
    //start 日志
	public final static Integer LOG_TYPE_CONFIG = 1;//配置日志
	public final static Integer LOG_TYPE_CTRL = 2;//控制日志
	public final static Map<Integer,String> logTypeAndNameMap = new LinkedHashMap<Integer,String>();
	static {
		logTypeAndNameMap.put(LOG_TYPE_CONFIG, "config");
		logTypeAndNameMap.put(LOG_TYPE_CTRL, "ctrl");
	}
	//end 日志类型
	
	//start 系统编号
	public final static int SYSTEM_USER = 1; //用户管理配置
	public final static int SYSTEM_DEVICE=2; //设备管理配置
	public final static int SYSTEM_CONFIG =3; //系统配置
	public final static int SYSTEM_LOG =4; //日志查询
	public final static int SYSTEM_PFM=5; //停车场配置
	
	public final static String STR_SYSTEM_USER = "用户管理配置"; //用户管理配置
	public final static String STR_SYSTEM_DEVICE="设备管理配置"; //设备管理配置
	public final static String STR_SYSTEM_CONFIG ="系统配置"; //系统配置
	public final static String STR_SYSTEM_LOG ="日志查询"; //日志查询
	public final static String STR_SYSTEM_PFM="停车场配置"; //停车场配置
	
	
	
	
	public final static Map<Integer,String> systemIdAndNameMap = new LinkedHashMap<Integer,String>(); //系统编号与名称
	static{
		systemIdAndNameMap.put(SYSTEM_USER, "user");
		systemIdAndNameMap.put(SYSTEM_DEVICE, "device");
		systemIdAndNameMap.put(SYSTEM_CONFIG, "config");
		systemIdAndNameMap.put(SYSTEM_LOG, "log");
		systemIdAndNameMap.put(SYSTEM_PFM, "pfm");
	}

	public final static Map<Integer,String> systemIdAndStrNameMap = new LinkedHashMap<Integer,String>(); 
	static{
		systemIdAndStrNameMap.put(SYSTEM_USER, STR_SYSTEM_USER);
		systemIdAndStrNameMap.put(SYSTEM_DEVICE, STR_SYSTEM_DEVICE);
		systemIdAndStrNameMap.put(SYSTEM_CONFIG, STR_SYSTEM_CONFIG);
		systemIdAndStrNameMap.put(SYSTEM_LOG, STR_SYSTEM_LOG);
		systemIdAndStrNameMap.put(SYSTEM_PFM, STR_SYSTEM_PFM);
	}
	
	//end 系统编号
	
	//start 系统模块
	public final static Integer LOG_MODULE_BASIC_ROLE =1;//角色管理
	public final static Integer LOG_MODULE_BASIC_USER = 2;//用户管理
	public final static Integer LOG_MODULE_BASIC_DEVICE = 3;//设备管理
	public final static Integer LOG_MODULE_BASIC_DEVICE_LPR = 4;//停车场lpr设备
	public final static Integer LOG_MODULE_BASIC_DEVICE_SCREEN = 5;//停车场显示屏设备
	public final static Integer LOG_MODULE_BASIC_PFM_SERVER = 6;//停车场服务
	public final static Integer LOG_MODULE_BASIC_SYSTEM_PARAMSS = 7;//系统参数
	public final static Integer LOG_MODULE_BASIC_WANGYU= 8;//网域
	public final static Integer LOG_MODULE_BASIC_DATABASE= 9;//数据库
	public final static Integer LOG_MODULE_BASIC_ALERM_LOG= 10;//报警日志
	public final static Integer LOG_MODULE_BASIC_CONFIG_LOG= 11;//配置日志
	public final static Integer LOG_MODULE_BSIC_DEVICE_LOG= 12;//设备日志 
	public final static Integer LOG_MODULE_BSIC_CTRL_LOG =13;
	
	public final static Integer LOG_MODULE_PFM_CAR_TYPE = 14; //车辆类型
	public final static Integer LOG_MODULE_PFM_CAR_INFO =15; //车辆信息
	public final static Integer LOG_MODULE_PFM_CHARGE_RULE = 16; //收费规则
	public final static Integer LOG_MODULE_PFM_ACCOUNT= 17; //账户管理
	public final static Integer LOG_MODULE_PFM_MONITOR =18; //出入监控
	public final static Integer LOG_MODULE_PFM_PARK = 19; //车场管理
	public final static Integer LOG_MODULE_PFM_TIME =20;  //通行时段
	public final static Integer LOG_MODULE_PFM_ENTRANCE =21; //出入口
	public final static Integer LOG_MODULE_PFM_BOOTH =22; //岗亭管理
	public final static Integer LOG_MODULE_PFM_SPECIALCARNUM =23; //特殊车牌
	public final static Integer LOG_MODULE_PFM_MESSAGE = 24; //消息管理
	public final static Integer LOG_MODULE_PFM_CHARGER_RECORD_REPORT = 25; //收费记录查询
	public final static Integer LOG_MODULE_PFM_NORAML_REPORT = 26; //正常通行记录
	public final static Integer LOG_MODULE_PFM_ABNORAML_REPORT = 27; //异常通行记录
	public final static Integer LOG_MODULE_PFM_IN_PARK_CAR_REPORT = 28; //场内查询报表
	public final static Integer LOG_MODULE_PFM_ACCOUNT_REPORT = 29; //账户报表操作记录
	
	public final static String STR_LOG_MODULE_BASIC_ROLE ="角色管理";//角色管理
	public final static String STR_LOG_MODULE_BASIC_USER = "用户管理";//用户管理
	public final static String STR_LOG_MODULE_BASIC_DEVICE = "设备管理";//设备管理
	public final static String STR_LOG_MODULE_BASIC_DEVICE_LPR = "停车场lpr设备管理";//停车场lpr设备
	public final static String STR_LOG_MODULE_BASIC_DEVICE_SCREEN = "停车场显示屏设备";//停车场显示屏设备
	public final static String STR_LOG_MODULE_BASIC_PFM_SERVER ="停车场服务";//停车场服务
	public final static String STR_LOG_MODULE_BASIC_SYSTEM_PARAMSS = "系统参数";//系统参数
	public final static String STR_LOG_MODULE_BASIC_WANGYU= "网域";//网域
	public final static String STR_LOG_MODULE_BASIC_DATABASE= "数据库";//数据库
	public final static String STR_LOG_MODULE_BASIC_ALERM_LOG= "报警日志";//报警日志
	public final static String STR_LOG_MODULE_BASIC_CONFIG_LOG= "配置日志";//配置日志
	public final static String STR_LOG_MODULE_BSIC_DEVICE_LOG= "设备日志 ";//设备日志 
	public final static String STR_LOG_MODULE_BSIC_CTRL_LOG ="控制日志 ";
	
	public final static String STR_LOG_MODULE_PFM_CAR_TYPE = "车辆类型"; //车辆类型
	public final static String STR_LOG_MODULE_PFM_CAR_INFO ="车辆信息"; //车辆信息
	public final static String STR_LOG_MODULE_PFM_CHARGE_RULE = "收费规则"; //收费规则
	public final static String STR_LOG_MODULE_PFM_ACCOUNT= "账户管理"; //账户管理
	public final static String STR_LOG_MODULE_PFM_MONITOR ="出入监控"; //出入监控
	public final static String STR_LOG_MODULE_PFM_PARK = "车场管理"; //车场管理
	public final static String STR_LOG_MODULE_PFM_TIME ="通行时段";  //通行时段
	public final static String STR_LOG_MODULE_PFM_ENTRANCE ="出入口"; //出入口
	public final static String STR_LOG_MODULE_PFM_BOOTH ="岗亭管理"; //岗亭管理
	public final static String STR_LOG_MODULE_PFM_SPECIALCARNUM ="特殊车牌"; //特殊车牌
	public final static String STR_LOG_MODULE_PFM_MESSAGE = "消息管理"; //消息管理
	public final static String STR_LOG_MODULE_PFM_CHARGER_RECORD_REPORT ="收费记录导出"; //收费记录查询
	public final static String STR_LOG_MODULE_PFM_NORAML_REPORT = "正常通行记录导出"; //正常通行记录
	public final static String STR_LOG_MODULE_PFM_ABNORAML_REPORT = "异常通行记录导出"; //异常通行记录
	public final static String STR_LOG_MODULE_PFM_IN_PARK_CAR_REPORT = "场内查询报表导出"; //场内查询报表
	public final static String STR_LOG_MODULE_PFM_ACCOUNT_REPORT = "账户查询报表"; //场内查询报表
	
	
	
	
	
	/* 模块id与名称对应map*/
	public final static Map<Integer,String> moduleIdAndNameMap = new LinkedHashMap<Integer,String>();
	static{
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_ROLE, "role");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_USER, "user");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_DEVICE, "device");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_DEVICE_LPR, "device_lpr");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_DEVICE_SCREEN, "device_screen");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_PFM_SERVER, "pfm_server");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_SYSTEM_PARAMSS, "system_params");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_WANGYU, "wangyu");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_DATABASE, "database");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_ALERM_LOG, "alerm_log");
		moduleIdAndNameMap.put(LOG_MODULE_BASIC_CONFIG_LOG, "config_log");
		moduleIdAndNameMap.put(LOG_MODULE_BSIC_DEVICE_LOG, "device_log");
		moduleIdAndNameMap.put(LOG_MODULE_BSIC_CTRL_LOG, "ctrl_log");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_CAR_TYPE, "car_type");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_CAR_INFO, "car_info");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_CHARGE_RULE, "chager_rule");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_ACCOUNT, "account");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_MONITOR, "monitor");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_PARK, "park");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_TIME, "time");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_ENTRANCE, "entrance");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_BOOTH, "booth");	
		moduleIdAndNameMap.put(LOG_MODULE_PFM_SPECIALCARNUM, "specialcarnum");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_MESSAGE, "message");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_CHARGER_RECORD_REPORT, "charger_report");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_NORAML_REPORT, "noraml_report");	
		moduleIdAndNameMap.put(LOG_MODULE_PFM_ABNORAML_REPORT, "annoraml_report");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_IN_PARK_CAR_REPORT, "in_park_car_report");
		moduleIdAndNameMap.put(LOG_MODULE_PFM_ACCOUNT_REPORT, "account_report");
	}
	
	public final static Map<Integer,String> moduleIdAndStrNameMap = new LinkedHashMap<Integer,String>();
	static{
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_ROLE,STR_LOG_MODULE_BASIC_ROLE );
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_USER, STR_LOG_MODULE_BASIC_USER);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_DEVICE, STR_LOG_MODULE_BASIC_DEVICE);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_DEVICE_LPR, STR_LOG_MODULE_BASIC_DEVICE_LPR);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_DEVICE_SCREEN, STR_LOG_MODULE_BASIC_DEVICE_SCREEN);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_PFM_SERVER, STR_LOG_MODULE_BASIC_PFM_SERVER);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_SYSTEM_PARAMSS, STR_LOG_MODULE_BASIC_SYSTEM_PARAMSS);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_WANGYU, STR_LOG_MODULE_BASIC_WANGYU);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_DATABASE, STR_LOG_MODULE_BASIC_DATABASE);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_ALERM_LOG, STR_LOG_MODULE_BASIC_ALERM_LOG);
		moduleIdAndStrNameMap.put(LOG_MODULE_BASIC_CONFIG_LOG, STR_LOG_MODULE_BASIC_CONFIG_LOG);
		moduleIdAndStrNameMap.put(LOG_MODULE_BSIC_DEVICE_LOG, STR_LOG_MODULE_BSIC_DEVICE_LOG);
		moduleIdAndStrNameMap.put(LOG_MODULE_BSIC_CTRL_LOG, STR_LOG_MODULE_BSIC_CTRL_LOG);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_CAR_INFO, STR_LOG_MODULE_PFM_CAR_INFO);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_CHARGE_RULE, STR_LOG_MODULE_PFM_CHARGE_RULE);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_ACCOUNT, STR_LOG_MODULE_PFM_ACCOUNT);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_MONITOR, STR_LOG_MODULE_PFM_MONITOR);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_PARK, STR_LOG_MODULE_PFM_PARK);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_TIME, STR_LOG_MODULE_PFM_TIME);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_ENTRANCE, STR_LOG_MODULE_PFM_ENTRANCE);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_BOOTH, STR_LOG_MODULE_PFM_BOOTH);	
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_SPECIALCARNUM, STR_LOG_MODULE_PFM_SPECIALCARNUM);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_MESSAGE, STR_LOG_MODULE_PFM_MESSAGE);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_CHARGER_RECORD_REPORT, STR_LOG_MODULE_PFM_CHARGER_RECORD_REPORT);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_NORAML_REPORT, STR_LOG_MODULE_PFM_NORAML_REPORT);	
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_ABNORAML_REPORT, STR_LOG_MODULE_PFM_ABNORAML_REPORT);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_IN_PARK_CAR_REPORT, STR_LOG_MODULE_PFM_IN_PARK_CAR_REPORT);
		moduleIdAndStrNameMap.put(LOG_MODULE_PFM_ACCOUNT_REPORT, STR_LOG_MODULE_PFM_ACCOUNT_REPORT);
		
	}
	
	/* 模块与系统对应map*/
	public final static Map<Integer,Integer> moduleIdAndSystemIdMap = new LinkedHashMap<Integer,Integer>();
	static{
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_ROLE, SYSTEM_USER);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_USER, SYSTEM_USER);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_DEVICE, SYSTEM_DEVICE);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_DEVICE_LPR, SYSTEM_DEVICE);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_DEVICE_SCREEN, SYSTEM_DEVICE);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_PFM_SERVER, SYSTEM_DEVICE);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_SYSTEM_PARAMSS, SYSTEM_CONFIG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_WANGYU, SYSTEM_CONFIG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_DATABASE, SYSTEM_CONFIG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_ALERM_LOG, SYSTEM_LOG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BASIC_CONFIG_LOG, SYSTEM_LOG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BSIC_DEVICE_LOG, SYSTEM_LOG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_BSIC_CTRL_LOG, SYSTEM_LOG);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_CAR_INFO, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_CHARGE_RULE, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_ACCOUNT, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_MONITOR, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_PARK, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_TIME, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_ENTRANCE, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_BOOTH, SYSTEM_PFM);	
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_SPECIALCARNUM, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_MESSAGE, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_CHARGER_RECORD_REPORT, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_NORAML_REPORT, SYSTEM_PFM);	
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_ABNORAML_REPORT, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_IN_PARK_CAR_REPORT, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_CAR_TYPE, SYSTEM_PFM);
		moduleIdAndSystemIdMap.put(LOG_MODULE_PFM_ACCOUNT_REPORT, SYSTEM_PFM);
		
	}
	/*系统包含的模块map*/
	public final static Map<Integer,List<Integer>> systemIdAnModuleListMap =  new LinkedHashMap<Integer,List<Integer>>(); //系统编号与包含的模块编号集合
	static{
		systemIdAnModuleListMap.put(SYSTEM_USER, Arrays.asList(LOG_MODULE_BASIC_ROLE,LOG_MODULE_BASIC_USER)); //用户管理配置模块
		systemIdAnModuleListMap.put(SYSTEM_DEVICE, Arrays.asList(LOG_MODULE_BASIC_DEVICE,
				LOG_MODULE_BASIC_DEVICE_LPR,
				LOG_MODULE_BASIC_DEVICE_SCREEN,
				LOG_MODULE_BASIC_PFM_SERVER)); //设备管理
		systemIdAnModuleListMap.put(SYSTEM_CONFIG, Arrays.asList(LOG_MODULE_BASIC_SYSTEM_PARAMSS,
				LOG_MODULE_BASIC_WANGYU,
				LOG_MODULE_BASIC_DATABASE)); //系统配置
		systemIdAnModuleListMap.put(SYSTEM_LOG, Arrays.asList(LOG_MODULE_BASIC_ALERM_LOG,
				LOG_MODULE_BASIC_CONFIG_LOG,
				LOG_MODULE_BSIC_DEVICE_LOG,
				LOG_MODULE_BSIC_CTRL_LOG)); //日志查询
		systemIdAnModuleListMap.put(SYSTEM_PFM, Arrays.asList(LOG_MODULE_PFM_CAR_TYPE,
				LOG_MODULE_PFM_CAR_INFO,
				LOG_MODULE_PFM_CHARGE_RULE,
				LOG_MODULE_PFM_ACCOUNT,
				LOG_MODULE_PFM_MONITOR,
				LOG_MODULE_PFM_PARK,
				LOG_MODULE_PFM_CAR_TYPE,
				LOG_MODULE_PFM_TIME,
				LOG_MODULE_PFM_ENTRANCE,
				LOG_MODULE_PFM_BOOTH,
				LOG_MODULE_PFM_SPECIALCARNUM,
				LOG_MODULE_PFM_MESSAGE,
				LOG_MODULE_PFM_CHARGER_RECORD_REPORT,
				LOG_MODULE_PFM_NORAML_REPORT,
				LOG_MODULE_PFM_ABNORAML_REPORT,
				LOG_MODULE_PFM_IN_PARK_CAR_REPORT,
				LOG_MODULE_PFM_ACCOUNT_REPORT));
	}	
	//end 系统模块


}
	
