package com.wadejerry.scms.modules.device.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.device.model.Server;

public interface PfmServerMapper {
    int deleteByPrimaryKey(Integer pfmServerId);

    int insert(PfmServer record);

    int insertSelective(PfmServer record);

    PfmServer selectByPrimaryKey(Integer pfmServerId);

    int updateByPrimaryKeySelective(PfmServer record);

    int updateByPrimaryKey(PfmServer record);
    
    int selectCountByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    List<Server> selectByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    int selectCountByIpANDPort(@Param("ip")String ip,@Param("port")String port,@Param("companyId")int companyId);
    
    int selectCountByIpANDPortExceptself(@Param("id")Integer id,@Param("ip")String ip,@Param("port")String port,@Param("companyId")int companyId);
    
    List<Map<String,String>> selectAllTopLot(int companyId);
    
    int selectParkLotByparkId(@Param("companyId")int companyId,@Param("parkId") int parkId);
    
    int selectParkLotByparkIdExceptSelf(@Param("companyId")int companyId,@Param("parkId") int parkId,@Param("id") int id);

	List<PfmServer> selectAllServer();
	
	int checkIfDeviceRelated(Integer serverId);
}