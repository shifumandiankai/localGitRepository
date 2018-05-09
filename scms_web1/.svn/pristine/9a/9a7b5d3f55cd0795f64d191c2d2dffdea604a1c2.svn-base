package com.wadejerry.scms.utils.db;

import com.wadejerry.scms.utils.Log.LogManager;

/**
 * PostgreSQL函数实现类
 * <p></p>
 * @author jiangdahui Aug 24, 2013 3:35:41 PM
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} Aug 24, 2013
 * @modify by reason:{方法名}:{原因}
 */
public class PostgreSQLDBContext {
	public void parseJdbcUrl(String url, DataSourceInfo info) {
		int end = url.indexOf("?");
		end = (end == -1)?url.length():end;
		url = url.substring(0, end);
		int start = url.lastIndexOf("/");
		
		String dbName = url.substring(start + 1);
		
		url = url.substring(0, start);
		url = url.substring(url.lastIndexOf("/") + 1);
		String[] s = url.split(":");
		info.setDbName(dbName);
		info.setHost(s[0]);
		info.setPort(Integer.parseInt(s[1]));
	}

	/**
	 * 根据rule名称截取分区表名称的sql，rule以insert_开头
	 * substring(r.rulename,8,100)中的8对应rule前缀insert_
	 * @author jiangdahui Aug 22, 2013 4:31:49 PM
	 * @param tableName
	 * @return 
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	public String getPartitionInfoSQL(String tableName) {
		String sql = "SELECT substring(r.rulename,8,100) as partitionName FROM ((pg_rewrite r JOIN pg_class c ON ((c.oid = r.ev_class))) LEFT JOIN pg_namespace n ON ((n.oid = c.relnamespace))) WHERE (r.rulename <> '_RETURN'::name) AND (n.nspname='public') AND (c.relname='"
				+ tableName + "') order by substring(r.rulename,8,100) asc";
		return sql;
	}
	

	public String getRemovePartitionSQL(String partitionName) {
		return "DROP TABLE " + partitionName + " CASCADE";
	}
	
	public void restoreDB(String userName, String password, String dbName, String host, String backupFile) throws Exception {
		//windows
		String setPwdCommond = "set";
		setPwdCommond += " PGPASSWORD=" + password;
        String restoreCommond = getDatabaseBinPath() + "psql -h %s -U %s -d %s -E -f \"%s\"";
        restoreCommond = String.format(restoreCommond, host, userName, dbName, backupFile);
       // LogUtils.logInfo("PostgreSQL数据库还原脚本：" + setPwdCommond + " " + restoreCommond);
        LogManager.logInfo("PostgreSQL数据库还原脚本：" + setPwdCommond + " " + restoreCommond);
        CmdRunResult result;
        result = CommonFun.callCmd(setPwdCommond, restoreCommond);
        if (result.getExitValue() == 0) {
            //LogUtils.logDebug("脚本执行结果：" + result);
            //LogUtils.logDebug("PostgreSQL数据库还原执行成功");
        	LogManager.logDebug("脚本执行结果：" + result);
        	LogManager.logDebug("PostgreSQL数据库还原执行成功");
        } else {
           // LogUtils.logInfo(result.getOutput());
        }
    }
	
	/**
	 * 备份数据库
	 * @author jinbenbin 2013-9-14 下午02:03:45
	 */
	public void backupDB(String userName, String password, String dbName, String host, String dumpFile, String... ignoreTables) throws Exception {
		//windows
		String setPwdCommond = "set";
		setPwdCommond += " PGPASSWORD=" + password;
		StringBuilder dumpCommand = new StringBuilder(getDatabaseBinPath() + "pg_dump -c -b -E UTF8 -U %s -h %s");
		if (ignoreTables != null) {
			for (String ignoreTable : ignoreTables) {
				dumpCommand.append(" -T ").append(ignoreTable);
			}
		}
		dumpCommand.append(" -f \"%s\" %s");
		String command = String.format(dumpCommand.toString(), userName, host, dumpFile, dbName);
		//LogUtils.logInfo("PostgreSQL数据库备份脚本：" + setPwdCommond + " " + command);
		LogManager.logInfo("PostgreSQL数据库备份脚本：" + setPwdCommond + " " + command);
		CmdRunResult result = CommonFun.callCmd(setPwdCommond, command);
		if (result.getExitValue() == 0) {
			//LogUtils.logDebug("脚本执行结果：" + result);
			//LogUtils.logDebug("PostgreSQL数据库备份执行成功");
			LogManager.logDebug("脚本执行结果：" + result);
			LogManager.logDebug("PostgreSQL数据库备份执行成功");
		} else {
			throw new Exception(result.getOutput());
		}
	}
	
	/** 服务器上Root相对路径*/
	private static final String CMSTOMCATROOTPATH = "CMS/webserver/Tomcat/webapps/ROOT";
	
	/** 服务器上数据库bin路径*/
	private static final String CMSDATABASEBINPATH = "CMS/database/bin/";
	
	/**
	 * 原因：服务器上可能没有配置环境变量导致bat命令执行失败，如数据库备份报错"'pg_dump'不是内部或外部命令"
	 * 解决方案：在pg_dump命令前加上PostgreSQL数据库的安装路径bin目录，根据WebRoot路径来获取
	 * 8700项目在服务器上安装目录是统一的，PostgreSQL数据库路径是"安装路径/CMS/database"，Tomcat路径是"安装路径/CMS/webserver/Tomcat"
	 * @author jinbenbin 2013-10-17 上午09:47:38
	 */
	public String getDatabaseBinPath() {
		StringBuilder databaseBinPath = new StringBuilder();
		String rootPath = CommonFun.getWebRootPath();
		if (rootPath.indexOf(CMSTOMCATROOTPATH) != -1) {// 判断是不是服务器上安装目录结构，为了兼容本地调试
			databaseBinPath.append("\"");
			databaseBinPath.append(rootPath.substring(0, rootPath.indexOf(CMSTOMCATROOTPATH))).append(CMSDATABASEBINPATH);
			databaseBinPath.append("\"");
		}
		return databaseBinPath.toString();
	}
	
}
