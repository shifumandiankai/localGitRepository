package com.wadejerry.scms.utils;

public class StringUtil {

	/**
	*  对象名称序列化成 列名 
	* @author zhanying 2016年9月22日 下午4:10:55
	* @param  @param objName
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public static String objNameToTableColName(String objName) {
		if(objName == null){
			return "" ;
		}
		StringBuffer strbf  = new StringBuffer();
		String str = "";
		for (int i = 0; i < objName.length(); i++) {
			str = objName.substring(i,i+1);
			if(str != str.toLowerCase()){
				strbf.append("_");
			}
			strbf.append(str);
		}
		return strbf.toString();
	}
	public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static String reverse(final String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
}
