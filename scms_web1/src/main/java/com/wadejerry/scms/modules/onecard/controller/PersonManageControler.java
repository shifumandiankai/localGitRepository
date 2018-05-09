package com.wadejerry.scms.modules.onecard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wadejerry.scms.frame.Authority.AuthorityGetter;
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.ExTemplate;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.onecard.dto.BimCardDto;
import com.wadejerry.scms.modules.onecard.dto.BimDeptDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.DeptTreeDto;
import com.wadejerry.scms.modules.onecard.dto.PersonManagerDto;
import com.wadejerry.scms.modules.onecard.model.BimCate;
import com.wadejerry.scms.modules.onecard.service.BimCardService;
import com.wadejerry.scms.modules.onecard.service.BimCateService;
import com.wadejerry.scms.modules.onecard.service.BimDeptService;
import com.wadejerry.scms.modules.onecard.service.BimPersonExtService;
import com.wadejerry.scms.modules.onecard.service.BimPersonPhotoService;
import com.wadejerry.scms.modules.onecard.service.PersonManagerService;
import com.wadejerry.scms.modules.pfm.service.PfmParkingLotService;
import com.wadejerry.scms.modules.pfm.serviceImpl.ExcelReader;
/*import com.wadejerry.scms.modules.pfm.serviceImpl.ExcelReader;
import com.wadejerry.scms.modules.pfm.serviceImpl.ExcelWriter;*/
//import com.wadejerry.scms.utils.ExcelReader;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.PinYinUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class PersonManageControler {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PersonManagerService managerService;
	@Autowired
	private BimDeptService bimDeptService;
	@Autowired
	private BimPersonExtService bimPersonExtService;
	@Autowired
	private ExTemplate exTemplate;
	@Autowired
	private BimCardService bimCardService;
	@Autowired
	private BimCateService bimCateService;
	@Autowired
	private AuthorityGetter authorityGetter;
	@Autowired
	private BimPersonPhotoService bimPersonPhotoService;

	/**
	 * 分页获取角色信息
	 * 
	 * @param
	 * @throws ParseException
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	@RequestMapping("/personnel/getpersonnelInfo.do")
	@ResponseBody
	private void GetPersonInfoByPage() throws ParseException {
		List<Integer> listtype = authorityGetter.carTypeAuth();
		String flag = null;
		List<Integer> listauth = null;
		if (listtype != null) {
			// 不是管理员
			listauth = authorityGetter.deptAuth();
			if (listauth.size() == 0) {
				// 无部门权限
				flag = "0";
			} else {
				// 有部门权限
				flag = "1";
			}

		}

		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
		if (!paramsDto.getSearchValue0().equals("")) {
			// 部门节点触发事件
			paramsDto.setIntValue5(Integer.valueOf(paramsDto.getSearchValue0()));
		}
		int iTotal = managerService.getRecordTotal(paramsDto, flag, listauth); // 获取记录总数
		List<PersonManagerDto> dtoList = null;
		if (iTotal > 0) {
			if (paramsDto.getLength() == -1) { // 查询所有记录
				paramsDto.setLength(iTotal);
			}
			dtoList = managerService.getpersonListByPage(paramsDto, flag, listauth); // 获取分页记录

		}

		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(dtoList);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}

	@RequestMapping("/personnel/getptree.do")
	@ResponseBody
	private void Getptree() {
		int companyid = LoginInfo.getCompanyId();
		// DeptTreeDto mr = new DeptTreeDto();
		List<DeptTreeDto> list = managerService.selectPTree(companyid);
		BimDeptDto bimDeptDto = new BimDeptDto();
		if (list.size() == 0) {
			bimDeptDto.setBimCompanyId(LoginInfo.getCompanyId());
			bimDeptDto.setDeptCode(0);
			bimDeptDto.setDeptName("默认部门");
			bimDeptDto.setInCode(-1);
			bimDeptDto.setDeptLevel(0);
			bimDeptDto.setPinyin("mrbm");
			bimDeptService.insertInfo(bimDeptDto);
			DeptTreeDto deptTreeDto = new DeptTreeDto();
			deptTreeDto.setId(bimDeptDto.getBimDeptId());
			deptTreeDto.setDeptCode(0);
			deptTreeDto.setName("默认部门");
			deptTreeDto.setSpId(-1);
			deptTreeDto.setPinyin("mrbm");
			list.add(deptTreeDto);
		} else {
			/*
			 * for(DeptTreeDto temp:list){ temp.setCustom2(temp.getpId()); }
			 */

		}
		AjaxUtil.ajaxWriteObject(list, response);
	}

	// 保存person信息
	@RequestMapping("/personnel/savepersonnelInfo.do")
	@ResponseBody
	public void SavePersonInfo(HttpServletResponse response, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) (ServletRequest) request;
		MultipartFile mFile = multipartRequest.getFile("up_img");// 上传的文件
		long size = mFile.getSize();
		String path = null;
		String srcPath = null;
		if (size != 0) {
			// 得到上传服务器的路径
			path = request.getSession().getServletContext().getRealPath("/PersonImage");
			File myfile = new File(path);
			if (!myfile.exists()) {
				myfile.mkdir();
			}
			// 得到上传的文件的文件名
			String filename = mFile.getOriginalFilename();
			if (size > (1024 * 1024)) {
				OperateResult result = new OperateResult();
				result.setResult(false);
				result.setMsg("只允许上传1M之内的图片！");
				AjaxUtil.ajaxWrite(result, response);
				return;
			}
			InputStream inputStream = null;
			FileOutputStream outputStream = null;
			try {
				inputStream = mFile.getInputStream();
				byte[] b = new byte[1048576];
				int length = inputStream.read(b);
				String imgName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				path += ("\\" + imgName + filename);
				srcPath = "\\PersonImage\\" + imgName + filename;
				// 文件流写到服务器端
				outputStream = new FileOutputStream(path);
				outputStream.write(b, 0, length);

			} catch (IOException e) {
				e.printStackTrace();
				OperateResult result = new OperateResult();
				if (e instanceof FileFormatException) {
					result.setResult(false);
					result.setMsg("头像格式错误！");
				} else {
					result.setResult(false);
					result.setMsg("服务器读写数据异常");
				}
				AjaxUtil.ajaxWrite(result, response);
			} finally {

				if (inputStream != null)
					try {
						inputStream.close();

					} catch (IOException e) {
						e.printStackTrace();
					}
				if (outputStream != null)
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}

		String formData = request.getParameter("condition");
		try {
			JSONObject jsonObject = new JSONObject(formData);
			if (jsonObject.has("birthday")) {
				String birthday = jsonObject.get("birthday").toString();
				if (!birthday.equals("")) {
					jsonObject.put("birthday", birthday + " 00:00:00");
				}
			}
			if (jsonObject.has("jobDate")) {
				String jobDate = jsonObject.get("jobDate").toString();
				if (!jobDate.equals("")) {
					jsonObject.put("jobDate", jobDate + " 00:00:00");
				}
			}
			if (jsonObject.has("resignationDate")) {
				String resignationDate = jsonObject.get("resignationDate").toString();
				if (!resignationDate.equals("")) {
					jsonObject.put("resignationDate", resignationDate + " 00:00:00");
				}
			}

			formData = jsonObject.toString();
		} catch (JSONException e1) {
			LogManager.logException(e1);
			return;
		}

		BimPersonDto bimpersonDto = JacksonUtil.toObject(formData, BimPersonDto.class);
		BimPersonDto bpDto = null;
		PageParamsDto pageParamsDto = new PageParamsDto(request);
		BimPersonDto personByCode = managerService.findByPersonCode(bimpersonDto.getPersonCode(),pageParamsDto.getCompanyId());
		BimDeptDto bimDeptDto = bimDeptService.selectInfoByDeptName(bimpersonDto.getDeptName());
		if (bimpersonDto.getPersonId() == null) {// 添加
			if (personByCode != null) {
				AjaxUtil.ajaxWrite(false, "人员编号已存在", response);
				return;
			}
			try {
				bpDto = new BimPersonDto();
				bpDto.setBimCompanyId(pageParamsDto.getCompanyId());
				bpDto.setPersonCode(bimpersonDto.getPersonCode());
				bpDto.setPersonName(bimpersonDto.getPersonName());
				if (bimpersonDto.getSex() == 0) {
					bpDto.setSex(0);
				} else {
					bpDto.setSex(1);
				}

				bpDto.setCustom3(bimpersonDto.getCustom3());

				// bpDto.setCustom3(bimpersonDto.getCustom3());
				bpDto.setImageSrc(srcPath);
				bpDto.setAddress(bimpersonDto.getAddress());
				bpDto.setCerttype(bimpersonDto.getCerttype());
				bpDto.setCertId(bimpersonDto.getCertId());
				bpDto.setBirthday(bimpersonDto.getBirthday());
				bpDto.setPinyin(bimpersonDto.getPinyin());
				bpDto.setPhone(bimpersonDto.getPhone());
				/*if (bimpersonDto.getCustom1() == null) {// 短信开门
					bpDto.setCustom1(0);
				} else {
					bpDto.setCustom1(1);
				}*/

				bpDto.setMail(bimpersonDto.getMail().trim());
				bpDto.setEnglishName(bimpersonDto.getEnglishName());
				bpDto.setJobDate(bimpersonDto.getJobDate());
				bpDto.setResignationDate(bimpersonDto.getResignationDate());
				bpDto.setEducational(bimpersonDto.getEducational());
				bpDto.setEthnic(bimpersonDto.getEthnic());
				bpDto.setBimDeptId(bimDeptDto.getBimDeptId());

				if (bimpersonDto.getFingerprint1Num() != null) {
					bpDto.setFingerprint1Num(Integer.valueOf(bimpersonDto.getFingerprint1Num()));
					bpDto.setFingerprint1(bimpersonDto.getFingerprint1());
					
				}
				if (bimpersonDto.getFingerprint2Num() != null) {
					bpDto.setFingerprint2Num(Integer.valueOf(bimpersonDto.getFingerprint2Num()));
					bpDto.setFingerprint2(bimpersonDto.getFingerprint2());
					//UUID uuid = UUID.randomUUID();
					//bpDto.setFingerprintGuid(uuid.toString().toUpperCase().replace("-", ""));
				}
				if(bimpersonDto.getFingerprint1Num() != null&&bimpersonDto.getFingerprint2Num() != null){
					UUID uuid = UUID.randomUUID();
					bpDto.setFingerprintGuid(uuid.toString().toUpperCase().replace("-", ""));
				}
				
				managerService.SavePersonInfo(bpDto);
			} catch (Exception e) {
				LogManager.logException(e);
				AjaxUtil.ajaxWrite(false, "操作失败", response); // 返回失败
				return;
			}
		}

		else {

			if (personByCode != null) {
				int a = bimpersonDto.getPersonId();
				int b = personByCode.getPersonId();
				if (a != b) {
					AjaxUtil.ajaxWrite(false, "人员编号已使用", response);
					return;
				}
			}

			// bpDto = managerService.getInfoById(bimpersonDto.getPersonId());
			try {
				/*if(srcPath!=null||(srcPath==null&&bimpersonDto.getPhotoSrc()!=null)){
					bimpersonDto.setImageSrc(srcPath);
				}else{
					bimpersonDto.setImageSrc(null);
				}*/
				
				if(srcPath!=null){
					bimpersonDto.setImageSrc(srcPath);
				}else if(srcPath==null&&bimpersonDto.getPhotoSrc()!=null){
					bimpersonDto.setImageSrc(bimpersonDto.getPhotoSrc());
				}else{
					bimpersonDto.setImageSrc(null);
				}
				if (bimpersonDto.getSex() == 0) {
					bimpersonDto.setSex(0);
				} else {
					bimpersonDto.setSex(1);
				}
				if(bimpersonDto.getFingerprint1Num() != null&&bimpersonDto.getFingerprint2Num() != null){
					UUID uuid = UUID.randomUUID();
					bimpersonDto.setFingerprintGuid(uuid.toString().toUpperCase().replace("-", ""));
				}
				managerService.updatePersonInfo(bimpersonDto);

			} catch (Exception e) {
				LogManager.logException(e);
				AjaxUtil.ajaxWrite(false, "操作失败", response); // 返回失败
				return;
			}

		}
		AjaxUtil.ajaxWrite(true, "", response);
	}

	@RequestMapping("/personnel/updateInfoByNode.do")
	@ResponseBody
	public void updateInfoByNode() {
		String deptid = request.getParameter("deptid");
		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
		paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		int iTotal = managerService.getRecordTotalByNode(Integer.valueOf(deptid)); // 获取记录总数
		List<PersonManagerDto> dtoList = null;
		if (iTotal > 0) {
			if (paramsDto.getLength() == -1) { // 查询所有记录
				paramsDto.setLength(iTotal);
				paramsDto.setIntValue2(Integer.valueOf(deptid));
			}
			dtoList = managerService.updateInfoByNode(paramsDto); // 获取分页记录
		}
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(dtoList);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
		// managerService.updateInfoByNode(Integer.valueOf(deptid));
	}

	/*
	 * 添加tree节点信息
	 */
	@RequestMapping("/personnel/adddept.do")
	@ResponseBody
	public void SelecctDeptInfo() {
		String formdata = request.getParameter("condition");
		BimDeptDto deptFormDto = JacksonUtil.toObject(formdata, BimDeptDto.class); // 获取表单数据
		Integer deptCode = deptFormDto.getDeptCode();// 部门编号
		String deptName = deptFormDto.getDeptName();// 部门名称
		String pinyin = deptFormDto.getPinyin();// 拼音
		int treeid = deptFormDto.getBimDeptId();// tree的id
		Integer type = deptFormDto.getType();// 类型： 0 添加 1 修改
		BimDeptDto bimDeptDto = new BimDeptDto();
		DeptTreeDto deptTreeDto = new DeptTreeDto();
		// 查询dept表中一共有多少条数据
		int count = bimDeptService.selectTotalCount();
		// 查询treeid为 id的基本信息
		BimDeptDto AllDto = bimDeptService.selectInfoByTreeId(treeid, LoginInfo.getCompanyId());
		BimDeptDto deptname = bimDeptService.selectInfoByDeptName(deptName);
		BimDeptDto deptcode = bimDeptService.selectByDeptCode(Integer.valueOf(deptCode));

		if (deptcode != null) {
			int a = deptcode.getBimDeptId();
			if (a != treeid) {
				AjaxUtil.ajaxWrite(false, "部门编号已经存在", response);
				return;
			}

		}
		if (deptname != null) {
			int a = deptname.getBimDeptId();
			if (a != treeid) {
				AjaxUtil.ajaxWrite(false, "部门名称已经存在", response);
				return;
			}

		}
		if (type == 0) {
			// 添加
			// 查询部门编号或名称是否存在

			// BimDeptDto bd = bimDeptService.selectInfoByCodeOrName(deptCode,
			// deptName, LoginInfo.getCompanyId());
			// if (bd == null) {
			// 不存在
			bimDeptDto.setBimCompanyId(LoginInfo.getCompanyId());
			bimDeptDto.setDeptCode(Integer.valueOf(deptCode));
			bimDeptDto.setPinyin(pinyin);
			bimDeptDto.setDeptName(deptName);
			bimDeptDto.setInCode(AllDto.getDeptCode());
			bimDeptDto.setDeptLevel(AllDto.getDeptLevel() + 1);
			// bimDeptDto.setCustom4(String.valueOf(AllDto.getBimDeptId()));
			bimDeptDto.setCustom2(AllDto.getBimDeptId());
			bimDeptDto.setPinyin(pinyin);
			bimDeptDto.setCreateTime(new Date());
			bimDeptService.insertInfo(bimDeptDto);

			// deptTreeDto.setBimDeptId(bimDeptDto.getBimDeptId());
			deptTreeDto.setId(bimDeptDto.getBimDeptId());
			deptTreeDto.setDeptCode(Integer.valueOf(deptCode));
			deptTreeDto.setName(deptName);
			deptTreeDto.setSpId(AllDto.getInCode() + 1);
			deptTreeDto.setpId(AllDto.getCustom2());
			deptTreeDto.setPinyin(pinyin);
			deptTreeDto.setCustom2(AllDto.getBimDeptId());
			AjaxUtil.ajaxWriteObject(true, deptTreeDto, response);
			return;
			/*
			 * } else { // 信息重复，不可添加 AjaxUtil.ajaxWrite(false, "部门编号或名称已经存在",
			 * response); return; }
			 */

			// }

		} else {
			// 修改
			// 查询部门编号和名称同时存在的记录数
			// BimDeptDto editDto = bimDeptService.selectCount(deptCode,
			// deptName);
			// BimDeptDto editDto =
			// bimDeptService.selectInfoByCodeOrName(deptCode, deptName,
			// LoginInfo.getCompanyId());
			// 1.当编号和名称都不存在时，修改成功2.当编号和名称都存在并且deptid相等时，修改成功
			// if (editDto == null) {
			bimDeptDto.setBimCompanyId(LoginInfo.getCompanyId());
			bimDeptDto.setDeptCode(Integer.valueOf(deptCode));
			bimDeptDto.setPinyin(pinyin);
			bimDeptDto.setDeptName(deptName);
			bimDeptDto.setInCode(AllDto.getDeptCode());
			bimDeptDto.setDeptLevel(AllDto.getDeptLevel() + 1);
			bimDeptDto.setCustom4(String.valueOf(AllDto.getBimDeptId()));
			bimDeptDto.setPinyin(pinyin);
			bimDeptDto.setBimDeptId(AllDto.getBimDeptId());
			bimDeptDto.setUpdateTime(new Date());
			bimDeptService.updateInfo(bimDeptDto);

			deptTreeDto.setId(bimDeptDto.getBimDeptId());
			deptTreeDto.setDeptCode(Integer.valueOf(deptCode));
			deptTreeDto.setName(deptName);
			deptTreeDto.setSpId(AllDto.getInCode());
			deptTreeDto.setpId(AllDto.getCustom2());
			deptTreeDto.setPinyin(pinyin);
			// deptTreeDto.setCustom2(AllDto.getBimDeptId());
			AjaxUtil.ajaxWriteObject(true, deptTreeDto, response);
			return;
			
		}

	}

	/*
	 * 获取名称的拼音代码
	 */
	@RequestMapping("/personnel/selectpinyin.do")
	@ResponseBody
	public void SelectPinyin() {
		String deptName = request.getParameter("val");
		String pinyin = PinYinUtil.cn2py(deptName);
		BimDeptDto bimDeptDto = new BimDeptDto();
		bimDeptDto.setPinyin(pinyin);
		AjaxUtil.ajaxWriteObject(bimDeptDto, response);
	}

	/*
	 * 修改dept信息
	 */
	@RequestMapping("/personnel/selectdeptInfo.do")
	@ResponseBody
	public void SelectDeptInfo() {
		String id = request.getParameter("id");// treeid
		// 查询所有信息
		BimDeptDto bimDeptDto = bimDeptService.selectInfoByTreeId(Integer.valueOf(id), LoginInfo.getCompanyId());
		// 判断是否拥有上级部门
		if (bimDeptDto.getDeptLevel() == 0) {
			// 没有上级部门
			bimDeptDto.setInCodeName("无");
		} else {
			// 根据上级部门编号查询上级信息
			BimDeptDto IncodeDto = bimDeptService.selectIncodeById(bimDeptDto.getInCode());
			bimDeptDto.setInCodeName(IncodeDto.getDeptName());

		}

		AjaxUtil.ajaxWriteObject(bimDeptDto, response);
	}

	/*
	 * 删除tree节点
	 * 
	 */
	@RequestMapping("/personnel/deleteTreeNode.do")
	@ResponseBody
	public void DelectTreeNode() {
		String id = request.getParameter("node");
		// 查询person表中是否存在选择部门的信息，如果存在，不可删除
		List<BimPersonDto> bimpersonDto = managerService.selectInfoByDeptId(Integer.valueOf(id));
		if (bimpersonDto.size() > 0) {// 存在 不可删除
			AjaxUtil.ajaxWrite(false, "部门或子部门下有人员信息,不可删除,", response);
			return;
		} else {
			// 查询所有信息
			BimDeptDto bimDeptDto = bimDeptService.selectInfoByTreeId(Integer.valueOf(id), LoginInfo.getCompanyId());

			bimDeptService.deleteTreeNode(bimDeptDto);
			AjaxUtil.ajaxWrite(true, "删除成功", response);
		}

	}

	/*
	 * 移至部门信息
	 */
	@RequestMapping("/personnel/changedept.do")
	@ResponseBody
	public void ChangeDeptInfo() {

		try {
			String formdata = request.getParameter("condition");
			String deptid = request.getParameter("deptid");
			JSONArray array = new JSONArray(formdata);
			for (int i = 0; i < array.length(); i++) {
				// JSONObject jsonObject = array.getJSONObject(i);
				String personId = array.get(i).toString();
				managerService.updateDeptInfo(Integer.valueOf(personId), Integer.valueOf(deptid));
			}
			AjaxUtil.ajaxWrite(true, "移至部门成功", response); // 返回失败

		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false, "移至部门失败", response); // 返回失败
			return;
		}

	}

	/*
	 * 删除人员信息
	 */
	@RequestMapping("/personnel/delpersonnelInfo.do")
	@ResponseBody
	public void DelPersonInfo() throws JSONException {
		String jsonArrayStr = request.getParameter("condition");
		JSONArray jsonArray = new JSONArray(jsonArrayStr);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String str = jsonObject.get("sex").toString();
			if (str.equals("男")) {
				jsonObject.put("sex", 0);
			} else {
				jsonObject.put("sex", 1);
			}
			jsonArray.put(i, jsonObject);
		}
		String jsonArrayToString = jsonArray.toString();

		try {
			List<PersonManagerDto> delDtoList = JacksonUtil.json2list(jsonArrayToString, PersonManagerDto.class);
			for (PersonManagerDto personManagerDto : delDtoList) {

				managerService.deletePersonById(personManagerDto.getPersonId());
			}
		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
			return;
		}
		AjaxUtil.ajaxWrite(true, "", response); // 返回成功

	}

	/*
	 * 导入
	 */
	@RequestMapping("/personnel/upload.do")
	@ResponseBody
	public void uploadInfo(HttpServletResponse response, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) (ServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("file");
		// 导入的类型 0：部门 1：员工
		String type = request.getParameter("type");
		// 得到上传服务器的路径

		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		// 得到上传的文件的文件名
		String filename = mFile.getOriginalFilename();
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = mFile.getInputStream();
			byte[] b = new byte[1048576];
			int length = inputStream.read(b);

			path += ("\\" + filename);
			// 文件流写到服务器端
			outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);

			// List<Map<String, String>> list = ExcelReader.ReadExcel(path);
			List<Map<String, String>> list = ExcelReader.ReadExcel(path);
			if (list.isEmpty()) {
				OperateResult result = new OperateResult();
				result.setResult(false);
				result.setMsg("excel表格为空！或者服务器异常，请重新上传文件。");
				AjaxUtil.ajaxWrite(result, response);
			} else {
				OperateResult result = null;
				try {
					if (Integer.valueOf(type) == 0) {
						// 部门
						result = bimDeptService.validateAndInsert(list);
					} else {
						// 员工
						result = managerService.validateAndInsert(list);
					}
				} catch (Exception e) {

					e.printStackTrace();
					result = new OperateResult();
					result.setResult(false);
					result.setMsg(e.getMessage());
				}

				AjaxUtil.ajaxWrite(result, response);
			}
		}

		catch (IOException e) {

			e.printStackTrace();
			OperateResult result = new OperateResult();
			if (e instanceof FileFormatException) {
				result.setResult(false);
				result.setMsg("文件格式错误！");
			} else {
				result.setResult(false);
				result.setMsg("服务器读写数据异常");
			}
			AjaxUtil.ajaxWrite(result, response);

		} finally {

			if (inputStream != null)
				try {
					inputStream.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/*
	 * 导出部门
	 */
	@RequestMapping("/personnel/Deptdownload.do")
	@ResponseBody
	public void DeptdownloadInfo() {
		List<Map<String, Object>> data = bimDeptService.download(LoginInfo.getCompanyId());
		String[] head = { "部门编号", "部门名称", "上级部门编号", "拼音" };
		String[] column = { "dept_code", "dept_name", "custom4", "pinyin" };
		exTemplate.DeptExport(data, head, column, 0);

	}

	/*
	 * 导出员工
	 */
	@RequestMapping("/personnel/Persondownload.do")
	@ResponseBody
	public void PersondownloadInfo() {

		List<Map<String, Object>> data = managerService.download(LoginInfo.getCompanyId());
		String[] head = { "人员编号*", "人员姓名*", "部门编号*", "性别", "证件类型", "证件号码", "出生日期", "联系电话", "拼音代码", "联系地址", "英文名",
				"E-MAIL", "到职日期", "离职日期", "学历", "民族" };
		String[] column = { "person_code", "person_name", "bim_dept_id", "sex", "certtype", "cert_id", "birthday",
				"phone", "pinyin", "address", "english_name", "mail", "enabledate", "disabledate", "educational",
				"national" };
		exTemplate.DeptExport(data, head, column, 1);

	}

	/*
	 * 导出部门模板
	 */
	@RequestMapping("/personnel/DeptDemodownload.do")
	@ResponseBody
	public void DeptdownloadInfoDemo() {
		List<Map<String, Object>> data = null;
		String[] head = { "部门编号", "部门名称", "上级部门编号", "拼音" };
		String[] column = { "dept_code", "dept_name", "in_code", "pinyin" };
		exTemplate.DeptExport(data, head, column, 0);

	}

	/*
	 * 导出员工模板
	 */
	@RequestMapping("/personnel/PersonDemodownload.do")
	@ResponseBody
	public void PersondownloadInfoDemo() {
		List<Map<String, Object>> data = null;
		String[] head = { "人员编号*", "人员姓名*", "部门编号*", "性别", "证件类型", "证件号码", "出生日期", "联系电话", "拼音代码", "联系地址", "英文名",
				"E-MAIL", "到职日期", "离职日期", "学历", "民族" };
		String[] column = { "person_code", "person_name", "bim_dept_id", "sex", "certtype", "cert_id", "birthday",
				"phone", "pinyin", "address", "english_name", "mail", "enabledate", "disabledate", "educational",
				"national" };
		exTemplate.DeptExport(data, head, column, 1);
	}

	/*
	 * 批量开卡
	 */
	@RequestMapping("/personnel/batchcard.do")
	@ResponseBody
	public void GetBatchcardByPage() throws JSONException {
		String formdata = request.getParameter("formdata");
		// BimCardDto bimCardDto=new BimCardDto();
		StringBuffer sb = new StringBuffer();
		JSONObject jsonobj = new JSONObject(formdata);
		if (jsonobj.has("cardNumberArray")) {
			if (!(jsonobj.get("cardNumberArray") instanceof JSONArray)) {
				List<String> cardlist = new ArrayList<String>();
				cardlist.add((String) jsonobj.get("cardNumberArray"));
				jsonobj.put("cardNumberArray", cardlist);
			}

		}
		if (jsonobj.has("personIdArray")) {
			if (!(jsonobj.get("personIdArray") instanceof JSONArray)) {
				List<String> personidlist = new ArrayList<String>();
				personidlist.add((String) jsonobj.get("personIdArray"));
				jsonobj.put("personIdArray", personidlist);
			}

		}
		if (jsonobj.has("subSystem")) {
			List<String> usesystemlist = new ArrayList<String>();
			if (!(jsonobj.get("subSystem") instanceof JSONArray)) {
				usesystemlist.add((String) jsonobj.get("subSystem"));
				/*
				 * int count=1; for(String temp:usesystemlist){ sb.append(temp);
				 * if(count<usesystemlist.size()){ sb.append(","); count++; } }
				 */
				jsonobj.put("subSystem", usesystemlist);
			}

		}
		formdata = jsonobj.toString();
		BimCardDto bimCardDto = JacksonUtil.toObject(formdata, BimCardDto.class); // 获取表单数据
		if (bimCardDto.getSubSystem() != null) {

			int count = 1;
			for (String temp : bimCardDto.getSubSystem()) {
				sb.append(temp);
				if (count < bimCardDto.getSubSystem().size()) {
					sb.append(",");
					count++;
				}
			}
			bimCardDto.setSubSystemStr(sb.toString());
		}

		bimCardDto.setCardStatus(0);
		bimCardDto.setBimCompanyId(LoginInfo.getCompanyId());
		for (int i = 0; i < bimCardDto.getPersonIdArray().size(); i++) {
			Integer personId = Integer.valueOf(bimCardDto.getPersonIdArray().get(i));
			String cardId = bimCardDto.getCardNumberArray().get(i);
			bimCardDto.setCardId(cardId);
			bimCardDto.setPersonId(personId);
			bimCardService.insertCardInfo(bimCardDto);
		}
		AjaxUtil.ajaxWrite(true, "开卡成功", response);

		/*
		 * PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
		 * List<BimPersonDto> dtoList = null; List<Integer>
		 * personIdList=null;//personId列表 int iTotal = 0; // 获取记录总数
		 * if(!paramsDto.getSearchValue1().equals("")){ //不是第一次刷新页面,点击了批量开卡
		 * dtoList=new ArrayList<BimPersonDto>(); JSONArray jsonarray=new
		 * JSONArray(paramsDto.getSearchValue1());
		 * if(!paramsDto.getSearchValue2().equals("")){ //有搜索条件 personIdList=new
		 * ArrayList<Integer>(); for(int i=0;i<jsonarray.length();i++){
		 * JSONObject jsonobj=jsonarray.getJSONObject(i);
		 * if(jsonobj.has("personId")){ Integer
		 * personId=Integer.valueOf(jsonobj.getString("personId"));
		 * personIdList.add(personId);
		 * 
		 * } }
		 * iTotal=managerService.getRecordTotalByPerson(personIdList,paramsDto);
		 * // 获取搜索条件person的条数 if(iTotal>0){ if (paramsDto.getLength() == -1) {
		 * // 查询所有记录 paramsDto.setLength(iTotal); } for(Integer
		 * personId:personIdList){ BimPersonDto
		 * bimPersonDto=managerService.selectInfoByPersonId(personId,paramsDto);
		 * if(bimPersonDto!=null){ dtoList.add(bimPersonDto); }
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }else{ //无搜索条件 iTotal=jsonarray.length(); for(int
		 * i=0;i<jsonarray.length();i++){ JSONObject
		 * jsonobj=jsonarray.getJSONObject(i); if(jsonobj.has("personId")){
		 * Integer personId=Integer.valueOf(jsonobj.getString("personId"));
		 * BimPersonDto
		 * bimPersonDto=managerService.selectInfoByPersonId(personId,paramsDto);
		 * dtoList.add(bimPersonDto);
		 * 
		 * 
		 * }
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * DataTableDto data = new DataTableDto(); // 返回数据封装
		 * data.setDataList(dtoList); data.setDraw(paramsDto.getDraw());
		 * data.setRecordsFiltered(iTotal); data.setRecordsTotal(iTotal);
		 * AjaxUtil.ajaxWriteDataTable(data, response);
		 */

		/*
		 * String url="http://www.baidu.com";
		 * 
		 * 
		 * //1.使用默认的配置的httpclient CloseableHttpClient client =
		 * HttpClients.createDefault(); //2.使用get方法 //HttpGet httpGet = new
		 * HttpGet(url); HttpPost httppost=new HttpPost(url); InputStream
		 * inputStream = null; CloseableHttpResponse response = null;
		 * 
		 * try { //3.执行请求，获取响应
		 * 
		 * 
		 * 
		 * response = client.execute(httppost);
		 * 
		 * 
		 * //看请求是否成功，这儿打印的是http状态码
		 * System.out.println(response.getStatusLine().getStatusCode());
		 * //4.获取响应的实体内容，就是我们所要抓取得网页内容 HttpEntity entity = response.getEntity();
		 * 
		 * //5.将其打印到控制台上面
		 * 
		 * 
		 * //方法二 :使用inputStream if (entity != null) { inputStream =
		 * entity.getContent();
		 * 
		 * BufferedReader bufferedReader = new BufferedReader(new
		 * InputStreamReader(inputStream)); String line = ""; while ((line =
		 * bufferedReader.readLine()) != null) { System.out.println(line);
		 * 
		 * } }
		 * 
		 * } catch (UnsupportedOperationException | IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally { if
		 * (inputStream != null) { try { inputStream.close(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } if (response != null) { try {
		 * response.close(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 * 
		 * }
		 */

		/*
		 * HttpClient httpClient = new HttpClient(); //GetMethod getMethod = new
		 * GetMethod("http://www.baidu.com/"); GetMethod getMethod=new
		 * GetMethod(); try { int statusCode =
		 * httpClient.executeMethod(getMethod); if (statusCode !=
		 * HttpStatus.SC_OK) { System.err.println("Method failed: " +
		 * getMethod.getStatusLine()); } // 读取内容 byte[] responseBody =
		 * getMethod.getResponseBody(); // 处理内容 String html = new
		 * String(responseBody); System.out.println(html); } catch (Exception e)
		 * { System.err.println("页面无法访问"); }finally{
		 * getMethod.releaseConnection(); }
		 */

		/*
		 * HttpClient httpClient = new HttpClient(); PostMethod postMethod = new
		 * PostMethod(UrlPath);
		 * postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		 * new DefaultHttpMethodRetryHandler()); NameValuePair[] postData = new
		 * NameValuePair[2]; postData[0] = new NameValuePair("username",
		 * "xkey"); postData[1] = new NameValuePair("userpass", "********");
		 * postMethod.setRequestBody(postData); try { int statusCode =
		 * httpClient.executeMethod(postMethod); if (statusCode ==
		 * HttpStatus.SC_OK) { byte[] responseBody =
		 * postMethod.getResponseBody(); String html = new String(responseBody);
		 * System.out.println(html); } } catch (Exception e) {
		 * System.err.println("页面无法访问"); }finally{
		 * postMethod.releaseConnection(); }
		 */

	}

	@RequestMapping("/personnel/searchByDept.do")
	@ResponseBody
	private void SearchByDept() {
		int companyid = LoginInfo.getCompanyId();
		String val = request.getParameter("formData");
		List<DeptTreeDto> list = managerService.selectPTreeByDeptName(companyid, val);
		BimDeptDto bimDeptDto = null;
		for (DeptTreeDto temp : list) {
			temp.setCustom2(temp.getpId());
			bimDeptDto = bimDeptService.selectInfoByTreeId(temp.getpId(), companyid);
			if (bimDeptDto == null) {
				temp.setpName("无");
			} else {
				temp.setpName(bimDeptDto.getDeptName());
			}
		}
		AjaxUtil.ajaxWriteObject(list, response);
	}

	/*
	 * 初始化身份类型
	 */
	@RequestMapping("/personnel/selectCateType.do")
	@ResponseBody
	private void SelectCateType() {
		List<SelectDataDto> list = new ArrayList<SelectDataDto>();
		SelectDataDto selectDto = null;
		List<BimCate> bimCate = bimCateService.selectAllInfo(0);
		SelectDataDto dataDto = new SelectDataDto();
		dataDto.setId(-1);
		dataDto.setText("无");
		list.add(dataDto);
		if (bimCate.size() > 0) {
			for (BimCate temp : bimCate) {
				selectDto = new SelectDataDto();
				selectDto.setId(temp.getBimCateId());
				selectDto.setText(temp.getCateName());
				list.add(selectDto);
			}
		} /*
			 * else{ selectDto=new SelectDataDto(); selectDto.setId(-1);
			 * selectDto.setText("无"); list.add(selectDto); }
			 */
		AjaxUtil.ajaxWriteObject(list, response);
	}

	/*
	 * 删除指纹
	 */
	@RequestMapping("/personnel/deletefinger.do")
	@ResponseBody
	private void Deletefinger() {
		/*String fingerindex = request.getParameter("fingerprint1Num");
		int fingerprint1Num = Integer.valueOf(fingerindex);
		bimPersonPhotoService.updateFinger(fingerprint1Num);
		AjaxUtil.ajaxWrite(true, response);*/
		String formdata= request.getParameter("formdata");
	}

	/*
	 * 删除所有指纹
	 */
	@RequestMapping("/personnel/deleteallfinger.do")
	@ResponseBody
	private void Deleteallfinger() {
		bimPersonPhotoService.updateAllFinger();
		AjaxUtil.ajaxWrite(true, response);
	}

}
