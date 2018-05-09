package com.wadejerry.scms.modules.license.controller;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wadejerry.scms.modules.license.service.LiscenseService;
import com.wadejerry.scms.utils.AjaxUtil;

@Controller
public class LicenseController {

	@Autowired
	private LiscenseService liscenseService;
	@RequestMapping(value="/toLiscenseImport",method=RequestMethod.GET)
	public ModelAndView toImportliscense(HttpServletRequest request,HttpServletResponse response){
		
		String tipinfo = liscenseService.currentLiscenseInfo();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/basic/liscense/LiscenseImport");
		mv.addObject("tipinfo", tipinfo);
		return mv;
	}
	@RequestMapping(value="/LiscenseImport",method=RequestMethod.POST)
	@ResponseBody
	public void importliscense(HttpServletRequest request,HttpServletResponse response){
		
		MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) (ServletRequest)request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("liscense");
		// 得到上传服务器的路径
		if(mFile!=null){//假设文件格式正确
		try {
			InputStream fileStream = mFile.getInputStream();
			InputStreamReader reader = new InputStreamReader(fileStream);
			StringBuilder sb = new StringBuilder();
			char[] temp = new char[1024];
			int len=0;
			while((len=reader.read(temp,0,temp.length))!=-1){
				sb.append(temp, 0, len);
			}
			liscenseService.importLiscense(sb.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AjaxUtil.ajaxWrite(false,"导入失败", response);
		}
		AjaxUtil.ajaxWrite(true,"导入成功", response);
		}
		else{
			AjaxUtil.ajaxWrite(false, "服务器没有找到要导入的文件",response);
		}
		
	}
	
	
	@RequestMapping(value="/license/licenseinfo",method=RequestMethod.GET)
	@ResponseBody
	public void getLicenseInfo(HttpServletRequest request,HttpServletResponse response){
		
		try{
			
			AjaxUtil.ajaxWriteObject(true, liscenseService.getLicenseInfo(), response);
			
		}
		catch(Exception e){
			AjaxUtil.ajaxWriteObject(false, null, response);
		}
	}
}
