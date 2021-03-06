package com.wadejerry.scms.modules.onecard.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.onecard.dao.BimDeptMapper;
import com.wadejerry.scms.modules.onecard.dto.BimDeptDto;
import com.wadejerry.scms.modules.onecard.service.BimDeptService;
import com.wadejerry.scms.utils.Validator;

@Service("bimDeptService")
public class BimDeptServiceImpl implements BimDeptService {
	@Autowired
	private BimDeptMapper bimDeptMapper;

	public BimDeptDto selectInfoByTreeId(Integer treeid, Integer companyid) {
		return bimDeptMapper.selectInfoByTreeId(treeid, companyid);

	}

	public BimDeptDto selectInfoByCodeOrName(Integer deptCode, String deptName, Integer companyid) {

		return bimDeptMapper.selectInfoByCodeOrName(deptCode, deptName, companyid);
	}

	public void insertInfo(BimDeptDto bimDeptDto) {
		bimDeptMapper.insertInfo(bimDeptDto);

	}

	public int selectTotalCount() {

		return bimDeptMapper.selectTotalCount();
	}

	public BimDeptDto selectIncodeById(Integer inCode) {

		return bimDeptMapper.selectIncodeById(inCode);
	}

	public void updateInfo(BimDeptDto bimDeptDto) {
		bimDeptMapper.updateInfo(bimDeptDto);

	}

	public void deleteTreeNode(BimDeptDto bimDeptDto) {
		bimDeptMapper.deleteTreeNode(bimDeptDto);

	}

	public BimDeptDto selectCount(Integer deptCode, String deptName) {
		return bimDeptMapper.selectCount(deptCode, deptName);

	}

	public BimDeptDto selectInfoByDeptName(String deptName) {
		return bimDeptMapper.selectInfoByDeptName(deptName);
	}

	@Override
	public List<Map<String, Object>> download(int companyid) {

		return bimDeptMapper.download(companyid);
	}

	@Override
	public OperateResult validateAndInsert(List<Map<String, String>> list) {
		// 基本验证
		OperateResult result = new OperateResult();
		int index = 1;
		// 标识数据是否有错误
		boolean b = true;
		String error = "";
		for (Map<String, String> map : list) {
			// System.out.println(map.get("出生日期").toString());
			if (index == 1) {
				if (map.get("部门编号") == null// 编号是否存在
						|| map.get("部门名称") == null// 部门名称是否存在
						|| map.get("上级部门编号") == null// 上级编号是否存在
						|| map.get("拼音") == null

				) {
					throw new RuntimeException("请使用模板！您的模板存在列名不正确的情况，请仔细核对！");
				}
			}
			index += 1;

			if (!Validator.isName(map.get("部门名称").trim())) {// 部门名称格式
				b = false;
				error += "第" + index + "行部门名称格式错误；\\n";
			}

			if (bimDeptMapper.selectCountByDeptName(map.get("部门名称").trim(), LoginInfo.getCompanyId()) > 0)// 部门名称是否存在
			{
				b = false;
				error += "第" + index + "行请确认是否存在此部门名称；\\n";
			}
			/*if (bimDeptMapper.selectCountByInCode(Integer.valueOf(map.get("上级部门编号").trim()),
					LoginInfo.getCompanyId()) == 0)// 上级编号是否存在
			{
				b = false;
				error += "第" + index + "行请确认是否存在此上级部门编号；\\n";
			}*/
		}
		if (!b) {
			result.setResult(false);
			result.setMsg(error);
			return result;
		} else {
			for (Map<String, String> map : list) {
				BimDeptDto bimDeptDto = new BimDeptDto();
				bimDeptDto.setBimCompanyId(LoginInfo.getCompanyId());
				bimDeptDto.setBimDeptId(bimDeptMapper.selectTotalCount() + 1);
				if (bimDeptMapper.selectCountByDeptCode(Integer.valueOf(map.get("部门编号").trim()),
						LoginInfo.getCompanyId()) > 0) {
					throw new RuntimeException("已存在部门编号为 " + map.get("部门编号").trim() + " 的部门信息！");
				}
				bimDeptDto.setDeptCode(Integer.valueOf(map.get("部门编号").trim()));
				bimDeptDto.setDeptName(map.get("部门名称").trim());
				bimDeptDto.setInCode(Integer.valueOf(map.get("上级部门编号").trim()));
				bimDeptDto.setPinyin(map.get("拼音").trim());
				// 查询上级部门的等级类型
				//BimDeptDto DeptDto = bimDeptMapper.selectIncodeByDeptCode(Integer.valueOf(map.get("上级部门编号").trim()));
				BimDeptDto DeptDto = bimDeptMapper.selectByDeptCode(Integer.valueOf(map.get("上级部门编号").trim()));
				bimDeptDto.setDeptLevel(DeptDto.getDeptLevel() + 1);
				bimDeptDto.setCustom4(String.valueOf(DeptDto.getBimDeptId()));
				bimDeptMapper.exportDept(bimDeptDto);// 导入部门信息

			}

		}
		result.setResult(true);
		return result;
	}

	@Override
	public BimDeptDto selectByDeptCode(Integer code) {
		
		return bimDeptMapper.selectByDeptCode(code);
	}

}
