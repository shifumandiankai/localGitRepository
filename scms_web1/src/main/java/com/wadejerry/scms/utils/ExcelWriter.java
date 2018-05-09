package com.wadejerry.scms.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wadejerry.scms.utils.date.DateUtil;

public class ExcelWriter {
	
	public static Workbook write(List<Map<String ,Object>> list,String titleRow[])  {  
        Workbook wb = null; 

          Sheet sheet =null;
     
          
          wb = new HSSFWorkbook();

          
            //创建sheet对象   
            sheet = (Sheet) wb.createSheet("sheet1");  //这里uo

         //创建sheet对象   
        if (sheet==null) {
            sheet = (Sheet) wb.createSheet("sheet1");  
        }
        
        //添加表头  
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

   

        
        row = sheet.createRow(0);    //创建第一行    
        for(int i = 0;i < titleRow.length;i++){  
            cell = row.createCell(i);  
            cell.setCellValue(titleRow[i]);  
            sheet.setColumnWidth(i, 20 * 256); 
        }  
        row.setHeight((short) 540); 

        //循环写入行数据   
        for (int i = 0; i < list.size(); i++) {  
            row = (Row) sheet.createRow(i+1);  
            row.setHeight((short) 500); 
            row.createCell(0).setCellValue(list.get(i).get("car_number")==null?"":list.get(i).get("car_number").toString());
            row.createCell(1).setCellValue(list.get(i).get("person_name")==null?"":list.get(i).get("person_name").toString());
            row.createCell(2).setCellValue(list.get(i).get("sex")==null?"":list.get(i).get("sex").toString());
            row.createCell(3).setCellValue(list.get(i).get("phone")==null?"":list.get(i).get("phone").toString());
            row.createCell(4).setCellValue(list.get(i).get("type_name")==null?"":list.get(i).get("type_name").toString());
            row.createCell(5).setCellValue(list.get(i).get("card_id")==null?"":list.get(i).get("card_id").toString());
            row.createCell(6).setCellValue(list.get(i).get("status")==null?"":list.get(i).get("status").toString());
            row.createCell(7).setCellValue(list.get(i).get("cert_id")==null?"":list.get(i).get("cert_id").toString());
            row.createCell(8).setCellValue(list.get(i).get("enable_time")==null?"":DateUtil.YMDsdf.format((Date)(list.get(i).get("enable_time"))));
            row.createCell(9).setCellValue(list.get(i).get("disable_time")==null?"":DateUtil.YMDsdf.format((Date)(list.get(i).get("disable_time"))));
            row.createCell(10).setCellValue(list.get(i).get("note")==null?"":list.get(i).get("note").toString());
            row.createCell(11).setCellValue(list.get(i).get("addres")==null?"":list.get(i).get("addres").toString());
        }  
        return wb;
        
       
    }  
    
}  

