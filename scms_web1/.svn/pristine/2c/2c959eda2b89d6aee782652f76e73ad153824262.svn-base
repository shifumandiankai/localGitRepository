package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.wadejerry.scms.utils.date.DateUtil;

public class ExcelWriter {
	
	public static Workbook write(List<Map<String ,Object>> list,String titleRow[])  {  
		
        Workbook wb = null; 

        Sheet sheet =null;

       //创建工作文档对象   
        wb = new HSSFWorkbook();
       //创建sheet对象   
        sheet = (Sheet) wb.createSheet("车辆信息导出表");  
        //定义表头cell样式
        HSSFCellStyle setBorder = (HSSFCellStyle) wb.createCellStyle();
        setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
        setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        setBorder.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 设置背景色    
        setBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        //定义表头字体
        HSSFFont font2 = (HSSFFont) wb.createFont();    
        font2.setFontName("仿宋_GB2312");    
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示   
        font2.setFontHeightInPoints((short) 12);  
        setBorder.setFont(font2);
        
        //定义内容cell样式  单行
        HSSFCellStyle setBordercontent1 = (HSSFCellStyle) wb.createCellStyle();
        setBordercontent1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        setBordercontent1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        setBordercontent1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        setBordercontent1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
        setBordercontent1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
       /* setBordercontent1.setFillForegroundColor((short) 13);// 设置背景色    
*/        setBordercontent1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        //定义内容cell样式  双杭行
        HSSFCellStyle setBordercontent2 = (HSSFCellStyle) wb.createCellStyle();
        setBordercontent2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        setBordercontent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        setBordercontent2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        setBordercontent2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
        setBordercontent2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        setBordercontent2.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 设置背景色    
        setBordercontent2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        //定义内容字体
        HSSFFont font3 = (HSSFFont) wb.createFont();    
        font3.setFontName("仿宋_GB2312");    
     /*   font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示   
*/        font3.setFontHeightInPoints((short) 12);  
		setBordercontent2.setFont(font3);//双行格式
		setBordercontent1.setFont(font3);//单行格式
       //添加表头  
        Row row = sheet.createRow(0);
        Cell cell = null;
        row = sheet.createRow(0);    //创建第一行    
        for(int i = 0;i < titleRow.length;i++){  
            cell = row.createCell(i);  
            cell.setCellValue(titleRow[i]);  
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(setBorder);
            sheet.setColumnWidth(i, 20 * 256); 
            
        }  
        row.setHeight((short)-1); 

        int yu ;
        //循环写入行数据   
        for (int i = 0; i < list.size(); i++) {  
        	yu = i%2;
            row = (Row) sheet.createRow(i+1);  
            row.setHeight((short)-1); 
            cell = row.createCell(0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("car_number")==null?"":list.get(i).get("car_number").toString());
            
            cell =row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("person_name")==null?"":list.get(i).get("person_name").toString());
            cell = row.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("sex")==null?"":list.get(i).get("sex").toString());
            cell = row.createCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("phone")==null?"":list.get(i).get("phone").toString());
            cell = row.createCell(4);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("type_name")==null?"":list.get(i).get("type_name").toString());
            cell = row.createCell(5);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("card_id")==null?"":list.get(i).get("card_id").toString());
            cell =  row.createCell(6);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("status")==null?"":list.get(i).get("status").toString());
        
            cell =  row.createCell(7);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("cert_id")==null?"":list.get(i).get("cert_id").toString());
            cell =  row.createCell(8);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("enable_time")==null?"":DateUtil.YMDsdf.format((Date)(list.get(i).get("enable_time"))));
            cell =  row.createCell(9);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("disable_time")==null?"":DateUtil.YMDsdf.format((Date)(list.get(i).get("disable_time"))));
            cell =   row.createCell(10);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("note")==null?"":list.get(i).get("note").toString());
            cell =  row.createCell(11);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(yu==0?setBordercontent1:setBordercontent2);
            cell.setCellValue(list.get(i).get("addres")==null?"":list.get(i).get("addres").toString());
        }  
        return wb;
        

    }  
    
}  

