package com.wadejerry.scms.webservice.server;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadejerry.scms.modules.basic.dao.BimCardAppMapper;
import com.wadejerry.scms.modules.device.dao.PfmServerMapper;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.pfm.dao.PfmChargeRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmRecordMapper;
import com.wadejerry.scms.modules.pfm.dao.WebServiceMapper;
import com.wadejerry.scms.mq.dto.ChargeRecordDto;
import com.wadejerry.scms.mq.dto.InRecordDto;
import com.wadejerry.scms.mq.dto.OutRecordDto;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.webservice.server.support.CommonPageRequest;
import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.AccountResult;
import com.wadejerry.scms.webservice.server.support.pfm.BoothResult;
import com.wadejerry.scms.webservice.server.support.pfm.CarResult;
import com.wadejerry.scms.webservice.server.support.pfm.CarTypeRelResult;
import com.wadejerry.scms.webservice.server.support.pfm.CarTypeResult;
import com.wadejerry.scms.webservice.server.support.pfm.CarriagewayDeviceRelResult;
import com.wadejerry.scms.webservice.server.support.pfm.CarriagewayResult;
import com.wadejerry.scms.webservice.server.support.pfm.ChargeRuleResult;
import com.wadejerry.scms.webservice.server.support.pfm.DeviceScreenResult;
import com.wadejerry.scms.webservice.server.support.pfm.EntranceResult;
import com.wadejerry.scms.webservice.server.support.pfm.LPRResult;
import com.wadejerry.scms.webservice.server.support.pfm.ParkResult;
import com.wadejerry.scms.webservice.server.support.pfm.PfmTimeResult;
import com.wadejerry.scms.webservice.server.support.pfm.SpecialCarNumResult;
import com.wadejerry.scms.webservice.server.support.pfm.SubsidiaryCarNumResult;
import com.wadejerry.scms.webservice.server.support.pfm.TaskMessageResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.AccountDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.AnshiDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.BaoqiDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.BoothDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarInfoDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarTypeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarTypeRelDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDeviceRelDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRuleDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.DaynightDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.DeviceScreenDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.EntranceDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.JianmianReleaseDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.LPRDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ParkDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PayOrderDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodTimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PertimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PfmTimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.SpecialCarNumDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.SubsidiaryCarNumDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TaskMessageDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeTimeDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodTimeDto;
import com.wadejerry.scms.webservice.server.utils.TransPfmService;

/**
 * 
 * @ClassName: PfmService
 * @Description:停车场WebService类
 * @author zhanying
 * @date 2017年3月1日 下午2:22:03
 *
 */
@Component("pfmService")
public class PfmService {

	@Autowired
	private WebServiceMapper pfmbm;  //获取记录mapper
	@Autowired
	private PfmServerMapper serverMapper; //停车场服务mapper
	@Autowired
	private TransPfmService transPfmService;


	/**
	 * 获取id获取当前及下级车场信息s
	 * @author zhanying 2017年3月1日 下午2:40:06
	 * @param @param serverId
	 * @param @param parkId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public ParkResult getParkList(int serverId, int parkId) {
		ParkResult parkResult = new ParkResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			parkResult.setParkDtoArr(pfmbm.selectAllParkDto(server.getBimCompanyId(), parkId).toArray(new ParkDto[0]));
			parkResult.setiRet(true);
		} else {
			parkResult.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return parkResult;
	}

	/**
	 * 获取车场包含的出入口信息
	 * @author zhanying 2017年3月1日 下午2:45:47
	 * @param @param serverId
	 * @param @param parkIdArr
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public EntranceResult getEntranceList(int serverId, int[] parkIdArr) {
		EntranceResult result = new EntranceResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			result.setEntranceDtoArr(pfmbm.selectAllEntranceDto(server.getBimCompanyId(), parkIdArr)
					.toArray(new EntranceDto[0]));
			result.setiRet(true);
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}

	/**
	 * 获取出入口包含的车道信息
	 * @author zhanying 2017年3月1日 下午2:45:57
	 * @param @param serverId
	 * @param @param entranceIdArr
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CarriagewayResult getCarriageway(int serverId, int[] entranceIdArr) {
		CarriagewayResult result = new CarriagewayResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && entranceIdArr.length != 0) {
			result.setCarriagewayDtoArr(pfmbm.selectAllCarriagewayDto(server.getBimCompanyId(), entranceIdArr)
					.toArray(new CarriagewayDto[0]));
			result.setiRet(true);
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}

	/**
	 * 获取车道与设备的关联信息
	 * @author zhanying 2017年3月1日 下午3:01:28
	 * @param @param serverId
	 * @param @param carriagewayIdArr
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CarriagewayDeviceRelResult getCarriagewayDeviceRelList(int serverId, int[] carriagewayIdArr) {
		CarriagewayDeviceRelResult result = new CarriagewayDeviceRelResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && carriagewayIdArr.length != 0) {
			result.setCarriagewayDeviceRelDtoArr(pfmbm.selectAllCarriagewayDeviceRelDto(carriagewayIdArr)
					.toArray(new CarriagewayDeviceRelDto[0]));
			result.setiRet(true);
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}

	/***
	 * 获取岗亭信息
	 * @author zhanying 2017年2月8日 下午1:52:30
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public BoothResult getBoothList(int serverId, int[] entranceIdArr) {
		BoothResult r = new BoothResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && entranceIdArr.length != 0) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setiRet(true);
			r.setBoothDtoArr(pfmbm.selectAllBoothDto(bimcompanyId, entranceIdArr).toArray(new BoothDto[0]));
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	}

	/**
	 * 获取服务管理的设备信息
	 * @author zhanying 2017年2月8日 下午2:00:36
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public LPRResult getDeviceByServerId(int serverId) {
		LPRResult r = new LPRResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setLprDtoArr((pfmbm.selectAllLPRDto(bimcompanyId).toArray(new LPRDto[0])));
			r.setiRet(true);
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	}

	/**
	 * 获取车辆信息，更具更新时间
	 * @author zhanying 2017年2月8日 下午2:09:34
	 * @param @param serverId
	 * @param @param updateTime
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CarResult getCarInfo(int serverId, long updateTime, CommonPageRequest page, int[] parkIdArr) {
		CarResult r = new CarResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setiRet(true);
			r.setPageSize(page.getPageItems());
			r.setCurrentPage(page.getReqPage());
			int allCount = pfmbm.selectAllCarInfoDtoCount(bimcompanyId, new java.sql.Timestamp(updateTime),
					page, parkIdArr);
			r.setAllRow(allCount);
			r.setTotalPage(allCount % page.getPageItems() == 0 ? allCount / page.getPageItems()
					: allCount / page.getPageItems() + 1);
			if (allCount > 0) {
				r.setCarInfoDtoArr((pfmbm
						.selectAllCarInfoDto(bimcompanyId, new java.sql.Timestamp(updateTime), page, parkIdArr)
						.toArray(new CarInfoDto[0])));
				Set<Integer> carIds = new HashSet<>();
				for (CarInfoDto i : r.getCarInfoDtoArr()) {
					carIds.add(i.getCarId());
				}
				r.setSubsidiaryCarNumDtoArr(
						pfmbm.selectSubsiByCarid(carIds).toArray(new SubsidiaryCarNumDto[0]));
			} else {
				r.setCarInfoDtoArr(new CarInfoDto[0]);
				r.setSubsidiaryCarNumDtoArr(new SubsidiaryCarNumDto[0]);
			}

		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;

	}

	/**
	 * 获取车辆类型
	 * @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CarTypeResult getCarType(int serverId, int[] parkIdArr) {
		CarTypeResult r = new CarTypeResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setCarTypeDtoArr((pfmbm.selectAllCarTypeDto(bimcompanyId, parkIdArr).toArray((new CarTypeDto[0]))));
			r.setiRet(true);
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	}

	/**
	 * 获取收费规则
	 * @author zhanying 2017年2月8日 下午2:15:02
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public ChargeRuleResult getChargeRule(int serverId, int[] parkIdArr) {
		ChargeRuleResult r = new ChargeRuleResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setChargeRuleDtoArr(
					(pfmbm.selectAllChargeRuleDto(bimcompanyId, parkIdArr).toArray(new ChargeRuleDto[0])));
			r.setAnshiDtoArr(pfmbm.selectAllAnshiDto(bimcompanyId, parkIdArr).toArray(new AnshiDto[0]));
			r.setBaoqiDtoArr(pfmbm.selectAllBaoqiDto(bimcompanyId, parkIdArr).toArray(new BaoqiDto[0]));
			r.setDaynightDtoArr(pfmbm.selectAllDaynightDto(bimcompanyId, parkIdArr).toArray(new DaynightDto[0]));
			r.setJianmianReleaseDtoArr(
					pfmbm.selectAllJianmianReleaseDto(bimcompanyId, parkIdArr).toArray(new JianmianReleaseDto[0]));
			r.setPeriodDtoArr(pfmbm.selectAllPeriodDto(bimcompanyId, parkIdArr).toArray(new PeriodDto[0]));
			r.setPeriodTimeArr((pfmbm.selectAllPeriodTimeDto(bimcompanyId, parkIdArr).toArray(new PeriodTimeDto[0])));
			r.setTimeChargeDtoArr(pfmbm.selectAllTimeChargeDto(bimcompanyId, parkIdArr).toArray(new TimeChargeDto[0]));
			r.setTimeChargeTimeDtoArr(
					pfmbm.selectAllTimeChargeTimeDto(bimcompanyId, parkIdArr).toArray(new TimeChargeTimeDto[0]));
			r.setUnitPeriodDtoArr(pfmbm.selectAllUnitPeriodDto(bimcompanyId, parkIdArr).toArray(new UnitPeriodDto[0]));
			r.setPertimeDtoArr(pfmbm.selectAllPertimeDto(bimcompanyId, parkIdArr).toArray(new PertimeDto[0]));
			r.setUnitPeriodTimeDtoArr(
					pfmbm.selectAllUnitPeriodTimeDto(bimcompanyId, parkIdArr).toArray(new UnitPeriodTimeDto[0]));
			r.setiRet(true);
		}
		else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	}

	/***
	 * 获取车辆与类型关联表
	 * 
	 * @author zhanying 2017年2月8日 下午2:18:44
	 * @param @param
	 *            serverId
	 * @param @param
	 *            updatTime
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CarTypeRelResult getCarTypeRel(int serverId, long updatTime, CommonPageRequest page, int[] parkIdArr) {
		CarTypeRelResult r = new CarTypeRelResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			Integer bimcompanyId = server.getBimCompanyId();
			int allCount = pfmbm.selectAllCarTypeRelDtoCount(bimcompanyId, new java.sql.Timestamp(updatTime), page,
					parkIdArr);
			r.setAllRow(allCount);
			r.setCurrentPage(page.getReqPage());
			r.setPageSize(page.getPageItems());
			r.setTotalPage(allCount % page.getPageItems() == 0 ? allCount / page.getPageItems()
					: allCount / page.getPageItems() + 1);
			if (allCount != 0) {
				r.setCarTypeRelDtoArr(
						pfmbm.selectAllCarTypeRelDto(bimcompanyId, new java.sql.Timestamp(updatTime), page, parkIdArr)
								.toArray(new CarTypeRelDto[0]));
			} else {
				r.setCarTypeRelDtoArr(new CarTypeRelDto[0]);
			}
			r.setiRet(true);
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;

	}

	/**
	 * 获取账户信息
	 * @author zhanying 2017年2月8日 下午2:21:08
	 * @param @param serverId
	 * @param @param updateTime
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public AccountResult getAccountInfo(int serverId, long updateTime, CommonPageRequest page, int[] parkIdArr) {
		AccountResult r = new AccountResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null && parkIdArr.length != 0) {
			int bimcompanyId = server.getBimCompanyId();
			int allCount = pfmbm.selectAllAccountDtoCount(bimcompanyId, new java.sql.Timestamp(updateTime), page,
					parkIdArr);
			r.setAllRow(allCount);
			r.setCurrentPage(page.getReqPage());
			r.setPageSize(page.getPageItems());
			r.setTotalPage(allCount % page.getPageItems() == 0 ? allCount / page.getPageItems()
					: allCount / page.getPageItems() + 1);
			if (allCount != 0) {
				r.setAccountDtoArr(
						pfmbm.selectAllAccountDto(bimcompanyId, new java.sql.Timestamp(updateTime), page, parkIdArr)
								.toArray(new AccountDto[0]));
			} else {
				r.setAccountDtoArr(new AccountDto[0]);
			}
			r.setiRet(true);
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;

	}

	/**
	 * 获取通行时段
	 * @author zhanying 2017年2月8日 下午3:56:37
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public PfmTimeResult getPfmTime(int serverId) {
		PfmTimeResult r = new PfmTimeResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setiRet(true);
			r.setPfmTimeDtoArr(pfmbm.selectAllPfmTimeDto(bimcompanyId).toArray((new PfmTimeDto[0])));
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	}

	/**
	 * 更新使用的车位数量
	 * @author zhanying 2017年3月2日 下午2:52:25
	 * @param @param serverId
	 * @param @param useParkSpacesCoun
	 * @param @param parkId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CommonResult updateParkSpaces(int serverId, int useParkSpacesCoun, int parkId) {
		return new CommonResult();
	}

	/**
	 * 获取显示屏设备信息
	 * @author zhanying 2017年5月2日 下午1:50:19
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public DeviceScreenResult getDeviceScreen(int serverId) {
		DeviceScreenResult r = new DeviceScreenResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			Integer bimcompanyId = server.getBimCompanyId();
			r.setiRet(true);
			r.setDeviceScreenDtoArr(pfmbm.selectAllDeviceScreen(bimcompanyId).toArray(new DeviceScreenDto[0]));
		} else {
			r.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return r;
	
	}

	/**
	 * 获取计划信息
	 * @author zhanying 2017年5月2日 下午1:50:39
	 * @param @paramserverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public TaskMessageResult getTaskMessage(int serverId) {
		TaskMessageResult result = new TaskMessageResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			Integer bimcompanyId = server.getBimCompanyId();
			result.setiRet(true);
			result.setTaskMessageDtoArr(pfmbm.selectAllTaskMessage(bimcompanyId).toArray(new TaskMessageDto[0]));
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}
	
	/**
	* 获取特殊车牌
	* @author zhanying 2017年5月15日 下午7:55:37
	* @param  @param serverId
	* @param  @param updateTime
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public SpecialCarNumResult  getSpecialCarNum(int serverId, long updateTime){
		SpecialCarNumResult result = new SpecialCarNumResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			Integer bimcompanyId = server.getBimCompanyId();
			result.setiRet(true);
			result.setSpecialCarNumDtoArr(pfmbm.selectAllSpecial(bimcompanyId, new Date(updateTime)).toArray(new SpecialCarNumDto[0]));
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}

	/**
	* 根据车辆id获取相关联的附属车牌
	* @author zhanying 2017年5月16日 上午11:06:14
	* @param  @param serverId
	* @param  @param carId
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public SubsidiaryCarNumResult getSubsidiaryCarNumByCarId(int serverId,int carId){
		SubsidiaryCarNumResult result = new SubsidiaryCarNumResult();
		PfmServer server = serverMapper.selectByPrimaryKey(serverId);
		if (server != null) {
			result.setiRet(true);
			result.setSubsidiaryCarNumArr(pfmbm.selectAllSubsidiaryCarNum(carId).toArray(new String[0]));
		} else {
			result.setiRet(false);
			LogManager.logInfo("服务器不存在或数组长度为0 serverId:"+serverId);
		}
		return result;
	}
	
	/**
	* 上传收费记录
	* @author zhanying 2017年6月14日 上午10:37:58
	* @param  @param serverId
	* @param  @param chargeRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	
	public CommonResult uploadChargerRecord(int serverId,ChargeRecordDto chargeRecordDto) {
		CommonResult result = null;
		try{
			result = transPfmService.uploadChargerRecord(serverId, chargeRecordDto);
		}
		catch(Exception e){
			result.setiRet(false);
		}
		return result;
	}
	
	/**
	* 上传入场记录
	* @author zhanying 2017年6月14日 上午10:40:31
	* @param  @param serverId
	* @param  @param chargeRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	
	public CommonResult uploadInRecord(int serverId,InRecordDto inRecordDto){
		CommonResult result = new CommonResult();
		try {
			result =  transPfmService.uploadInRecord(serverId, inRecordDto);
		}
		catch(Exception e) {
			result.setiRet(false);
		}
		return result;
	}
	
	/**
	* 上传出场记录
	* @author zhanying 2017年6月14日 上午10:43:29
	* @param  @param serverId
	* @param  @param outRecordDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */

	public CommonResult uploadOutRecord(int serverId,OutRecordDto outRecordDto) {	
		CommonResult result = new CommonResult();
		try {
			result = transPfmService.uploadOutRecord(serverId, outRecordDto);
		}
		catch(Exception e) {
			result.setiRet(false);
		}
		return result;
	}
	
	/**
	* 上传移动支付记录
	* @author zhanying 2017年7月18日 下午4:34:08
	* @param  @param serverId
	* @param  @param payOrderDto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public CommonResult uploadPayOrder(int serverId,PayOrderDto payOrderDto) {
		CommonResult result = new CommonResult();
		try {
			result = transPfmService.uploadPayOrder(serverId, payOrderDto);
		}
		catch(Exception e) {
			result.setiRet(false);
		}
		return result;
	}
}
