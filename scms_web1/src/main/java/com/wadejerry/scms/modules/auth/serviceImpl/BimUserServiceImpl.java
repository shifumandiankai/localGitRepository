package com.wadejerry.scms.modules.auth.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.RoleDto;
import com.wadejerry.scms.modules.auth.dto.UserDto;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.auth.service.BimUserService;
import com.wadejerry.scms.utils.date.DateUtil;

@Service("bimUserService")
public class BimUserServiceImpl implements BimUserService {
	@Autowired
	private BimUserMapper bimUserMapper;
	@Autowired
	private BimUserRoleMapper bimUserRoleMapper;

	public BimUser getUserById(int bimUserId) {
		return bimUserMapper.selectByPrimaryKey(bimUserId);
	}

	public BimUser findUserByLoginName(String userName, int userType) {
		List<BimUser> userList = bimUserMapper.selectByUserName(userName, userType);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<UserDto> getBimUserListByPage(PageParamsDto paramsDto) {
		List<UserDto> userList = bimUserMapper.selectByPage(paramsDto);
		List<UserRoleRelDto> userRoleRleList = null;
		for (UserDto dto : userList) {
			userRoleRleList = bimUserRoleMapper.selectUserRoleDtoByUserId(dto.getBimUserId());
			List<String> roleIdList = new ArrayList<String>();
			for(UserRoleRelDto userRoleRelDto : userRoleRleList){
				roleIdList.add(Integer.toString(userRoleRelDto.getRoleId()));
			}
			//dto.setDisableTime(dto.getDisableTime());
			dto.setSelRoleIds(roleIdList);
		}
		return userList;
	}

	@Override
	public int getRecordTotal(PageParamsDto paramsDto) {
		int iTotal = bimUserMapper.selectRecordTotal(paramsDto);
		return iTotal;
	}

	@Override
	public List<RoleDto> getRoleListByPage(PageParamsDto paramsDto) {
		 List<UserRoleRelDto> dtos = bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		 List<Integer> roleIds = new ArrayList();
		 if(dtos.size()!=0){
			 for(UserRoleRelDto dto:dtos){
				 roleIds.add(dto.getRoleId());
			 }
		 }
		 List<RoleDto> roleDtoList = null;
			 roleDtoList = bimUserMapper.selectRoleListByPage(paramsDto,roleIds);
		 
		 
		return roleDtoList;
	}

	@Override
	public int deleteUserById(int id) {
		return bimUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertUser(BimUser user) {
		return bimUserMapper.insertSelective(user);
	}

	@Override
	public int updateUser(BimUser user) {
		return bimUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public int getRoleRecordTotal(PageParamsDto paramsDto) {
		// TODO Auto-generated method stub
		 List<UserRoleRelDto> dtos = bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		 List<Integer> roleIds = new ArrayList();
		 if(dtos.size()!=0){
			 for(UserRoleRelDto dto:dtos){
				 roleIds.add(dto.getRoleId());
			 }
		 }
		 List<RoleDto> roleDtoList = null;
		return bimUserMapper.selectRoleRecordTotal(paramsDto, roleIds);
	}

	@Override
	public List<UserDto> selectInfo() {
		
		return bimUserMapper.selectInfo();
	}

}
