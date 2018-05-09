package com.wadejerry.scms.frame.constant.user;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* 系统配置常量
* @ClassName: ConstParamSysConfig
* @Description: TODO
* @author zhanying
* @date 2016年12月28日 上午11:02:00
*
 */
public class ConstParamSysConfig {
    
    public static final int CONFIG_TYPE_LOG = 0; // 日志
    public static final int CONFIG_TYPE_TIME = 1; // 校时
    public static final int CONFIG_TYPE_EMAIL = 2; // 邮箱
    public static final int CONFIG_TYPE_ONECARD = 3;// 一卡通
    public static final int CONFIG_TYPE_PMS = 4;// 停车场
    public static final int CONFIG_TYPE_DBMANAGE = 5;// 数据库管理
    public static final int CONFIG_TYPE_LINK = 6;// 第三方链接
    public static final int CONFIG_PTZ_SEIZE = 7;// 云台抢占
    public static final int CONFIG_LOGO_CUSTOM = 8;// Logo自定义
    public static final int CONFIG_TYPE_PFM =9;	//停车场
    
    public static final Integer TYPE_INT = 0;
    public static final Integer TYPE_STRING = 1;
    
    public static final Integer LOG_ALARM_PARAM_ID = 14;	//报警日志ID
    public static final Integer LOG_CONFIG_PARAM_ID = 15;	//配置日志
    public static final Integer LOG_CONTROL_PARAM_ID = 13;	//控制日志
    public static final Integer LOG_NET_PARAM_ID = 16;	//网管日志
    public static final Integer LOG_WORD_PARAM_ID = 17;	//工作日志
    public static final Integer LOG_AUTO_SET_TIME_STYLE_ID = 11; //自动校时时间
    public static final Integer LOG_AUTO_SET_TIME_STYLE_WEEK_ID =12; //自动校时间星期
   
    public static final Integer PFM_PHOTO_SAVE_TIME = 18;	//停车场图片保存时间
    public static final Integer PFM_PHOTO_SVAE_PATH = 19;	//车辆抓拍图片保存路径
    public static final Integer SCMS_PAY_URL= 20;	//支付平台平台地址
    public static final Integer SUCCESS_FUL_PAY_OFF_TIME= 21;	//支付平台平台地址
    
    public static final Map<Integer, Integer> MONTHMAP = new LinkedHashMap<Integer, Integer>();
    static {
        MONTHMAP.put(1, 1);
        MONTHMAP.put(3, 3);
        MONTHMAP.put(6, 6);
        MONTHMAP.put(9, 9);
        MONTHMAP.put(12, 12);
        MONTHMAP.put(15, 15);
        MONTHMAP.put(18, 18);
        MONTHMAP.put(21, 21);
        MONTHMAP.put(24, 24);
    }
    
    /** 默认80端口*/
    public static final Integer SERVERPORT = 80;
    
    /** 默认备份文件数量上限 */
    public static final Integer MAXFILEQUANTITY = 24;
    
    /** 默认备份文件总大小限制（MB） */
    public static final Integer MAXFILETOTALSIZE = 20480;
    
    /** 天计划 */
    public static final Integer DAYPLANTYPE = 1;
    
    /** 星期计划 */
    public static final Integer WEEKPLANTYPE = 2;
    
    /** 月计划 */
    public static final Integer MONTHPLANTYPE = 3;
    
    /** 间隔计划 */
    public static final Integer INTERVALPLANTYPE = 4;
    
    /** 是否启用第三方链接，0否，1是 */
    public static final Integer ENABLELINK = 1;
    
    /** Logo大小最大限制2M */
    public static final Integer LOGO_MAXSIZE = 2;
    
    /** Logo单位换算 */
    public static final Integer LOGO_CONVERTOR = 1024 * 1024;
    
    /** 缺省路径 */
    public static final String DEFAULT_PATH = "/";
    
    /** Logo存储目录 */
    public static final String LOGO_DIRECTORY = "/skin/logo/";
    
    /** Logo存储目录 */
    public static final String LOGO_UPLOAD_DIRECTORY = "skin" + File.separator + "logo" + File.separator;
    
    /** Logo临时文件后缀 */
    public static final String LOGO_TMP_SUFFIX = ".tmp";
    
    /** Logo支持的图片格式后缀 */
    public static final String[] LOGO_FORMAT_SUFFIX = {".jpg", ".bmp", ".png"};
    
    /** 记录用户操作日志 */
    public static final String LOG_SYSTEM_OPERATE = "logContent.system.operate";
    
    /** 是否启用第三方链接 */
    public static final String ENABLE_THIRDPARTY_LINK = "enableThirdPartyLink";
    
    /** 登陆模块数组长度 */
    public static final Integer LOGINMODULE_LENGTH = 3;
    
    /** 皮肤信息，0是红版，其它是黑版 */
    public static final Integer SKININFO = 0;
    
}
