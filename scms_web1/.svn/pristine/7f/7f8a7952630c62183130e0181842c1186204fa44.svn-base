package com.wadejerry.scms.modules.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wadejerry.scms.utils.Log.LogManager;

/**
 * 
 * <p></p>
 * @author jiangdahui Aug 22, 2013 4:58:02 PM
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} Aug 22, 2013
 * @modify by reason:{方法名}:{原因}
 */
@SuppressWarnings("unchecked")
//public class CommonDAO extends HibernateEntityDao {
public class CommonDAO  {
	
	/**
	 * 根据完整的sql得到一个map列表
	 * @author jiangdahui Aug 22, 2013 4:58:14 PM
	 * @param sql
	 * @param params
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	public List<Map<String, Object>> findMapListBySQL(final String tableName, final List params) {
		//TODO 
		return null;
	
	}


	/**
	 * 执行sql语句
	 * @author jiangdahui Aug 22, 2013 4:58:23 PM
	 * @param sql
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	public Boolean excuteSQL(final String sql) {
/*		return (Boolean)getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				boolean result = false;
				Statement stmt = null;
                try {
	                Connection con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
	                stmt = con.createStatement();
	                result = stmt.execute(converSQL(sql));
                } catch (Exception e) {
                	LogManager.logException(e);
                } finally {
                	if(stmt != null){
                		stmt.close();
                	}
                	session.close();
                }
				return result;
			}
		});*/
		//TODO 
		return null;
	}
	
	/**
	 * 转换SQL
	 * @author jiangdahui Aug 22, 2013 4:58:27 PM
	 * @param sql
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	public static String converSQL(String sql) {
		LogManager.logDebug("before converSQL:"+sql);
		int index = 0;
		String match = " as ";
		int matchLength = match.length();
		String temp = sql.replaceAll(" {2,}", " ");//把字符串s中的多个空格替换为一个空格
		StringBuffer result = new StringBuffer();
		while ((index = temp.indexOf(match)) > -1) {
			int start = index + matchLength;
			//过滤空格
//			while (start + 1 <= temp.length() && temp.substring(start, start + 1).equals(" ")) {
//				start++;
//			}
			result.append(temp.substring(0, start)).append("\"");
			temp = temp.substring(start);
			start = 0;
			//给as名称加冒号,到空格、逗号、括号结束
			while (start + 1 <= temp.length() && !temp.substring(start, start + 1).equals(" ")
					&& !temp.substring(start, start + 1).equals(",") && !temp.substring(start, start + 1).equals(")")) {
				start++;
			}
			result.append(temp.substring(0, start)).append("\"");
			temp = temp.substring(start);
		}
		if (index == -1) {
			index = 0;
		}
		result.append(temp.substring(index));
		LogManager.logDebug("after  converSQL:"+result.toString());
		return result.toString();
	}
	
	/**
	 * 根据sql语句和类型得到类型中的属性
	 * @author jiangdahui Aug 22, 2013 4:58:36 PM
	 * @param rs
	 * @param clazz
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private List<String> getFieldList(ResultSet rs, Class clazz) {
		List<String> fieldList = new ArrayList<String>();
		// 得到rs的结果集数据元对象
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			// 得到字段(列)的数目
			int colCount = rsmd.getColumnCount();
			// 得到并显示出字段名和数据类型
			for (int i = 1; i <= colCount; i++) {
				String colName = rsmd.getColumnLabel(i);
				if (colName == null || colName.trim().equals("")) {
					colName = rsmd.getColumnName(i);
				}
				
//				String typeName = rsmd.getColumnTypeName(i);
				try {
					if (clazz == null || clazz.getDeclaredField(colName) != null) {
						fieldList.add(colName);
					}
				} catch (SecurityException e) {					
					LogManager.logException(e);
					continue;
				} catch (NoSuchFieldException e) {
					LogManager.logException(e);
					continue;
				}
			}
		} catch (SQLException e) {
			LogManager.logException(e);
		}
		
		return fieldList;
	}
	
	/**
	 * 
	 * @author jiangdahui Aug 22, 2013 4:58:42 PM
	 * @param rs
	 * @param fieldList
	 * @return
	 * @throws SQLException
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private List<Map<String, Object>> getMapList(ResultSet rs, List<String> fieldList) throws SQLException {
		List<Map<String, Object>> list = new ArrayList();
		while (rs.next()) {
			Map map = getMap(rs, fieldList);
			if (map != null) {
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * 
	 * @author jiangdahui Aug 22, 2013 4:58:46 PM
	 * @param rs
	 * @param fieldList
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 22, 2013
	 * @modify by reason:{原因}
	 */
	private Map getMap(ResultSet rs, List<String> fieldList) {
		Map map = new HashMap();
		for (String f : fieldList) {
			try {
				Object value = rs.getObject(f);
				map.put(f, value);
			} catch (Exception e) {
				//LogUtils.logException(e);
				LogManager.logException(e);
			}
		}
		return map;
	}

    /**
     * 填充参数
     * @author jiangdahui Aug 22, 2013 4:58:55 PM
     * @param pstmt
     * @param sql
     * @param params
     * @throws SQLException
     * @modificationHistory=========================逻辑或功能性重大变更记录
     * @modify by user: {修改人} Aug 22, 2013
     * @modify by reason:{原因}
     */
	private void fillParams(PreparedStatement pstmt, String sql, List params) throws SQLException {
		if (params != null && params.size() > 0) {
			String s = sql;
			int i = s.indexOf("?");
			int no = 1;
			while (i != -1) {
				pstmt.setObject(no, params.get(no - 1));
				if (i == s.length() - 1) {
					break;
				}
				s = s.substring(i + 1);
				i = s.indexOf("?");
				no++;
			}
		}
	}
}
