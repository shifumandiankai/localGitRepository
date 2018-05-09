package com.wadejerry.scms.modules.auth.serviceImpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.auth.dao.BimAuthorizationMapper;
import com.wadejerry.scms.modules.auth.dao.BimRoleAreaAuthMapper;
import com.wadejerry.scms.modules.auth.dao.BimRoleAuthMapper;
import com.wadejerry.scms.modules.auth.dao.BimRoleDeptAuthMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.AuthTreeDto;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimRoleAreaAuth;
import com.wadejerry.scms.modules.auth.model.BimRoleAuth;
import com.wadejerry.scms.modules.auth.model.BimRoleDeptAuth;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.auth.model.BimUserRole;
import com.wadejerry.scms.modules.auth.service.BimUserRoleRelService;
import com.wadejerry.scms.modules.pfm.dto.CarTypeTreeDto;

@Service("bimUserRoleRelService")
public class BimUserRoleRelServiceImpl implements BimUserRoleRelService {
	@Autowired
	private BimUserRoleMapper bimUserRoleMapper;
	@Autowired
	private BimAuthorizationMapper bimAuthorizationMapper;
	@Autowired
	private BimRoleAuthMapper bimRoleAuthMapper;
	@Autowired
	private BimUserMapper userMapper;
	
	@Autowired
	private BimRoleAreaAuthMapper bimRoleAreaAuthMapper;
	@Autowired
	private BimRoleDeptAuthMapper bimRoleDeptAuthMapper;
	@Override
	public int deleteByUserId(int userId) {
		return bimUserRoleMapper.deleteByUserId(userId);
	}

	@Override
	public int insertUserRoleRel(BimUserRole userRoleRel) {
		return bimUserRoleMapper.insertSelective(userRoleRel);
	}

	@Override
	public List<UserRoleRelDto> selectUserRoleDtoByUserId(int userId) {
		return bimUserRoleMapper.selectUserRoleDtoByUserId(userId);
	}

	@Override
	public List<AuthTreeDto> AllAuthorizations(Integer roleId) {
		// TODO Auto-generated method stub
		List<AuthTreeDto> list = bimAuthorizationMapper.selectAll(roleId,LoginInfo.getSubSystem());
		if(!LoginInfo.isCompanyManager()){
			for(AuthTreeDto dto:list){
				dto.setChkDisabled(true);
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public OperateResult SaveAutho(Integer roleid, List<Integer> auths) {
		OperateResult result = new OperateResult();
		
		bimRoleAuthMapper.deleteByRoleId(roleid);
		BimRoleAuth bimRoleAuth =null;
		for(Integer auth :auths){
			bimRoleAuth = new BimRoleAuth();
			bimRoleAuth.setCreateTime(new Date());
			bimRoleAuth.setRoleId(roleid);
			bimRoleAuth.setBimAuthorizationId(auth);
			bimRoleAuthMapper.insertSelective(bimRoleAuth);
			
		}
		result.setResult(true);
		return result;
	}

	@Override
	public int countByRoleId(Integer roleid) {
		// TODO Auto-generated method stub
		return bimUserRoleMapper.countByRoleId(roleid);
	}

	@Override
	public List<String> selectRoleBy(Integer userid) {
		// TODO Auto-generated method stub
		return userMapper.selectRoleByUserId(userid);
	}

	@Override
	public List<String> selectPermissionByid(Integer id) {
		// TODO Auto-generated method stub
		List<String> permission =  bimAuthorizationMapper.selectPermissionByUserId(id);
		List<String> permissionM = bimAuthorizationMapper.selectPermissionsBySubs(LoginInfo.getSubSystem());
		permission.retainAll(permissionM);
		return permission;
	}

	@Override
	public List<String> AllAuthorizationsManager() {
		// TODO Auto-generated method stub
		return bimAuthorizationMapper.selectPermissionsBySubs(LoginInfo.getSubSystem());
	}

	@Override
	public List<CarTypeTreeDto> selectCarTypeAuth(Integer roleid) {
		// TODO Auto-generated method stub
		BimUser user=userMapper.selectByPrimaryKey(roleid);
		String[] types=new String[0];
		if(user!=null){
			if(user.getCustom3()!=null&&user.getCustom3().length()!=0){
				types= user.getCustom3().split(",");
			}
		}
		List<Integer> temp = new ArrayList<>();
		for(String type:types){
			temp.add(Integer.parseInt(type));
		}
		List<CarTypeTreeDto> list=bimAuthorizationMapper.selectCartypeBy(LoginInfo.getCompanyId(), temp);
		if(!LoginInfo.isCompanyManager()){
			for(CarTypeTreeDto dto:list){
				dto.setChkDisabled(true);
			}
		}
		return list;
	}

	@Override
	public OperateResult saveCarTypeAuth(Integer roleid, String auths) {
		OperateResult result = new OperateResult();
		BimUser user =new BimUser();
		user.setBimUserId(roleid);
		user.setCustom3(auths);
		userMapper.updateByPrimaryKeySelective(user);
		result.setResult(true);
		return result;
	}

	@Override
	public OperateResult saveChuRukouAuth(Integer roleid, String auths) {
		OperateResult result = new OperateResult();
		BimUser user =new BimUser();
		user.setBimUserId(roleid);
		user.setCustom4(auths);
		userMapper.updateByPrimaryKeySelective(user);
		result.setResult(true);
		return result;
	}

	@Override
	public List<AuthTreeDto> getAreaAuthorizations(Integer roleId) {
		List<AuthTreeDto> list = bimRoleAreaAuthMapper.selectAreaAuthTree(roleId, LoginInfo.getCompanyId());
		if(!LoginInfo.isCompanyManager()){
			for(AuthTreeDto dto:list){
				dto.setChkDisabled(true);
			}
		}
		
		return list;
	}
	@Override
	public List<AuthTreeDto> getDeptAuthorizations(Integer roleId) {
		List<AuthTreeDto> list = bimRoleDeptAuthMapper.selectDeptAuthTree(roleId, LoginInfo.getCompanyId());
		if(!LoginInfo.isCompanyManager()){
			for(AuthTreeDto dto:list){
				dto.setChkDisabled(true);
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public OperateResult saveAreaAutho(Integer roleid, List<Integer> areaauths) {
		OperateResult result = new OperateResult();
		
		bimRoleAreaAuthMapper.deleteByRoleId(roleid);
		BimRoleAreaAuth bimRoleAreaAuth =null;
		for(Integer auth :areaauths){
			bimRoleAreaAuth = new BimRoleAreaAuth();
			bimRoleAreaAuth.setUpdateTime(new Date());
			bimRoleAreaAuth.setBimRoleId(roleid);
			bimRoleAreaAuth.setAcmAreaId(auth);
			bimRoleAreaAuthMapper.insertSelective(bimRoleAreaAuth);
			
		}
		result.setResult(true);
		return result;
	}

	@Override
	@Transactional
	public OperateResult saveDeptAutho(Integer roleid, List<Integer> deptauths) {
		
		OperateResult result = new OperateResult();
		
		bimRoleDeptAuthMapper.deleteByRoleId(roleid);
		BimRoleDeptAuth bimRoledeptAuth =null;
		for(Integer auth :deptauths){
			bimRoledeptAuth = new BimRoleDeptAuth();
			bimRoledeptAuth.setUpdateTime(new Date());
			bimRoledeptAuth.setBimRoleId(roleid);
			bimRoledeptAuth.setBimDeptId(auth);;
			bimRoleDeptAuthMapper.insertSelective(bimRoledeptAuth);
			
		}
		result.setResult(true);
		return result;
	}
}
