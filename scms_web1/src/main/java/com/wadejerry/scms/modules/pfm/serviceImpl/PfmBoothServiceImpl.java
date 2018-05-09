package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.pfm.dao.pfmBoothMapper;
import com.wadejerry.scms.modules.pfm.dto.BoothDto;
import com.wadejerry.scms.modules.pfm.model.PfmBooth;
import com.wadejerry.scms.modules.pfm.service.PfmBoothService;

@Service("Boothservice")
public class PfmBoothServiceImpl implements PfmBoothService {
	@Autowired
	private pfmBoothMapper pfmBoothmapper;
	@Autowired
	private BimUserRoleMapper bimUserRoleMapper;
	@Autowired
	private BimUserMapper userMapper;

	public int getRecordTotal(PageParamsDto paramsDto) {
		// 权限
		List<UserRoleRelDto> userroles = bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		List<Integer> entrances = new ArrayList();
		for (UserRoleRelDto temp : userroles) {
			BimUser user = userMapper.selectByPrimaryKey(temp.getRoleId());
			if (user != null) {
				if (user.getCustom4() != null && user.getCustom4().length() != 0) {
					List<String> list = Arrays.asList(user.getCustom4().split(","));
					for (String temp1 : list) {
						temp1 = temp1.substring(1);
						entrances.add(Integer.parseInt(temp1));
					}
				}
			}

		}
		int iTotal = pfmBoothmapper.getRecordTotal(paramsDto, entrances);
		return iTotal;
	}

	public List<BoothDto> getBoothListByPage(PageParamsDto paramsDto) {
		// 权限
		List<UserRoleRelDto> userroles = bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		List<Integer> entrances = new ArrayList();
		for (UserRoleRelDto temp : userroles) {
			BimUser user = userMapper.selectByPrimaryKey(temp.getRoleId());
			if (user != null) {
				if (user.getCustom4() != null && user.getCustom4().length() != 0) {
					List<String> list = Arrays.asList(user.getCustom4().split(","));
					for (String temp1 : list) {
						temp1 = temp1.substring(1);
						entrances.add(Integer.parseInt(temp1));
					}
				}
			}

		}
		List list = pfmBoothmapper.getBoothListByPage(paramsDto, entrances);
		return list;
	}

	public PfmBooth findByBoothName(String boothName, int companyId) {
		PfmBooth pfmBooth = pfmBoothmapper.findByBoothName(boothName, companyId);
		return pfmBooth;
	}

	public void insertBoothDto(PfmBooth insertbooth) {
		pfmBoothmapper.insertBoothDto(insertbooth);

	}

	public PfmBooth selectID(String parkName, String timeName) {
		PfmBooth BoothDto = pfmBoothmapper.selectID(parkName, timeName);
		return BoothDto;
	}

	public PfmBooth findByCodeName(String boothCode, int companyId) {
		PfmBooth pfmBooth = pfmBoothmapper.findByCodeName(boothCode, companyId);
		return pfmBooth;
	}

	public void updateBoothDto(PfmBooth insertbooth) {
		pfmBoothmapper.updateBoothDto(insertbooth);

	}

	public void deleteBoothById(Integer pfmBoothId) {
		pfmBoothmapper.deleteBoothById(pfmBoothId);

	}

	/*
	 * public DevicelrpDto selectAllLpr() { return
	 * pfmBoothmapper.selectAllLpr();
	 * 
	 * }
	 */
	public PfmBooth selectAllLpr(int id) {
		return pfmBoothmapper.selectAllLpr(id);

	}

	public List<DevicelrpDto> selectAll() {
		return pfmBoothmapper.selectAll();

	}

	@Override
	public PfmBooth selectByPfmEntranceId(Integer pfmEntranceId, int commpanyId) {

		return pfmBoothmapper.selectByPfmEntranceId(pfmEntranceId, commpanyId);
	}

}
