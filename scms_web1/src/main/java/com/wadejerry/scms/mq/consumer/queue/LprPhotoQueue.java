package com.wadejerry.scms.mq.consumer.queue;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.wadejerry.scms.frame.web.SystemParams;
import com.wadejerry.scms.mq.dto.LprDto;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.file.FileUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

/**
 * @ClassName: LprPhotoQueue
 * @Description:  转存车辆抓拍图片
 * @author zhanying
 * @date 2017年3月30日 下午10:25:23
 *
 */
@Component
public class LprPhotoQueue implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String jsonStr = ((TextMessage) message).getText();
			LprDto lprDto =  JacksonUtil.toObject(jsonStr, LprDto.class);
			String strDate = lprDto.getPhotoName().substring(0,8);	//每天的图片存储一个文件夹
			File dir = new File(SystemParams.lprPath+"/"+strDate);	//如果文件夹不存在先创建
			if(!dir.exists()){
				dir.mkdirs();
			}			
			FileUtil.deleteFile(dir.getPath(),lprDto.photoName);	//如果存在就删除
			FileUtil.writeFile(dir.getPath()+"/"+lprDto.photoName, lprDto.photo);	//保存图片
		} catch (JMSException e) {
			LogManager.logException(e);
		} catch (Exception e) {
			LogManager.logException(e);
		}
	}
}
