package com.wadejerry.scms.utils.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import com.wadejerry.scms.utils.Log.LogManager;


/**
* @ClassName: FileUtil
* @Description: 文件的一些操作 
* @author zhanying
* @date 2016年9月6日 下午5:18:17
*
 */
public class FileUtil {
	
	/**
	* @Title: copyFile 
	* @Description: 拷贝文件
	* @param @param src
	* @param @param dest
	* @param @throws FileNotFoundException
	* @param @throws IOException    
	* @return void   
	* @throws
	 */
	public static void copyFile(String src, String dest) throws FileNotFoundException, IOException{
		if(StringUtils.hasLength(src) && StringUtils.hasLength(dest)){
			FileCopyUtils.copy(new File(src), new File(dest));
		}
	}

	/**
	* @Title: copyFile 
	* @Description: 拷贝文件
	* @param @param src
	* @param @param dest
	* @param @throws IOException    
	* @return void   
	* @throws
	 */
	public static void copyFile(File src, File dest) throws IOException{
		FileCopyUtils.copy(src, dest);
	}
	
	/**
	* @Title: renameFile 
	* @Description: 文件重命名
	* @param @param path
	* @param @param oldName
	* @param @param newName    
	* @return void   
	* @throws
	 */
	public static void renameFile(String path, String oldName, String newName){
		if(StringUtils.hasLength(path) && StringUtils.hasLength(oldName) && StringUtils.hasLength(newName)){
			renameFile(new File(path+oldName), new File(path+newName));
		}
	}
	
	/**
	* @Title: renameFile 
	* @Description:  文件重命名
	* @param @param oldFile
	* @param @param newFile    
	* @return void   
	* @throws
	 */
	public static void renameFile(File oldFile, File newFile){
		if(oldFile!=null && newFile!=null){
			if(newFile.exists()){
				LogManager.logInfo(newFile.getName()+"已经存在！");
			}else{
                oldFile.renameTo(newFile);
			}
		}
	}
	
	/**
	* @Title: deleteFile 
	* @Description: 删除文件
	* @param @param path
	* @param @param fileName    
	* @return void   
	* @throws
	 */
	public static void deleteFile(String path, String fileName){
		deleteFile(new File(path+fileName));
	}
	
	/**
	* @Title: deleteFile 
	* @Description: 删除文件
	* @param @param file    
	* @return void   
	* @throws
	 */
	public static void deleteFile(File file){
		if(file != null){
			if(file.exists() && file.isFile()){
				file.delete();
			}
		}
	}
	
	/***
	* 将字节流转成文件
	* @Title: writeFile 
	* @Description: TODO
	* @param @param path
	* @param @param bytes    
	* @return void   
	* @throws
	 */
	public static void writeFile(String path,byte[] bytes){
		int n = 1024;
		  FileOutputStream os = null;
		  try {
			// 创建文件输出流对象
			File file = new File(path);
			os= new FileOutputStream(file);
			  // 写入输出流
			int length = bytes.length;
			int start = 0;
			while(length>start+n){
				os.write(bytes, start, n);
				start= start+n;
			}
			if(length != start+n){
				n = length-start;
				os.write(bytes, start, n);
			}
		} catch (IOException e) {
			LogManager.logException(e);
		}finally{
			  // 关闭输出流
			  try {
				if(os !=null){
					os.close();
				}
			} catch (IOException e) {
				LogManager.logException(e);
			}
		}
	}
	
}

