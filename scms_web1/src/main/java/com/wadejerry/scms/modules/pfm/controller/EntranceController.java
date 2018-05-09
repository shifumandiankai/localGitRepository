package com.wadejerry.scms.modules.pfm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.Authority.AuthorityGetter;
import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.service.PfmDeviceService;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dto.ConfigServiceDto;
import com.wadejerry.scms.modules.pfm.dto.EntranceDto;
import com.wadejerry.scms.modules.pfm.dto.ParkingLotDto;
import com.wadejerry.scms.modules.pfm.dto.TimeDto;
import com.wadejerry.scms.modules.pfm.dto.ZEntranceTreeDto;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.model.PfmCarriagewayDeviceRel;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.service.PfmParkingLotService;
import com.wadejerry.scms.modules.pfm.service.EntranceService;
import com.wadejerry.scms.modules.pfm.service.PfmCarriagewayService;
import com.wadejerry.scms.modules.pfm.service.PfmTimeService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class EntranceController {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private EntranceService entranceService;
	@Autowired
	private PfmParkingLotService pfmParkingLotService;
	@Autowired
	private PfmCarriagewayService pfmCarriagewayService;
	@Autowired
	private PfmDeviceService pfmDeviceService;
	@Autowired
	private PfmTimeService pfmtimeservice;
	@Autowired
	private PfmCarriagewayDeviceRelMapper pfmCarriagewayDeviceRelMapper;
	@Autowired
	private BimUserRoleMapper bimUserRoleMapper;
	@Autowired
	private BimUserMapper userMapper;
	@Autowired
	private LogRecord logRecord;
	@Autowired
	private AuthorityGetter authorityGetter;
	
	
	@RequestMapping("/entrance/gettree.do")
	@ResponseBody
	public void GetTree(){

	
		List<UserRoleRelDto> userroles=	bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		List<String> entranceIds = new ArrayList<>();
		for(UserRoleRelDto temp:userroles){
			BimUser user = userMapper.selectByPrimaryKey(temp.getRoleId());
			if(user!=null){
				if(user.getCustom4()!=null&&user.getCustom4().length()!=0){
					List<String> list=Arrays.asList(user.getCustom4().split(","));
					for(String temp1:list){
						entranceIds.add(temp1);
					}
				}
			}

		}
		
		List<Integer> listtype=authorityGetter.parkAuth();//车场权限
		List<Integer> listentrance=authorityGetter.churuKouAuth();//出入口权限
		List<Integer> listtypeId=null;
		List<Integer> listentranceId=null;
		Integer flag=null;
		Integer enflag=null;
		if(listtype!=null){//不是企业管理员，需要权限
			if(listtype.size()>0){
				//有权限
				listtypeId=listtype;
			}else{
				//操作员但是没给权限
				flag=0;
			}
		}
		
		if(listentrance!=null){//不是企业管理员，需要权限
			if(listentrance.size()>0){
				//有权限
				listentranceId=listentrance;
			}else{
				enflag=0;
			}
		}
		
		
		int companyid = LoginInfo.getCompanyId();
		List<ZEntranceTreeDto> list=new ArrayList<ZEntranceTreeDto>();
		List<ZEntranceTreeDto> list2=new ArrayList<ZEntranceTreeDto>();
		list=entranceService.SelectParkingTreeByType(companyid,listtypeId,flag);
		//list=entranceService.SelectParkingTree(companyid);
		if(list.size() > 0){
			int k=0,a=0,b=0;
			for(ZEntranceTreeDto dto:list){
				List<PfmEntrance> entrancelist=entranceService.selectByParkidByType(Integer.valueOf(dto.getId()),LoginInfo.getCompanyId(),listtypeId,enflag,listentranceId);
				ZEntranceTreeDto treeDto3=new ZEntranceTreeDto();
				//treeDto3.setStrid("p"+dto.getId());
				treeDto3.setId("p"+dto.getId());
				treeDto3.setName(dto.getName());
				treeDto3.setpId("0");
				treeDto3.setHasPermission(true);
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
					if(entrancelist.get(i).getTimeId()==null){
						treeDto1.setTimeId(-1);
					}else{
						treeDto1.setTimeId(entrancelist.get(i).getTimeId());
					}
					
					//treeDto1.setEntranceenable(0);
					treeDto1.setFalsename(entrancelist.get(i).getEntranceName());
					treeDto1.setFalsecarriagewayenable(entrancelist.get(i).getEnabled());
					treeDto1.setEnablename("出入口"+(i+1));
					if(LoginInfo.isCompanyManager()||(!LoginInfo.isCompanyManager()&&entranceIds.contains(treeDto1.getId()))){
						treeDto1.setHasPermission(true);
					}
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
						treeDto2.setHasPermission(treeDto1.isHasPermission());
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
						treeDto2.setCustom3(CarriagewayDto.get(j).getCustom3());
						treeDto2.setCarryType(CarriagewayDto.get(j).getCarriagewayType());
						treeDto2.setAutoCharge(CarriagewayDto.get(j).getCustom1());
						treeDto2.setCarriageTimeInterval(CarriagewayDto.get(j).getCustom2());
						list2.add(treeDto2);
						if(b==CarriagewayDto.size()){
							b=0;
						}
					}
				}
			}	
		}

		//2017-08-09 zy 针对出入监控过滤未启用的出入口
		String filter = request.getParameter("filter");
		if(filter != null && filter.equals("1")) {
			//过滤后的数据集合
			List<ZEntranceTreeDto> dtoList =new ArrayList<ZEntranceTreeDto>(); 
			//启用的出入口集合
			List<String> enableIdList = new ArrayList<String>();
			for(ZEntranceTreeDto dto : list2) {
				if(dto.getEntranceid() != null && dto.getEntranceenabled() != null && dto.getEntranceenabled().equals(1)) {
					enableIdList.add(dto.getId());
				}
			}
			for(ZEntranceTreeDto dto : list2) {
				if(dto.getEntranceid() == null && dto.getAwayid() == null) {  //车场
					dtoList.add(dto);
				} else if(dto.getEntranceid() != null && enableIdList.contains(dto.getId())){ //启动的出入口
					dtoList.add(dto);
				} else if(dto.getCarrawayenabled() !=null && dto.getCarrawayenabled().equals(1) &&  enableIdList.contains(dto.getpId())) { //启用的车道
					dtoList.add(dto);
				}
			}
			list2 = dtoList;
		}
		
		AjaxUtil.ajaxWriteObject(list2, response);
	}
	/*
	 * 点击出入口节点
	 */
	@RequestMapping("/entrance/selectInfoByParentNode.do")
	@ResponseBody
	public void SelectInfoByNodeName(){
		String parentname=request.getParameter("parentname");
		String entranceid=request.getParameter("pfmEntranceId");
		//entranceid.substring(1);
		int companyid = LoginInfo.getCompanyId();
		ParkingLotDto parkingLotDto=pfmParkingLotService.selectparkInfo(parentname);
		List<PfmEntrance> list=entranceService.selectInfoByPfmEntranceId(Integer.valueOf(entranceid.substring(1)));
		//List<PfmEntrance> list=entranceService.selectInfoByParkId(parkingLotDto.getPfmParkingLotId(),companyid,Integer.valueOf(entranceid.substring(1)));
		if(list.size()!=0){
			AjaxUtil.ajaxWriteObject(list.get(0), response);
		}
		
		
	}
	/*
	 * 点击车道节点
	 */
	@RequestMapping("/entrance/selectInfoByAwayNode.do")
	@ResponseBody
	public void SelectInfoByAwayName(){
		/*String parkId=request.getParameter("parkId");//出入口名称
		String entranceid=request.getParameter("entranceid");//第几个出入口(1~4)
		String awayid=request.getParameter("awayid");
		String carriagewayId=request.getParameter("carriagewayId");
		int companyid = LoginInfo.getCompanyId();
		List<PfmEntrance> list=entranceService.selectInfoByParkId(Integer.valueOf(parkId),companyid,Integer.valueOf(entranceid));
		if(list.size()!=0){
			List<PfmCarriageway> awaylist=pfmCarriagewayService.selectInfoByEntranceId(list.get(0).getPfmEntranceId(),companyid,Integer.valueOf(awayid));
			if(awaylist.size()!=0){
				AjaxUtil.ajaxWriteObject(awaylist.get(0), response);
			}
			//AjaxUtil.ajaxWriteObject(list.get(0), response);
		}*/
		String carriagewayId=request.getParameter("carriagewayId");
		PfmCarriageway carriageway=pfmCarriagewayService.selectIfoBycarriagewayId(Integer.valueOf(carriagewayId.substring(1)));
		if(carriageway!=null){
			AjaxUtil.ajaxWriteObject(carriageway, response);
		}
		
	}
	/*
	 * 设备绑定
	 */
	@RequestMapping("/entrance/configservice.do")
	@ResponseBody
	public void configService(){
		List<DevicelrpDto> listDto =pfmDeviceService.selectAllLpr();
		SelectDataDto selectDto=null;
		List<SelectDataDto> list=new ArrayList<SelectDataDto>();
		if(listDto.size()==0){
			selectDto=new SelectDataDto();
			selectDto.setId(0);
			selectDto.setText("无设备选择");
			list.add(selectDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
		else{
			for(DevicelrpDto Devicelrpdto:listDto){
				selectDto=new SelectDataDto();
				selectDto.setId(Devicelrpdto.getPfmDeviceLprId());
				selectDto.setText(Devicelrpdto.getDeviceName());
				list.add(selectDto);
			   }
			AjaxUtil.ajaxWriteObject(list, response);
		}
	}
	
	/*
	 * 时段绑定
	 */
	@RequestMapping("/entrance/configtime.do")
	@ResponseBody
	public void configTime(){
		List<TimeDto> listDto =pfmtimeservice.configTime(LoginInfo.getCompanyId());
		SelectDataDto selectDto=null;
		SelectDataDto dataDto=new SelectDataDto();
	    List<SelectDataDto> list=new ArrayList<SelectDataDto>();
		/*if(listDto.size()==0){
			selectDto=new SelectDataDto();
			selectDto.setId(-1);
			selectDto.setText("无");
			list.add(selectDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
		else{	
			for(TimeDto TimeDto:listDto){
				selectDto=new SelectDataDto();
				selectDto.setId(TimeDto.getPfmTimeId());
				selectDto.setText(TimeDto.getTimeName());
				list.add(selectDto);
			   }
			
			AjaxUtil.ajaxWriteObject(list, response);
		}*/
		if(listDto.size()!=0){
			for(TimeDto TimeDto:listDto){
				selectDto=new SelectDataDto();
				selectDto.setId(TimeDto.getPfmTimeId());
				selectDto.setText(TimeDto.getTimeName());
				list.add(selectDto);
			   }
		}
		AjaxUtil.ajaxWriteObject(list, response);
	}
	
	/*
	 * 保存信息
	 */
	@RequestMapping("/entrance/saveInfo.do")
	@ResponseBody
	public void SaveInfo() throws JSONException{
		String formData = request.getParameter("condition");
		JSONObject jsonObject=new JSONObject(formData);
		if(jsonObject.has("deviceId")){
			if(!(jsonObject.get("deviceId") instanceof JSONArray)){
				ArrayList<String> arrayList=new ArrayList<String>();
				arrayList.add((String) jsonObject.get("deviceId"));
				jsonObject.put("deviceId", arrayList);
			}
		}else{
			if(jsonObject.has("hdeviceId")){
				if(!(jsonObject.get("hdeviceId") instanceof JSONObject)){
					ArrayList<String> arrayList=new ArrayList<String>();
					String[] a=jsonObject.getString("hdeviceId").split(",");
					for(String temp:a){
						arrayList.add(temp);
					}
					jsonObject.put("deviceId", arrayList);
				}
			}
		}
		
		formData = jsonObject.toString();
		EntranceDto entranceDto =JacksonUtil.toObject(formData, EntranceDto.class);
		String entrancestr=entranceDto.getParentId().substring(1);//出入口id
		String carriawaystr=entranceDto.getSelectId().substring(1);//车道id
		//根据出入口名字查询信息
		PfmEntrance entranceAll=entranceService.selectByPfmEntranceId(Integer.valueOf(entrancestr),LoginInfo.getCompanyId());
		EntranceDto dto=new EntranceDto();
		PfmEntrance entranceByname=null;
		PfmCarriageway carriagewayByname=null;
		if(entranceDto.getEntranceName()==null){
			//出入口不启动
			entranceByname=entranceService.selectName(entranceDto.getHentranceName(),entranceAll.getParkId(),LoginInfo.getCompanyId());
			dto.setEntranceName(entranceDto.getHentranceName());//出入口名称
			if(entranceDto.getHtimeId()!=-1){
				dto.setTimeId(entranceDto.getHtimeId());//时段id
			}
			dto.setEtenabled(0);//出入口不启用
			dto.setCustom1(entranceDto.getHtimeInterval());
			/*if(carriagewayByname!=null&&!carriagewayByname.getCarriagewayId().equals(Integer.valueOf(carriawaystr))){
				AjaxUtil.ajaxWrite(false, "车道名称已使用", response);
				return;
			}*/
		}
		else{
			//出入口启用
			dto.setEntranceName(entranceDto.getEntranceName());
			if(entranceDto.getTimeId()!=-1){
				dto.setTimeId(entranceDto.getTimeId());
			}
			entranceByname=entranceService.selectName(entranceDto.getEntranceName(),entranceAll.getParkId(),LoginInfo.getCompanyId());
			dto.setEtenabled(1);
			dto.setCustom1(entranceDto.getTimeInterval());
			
		}
		if(entranceByname!=null&&!entranceByname.getPfmEntranceId().equals(Integer.valueOf(entrancestr))){
			AjaxUtil.ajaxWrite(false, "出入口名称已使用", response);
			return;
		}
		
		if(entranceDto.getCarriagewayName()!=null){
			//车道启用
            carriagewayByname=pfmCarriagewayService.selectByName(entranceDto.getCarriagewayName(),Integer.valueOf(entrancestr),LoginInfo.getCompanyId());//同一个车场车道名称不能相同
            if(carriagewayByname!=null&&!carriagewayByname.getCarriagewayId().equals(Integer.valueOf(carriawaystr))){
    			AjaxUtil.ajaxWrite(false, "车道名称已使用", response);
    			return;
    		}
            dto.setAwayenabled(1);//车道启用
            //dto.setAutoCharge(entranceDto.getAutoCharge());
            dto.setCarriageTimeInterval(entranceDto.getCarriageTimeInterval());
            //dto.setcust
			dto.setCarriagewayType(entranceDto.getCarriagewayType());
			//dto.setDeviceId(entranceDto.getDeviceId());
			if(entranceDto.getInOut()!=null){
				dto.setInOut(1);//启用
			}
			else{
				dto.setInOut(0);//不启用
			}
			if(entranceDto.getCustom3()!=null){
				dto.setCustom3(1);//启用
			}
			else{
				dto.setCustom3(0);//不启用
			}
			if(entranceDto.getAutoReleaseFixed()!=null){
				dto.setAutoReleaseFixed(1);
			}
			else{
				dto.setAutoReleaseFixed(0);
			}
			if(entranceDto.getAutoReleaseTemp()!=null){
				dto.setAutoReleaseTemp(1);
			}
			else{
				dto.setAutoReleaseTemp(0);
			}
			if(entranceDto.getReleaseFixed()!=null){
				dto.setReleaseFixed(1);
			}
			else{
				dto.setReleaseFixed(0);
			}
			if(entranceDto.getReleaseTemp()!=null){
				dto.setReleaseTemp(1);
			}
			else{
				dto.setReleaseTemp(0);
			}
			if(entranceDto.getAutoCharge()!=null){
				dto.setAutoCharge(1);
			}
			else{
				dto.setAutoCharge(0);
			}
			dto.setCarriagewayName(entranceDto.getCarriagewayName());//车道名称
			
		}
		else{
			//车道不启用
			carriagewayByname=pfmCarriagewayService.selectByName(entranceDto.getHcarriagewayName(),Integer.valueOf(entrancestr),LoginInfo.getCompanyId());
			if(carriagewayByname!=null&&!carriagewayByname.getCarriagewayId().equals(Integer.valueOf(carriawaystr))){
    			AjaxUtil.ajaxWrite(false, "车道名称已使用", response);
    			return;
    		}
			dto.setAwayenabled(0);//车道不启用
			dto.setCarriagewayType(entranceDto.getHcarriagewayType());
			 dto.setCarriageTimeInterval(entranceDto.getHcarriageTimeInterval());
			//dto.setDeviceId(entranceDto.gethd);
			if(entranceDto.getHinOut()==1){
				dto.setInOut(1);//启用
			}
			else{
				dto.setInOut(0);//不启用
			}
			if(entranceDto.getHcustom3()==1){
				dto.setCustom3(1);//启用
			}
			else{
				dto.setCustom3(0);//不启用
			}
			if(entranceDto.getHautoReleaseFixed()==1){
				dto.setAutoReleaseFixed(1);
			}
			else{
				dto.setAutoReleaseFixed(0);
			}
			if(entranceDto.getHautoReleaseTemp()==1){
				dto.setAutoReleaseTemp(1);
			}
			else{
				dto.setAutoReleaseTemp(0);
			}
			if(entranceDto.getHreleaseFixed()==1){
				dto.setReleaseFixed(1);
			}
			else{
				dto.setReleaseFixed(0);
			}
			if(entranceDto.getHreleaseTemp()==1){
				dto.setReleaseTemp(1);
			}
			else{
				dto.setReleaseTemp(0);
			}
			if(entranceDto.getHautoCharge()!=null){
				dto.setAutoCharge(1);
			}
			else{
				dto.setAutoCharge(0);
			}
			dto.setCarriagewayName(entranceDto.getHcarriagewayName());//车道名称
		}
		
		dto.setPfmEntranceId(Integer.valueOf(entrancestr));//出入口id
		dto.setCarriagewayId(Integer.valueOf(carriawaystr));//车道id
		dto.setBimCompanyId(LoginInfo.getCompanyId());
		dto.setDeviceId(entranceDto.getDeviceId());
		Date time=new Date();
		dto.setUpdateTime(time);
		dto.setUpdateCarriawayTime(time);
		dto.setModle(0);//车卡模式
		entranceService.updateInfoAway(dto);
		if(entranceDto.getEntranceName()==null){
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_ENTRANCE, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG,entranceDto.getHentranceName());
		}else{
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_ENTRANCE, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG,entranceDto.getEntranceName());
		}
		
		AjaxUtil.ajaxWrite(true, "保存成功", response);
		return;
	}
	
	 
	/*@RequestMapping("/entrance/SelectInfoById")
	@ResponseBody
	public void SelectInfoByPfmentranceId(){
		String carrId=request.getParameter("carrid");
		PfmCarriageway carriageway=pfmCarriagewayService.selectIfoBycarriagewayId(Integer.valueOf(carrId));
		if(carriageway!=null){
			AjaxUtil.ajaxWriteObject(carriageway, response);
		}
		
	}
	@RequestMapping("/entrance/SelectEntranceInfoById")
	@ResponseBody
	public void SelectEntranceInfoById(){
		String entranceId=request.getParameter("entranceId");
		PfmEntrance entrance=entranceService.selectByPfmEntranceId(Integer.valueOf(entranceId),LoginInfo.getCompanyId());
		if(entrance!=null){
			AjaxUtil.ajaxWriteObject(entrance, response);
		}
		
	}*/
	}
