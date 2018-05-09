package com.wadejerry.scms.modules.onecard.serviceImpl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.onecard.dao.BimCateMapper;
import com.wadejerry.scms.modules.onecard.dao.BimPersonExtMapper;
import com.wadejerry.scms.modules.onecard.dao.BimPersonPhotoMapper;
import com.wadejerry.scms.modules.onecard.dao.PersonManagerMapper;
import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.DeptTreeDto;
import com.wadejerry.scms.modules.onecard.dto.PersonManagerDto;
import com.wadejerry.scms.modules.onecard.model.BimCatePerson;
import com.wadejerry.scms.modules.onecard.model.BimPersonPhoto;
import com.wadejerry.scms.modules.onecard.model.PersonManager;
import com.wadejerry.scms.modules.onecard.service.PersonManagerService;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarInfo;
import com.wadejerry.scms.modules.pfm.model.PfmCarTypeRel;
import com.wadejerry.scms.modules.pfm.model.PfmParkSpaceCarRel;
import com.wadejerry.scms.utils.Validator;
import com.wadejerry.scms.utils.date.DateUtil;

@Service("managerService")
public class PersonManegerServiceImpl implements PersonManagerService {
    @Autowired
    private PersonManagerMapper managerMapper;
    @Autowired
    private BimPersonExtMapper bimPersonExtMapper; 
    @Autowired
    private BimPersonPhotoMapper bimPersonPhotoMapper;
    @Autowired
	 private BimCateMapper bimCateMapper;
    
    
	public int getRecordTotal(PageParamsDto paramsDto,String flag,List<Integer>listauth) {
		return managerMapper.getRecordTotal(paramsDto,flag,listauth);
		 
	}
	public List<PersonManagerDto> getpersonListByPage(PageParamsDto paramsDto,String flag,List<Integer>listauth) {
		return managerMapper.getpersonListByPage(paramsDto,flag,listauth);
		
	}
	public List<DeptTreeDto> selectPTree(int companyid) {
		
		return managerMapper.selectPTree(companyid);
	}
	public BimPersonDto findByPersonCode(String personCode, int companyId) {
		
		return managerMapper.findByPersonCode(personCode,companyId);
	}
	@Transactional(rollbackFor=Exception.class)
	public void SavePersonInfo(BimPersonDto bpDto) {
		 managerMapper.SavePersonInfo(bpDto);
		 if(!bpDto.getCustom3().equals("-1")){
			 BimCatePerson bimCatePerson=new BimCatePerson();
			 bimCatePerson.setPersonId(bpDto.getPersonId());
			 bimCatePerson.setBimCateId(Integer.valueOf(bpDto.getCustom3()));
			 bimCatePerson.setCreateTime(new Date());
			 bimCateMapper.insertBimCatePerson(bimCatePerson);
			 
		 }
		 BimPersonDto Dto2=new BimPersonDto();
		 //Dto2=managerMapper.findByPersonCode(bpDto.getPersonCode(),bpDto.getBimCompanyId());
		 Dto2.setPersonId(bpDto.getPersonId());
		 Dto2.setBirthday(bpDto.getBirthday());
		 Dto2.setEnglishName(bpDto.getEnglishName());
		 Dto2.setJobDate(bpDto.getJobDate());
		 Dto2.setResignationDate(bpDto.getResignationDate());
		 Dto2.setEducational(bpDto.getEducational());
		 Dto2.setEthnic(bpDto.getEthnic());
		 managerMapper.SaveBirthday(Dto2);
		 BimPersonPhoto bimPersonPhoto=new BimPersonPhoto();
		 bimPersonPhoto.setPersonId(Dto2.getPersonId());
		 bimPersonPhoto.setPhoto1(bpDto.getImageSrc());
		 if(bpDto.getFingerprint1Num()!=null){
			 bimPersonPhoto.setFingerprint1(bpDto.getFingerprint1());
			
			 bimPersonPhoto.setFingerprint1Num(bpDto.getFingerprint1Num());
		 }
		 if(bpDto.getFingerprint2Num()!=null){
			 bimPersonPhoto.setFingerprint2(bpDto.getFingerprint2());
			 //bimPersonPhoto.setFingerprintGuid(bpDto.getFingerprintGuid());
			 bimPersonPhoto.setFingerprint2Num(bpDto.getFingerprint2Num());
		 }
		 
		 if(bpDto.getFingerprint1Num()!=null&&bpDto.getFingerprint2Num()!=null){
			 bimPersonPhoto.setFingerprintGuid(bpDto.getFingerprintGuid());
		 }
		 
		 bimPersonPhotoMapper.insertInfo(bimPersonPhoto);
	}
	public BimPersonDto getInfoById(Integer personId) {
		return managerMapper.getInfoById(personId);
		
	}
	@Transactional(rollbackFor=Exception.class)
	public void updatePersonInfo(BimPersonDto bpDto) {
		managerMapper.updatePersonInfo(bpDto);
		///managerMapper.updateInfo(bpDto);
		bimPersonExtMapper.updateInfo(bpDto);
		bimPersonPhotoMapper.updatePhoto(bpDto);
		
	}
	/*public void updateInfoByNode(Integer valueOf) {
		
		managerMapper.updateInfoByNode(valueOf);
	}*/
	public int getRecordTotalByNode(Integer valueOf) {
		return managerMapper.getRecordTotalByNode(valueOf);
		
	}
	public List<PersonManagerDto> updateInfoByNode(PageParamsDto paramsDto) {
		return managerMapper.updateInfoByNode(paramsDto);
		
	}
	
	
	public PersonManager selectInfoByNmame(String deptName) {
		
		return managerMapper.selectInfoByNmame(deptName);
	}
	
	
	public PersonManager selectInfoByTreeId(String treeid, int id) {
		return managerMapper.selectInfoByTreeId(treeid,id);
	}
	
	public PersonManager selectInfoByCode(String deptCode, String deptName, int commpanyId) {
		return managerMapper.selectInfoByCode(deptCode,deptName,commpanyId);
	}
	
	public BimPersonDto selectByCodeAndName(String personCode, String personName) {
		
		return managerMapper.selectByCodeAndName(personCode,personName);
	}
	public void updateDeptInfo(Integer personId, Integer valueOf) {
		managerMapper.updateDeptInfo(personId,valueOf);
		
	}
	@Transactional
	public void deletePersonById(Integer personId) {
		bimCateMapper.deleteBimCatePerson(personId);
		bimPersonExtMapper.deletePersonById(personId);
		bimPersonPhotoMapper.updateInfo(personId);
		managerMapper.deletePersonById(personId);
		
	}
	public List<BimPersonDto> selectInfoByDeptId(Integer valueOf) {
		
		return managerMapper.selectInfoByDeptId(valueOf);
	}
	@Override
	public List<Map<String, Object>> download(int commpanyId) {
		return	managerMapper.download(commpanyId);
		 
	}
	@Override
	public OperateResult validateAndInsert(List<Map<String, String>> list) {
		//基本验证
				OperateResult result = new OperateResult();
				int index=1;
				//标识数据是否有错误
				boolean b = true;
				String error="";
				for(Map<String,String> map:list){
					//System.out.println(map.get("出生日期").toString());
					if(index==1){
						if(map.get("人员编号*")==null//编号是否相同
						||map.get("人员姓名*")==null
						||map.get("部门编号*")==null//编号是否存在
						||map.get("性别")==null
						||map.get("证件类型")==null//类型是否存在
						||map.get("证件号码")==null
						||map.get("出生日期")==null
						||map.get("联系电话")==null
						||map.get("拼音代码")==null
						||map.get("联系地址")==null
						||map.get("英文名")==null
						||map.get("E-MAIL")==null
						||map.get("到职日期")==null
						||map.get("离职日期")==null
						||map.get("学历")==null
						||map.get("民族")==null
								){
							throw new RuntimeException("请使用模板！您的模板存在列名不正确的情况，请仔细核对！");
						}
					}
					index+=1;
					if(!Validator.isEmail(map.get("E-MAIL").trim()))//车牌号

					{
						b=false;
						error+="第"+index+"行E-MAIL格式错误；\\n";
					}
					if(!map.get("联系电话").trim().equals("")&&!Validator.isMobile(map.get("联系电话").trim())){//手机号

						b=false;
						error+="第"+index+"行手机号格式错误；\\n";

					}
					if(!Validator.isName(map.get("人员姓名*").trim())){//性别
						b=false;
						error+="第"+index+"行姓名格式错误；\\n";
					}
					if(!(map.get("性别").trim().equals("男")||map.get("性别").trim().equals("女"))){//性别
						b=false;
						error+="第"+index+"行性别格式错误，请输入男或女；\\n";
					}
					
					if(!Validator.isDate(map.get("出生日期").trim()))//出生时间
					{
						b=false;
						error+="第"+index+"行启用日期格式错误，格式应如2008-08-08；\\n";
					}
					if(!Validator.isDate(map.get("到职日期").trim()))//到职时间
					{
						b=false;
						error+="第"+index+"行到职日期格式错误，格式应如2008-08-08；\\n";
					}
					if(!Validator.isDate(map.get("离职日期").trim()))//离职时间
					{
						b=false;
						error+="第"+index+"行离职日期格式错误，格式应如2008-08-08；\\n";
					}
					
					if(managerMapper.selectCountByDeptId(Integer.valueOf(map.get("部门编号*").trim()),LoginInfo.getCompanyId())==0)
					{
						b=false;
						error+="第"+index+"行请确认是否存在此部门编号；\\n";
					}
					if(managerMapper.selectCountByCertType(map.get("证件类型").trim(),LoginInfo.getCompanyId())==0)
					{
						b=false;
						error+="第"+index+"行请确认是否存在此证件类型；\\n";
					}
				}
				if(!b){
					result.setResult(false);
					result.setMsg(error);
					return result;
				}
				else{
					for(Map<String,String> map:list){
						BimPersonDto bimpersonDto=new BimPersonDto();
						bimpersonDto.setBimCompanyId(LoginInfo.getCompanyId());
						bimpersonDto.setPersonName(map.get("人员姓名*").trim());
						if(managerMapper.selectCountByPersonCode(map.get("人员编号*").trim(),LoginInfo.getCompanyId())>0){
							throw new RuntimeException("已存在人员编号为 "+map.get("人员编号*").trim()+" 的人员信息！");
						}
						bimpersonDto.setPersonCode(map.get("人员编号*").trim());
						bimpersonDto.setBimDeptId(Integer.valueOf(map.get("部门编号*").trim()));
						//carInfo.setAddres(map.get("地址").trim());
						//carInfo.setCertId(map.get("证件ID").trim());
						if(map.get("性别").trim().equals("男")){
							bimpersonDto.setSex(0);
						}
						else{
							bimpersonDto.setSex(1);
						}
						bimpersonDto.setCerttype(map.get("证件类型").trim());
						bimpersonDto.setCertId(map.get("证件号码").trim());
						bimpersonDto.setPhone(map.get("联系电话").trim());
						bimpersonDto.setPinyin(map.get("拼音代码").trim());
						bimpersonDto.setAddress(map.get("联系地址").trim());
						bimpersonDto.setEnglishName(map.get("英文名").trim());
						bimpersonDto.setMail(map.get("E-MAIL").trim());
						bimpersonDto.setEducational(map.get("学历").trim());
						//bimpersonDto.setEducational(map.get("民族").trim());
						bimpersonDto.setEthnic(map.get("民族").trim());
						try {
							bimpersonDto.setBirthday(DateUtil.YMDsdf.parse(map.get("出生日期").trim()));
							bimpersonDto.setJobDate(DateUtil.YMDsdf.parse(map.get("到职日期").trim()));
							bimpersonDto.setResignationDate(DateUtil.YMDsdf.parse(map.get("离职日期").trim()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SavePersonInfo(bimpersonDto);
						}
						
					}
					result.setResult(true);
					return result;
				}
	@Override
	public BimPersonDto selectInfoByPersonId(Integer personId,PageParamsDto paramsDto) {
		
		return managerMapper.selectInfoByPersonId(personId,paramsDto);
	}
	@Override
	public int getRecordTotalByPerson(List<Integer> personIdList, PageParamsDto paramsDto) {
		
		return managerMapper.getRecordTotalByPerson(personIdList,paramsDto);
	}
	@Override
	public List<DeptTreeDto> selectPTreeByDeptName(int companyid, String val) {
		
		return managerMapper.selectPTreeByDeptName(companyid,val);
	}
	
	}
	


