package com.wadejerry.scms.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wadejerry.scms.frame.web.SystemParams;
import com.wadejerry.scms.utils.Log.LogManager;

/**
* 
* @ClassName: DelPfmPhotoTask
* @Description: 定时删除车辆抓拍图片
* @author zhanying
* @date 2017年6月9日 下午1:41:29
*
 */
@DisallowConcurrentExecution
public class DelPfmPhotoTask implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			File file = new File(SystemParams.lprPath); //图片存储目录
			if(file.exists()) {
				File [] fileArr = file.listFiles(); //目录下所有文件
				Calendar c = Calendar.getInstance(); 
				c.add(Calendar.MONTH, -SystemParams.lprPhotoSaveTime); //当前时间减去保存时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
				int nowDate = Integer.parseInt(sdf.format(c.getTime())); 
				for(File tempFile : fileArr) {   	
					try {
						if(Integer.parseInt(tempFile.getName()) < nowDate){ //文件时间小于保存时间就删除
							tempFile.delete();
						}
					} catch (NumberFormatException e) { //防止文件夹名称不规范
						LogManager.logException(e);
					}
				}
			}
		} catch (Exception ex) {
			LogManager.logException(ex);
		}
		
	}

}
