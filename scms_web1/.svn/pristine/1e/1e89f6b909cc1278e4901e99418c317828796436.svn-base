package com.wadejerry.scms.modules.auth.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.auth.dto.RoleDto;
import com.wadejerry.scms.modules.auth.dto.UserDto;
import com.wadejerry.scms.modules.auth.model.BimUser;

public interface BimUserService {
	
	public BimUser getUserById(int id);

	public BimUser findUserByLoginName(String username,int userType);

	/**
	* 获取用户或角色信息
	* @author zhanying 2016年9月19日 下午10:22:52
	* @param  @param dto
	* @param  @param iType
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public List<UserDto> getBimUserListByPage(PageParamsDto dto);
	
	/**
	* 分页获取角色信息
	* @author zhanying 2016年9月29日 上午11:13:32
	* @param  @param dto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public List<RoleDto> getRoleListByPage(PageParamsDto dto);
	 
	/**
	* 获取记录总数
	* @author zhanying 2016年9月19日 下午10:24:44
	* @param  @param dto
	* @param  @param iType
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int getRecordTotal(PageParamsDto dto);
	
	public int getRoleRecordTotal(PageParamsDto dto);
	/**
	* 根据ID删除用户或角色
	* @author zhanying 2016年9月22日 下午8:19:55
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int deleteUserById(int id);

	/**
	* 添加一个角色或操作员 
	* @author zhanying 2016年9月22日 下午9:39:20
	* @param  @param user
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int insertUser(BimUser user);
	
	/**
	*  更新角色信息
	* @author zhanying 2016年9月22日 下午9:42:47
	* @param  @param user
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int updateUser(BimUser user);

	public List<UserDto> selectInfo();
	
}
