package com.wadejerry.scms.modules.auth.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.auth.dto.AuthTreeDto;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUserRole;
import com.wadejerry.scms.modules.pfm.dto.CarTypeTreeDto;

/**
 *  用户与角色关联Service&&角色和权限关联service
* @ClassName: BimUserRoleRelService
* @Description: TODO
* @author zhanying
* @date 2016年9月29日 上午11:19:53
 */
public interface BimUserRoleRelService {
	/**
	* 删除指定操作员角色管理 
	* @author zhanying 2016年9月29日 上午11:21:17
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int deleteByUserId(int userId);
	
	/**
	*  添加操作员角色
	* @author zhanying 2016年9月29日 上午11:25:44
	* @param  @param userId
	* @param  @param roleIdList
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int insertUserRoleRel(BimUserRole userRoleRel);
	
	/**
	* 获取用户角色信息
	* @author zhanying 2016年9月29日 下午2:54:04
	* @param  @param userId
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public List<UserRoleRelDto>selectUserRoleDtoByUserId(int userId);

	public List<String> AllAuthorizationsManager();
	
	int countByRoleId(Integer roleid);
	
	List<String> selectRoleBy(Integer userid);//根据用户id查找拥有角色
	
	List<String> selectPermissionByid(Integer id);//根据用户id查找拥有权限
	
	
	
	List<CarTypeTreeDto> selectCarTypeAuth(Integer roleid);//查询车辆类型权限
	
	public List<AuthTreeDto> AllAuthorizations(Integer roleId);//根据角色查询可配置的功能权限
	
	public List<AuthTreeDto> getAreaAuthorizations(Integer roleId);//根据角色查询可配置的门禁区域权限
	
	public List<AuthTreeDto> getDeptAuthorizations(Integer roleId);//根据角色查询可配置的门禁区域权限

	public OperateResult SaveAutho(Integer roleid,List<Integer> auths);//保存配置的功能权限
	
	public OperateResult saveCarTypeAuth(Integer roleid,String auths);//车辆类型权限保存
	
	public OperateResult saveChuRukouAuth(Integer roleid,String auths);//出入口权限保存
		
	public OperateResult saveAreaAutho(Integer roleid,List<Integer> areaauths);//保存配置的门禁权限
	
	public OperateResult saveDeptAutho(Integer roleid,List<Integer> deptauths);//保存配置的门禁权限
}
