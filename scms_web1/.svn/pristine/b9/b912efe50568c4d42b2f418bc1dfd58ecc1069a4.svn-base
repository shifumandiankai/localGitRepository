package com.wadejerry.scms.modules.auth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.auth.dao.BimRoleAuthMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.RoleDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.auth.service.BimUserRoleRelService;
import com.wadejerry.scms.modules.auth.service.BimUserService;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dto.ZEntranceTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.model.PfmCarriagewayDeviceRel;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.service.EntranceService;
import com.wadejerry.scms.modules.pfm.service.PfmCarriagewayService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class BimRoleControler {

	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private BimUserService bimUserService; // 角色Service
     @Autowired
     private BimUserRoleRelService bimUserRoleService;
     @Autowired
     private BimRoleAuthMapper bimRoleAuthMapper;
     @Autowired
     private EntranceService entranceService;
     @Autowired
     private PfmCarriagewayService pfmCarriagewayService;
     @Autowired
     private PfmCarriagewayDeviceRelMapper pfmCarriagewayDeviceRelMapper;
     @Autowired
     private BimUserMapper userMapper;
     @Autowired
     private LogRecord logRecord;
	/**
	* 分页获取角色信息 
	* @author zhanying 2016年9月22日 下午7:33:48
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/role/getRoleInfo.do")
	@ResponseBody
	private void GetRoleInfoByPage() {
		PageParamsDto paramsDto = new PageParamsDto(request);
		paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		int iTotal = 0; //获取记录总数
		List<RoleDto> dtoList = null;
		if(!SecurityUtils.getSubject().isPermitted("RoleSee")){
		}else{
			iTotal = bimUserService.getRoleRecordTotal(paramsDto); //获取记录总数
		if (iTotal > 0) {
			if(paramsDto.getLength() == -1){ //查询所有记录
				paramsDto.setLength(iTotal);
			}
			dtoList= bimUserService.getRoleListByPage(paramsDto); //获取分页记录
		}
		}
		DataTableDto data = new DataTableDto();  //返回数据封装 
		data.setDataList(dtoList);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}

	/**
	*  保存角色信息
	* @author zhanying 2016年9月22日 下午7:34:31
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/role/saveRole.do")
	@ResponseBody
	private void SaveRoleInfo() {
		String formData = request.getParameter("condition");
	    RoleDto roleDto  = JacksonUtil.toObject(formData, RoleDto.class);  //获取表单数据
    	//TODO 验证名称是否重复
		BimUser user=  null;
		BimUser role =  bimUserService.findUserByLoginName(roleDto.getUserName(), ConstUser.CONST_ROLE_TYPE);
	    if(roleDto.getBimUserId() == null){  //新增角色信息
	    	if(role != null) {
	    		AjaxUtil.ajaxWrite(false,"角色名称已使用", response);
	    		return;
	    	}
	    	user = new BimUser();
	    	user.setUserName(roleDto.getUserName());
	    	user.setNote(roleDto.getNote());
	    	user.setUserType(ConstUser.CONST_ROLE_TYPE);
	    	user.setBimCompanyId(LoginInfo.getCompanyId());
	    	user.setCreateTime(new Date());
	    	user.setUpdateTime(new Date());
	    	user.setInId(LoginInfo.getLoginId());
	    	bimUserService.insertUser(user); //添加角色信息
	    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG, user.getUserName());
	    } else {	//修改角色信息
	    	user =bimUserService.getUserById(roleDto.getBimUserId()); //获取用户信息
	    	if(role != null && user.getBimUserId().intValue() != role.getBimUserId().intValue() ){
	    		AjaxUtil.ajaxWrite(false,"角色名称已使用", response);
	    		return;
	    	}
	    	if(user != null)
	    	{
	    		user.setUserName(roleDto.getUserName());
		    	user.setNote(roleDto.getNote());
		       	user.setUpdateTime(new Date());
	    		bimUserService.updateUser(user); //更新角色信息
		    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG, user.getUserName());

	    	}
	    }    
		AjaxUtil.ajaxWrite(true,"", response);
	}
	
	/**
	* 删除角色信息
	* @author zhanying 2016年9月22日 下午7:34:10
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/role/delRole.do")
	@ResponseBody
	private void DelRoleInfo(){
		String jsonArrayStr = request.getParameter("condition");
		try {
			List<RoleDto> delDtoList = JacksonUtil.json2list(jsonArrayStr, RoleDto.class);
			for(RoleDto dto:delDtoList){
				if(bimUserRoleService.countByRoleId(dto.getBimUserId())>0){
					throw new Exception("尚有用户关联角色"+dto.getUserName());
				}
			}
			for(RoleDto roleDto : delDtoList){
				bimUserService.deleteUserById(roleDto.getBimUserId());
				bimRoleAuthMapper.deleteByRoleId(roleDto.getBimUserId());
		    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, roleDto.getUserName());

			}
		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false,e.getMessage(), response);	//返回失败
			return;
		}
		AjaxUtil.ajaxWrite(true,"", response);	//返回成功
	}
	
	/**
	* 获取角色下拉框数据
	* @author zhanying 2016年10月9日 下午1:35:41
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/role/GetRolesSelectData.do")
	@ResponseBody
	private void GetRolesSelectData(){	
		PageParamsDto paramsDto = new PageParamsDto(request);
		paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		paramsDto.setSearchValue0("");
		paramsDto.setStart(0);
		
		int iTotal = bimUserService.getRoleRecordTotal(paramsDto); //获取记录总数
		List<RoleDto> dtoList = new ArrayList<RoleDto>();
		if (iTotal > 0) {
			paramsDto.setLength(iTotal);
			dtoList= bimUserService.getRoleListByPage(paramsDto); //获取角色数据
		}
		List<SelectDataDto> list = new ArrayList<SelectDataDto>(); 
		SelectDataDto selectDataDto = null;
		for(RoleDto roleDto : dtoList){	//封装Select2 插件数据类型
			selectDataDto = new  SelectDataDto();
			selectDataDto.setId(roleDto.getBimUserId());
			selectDataDto.setText(roleDto.getUserName());
			list.add(selectDataDto);
		}
		AjaxUtil.ajaxWriteObject(list, response);
	}
	@RequestMapping("/role/permissionconfig.do")
	@ResponseBody
	private void PermissionConfig(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		if(roleId!=null){
		AjaxUtil.ajaxWriteObject(bimUserRoleService.AllAuthorizations(Integer.parseInt(roleId)), response);
		}
		else{
			AjaxUtil.ajaxWriteObject(false, response);
		}
	}
	
	@RequestMapping("/role/permissionsave.do")
	@ResponseBody
	private void PermissionSave(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		String authids = request.getParameter("authids");
		if(roleId!=null&&authids!=null){
			List<Integer> auths = new ArrayList<>();
			if(!"".equals(authids)){
				for(String s:authids.split(",")){
					auths.add(Integer.parseInt(s));
				}
			}
			AjaxUtil.ajaxWrite(bimUserRoleService.SaveAutho(Integer.parseInt(roleId), auths), response);
		}
		else{
			AjaxUtil.ajaxWrite(false, "", response);
		}
    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.LOG_TYPE_CONFIG, "配置模块权限");

	}
	@RequestMapping("/role/selectcartype.do")
	@ResponseBody
	public void selectCarType(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		AjaxUtil.ajaxWriteObject(bimUserRoleService.selectCarTypeAuth(Integer.parseInt(roleId)), response);
	}
	@RequestMapping("/role/cartypepermissionsave.do")
	@ResponseBody
	public void saveCarTypePermission(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		String authids = request.getParameter("authids");
		AjaxUtil.ajaxWrite(bimUserRoleService.saveCarTypeAuth(Integer.parseInt(roleId), authids),response);
    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.SYSTEM_CONFIG, "配置车辆权限");

	}
	@RequestMapping("/role/churukoupermissionsave.do")
	@ResponseBody
	public void saveChuRukouPermission(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		String authids = request.getParameter("authids");
		AjaxUtil.ajaxWrite(bimUserRoleService.saveChuRukouAuth(Integer.parseInt(roleId), authids),response);
    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.SYSTEM_CONFIG, "配置出入口权限");

	}
	@RequestMapping("/role/getchurukou.do")
	@ResponseBody
	public void getChurukouAuthTree(){
		String roleId = request.getParameter("roleid");
		BimUser user=userMapper.selectByPrimaryKey(Integer.parseInt(roleId));
		String[] types=new String[0];
		if(user!=null){//Custom4保存的是出入口的"e+id"
			if(user.getCustom4()!=null&&user.getCustom4().length()!=0){
				types= user.getCustom4().split(",");
			}
		}
		List<String> temps = new ArrayList<>();
		for(String type:types){
			temps.add(type);
		}
		int companyid = LoginInfo.getCompanyId();
		List<ZEntranceTreeDto> list=new ArrayList<ZEntranceTreeDto>();
		List<ZEntranceTreeDto> list2=new ArrayList<ZEntranceTreeDto>();
		list=entranceService.SelectParkingTree(companyid);
		int listsize=list.size();
		if(listsize==0){
			 AjaxUtil.ajaxWriteObject(list2, response);
			 return ;
		}
		else{
			int k=0,a=0,b=0;
			for(ZEntranceTreeDto dto:list){
				List<PfmEntrance> entrancelist=entranceService.selectByParkid(Integer.valueOf(dto.getId()),LoginInfo.getCompanyId());
				ZEntranceTreeDto treeDto3=new ZEntranceTreeDto();
				//treeDto3.setStrid("p"+dto.getId());
				treeDto3.setId("p"+dto.getId());
				treeDto3.setName(dto.getName());
				treeDto3.setpId("0");
				treeDto3.setNocheck(true);
				list2.add(treeDto3);
				for(int i=0;i<entrancelist.size();i++){
					List<PfmCarriageway> CarriagewayDto=pfmCarriagewayService.selectAllInfoByEntranceId(entrancelist.get(i).getPfmEntranceId(),LoginInfo.getCompanyId());
					a++;
					ZEntranceTreeDto treeDto1=new ZEntranceTreeDto();          
					//treeDto1.setStrid("e"+entrancelist.get(i).getPfmEntranceId());
					treeDto1.setId("e"+entrancelist.get(i).getPfmEntranceId());
					treeDto1.setName(entrancelist.get(i).getEntranceName());
					treeDto1.setpId(treeDto3.getId()); 
					treeDto1.setEntranceenabled(entrancelist.get(i).getEnabled());
					treeDto1.setEntranceid(a);//出入口编号编号好
					treeDto1.setTimeInterval(entrancelist.get(i).getCustom1());//出入口间隔
					if(temps.contains(treeDto1.getId())){
						treeDto1.setChecked(true);
					}
					if(!LoginInfo.isCompanyManager()){
						treeDto1.setChkDisabled(true);
					}
					if(entrancelist.get(i).getTimeId()==null){
						treeDto1.setTimeId(-1);
					}else{
						treeDto1.setTimeId(entrancelist.get(i).getTimeId());
					}
					
					//treeDto1.setEntranceenable(0);
					treeDto1.setFalsename(entrancelist.get(i).getEntranceName());
					treeDto1.setFalsecarriagewayenable(entrancelist.get(i).getEnabled());
					treeDto1.setEnablename("出入口"+(i+1));
					list2.add(treeDto1);
					
					if(a==entrancelist.size()){
						a=0;	
					}
					
					for(int j=0;j<CarriagewayDto.size();j++){
						b++;
						List<PfmCarriagewayDeviceRel> carriageways=pfmCarriagewayDeviceRelMapper.selectByCaId(CarriagewayDto.get(j).getCarriagewayId());
						ZEntranceTreeDto treeDto2=new ZEntranceTreeDto();
						if(carriageways.size()!=0){
							List<Integer> list1=new ArrayList<Integer>();
							for(PfmCarriagewayDeviceRel temp:carriageways){
								list1.add(temp.getDeviceId());
							}
							treeDto2.setDeviceId(list1);
						}
						//treeDto2.setStrid("c"+CarriagewayDto.get(j).getCarriagewayId());
						treeDto2.setId("c"+CarriagewayDto.get(j).getCarriagewayId());
						treeDto2.setName(CarriagewayDto.get(j).getCarriagewayName());
						treeDto2.setpId(treeDto1.getId());
						treeDto2.setAwayid(b);//车道编号
						//treeDto2.setCarriagewayenable(0);
						treeDto2.setFalsename(CarriagewayDto.get(j).getCarriagewayName());
						treeDto2.setFalsecarriagewayenable(CarriagewayDto.get(j).getEnabled());
						treeDto2.setEnablename("车道"+(j+1));
						treeDto2.setEntranceenabled(treeDto1.getEntranceenabled());
						treeDto2.setTimeId(treeDto1.getTimeId());
						treeDto2.setCarrawayenabled(CarriagewayDto.get(j).getEnabled());
						treeDto2.setAutoFixed(CarriagewayDto.get(j).getAutoReleaseFixed());
						treeDto2.setAutoTemp(CarriagewayDto.get(j).getAutoReleaseTemp());
						treeDto2.setReleaseFixed(CarriagewayDto.get(j).getReleaseFixed());
						treeDto2.setReleaseTemp(CarriagewayDto.get(j).getReleaseTemp());
						treeDto2.setInOut(CarriagewayDto.get(j).getInOut());
						treeDto2.setCarryType(CarriagewayDto.get(j).getCarriagewayType());
						treeDto2.setAutoCharge(CarriagewayDto.get(j).getCustom1());
						treeDto2.setCarriageTimeInterval(CarriagewayDto.get(j).getCustom2());
						treeDto2.setNocheck(true);
						list2.add(treeDto2);
						if(b==CarriagewayDto.size()){
							b=0;
						}
					}
				}
			}
			
			
			AjaxUtil.ajaxWriteObject(list2, response);
		
	}
}
	@RequestMapping("/role/area/permissionconfig.do")
	@ResponseBody
	private void areaPermissionConfig(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		if(roleId!=null){
		AjaxUtil.ajaxWriteObject(bimUserRoleService.getAreaAuthorizations(Integer.parseInt(roleId)), response);
		}
		else{
			AjaxUtil.ajaxWriteObject(false, response);
		}
	}
	
	@RequestMapping("/role/area/permissionsave.do")
	@ResponseBody
	private void areaPermissionSave(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		String authids = request.getParameter("authids");
		if(roleId!=null&&authids!=null){
			List<Integer> auths = new ArrayList<>();
			if(!"".equals(authids)){
				for(String s:authids.split(",")){
					auths.add(Integer.parseInt(s));
				}
			}
			AjaxUtil.ajaxWrite(bimUserRoleService.saveAreaAutho(Integer.parseInt(roleId), auths), response);
		}
		else{
			AjaxUtil.ajaxWrite(false, "", response);
		}
    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.LOG_TYPE_CONFIG, "配置门禁区域模块权限");

	}
	@RequestMapping("/role/dept/permissionconfig.do")
	@ResponseBody
	private void deptPermissionConfig(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		if(roleId!=null){
		AjaxUtil.ajaxWriteObject(bimUserRoleService.getDeptAuthorizations(Integer.parseInt(roleId)), response);
		}
		else{
			AjaxUtil.ajaxWriteObject(false, response);
		}
	}
	
	@RequestMapping("/role/dept/permissionsave.do")
	@ResponseBody
	private void deptPermissionSave(HttpServletRequest request,HttpServletResponse response){	
		String roleId = request.getParameter("roleid");
		String authids = request.getParameter("authids");
		if(roleId!=null&&authids!=null){
			List<Integer> auths = new ArrayList<>();
			if(!"".equals(authids)){
				for(String s:authids.split(",")){
					auths.add(Integer.parseInt(s));
				}
			}
			AjaxUtil.ajaxWrite(bimUserRoleService.saveDeptAutho(Integer.parseInt(roleId), auths), response);
		}
		else{
			AjaxUtil.ajaxWrite(false, "", response);
		}
    	logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_ROLE, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.LOG_TYPE_CONFIG, "配置部门模块权限");

	}
}
