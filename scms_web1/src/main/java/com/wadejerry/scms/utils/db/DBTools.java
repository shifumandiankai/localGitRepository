package com.wadejerry.scms.utils.db;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.common.service.CommonService;
import com.wadejerry.scms.utils.Log.LogManager;

/**
 * 
 * <p>数据库工具类</p>
 * @author ldy Aug 22, 2013 4:10:42 PM
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} Aug 22, 2013
 * @modify by reason:{方法名}:{原因}
 */
@SuppressWarnings("unchecked")
public class DBTools { 
	public final static String Calendar_yyyyMM = "yyyyMM";
	
	public final static int PARTITION_INTERVAL = 1;//1个月1张表
	public final static int PARTITION_NUM = 12;//一次创建12张表
	public final static String PARTITION_NAME = "partitionname"; //分区名称使用统一的别名“partitionName”
	
	public final static String TABLE_PFM_RECORD = "pfm_record";
	public final static String COLUMN_EVENT_TIME = "event_time";
	
	public final static String TABLE_PFM_CHAREGE_RECORD = "pfm_charge_record";
	public final static String COLUMN_CHARGE_TIME = "out_time";
	
	private static PostgreSQLDBContext DBContext = null;
	private static String packagePath = "/com/wadejerry/scms/utils/db/";
	private static String fileSuffix = "_tpl.txt";

	static{
		DBContext = new PostgreSQLDBContext(); 
	}
	
	/**
	 * 自动表分区任务入口
	 * @author ldy Aug 22, 2013 4:13:37 PM
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	public static void autoAddPartition() {
		LogManager.logInfo("自动表分区检查开始");
		if (isOracle()) {
			LogManager.logInfo("Oracle自动表分区功能由定时脚本完成");
		} else {
			autoAddPartition(TABLE_PFM_RECORD, COLUMN_EVENT_TIME);
			autoAddPartition(TABLE_PFM_CHAREGE_RECORD, COLUMN_CHARGE_TIME);
		}
		LogManager.logInfo("自动表分区检查结束");
	}
	

	/**
	 * 表分区删除入口-批量
	 * @author ldy Aug 22, 2013 4:13:02 PM
	 * @param partitionNames 分区表格式为tableName_yyyyMM
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	@SuppressWarnings("unused")
    private static void removePartitions(List<String> partitionNames) {
		LogManager.logInfo("表分区删除开始");
		CommonService commonService = AppContext.getBean("commonService");
		for (String name : partitionNames) {
			String sql = DBTools.getDBContext().getRemovePartitionSQL(name);
			commonService.excuteSQL(sql);		
			LogManager.logInfo(name+"表分区删除");
		}
		LogManager.logInfo("表分区删除结束");
	}
	
	/**
	 * 表分区删除入口-单个
	 * @author ldy Aug 26, 2013 11:27:34 AM
	 * @param partitionName  分区表格式为tableName_yyyyMM
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 26, 2013
	 * @modify by reason:{原因}
	 */
	private static Boolean removePartition(String partitionName) {
		CommonService commonService = AppContext.getBean("commonService");
		String sql = DBTools.getDBContext().getRemovePartitionSQL(partitionName);
		Boolean result = commonService.excuteSQL(sql);
		LogManager.logInfo(partitionName+"表分区删除");
		return result;
	}
	/**
	 * 
	 * @author ldy Sep 2, 2013 10:01:52 AM
	 * @param tableName 表名
	 * @param monthLength 保存的时长（月）
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Sep 2, 2013
	 * @modify by reason:{原因}
	 */
	public static void removePartitionByMonth(String tableName, int monthLength) {
		LogManager.logInfo("表分区删除开始");
		if (isOracle()) {
			LogManager.logInfo("Oracle自动表分区功能由定时脚本完成");
		} else {
			Calendar removeEndTime = Calendar.getInstance();
			removeEndTime.add(Calendar.MONTH, -(monthLength + 1));
			List<String> removePartitionList = getPartitionNames(tableName, null, removeEndTime, true);
			for(String str : removePartitionList){
				LogManager.logInfo(str);
				removePartition(str);
			}
		}
		LogManager.logInfo("表分区删除结束");
	}
	/**
	 * 获得指定时间范围内的分区
	 * @author ldy Aug 22, 2013 4:12:02 PM
	 * @param tableName 表名
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param includeBoundary 是否包含边界，即分区的时间范围与开始时间或结束时间相交
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private static List<String> getPartitionNames(String tableName, Calendar startTime, Calendar endTime, boolean includeBoundary) {
		String sql = DBTools.getDBContext().getPartitionInfoSQL(tableName);
		List<String> partitionNames = new ArrayList<String>();
		
			CommonService commonService = AppContext.getBean("commonService");
			List<Map<String, Object>> list = commonService.findPartitionList(sql);
			int index = tableName.length();
			for (Map<String, Object> map : list) {
				// 分区名称，格式为tableName_yyyyMM，1个月1张分表
				String partitionName = (String)map.get(PARTITION_NAME);
				if (partitionName == null || partitionName.length() <= index) {
					LogManager.logInfo(partitionName+" is error");
					continue;
				}
				String time = partitionName.substring(index+1);// 去掉表名部分
				Calendar start = CommonFun.toCalendar(time, Calendar_yyyyMM);
				Calendar end = (Calendar)start.clone();
				end.add(Calendar.MONTH, 1);//201308+1=201309
				
				// 检查开始时间
				if (startTime != null) {
					if (start.compareTo(startTime) < 0) {//start早于startTime
						if (includeBoundary && end.compareTo(startTime) > 0) {
							partitionNames.add(partitionName);
						}
						continue;
					}
				}
				// 检查结束时间
				if (endTime != null) {
					if (end.compareTo(endTime) > 0) {//end晚于endTime
						if (includeBoundary && start.compareTo(endTime) < 0) {
							partitionNames.add(partitionName);
						}
						continue;
					}
				}
				partitionNames.add(partitionName);
			}
		
		return partitionNames;
	}
	
	
	/**
	 * 增加表分区
	 * @author ldy Aug 24, 2013 3:09:59 PM
	 * @param tableName 数据库表名
	 * @param startTime
	 * @param queryParam 查询字段
	 * @throws Exception
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	private static void addPartition(String tableName, Calendar startTime, String queryParam){
		if (tableName == null) {
			LogManager.logDebug("表名不能为空");
			return;
		}
		if (startTime == null) {
			//LogUtils.logDebug("开始时间不能为空");
			LogManager.logDebug("开始时间不能为空");
			return;
		}
		StringBuilder sql = new StringBuilder();
		// 创建分区的sql脚本模板
		String tpl = CommonFun.getCurrentPath()+ packagePath + DBTools.getDataBaseType() + fileSuffix;
		//LogUtils.logInfo(tpl);
		LogManager.logInfo(tpl);
		// 读取分区脚本模板
		String tplScript = CommonFun.readFile(tpl);
		if (tplScript == null) {
			//LogUtils.logDebug("创建分区脚本模板文件不存在");
			LogManager.logDebug("创建分区脚本模板文件不存在");
			return;
		}
		//记录插入时判断插入哪个表用
		Calendar start = (Calendar)startTime.clone();
		Calendar end = (Calendar) start.clone();
		end.add(Calendar.MONTH, DBTools.PARTITION_INTERVAL);
		
		String tableSuffix = "";
		for(int i = 0; i < DBTools.PARTITION_NUM; i=i+DBTools.PARTITION_INTERVAL){
			String month = String.valueOf(start.get(Calendar.MONTH)+1);//8月份取到的值是7
			if(month.length() <= 1 ){//将月补齐到两位
				month = "0" + month;
			}
			tableSuffix = start.get(Calendar.YEAR)+month;

			//LogUtils.logDebug(startTime.getTime().toString());
			//LogUtils.logDebug(tableSuffix);
			String script = tplScript;
			// 将分区脚本模板修正为实际可执行的脚本
			script = script.replaceAll("#table-suffix#", tableSuffix);
			script = script.replaceAll("#start-time#", CommonFun.format(start, "yyyy-MM-dd"));
			script = script.replaceAll("#end-time#", CommonFun.format(end, "yyyy-MM-dd"));
			script = script.replaceAll("#table-name#", tableName);
			script = script.replaceAll("#query-param#", queryParam);
			sql.append(script).append("\n");
			
			//为下个分区准备
			start.add(Calendar.MONTH, DBTools.PARTITION_INTERVAL);
			end.add(Calendar.MONTH, DBTools.PARTITION_INTERVAL);
			
		}
		execSQLByContext(sql.toString());
	}
	
    /**
     * 执行SQL脚本
     * @author ldy Aug 22, 2013 4:13:29 PM
     * @param sql
     * @throws Exception
     * @modificationHistory=========================逻辑或功能性重大变更记录
     * @modify by user: {修改人} Aug 22, 2013
     * @modify by reason:{原因}
     */
	private static void execSQLByContext(String sql){
		String tempPath = CommonFun.getTempPath();
		Random random = new Random();
		// 临时sql文件
		String sqlFileName = CommonFun.format(new Date(), "yyyyMMddHHmmss") + random.nextInt() + ".sql";
		sqlFileName = tempPath + "/" + sqlFileName;
		// 将sql写入临时文件
		CommonFun.writeFile(sqlFileName, sql);
		DataSourceInfo dataSourceInfo = getDataSourceInfo();
		final String userName = dataSourceInfo.getUserName();
		final String password = dataSourceInfo.getPassword();
		final String dbName = dataSourceInfo.getDbName();
		final String host = dataSourceInfo.getHost();
		PostgreSQLDBContext dbContext = getDBContext();
        try {
            dbContext.restoreDB(userName, password, dbName, host, sqlFileName);
        } catch (Exception e) {
            //LogUtils.logException(e);
        	LogManager.logException(e);
        }
		File sqlFile = new File(sqlFileName);
		if (!sqlFile.delete()) {
			//LogUtils.logInfo("临时生成的脚本文件删除失败");
			LogManager.logInfo("临时生成的脚本文件删除失败");
		}
	}

	
	/**
	 * 自动表分区
	 * @author ldy Aug 22, 2013 4:13:54 PM
	 * @param tableName
	 * @param queryParam 查询字段
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private static void autoAddPartition(String tableName, String queryParam) {
		List<String> partitionNames = getPartitionNames(tableName, Calendar.getInstance(), null, true);
		Calendar startTime = null;
		Calendar lastTime = null;//最后一张分表的时间
		Calendar now = Calendar.getInstance();
		boolean partition = false;
		if (partitionNames == null || partitionNames.size() == 0) {
			LogManager.logInfo(tableName + "表分区不存在");
			startTime = now;
			startTime.set(Calendar.HOUR_OF_DAY, 0);
			startTime.set(Calendar.MINUTE, 0);
			startTime.set(Calendar.SECOND, 0);
			LogManager.logInfo(startTime.getTime().toString());
			partition = true;
		} else {
			LogManager.logInfo("检查" +tableName + "表分区");
			String last = partitionNames.get(partitionNames.size() - 1);
			int index = tableName.length() + 1;
			String time = last.substring(index);// 去掉表名部分
			if (time.length() == Calendar_yyyyMM.length()) {
				lastTime = CommonFun.toCalendar(time, Calendar_yyyyMM);// 解析得到时间范围
				LogManager.logDebug(now.getTime().toString());
				LogManager.logDebug(lastTime.getTime().toString());
				if (now.compareTo(lastTime) >= 0) {//now晚于或等于end，最后一个月触发创建下一年的分区表
					LogManager.logInfo(tableName + "表可用分区只剩当前月，触发下一年表分区");
					startTime = (Calendar)lastTime.clone();
					startTime.add(Calendar.MONTH, PARTITION_INTERVAL);
					partition = true;
				}
			}
		}
		if (partition) {
			addPartition(tableName, startTime, queryParam);
		}
	}
	

	/**
	 * 得到数据库类型
	 * @author ldy Aug 22, 2013 4:10:55 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private static String getDataBaseType() {
/*		if (dataBaseType == null) {
			LocalSessionFactoryBean sessionFactory = DBTools.getLocalSessionFactoryBean();// Spring中配置的SessionFactory
			if (sessionFactory != null) {
				DataSource dataSource = sessionFactory.getDataSource();
				String driverClass = null;// 驱动。解析当前使用的驱动名称来得到数据源类型
				if (dataSource instanceof ComboPooledDataSource) {
					ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;
					driverClass = comboPooledDataSource.getDriverClass();
				} else if (dataSource instanceof DriverManagerDataSource){
					DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource)dataSource;
					driverClass = driverManagerDataSource.getUrl();
				}
				if (driverClass != null) {
					if (driverClass.indexOf("OracleDriver") > -1 || driverClass.indexOf("oracle") > -1) {
						dataBaseType = "oracle";
					} else if (driverClass.indexOf("postgresql") > -1 || driverClass.indexOf("pgsql") > -1) {
						dataBaseType = "postgresql";
					}
				}
			}
		}
		return dataBaseType;*/
		return "postgresql";
	}
	
	/**
	 * 得到数据库上下文
	 * @author ldy Aug 22, 2013 4:11:25 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private static PostgreSQLDBContext getDBContext() {
		return DBContext;
	}

	/**
	 * 得到当前使用的SessionFactory
	 * @author ldy Aug 22, 2013 4:11:35 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	@SuppressWarnings("rawtypes")
	private static SqlSessionFactoryBean getLocalSessionFactoryBean() {
		Map map = AppContext.getContext().getBeansOfType(SqlSessionFactoryBean.class);
		SqlSessionFactoryBean sessionFactory = null;
		for (Object value : map.values()) {
			if (value instanceof SqlSessionFactoryBean) {
				sessionFactory = (SqlSessionFactoryBean)value;
				break;
			}
		}
		return sessionFactory;
	}
	
	/**
	 * 得到数据源信息——proxool方式
	 * @author ldy Aug 22, 2013 4:11:45 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
/*	private static DataSourceInfo getProxoolDataSourceInfo() {
		DataSourceInfo dataSourceInfo = new DataSourceInfo();
			String driver = null;
			String url = null;
			String userName = null;
			String password = null;
			String encoding = null;
			dataSourceInfo.setDriver(driver);
			dataSourceInfo.setUserName(userName);
			dataSourceInfo.setPassword(password);
			dataSourceInfo.setUrl(url);
			if (url != null) {
				getDBContext().parseJdbcUrl(url, dataSourceInfo);// 解析URL，获得数据库连接的ip、端口、数据库名称等信息
				Pattern pattern = Pattern.compile("UTF-8|GBK|GB2312", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(url);
				if (matcher.find()) {
					encoding = matcher.group();
				}
			}
			dataSourceInfo.setEncoding(encoding);
		}
		return dataSourceInfo;
		
	}*/
	
	/**
	 * 得到数据源信息——spring方式,改成proxool后不能使用
	 * @author ldy Aug 22, 2013 4:11:45 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	@SuppressWarnings("unused")
	private static DataSourceInfo getDataSourceInfo() {
		DataSourceInfo dataSourceInfo = new DataSourceInfo();
		SqlSessionFactoryBean sessionFactory = DBTools.getLocalSessionFactoryBean();
		
			DataSource dataSource = (DataSource)AppContext.getBean("dataSource");
			String driver = null;
			String url = null;
			String userName = null;
			String password = null;
			String encoding = null;
			if (dataSource instanceof BasicDataSource) {
				BasicDataSource basicDataSource = (BasicDataSource)dataSource;
				driver = basicDataSource.getDriverClassName();
				url = basicDataSource.getUrl();
				userName = basicDataSource.getUsername();
				password = basicDataSource.getPassword();
				
			}
			dataSourceInfo.setDriver(driver);
			dataSourceInfo.setUserName(userName);
			dataSourceInfo.setPassword(password);
			dataSourceInfo.setUrl(url);
			if (url != null) {
				getDBContext().parseJdbcUrl(url, dataSourceInfo);// 解析URL，获得数据库连接的ip、端口、数据库名称等信息
				Pattern pattern = Pattern.compile("UTF-8|GBK|GB2312", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(url);
				if (matcher.find()) {
					encoding = matcher.group();
				}
			}
			dataSourceInfo.setEncoding(encoding);
		
		return dataSourceInfo;
		
	}
	
	/**
	 * 备份数据库
	 * @author ldy 2013-9-14 下午01:58:23
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} 2013-9-14
	 * @modify by reason:{原因}
	 */
	public static void backupDB() throws Exception {
		//DataSourceInfo dataSourceInfo = getProxoolDataSourceInfo();
		InputStream is = DBTools.class.getResourceAsStream("/properties/jdbc.properties");
		Properties prop = new Properties();
		prop.load(is);
		String userName = prop.getProperty("jdbc_username");
		String password = prop.getProperty("jdbc_password");
		String dbName = prop.getProperty("jdbc_dbname");
		String host = prop.getProperty("jdbc_host");

		String dataBaseBackupDir = getDataBaseBackupDir();
		CommonFun.makeDir(dataBaseBackupDir);
		String tempDir = CommonFun.getFilePath(CommonFun.getAttachmentPath(), "DataBaseBackupTemp");// 先在临时目录生成，避免用户操作中间文件
		CommonFun.makeDir(tempDir);
		String dumpFile = tempDir + dbName + "_1.sql";
		getDBContext().backupDB(userName, password, dbName, host, dumpFile);
		File sqlFile = new File(dumpFile);
		if (!sqlFile.exists()) {
			throw new Exception("备份失败！");
		}
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String targetDir = dataBaseBackupDir + dbName + "_" + format.format(new Date()) + ".sql";
		boolean result = sqlFile.renameTo(new File(targetDir));// 将备份文件从临时目录剪切到目标目录
		if (!result) {
			throw new Exception("备份文件归档失败！");
		}	
	}
	
	/**
	 * 判断是否是Oracle
	 * @author ldy 2013-11-11 上午08:34:08
	 */
	public static boolean isOracle() {
		//return "oracle".equals(getDataBaseType());
		return false;
	}
	
	/**
	 * 判断是否是PostgreSQL
	 * @author ldy 2013-11-11 上午08:34:14
	 */
	public static boolean isPostgreSQL() {
		return true;
	}
	
	/**
	 * 数据库还原
	 * @author ldy 2013-9-14 下午04:00:29
	 */
	public static void restoreDBByFileName(String fileName) throws Exception {
		final String backupFilePath = getDataBaseBackupDir() + fileName;
		File backupFile = new File(backupFilePath);
		if (!backupFile.exists()) {
			throw new Exception("备份文件不存在！");
		}
		if (!fileName.endsWith(".sql")) {
			throw new Exception("备份文件格式不正确！");
		}
		execSQLFileByContext(backupFilePath);
	}
	
	
	 /** 执行sql文件
	 * @author ldy 2013-9-14 下午04:04:11
	 **/
	public static void execSQLFileByContext(String sqlFilePath) throws Exception {
		DataSourceInfo dataSourceInfo = getDataSourceInfo();
		final String userName = dataSourceInfo.getUserName();
		final String password = dataSourceInfo.getPassword();
		final String dbName = dataSourceInfo.getDbName();
		final String host = dataSourceInfo.getHost();
		getDBContext().restoreDB(userName, password, dbName, host, sqlFilePath);
	}
	
	/**
	 * 数据库备份文件存储位置
	 * @author ldy 2013-9-14 下午02:26:55
	 */
	public static String getDataBaseBackupDir() {
		return CommonFun.getFilePath(CommonFun.getAttachmentPath(), "DataBaseBackup");
	}
	
	/**
	 * 获取数据库备份文件目录
	 * @author ldy 2013-9-11 下午03:33:33
	 */
	public static List<File> getDataBaseBackupFiles() {
		File fDir = new File(getDataBaseBackupDir());
		if (fDir.exists() && fDir.isDirectory()) {
			File[] backupFiles = fDir.listFiles();
			List<File> files = new ArrayList<File>();
			for (File file : backupFiles) {
				String fileName = file.getName();
				if (!fileName.endsWith(".sql")) {
					continue;
				}
				files.add(file);
			}
			sort(files, new Comparator<File>() {
				public int compare(File o1, File o2) {
				    return (o2.lastModified() - o1.lastModified() > 0) ? 1 : -1;
				}
			});
			return files;
		}
		return new ArrayList<File>();
	}
	
	/**
	 * 文件排序
	 * @author ldy 2013-9-11 下午03:33:33
	 */
	@SuppressWarnings("rawtypes")
	public static void sort(Object source, Comparator comparator) {
		if (source instanceof List) {
			Collections.sort((List)source, comparator);
		} else
			if (source instanceof Object[]) {
				Arrays.sort((Object[])source, comparator);
			} else {
				throw new IllegalArgumentException("类型不支持");
			}
	}
	
	/**
	 * 删除数据库备份文件
	 * @author ldy 2013-9-14 下午04:33:51
	 */
	public static void deleteDBFileByFileName(String fileName) throws Exception {
		final String backupFilePath = getDataBaseBackupDir() + fileName;
		File backupFile = new File(backupFilePath);
		if (!backupFile.exists()) {
			throw new Exception("备份文件不存在！");
		}
		if (!fileName.endsWith(".sql")) {
			throw new Exception("备份文件格式不正确！");
		}
		if (!CommonFun.deleteFile(backupFile)) {
			throw new Exception("删除文件失败！");
		}
	}
	
	/**
	 * 根据序列名称获取不同数据库对应的表达方式
	 * @author ldy 2013-11-11 上午08:32:03
	 */
	public static String getSequence(String sequenceName) {
		StringBuilder sequence = new StringBuilder();
		if (isOracle()) {
			sequence.append(sequenceName).append(".NEXTVAL");
		} else {// PostgreSQL
			sequence.append("NEXTVAL('").append(sequenceName).append("')");
		}
		return sequence.toString();
	}

}
