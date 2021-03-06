package com.wadejerry.scms.frame.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.wadejerry.scms.utils.date.DateUtil;

public class DeptExportTemplate {
public static Workbook write(List<Map<String ,Object>> list,String titleRow[],String[] column)  {
		
		Workbook wb = null; 
		Sheet sheet =null;
		 wb = new HSSFWorkbook();
		 //创建sheet对象   
         sheet = (Sheet) wb.createSheet("sheet1"); 
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
//             cell.setCellStyle(style); // 样式，居中
             sheet.setColumnWidth(i, 20 * 256); 
         }  
         row.setHeight((short) 540); 
         //循环写入行数据   
         for (int i = 0; i < list.size(); i++) {  
             row = (Row) sheet.createRow(i+1);  
             row.setHeight((short) 500); 
             for(int j=0;j<column.length;j++){
            	 Iterator it=list.get(i).entrySet().iterator();
            	 while(it.hasNext()){
                  	Map.Entry<String,Object> entry=(Map.Entry) it.next();
                  	String key= entry.getKey();
                  	Object value=entry.getValue();
                  	if(column[j].equals(key)){
                  		/*if(column[j].equals("direction")){
                  			if(list.get(i).get(key)!=null){
                  				if(String.valueOf(value).equals("1")){
                  					row.createCell(j).setCellValue("进");
                  				}
                  				else{
                  					row.createCell(j).setCellValue("出");
                  				}
                  				
                  				
                  			}
                  			else{
                  				row.createCell(j).setCellValue("");
                  			}
                  		}else{*/
                  			if(value instanceof Date){
                  				//SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
                  				if(list.get(i).get(key)!=null){
                  					//String a=sdf.format(list.get(i).get(key));
                  					/*if(a.equals("2000")){
                  						row.createCell(j).setCellValue("");
                  					}else{*/
                  						row.createCell(j).setCellValue(DateUtil.df.format((Date)(list.get(i).get(key))));
                  					//}
                  				}else{
                  					row.createCell(j).setCellValue("");
                  				}
                  				
                          	}
                      		else{
                          		row.createCell(j).setCellValue(list.get(i).get(key)==null?"":list.get(i).get(key).toString());
                          	}
                  		//}
                  		break;
                  	}
                  	
                  } 
             }
           
         }  
         return wb;
		  
		
	}
}
