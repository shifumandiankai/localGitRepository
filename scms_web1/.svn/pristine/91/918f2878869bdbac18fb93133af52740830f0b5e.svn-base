package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

public class ExcelReader {
		public static String EXTENTION_XLS="xls";//2003
		public static String EXTENTION_XLSX="xlsx";//2007

	    
	    /**
	     * 
	     * @param is
	     * @param fileName
	     * @return
	     * @throws IOException
	     * 
	     * function : 获取workbook
	     */
	   public static Workbook getWorkBook(InputStream is,String filePath) throws IOException{
		   Workbook wb=null;
		   if(filePath.endsWith(EXTENTION_XLS)){
			   wb=new HSSFWorkbook(is);
		   }
		   else if(filePath.endsWith(EXTENTION_XLSX)){
			   wb = new XSSFWorkbook(is);
			   
		   }
		   return wb;
		   
	   } 
	   
	   /**
	     * 
	     * @param is
	     * @param fileName
	     * @return
	     * @throws Exception 
	     * @throws IOException
	     * 
	     * function : 校验excel文件
	     */
	   public static void preCheck(String filePath) throws FileNotFoundException,FileFormatException{
		   
		   File file = new File(filePath);
		   if(!file.exists()){
			   
			   throw new  FileNotFoundException("不存在此文件！");
		   }
		   if(!(filePath.endsWith(EXTENTION_XLS)||filePath.endsWith(EXTENTION_XLSX))){
			   
			   throw new FileFormatException("文件格式错误！");
		   }
		   
	   }
	   
	   
	   /**
	     * 
	     * @param is
	     * @param fileName
	     * @return
	     * @throws Exception 
	     * @throws IOException
	     * 
	     * 
	     * function :读取excel
	     */
	   
	   public static List<Map<String,String>> ReadExcel(String filePath)  throws IOException{
		  
		   preCheck(filePath);
		   InputStream is = null;
		   Workbook wb = null;
		   List<Map<String,String>> list = new ArrayList<>();
		  try{
			  is = new FileInputStream(filePath);
			  wb = getWorkBook(is, filePath);
			  Sheet sheet = wb.getSheetAt(0);
			  Row headerRow = sheet.getRow(0);
			  int columnCount = headerRow.getLastCellNum();
			 
			  for(int index = 1;index<sheet.getPhysicalNumberOfRows();index++){
				  Row row = sheet.getRow(index);
				  if(row==null){
					  sheet.createRow(index);
					  row=sheet.getRow(index);
				  }
				  
				  Map<String,String> map = new HashMap<>();
				  for(int tcell=0;tcell<columnCount;tcell++ ){
					  Cell head = headerRow.getCell(tcell,Row.CREATE_NULL_AS_BLANK);
					  Cell val =  row.getCell(tcell,Row.CREATE_NULL_AS_BLANK);
					  head.setCellType(Cell.CELL_TYPE_STRING);
					 val.setCellType(Cell.CELL_TYPE_STRING);
					  map.put(head.getStringCellValue(), val.getStringCellValue());
				  }
				  
				  list.add(map);
			  }
			  
		  }catch(IOException e){
			  
			  throw new IOException();
			  
		  }
		  finally{
			  if(is!=null){
				  try{is.close();
				  }
				  catch(Exception e){
					  e.printStackTrace();
				  }
			  }
			  
		  }
		  return list;
	   }
}
