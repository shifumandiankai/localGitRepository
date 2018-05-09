package com.wadejerry.scms.task;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.device.service.ServerService;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.CalibratingTime;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.CalibratingTimeResponse;

/**
* @ClassName: TimeCalibratingTask
* @Description: 服务器及设备定时校时
* @author zhanying
* @date 2017年6月9日 下午5:15:07
*
 */
@DisallowConcurrentExecution
public class TimeCalibratingTask implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String serverName ="";
		ServerService serverService =AppContext.getBean("serverService");
		try {
			 //获取所有服务
			List<PfmServer> serverList =  serverService.selectAllServer();
			for(PfmServer server: serverList) {
				serverName = server.getServerName();
				PfmWebServiceStub stub = new PfmWebServiceStub("http://"+server.getIp()+
						":"+server.getPort()+"/PfmWebService/service");
				CalibratingTime calibratingTime = new  CalibratingTime();
				calibratingTime.setTime((new Date()).getTime());
				CalibratingTimeResponse  calibratingTimeResponse= stub.calibratingTime(calibratingTime);
				Integer iRet = calibratingTimeResponse.getCalibratingTimeResult().getIRet();	
				if(iRet != 0) {
					LogManager.logInfo("服务器["+serverName+ "]校时失败！");
				}
			}
		} catch (AxisFault e) {
			LogManager.logException(e);
		} catch (RemoteException e) {
			LogManager.logException(e);
		}
		
	}
}
