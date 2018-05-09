package com.wadejerry.scms.modules.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.auth.dto.RoleDto;
import com.wadejerry.scms.modules.auth.dto.UserDto;
import com.wadejerry.scms.modules.auth.model.BimUser;

public interface BimUserMapper {
    int deleteByPrimaryKey(Integer bimUserId);

    int insert(BimUser record);

    int insertSelective(BimUser record);

    BimUser selectByPrimaryKey(Integer bimUserId);

    int updateByPrimaryKeySelective(BimUser record);

    int updateByPrimaryKey(BimUser record);
    
    List<BimUser> selectByUserName(String bimUserName,int userType);
    
    /***
    * 获取记录数
    * @author zhanying 2016年9月20日 上午11:01:42
    * @param  @param paramsDto
    * @param  @return 
    * @modificationHistory=========================逻辑或功能性重大变更记录
    * @modify by user: 
    * @modify by reason:
     */
    int selectRecordTotal(PageParamsDto paramsDto);
    
    int selectRoleRecordTotal(@Param("pageParams")PageParamsDto paramsDto,@Param("roleIds")List<Integer> id);
    /***
    * 获取分页数据
    * @author zhanying 2016年9月20日 上午11:01:54
    * @param  @param paramsDto
    * @param  @return 
    * @modificationHistory=========================逻辑或功能性重大变更记录
    * @modify by user: 
    * @modify by reason:
     */
    List<UserDto> selectByPage(PageParamsDto paramsDto);
    
    /**
    * 分页获取角色信息
    * @author zhanying 2016年9月22日 下午8:22:33
    * @param  @param paramsDto
    * @param  @return 
    * @modificationHistory=========================逻辑或功能性重大变更记录
    * @modify by user: 
    * @modify by reason:
     */
    List<RoleDto> selectRoleListByPage(@Param("pageParams")PageParamsDto paramsDto,@Param("roleIds")List<Integer> ids);
    
    
    List<String> selectRoleByUserId(Integer id);

	List<UserDto> selectInfo();
    
  
}