package com.wadejerry.scms.utils.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.wadejerry.scms.utils.Log.LogManager;

public class CommonFun {
	
	/**
	 * 
	 * @author jiangdahui Aug 24, 2013 3:33:50 PM
	 * @param cmd
	 * @return
	 * @throws Exception
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static CmdRunResult callCmd(String... cmd) throws Exception {
		CmdRunResult cmdRunResult = new CmdRunResult();
		StringBuilder buffer = new StringBuilder();
		for (String c : cmd) {
			buffer.append(c).append("\r\n");
		}
		String scriptDir = getTempPath();
		makeDir(scriptDir);
		String scriptFile = scriptDir + "script_" + new Date().getTime() + "_" + new Random().nextInt(1000);
		String content;
		content = buffer.toString();
		scriptFile += ".bat";
		//LogUtils.logDebug(scriptFile);
		LogManager.logDebug(scriptFile);
		
		writeFile(scriptFile, content);
		File file = new File(scriptFile);
		if (!file.exists()) {
			throw new Exception("生成脚本文件失败！");
		}
		Process proc = null;
	
		StreamGobbler errorGobbler = null;
		StreamGobbler outGobbler = null;
		try {
			proc = Runtime.getRuntime().exec("\""+scriptFile+"\"");
			
			errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
			errorGobbler.start();
			
			outGobbler = new StreamGobbler(proc.getInputStream(), "STDOUT");
			outGobbler.start();
			
			cmdRunResult.setExitValue(proc.waitFor());
			
		} catch (IOException e) {
			//LogUtils.logException(e);
			LogManager.logException(e);
		} finally {
			
			if (proc != null) {
				proc.destroy();
			}
			boolean b = file.delete();
			if (!b) {
				LogManager.logDebug("删除临时脚本文件失败：" + file);
			}
		}
		if (cmdRunResult.getExitValue() == 0) {
			cmdRunResult.setOutput(outGobbler.getOutput());
		} else {
			cmdRunResult.setOutput(errorGobbler.getOutput());
		}
		return cmdRunResult;
	}
	
	public static void writeFile(String fileName, String content) {
		writeFile(fileName, content, "UTF-8");
	}
	
	/**
	 * 根据文件路径和内容创建文件
	 * @author jiangdahui Aug 24, 2013 3:32:42 PM
	 * @param fileName
	 * @param content
	 * @param encoding
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static void writeFile(String fileName, String content, String encoding) {
		File file = new File(fileName);
		if (file.exists()) {
			boolean result = file.delete();
			if (!result) {
				//LogUtils.logDebug("写文件前删除文件<" + fileName + ">失败!");
				LogManager.logDebug("写文件前删除文件<" + fileName + ">失败!");
			}
		}
		if (!file.getParentFile().exists()) {
			makeDir(file.getParent());
		}
		PrintWriter fw = null;
		try {
			fw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encoding)));
			fw.write(content, 0, content.length());
			fw.flush();
		} catch (IOException e) {
			//LogUtils.logException(e);
			LogManager.logException(e);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
	
	/**
	 * 创建目录
	 * @author jiangdahui Aug 24, 2013 3:32:47 PM
	 * @param dir
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static void makeDir(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			makeDir(file.getParent());
			if (!file.mkdir()) {
				throw new IllegalArgumentException("参数有误，无法创建目录:" + file);
			}
		}
	}
	
	/**
	 * 获取附件目录路径
	 * @author jiangdahui Aug 24, 2013 3:32:53 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static String getAttachmentPath() {
		return getWebRootPath() + "/upload";
	}
	
	public static String getTempPath() {
		return getFilePath(getAttachmentPath(), "Temp");
	}
	
	public static String getFilePath(String... items) {
		StringBuilder s = new StringBuilder();
		for (String item : items) {
			s.append(item).append("/");
		}
		return s.toString();
	}
	
	public static String format(Object o, String... format) {
		if (o == null) {
			return "";
		}
		String f = null;
		if (o instanceof Date || o instanceof Calendar) {
			if (format.length > 0) {
				f = format[0];
			} else {
				f = "yyyy-MM-dd HH:mm:ss";
			}
			DateFormat dateFormat = new SimpleDateFormat(f);
			if (o instanceof Date) {
				return dateFormat.format((Date)o);
			} else
				if (o instanceof Calendar) {
					return dateFormat.format(((Calendar)o).getTime());
				}
		}
		return "";
	}
	
	public static String readFile(String path) {
		return readFile(path, "UTF-8");
	}
	
	/**
	 * 读文件内容
	 * @author jiangdahui Aug 24, 2013 3:33:01 PM
	 * @param path
	 * @param encoding
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static String readFile(String path, String encoding) {
		File file = new File(path);
		return readFile(file, encoding);
	}
	
	/**
	 * 读文件内容
	 * @author jiangdahui Aug 24, 2013 3:33:06 PM
	 * @param file
	 * @param encoding
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static String readFile(File file, String encoding) {
		if (file.exists()) {
			Scanner in = null;
			try {
				in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding)));
				StringBuffer s = new StringBuffer();
				while (in.hasNextLine()) {
					if (!s.toString().equals("")) {
						s.append("\n");
					}
					s.append(in.nextLine());
				}
				return s.toString();
			} catch (FileNotFoundException e) {
				//LogUtils.logException(e);
				LogManager.logException(e);
			} catch (UnsupportedEncodingException e) {
				//LogUtils.logException(e);
				LogManager.logException(e);
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取WebRoot目录路径
	 * @author jiangdahui Aug 24, 2013 3:33:12 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static String getWebRootPath() {
		String path = null;
		try {
			path = new File(URLDecoder.decode(CommonFun.class.getResource("/").getPath(), "utf-8")).getParentFile().getParent();
			path = path.replaceAll("\\\\", "/");
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				throw new Exception("目录不存在：" + dirFile);
			}
		} catch (Exception e) {
			path = "";
			//LogUtils.logException(e);
			LogManager.logException(e);
		}
		return path;
	}
	
	/**
	 * 获取当前class目录
	 * @author jiangdahui Aug 24, 2013 3:33:20 PM
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static String getCurrentPath() {
		String path = null;
		try {
			path = new File(URLDecoder.decode(CommonFun.class.getResource("/").getPath(), "utf-8")).getPath();
			path = path.replaceAll("\\\\", "/");
			//测试
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				throw new Exception("目录不存在：" + dirFile);
			}
		} catch (Exception e) {
			path = "";
			LogManager.logException(e);
		}
		return path;
	}
	
	/**
	 * 字符串转时间
	 * @author jiangdahui Aug 24, 2013 3:33:26 PM
	 * @param date
	 * @param format
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static Calendar toCalendar(String date, String... format) {
		Date d = toDate(date, format);
		if (d != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			return calendar;
		}
		return null;
	}
	
	/**
	 * 字符串转时间
	 * @author jiangdahui Aug 24, 2013 3:33:31 PM
	 * @param date
	 * @param format
	 * @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user: {修改人} Aug 24, 2013
	 * @modify by reason:{原因}
	 */
	public static Date toDate(String date, String... format) {
		if (date == null || "".equals(date)) {
			return null;
		}
		String f = null;
		if (format.length > 0) {
			f = format[0];
		} else {
			f = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat dateFormat = new SimpleDateFormat(f);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			LogManager.logException(e);
		}
		return null;
	}
	
	/**
	 * 删除文件
	 * @author jinbenbin 2013-9-11 下午04:22:00
	 */
	public static boolean deleteFile(File file) {
		boolean isdelete = false;
		if (file.exists()) {// 判断目录或文件是否存在
			if (file.isFile()) { // 判断是否为文件，为文件时调用删除文件方法
				if (file.delete()) {
					isdelete = true;
				}
			} else { // 为目录时调用删除目录方法
				for (File child : file.listFiles()) {
					deleteFile(child);
				}
				if (file.listFiles().length == 0) {
					if (file.delete()) {
						isdelete = true;
					}
				}
			}
		}
		return isdelete;
	}
	
	/**
	 * 取得文件大小,kb
	 * @author jinbenbin 2013-9-14 下午02:46:35
	 */
	public static long getFileSize(File f) throws Exception {
		long s = 0;
		if (f.isDirectory()) {
			for (File file : f.listFiles()) {
				s += getFileSize(file);
			}
		} else {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (IOException e) {
				LogManager.logException(e);
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
		}
		return s;
	}

}
