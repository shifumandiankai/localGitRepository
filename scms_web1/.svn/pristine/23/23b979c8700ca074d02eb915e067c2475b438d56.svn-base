package com.wadejerry.scms.modules.sysconfig.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.constant.user.ConstParamSysConfig;
import com.wadejerry.scms.modules.sysconfig.dao.BimSysParamsMapper;
import com.wadejerry.scms.modules.sysconfig.model.BimSysParams;
import com.wadejerry.scms.modules.sysconfig.service.BimSysParamsService;
import com.wadejerry.scms.task.TimeCalibratingTask;
import com.wadejerry.scms.utils.quartz.QuartzTaskJob;

@Service("bimSysParamsService")
public class BimSysParamsServiceImpl implements BimSysParamsService {
	@Autowired
	private BimSysParamsMapper bimSysParamsMapper;

	@Override
	public void UpdateParams(Integer key, Object value) {
		BimSysParams param = new BimSysParams(); 
		param.setBimSysParamsId(key);
		if(value.getClass() == Integer.class){
			param.setIntValue(Integer.parseInt(value.toString()));
		} else {
			param.setStrValue(value.toString());
		}
		bimSysParamsMapper.updateByPrimaryKeySelective(param);
	}

	@Override
	public BimSysParams GetParamByKey(Integer key) {
		return bimSysParamsMapper.selectByPrimaryKey(key);
	}

	@Override
	public QuartzTaskJob getQuartzTimeCalibratingTaskJob() {
		QuartzTaskJob quartzTaskJob = null;
		String time  = bimSysParamsMapper.selectByPrimaryKey(ConstParamSysConfig.LOG_AUTO_SET_TIME_STYLE_ID).getStrValue(); //定时校时时间
		int weekId =  bimSysParamsMapper.selectByPrimaryKey(ConstParamSysConfig.LOG_AUTO_SET_TIME_STYLE_WEEK_ID).getIntValue(); //定时校时日期
		if(weekId == 0 ) {	//每天执行
			quartzTaskJob = new QuartzTaskJob("TimeCalibratingTask", TimeCalibratingTask.class,
					String.format( "%s %s %s 1/1 * ? ",time.substring(6,8),time.substring(3,5), time.substring(0,2))); //每一天秒分小时
		} else { //每天特定一天执行
			quartzTaskJob = new QuartzTaskJob("TimeCalibratingTask", TimeCalibratingTask.class,
					String.format( "%s %s %s ? * %s",time.substring(6,8),time.substring(3,5), time.substring(0,2),weekId)); //每周一天秒分小时
		}
		return quartzTaskJob;
	}


}
