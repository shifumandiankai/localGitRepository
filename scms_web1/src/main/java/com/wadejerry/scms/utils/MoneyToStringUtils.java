package com.wadejerry.scms.utils;

public class MoneyToStringUtils {
	//除去数据库中money类型带的￥和，符号
	public static String Remove(String str){
		String temp=" ";
		/*if(str!=null&&!"".equals(str)){
			for(int i=0;i<str.length();i++){
				if(i!=0&&str.charAt(i)!=39&&str.charAt(i)!=44){
					temp+=str.charAt(i);
				}
				
		}
		}*/
		if(str!=null&&!"".equals(str)){
			for(int i=0;i<str.length();i++){
				if(str.charAt(i)!=39&&str.charAt(i)!=44){
					temp+=str.charAt(i);
				}
				
		}
		}
		return temp;
		
	}
}
