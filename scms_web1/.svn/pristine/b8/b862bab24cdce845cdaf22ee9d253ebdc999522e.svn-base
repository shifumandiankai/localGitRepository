package com.wadejerry.scms.frame.entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadejerry.scms.frame.Authority.AuthorityGetter;
@Component("exTemplate")
public  class ExTemplate {
	@Autowired
	private AuthorityGetter authorityGetter;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	public void Export(List<Map<String,Object>> data,String[] head,String[] column){
		
		String path = request.getSession().getServletContext().getRealPath("\\WEB-INF\\download");
		File file= new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		path+="\\";
		path+=new Date().getTime();
		path+=".xls";
		file= new File(path);
		if(file.exists()){
			file.delete();
		}
		
		Workbook  wb =null;
		OutputStream out = null;
		try{
			file.createNewFile();
			out = new FileOutputStream(file);
			wb =ExportTemplate.write(data, head,column);
			wb.write(out);

		}
		catch(Exception e){
			System.out.println(e);
			return ;

		}
		finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename=" + new String((new Date().getTime()+".xls").replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] bytes = new byte[1024];
			int length=0;
			while((length=input.read(bytes))!=-1){
				out.write(bytes, 0, length);
			}
			

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			if(input!=null)
				try {
					input.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			file.delete();
		}

	}
	
	/*
	 * type:0部门 1员工
	 */
public void DeptExport(List<Map<String,Object>> data,String[] head,String[] column,Integer type){
		
		String path = request.getSession().getServletContext().getRealPath("\\WEB-INF\\download");
		File file= new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		path+="\\";
		path+=new Date().getTime();
		path+=".xls";
		file= new File(path);
		if(file.exists()){
			file.delete();
		}
		
		Workbook  wb =null;
		OutputStream out = null;
		try{
			file.createNewFile();
			out = new FileOutputStream(file);
			if(type==0){
				wb =ExportTemplate.Deptwrite(data, head,column);
			}else{
				wb =ExportTemplate.Personwrite(data, head,column);
			}
			
			wb.write(out);

		}
		catch(Exception e){
			System.out.println(e);
			return ;

		}
		finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename=" + new String((new Date().getTime()+".xls").replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] bytes = new byte[1024];
			int length=0;
			while((length=input.read(bytes))!=-1){
				out.write(bytes, 0, length);
			}
			

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			if(input!=null)
				try {
					input.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			file.delete();
		}

	}
	
}
