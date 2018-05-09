package com.wadejerry.scms.pay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wadejerry.scms.frame.constant.WebServiceConst;
import com.wadejerry.scms.frame.web.SystemParams;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.device.service.ServerService;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;
import com.wadejerry.scms.modules.pfm.service.PfmParkingLotService;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.GetParkInfoByCarNum;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.GetParkInfoByCarNumResponse;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.ParkInfoResult;
import com.wadejerry.scms.webservice.client.SCMSServiceStub;
import com.wadejerry.scms.webservice.client.SCMSServiceStub.CommandDto;
import com.wadejerry.scms.webservice.client.SCMSServiceStub.EventResult;


/**
* @ClassName: PayThread
* @Description: 等待支付平台命令线程
* @author zhanying
* @date 2017年6月28日 上午10:33:50 等待支付查询接口
*
 */
public class PayThread extends Thread {
	
	public static boolean started  = true;
	@Autowired
	private PfmParkingLotService pfmParkingLotService; //车场Service
	@Autowired
	private ServerService serverService; //服务管理Service
	
	
	public void run(){
		SCMSServiceStub scmsServiceStub = null;
		List<PfmParkingLot> parkList = null;
		while(started) {
			try {
				if(SystemParams.scmsPayUrl.isEmpty()) {
					Thread.sleep(15000);
					continue;
				}
				parkList = pfmParkingLotService.selectAllByCompanyId(1);
				//是否有车场
				if(parkList.size() == 0) {
					Thread.sleep(15000);
					continue;
				}
				List<Integer> parkCodeList = new ArrayList<Integer>();	
				for(PfmParkingLot park:parkList) {
					if(park.getParkingLotCode() != null || park.getParkingLotCode() != "") {
						parkCodeList.add(Integer.parseInt(park.getParkingLotCode()));
					}
				}
				int[] parkCodeArr = new int[parkCodeList.size()];
				for (int i = 0; i < parkCodeList.size(); i++) {
					parkCodeArr[i] = parkCodeList.get(i);
				}
				scmsServiceStub = new SCMSServiceStub(SystemParams.scmsPayUrl);
				//等待命令参数
			    com.wadejerry.scms.webservice.client.SCMSServiceStub.WaitCommand waitCommand = 
						 new com.wadejerry.scms.webservice.client.SCMSServiceStub.WaitCommand();
			    waitCommand.setParkSerialNoList(parkCodeArr);
			    //等待支付平台命令
			    com.wadejerry.scms.webservice.client.SCMSServiceStub.WaitCommandResponse response = 
			    		scmsServiceStub.waitCommand(waitCommand);
			    //有命令
			    if(response.get_return().getIRet()) {
			    	CommandDto commandDto = response.get_return().getCommandDto();
			    	   com.wadejerry.scms.webservice.client.SCMSServiceStub.AppendEvent appendEvent =
		    			   new com.wadejerry.scms.webservice.client.SCMSServiceStub.AppendEvent();
		    	  	EventResult eventResult = new EventResult();
			    	switch (commandDto.getCommandId()) {
					case WebServiceConst.COMMAND_QUERY_ID:	//查询收费信息命令
						eventResult.setOrderId(commandDto.getOrderId());
						eventResult.setIRet(false);
						List<PfmServer> pfmServerList= serverService.selectAllServer();
						PfmServer pfmServer = null;		
						//根据序号获取命令对应的车场id 
						String parkCode= null;
						int  parkId=0;
						for(PfmParkingLot park:parkList) {
							parkCode = park.getParkingLotCode();
							if(parkCode != null && parkCode != "" && Integer.parseInt(parkCode) == commandDto.getParkSerialNo()) {
								parkId =  park.getPfmParkingLotId();
							}
						}
						for(PfmServer server : pfmServerList) {
							if(server.getParkId() == parkId) {
								pfmServer = server;
								break;
							}
						}
						if(pfmServer == null) {
							eventResult.setStrError("车场未关联服务");
						}
						else {
							ParkInfoResult parkInfoResult = null;
							//连接服务查询收费信息
							PfmWebServiceStub stub = new PfmWebServiceStub("http://"+pfmServer.getIp()+
									":"+pfmServer.getPort()+"/PfmWebService/service");
							GetParkInfoByCarNum getParkInfoByCarNum = new GetParkInfoByCarNum();
							getParkInfoByCarNum.setCarNumber(commandDto.getCarNumber());
							GetParkInfoByCarNumResponse responseq = stub.getParkInfoByCarNum(getParkInfoByCarNum);
							parkInfoResult =  responseq.getGetParkInfoByCarNumResult(); 
							//获取收费信息成功
							if(parkInfoResult.getIRet()== 0) {
								eventResult.setIRet(true);		
								com.wadejerry.scms.webservice.client.SCMSServiceStub.ParkInfoDto parkInfoDto =
										 new com.wadejerry.scms.webservice.client.SCMSServiceStub.ParkInfoDto();
								parkInfoDto.setCarNumber(parkInfoResult.getParkInfoDto().getCarNumber());
								parkInfoDto.setChargeFee(parkInfoResult.getParkInfoDto().getChargeFee());
								parkInfoDto.setInTime(parkInfoResult.getParkInfoDto().getInRecordDto().getInTime());
								parkInfoDto.setOutTime(parkInfoResult.getParkInfoDto().getEventTime());
								parkInfoDto.setNote(parkInfoResult.getParkInfoDto().getJianmianFee().toString());
								parkInfoDto.setParkTime(parkInfoResult.getParkInfoDto().getParkingduration());
								//返回事件信息
								eventResult.setParkInfoDto(parkInfoDto);
							}
							else {
								eventResult.setStrError(parkInfoResult.getStrMessage());
							}
						}
						appendEvent.setResultDto(eventResult);
						scmsServiceStub.appendEvent(appendEvent);
						break;
					default:
						break;
					}
			    }
			} catch (Exception e) {
				try {
					Thread.sleep(2000);  //发送失败,休眠2s
				} catch (InterruptedException e1) {
					LogManager.logException(e1);
					break;
				} 
				LogManager.logException(e);
			}
		}
	}

}
