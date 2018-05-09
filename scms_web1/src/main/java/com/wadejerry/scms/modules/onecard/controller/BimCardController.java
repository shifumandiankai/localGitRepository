package com.wadejerry.scms.modules.onecard.controller;

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

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.onecard.dto.BimCardDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonPhotoDto;
import com.wadejerry.scms.modules.onecard.model.BimCard;
import com.wadejerry.scms.modules.onecard.model.BimCate;
import com.wadejerry.scms.modules.onecard.model.BimPersonPhoto;
import com.wadejerry.scms.modules.onecard.service.BimCardService;
import com.wadejerry.scms.modules.onecard.service.BimCateService;
import com.wadejerry.scms.modules.onecard.service.BimPersonPhotoService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class BimCardController {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private BimCardService bimCardService;
	@Autowired
	private BimCateService bimCateService;
	@Autowired
	private BimPersonPhotoService bimPersonPhotoService;
	
	@RequestMapping("/Card/cardInfo.do")
	@ResponseBody
	private void GetCardInfoByPage() {
		String personId=request.getParameter("personId");
		List<BimCard> list=bimCardService.selectCardInfoByPage(Integer.valueOf(personId));
		
		for(BimCard temp:list){
			if(temp.getUseSystem()!=null){
				StringBuffer sb=new StringBuffer();
				String[] arr=temp.getUseSystem().split(",");
				List syslist=Arrays.asList(arr);
	            for(int i=0;i<syslist.size();i++){
	            	String str1=(String) syslist.get(i);
	            	if(i>0&&i<syslist.size()){
	            		sb.append(",");
	            	}
	            	String a=str1.trim();
	            	switch(a){
					case "0":sb.append("门禁");
					break;
					case "1":sb.append("梯控");
					break;
					case "2":sb.append("考勤");
					break;
					case "3":sb.append("停车场");
					break;
					case "4":sb.append("消费");
					break;
					}
	            		 
				}
	            temp.setUseSystem(sb.toString());
				
				
				
			}
			
			
		}
		AjaxUtil.ajaxWriteObject(list, response);
	}
	
	/*
	 * 开卡
	 */
	@RequestMapping("/Card/bindCardInfo.do")
	@ResponseBody
	private void BindCardInfo()  {
		OperateResult operateResult=new OperateResult();
		BimCardDto bimCardDto=new BimCardDto();
		StringBuffer sb=new StringBuffer(); 
		String formdata=request.getParameter("formdata");
		JSONObject jsonobj;
		try {
			jsonobj = new JSONObject(formdata);
			if(jsonobj.has("subSystem")){
				List<String> usesystemlist=new ArrayList<String>();
				if(!(jsonobj.get("subSystem") instanceof JSONArray)){
					usesystemlist.add((String)jsonobj.get("subSystem").toString());
					jsonobj.put("subSystem", usesystemlist);
				}
			}
			formdata=jsonobj.toString();
			BimCardDto bimCarddata = JacksonUtil.toObject(formdata, BimCardDto.class);
			if(bimCarddata.getSubSystem()!=null){
				
				int count=1;
				for(String temp:bimCarddata.getSubSystem()){
					sb.append(temp);
					if(count<bimCarddata.getSubSystem().size()){
						sb.append(",");
						count++;
					}
				}
				bimCardDto.setSubSystemStr(sb.toString().trim());
		}
			bimCardDto.setCardStatus(0);
			bimCardDto.setBimCompanyId(LoginInfo.getCompanyId());
			bimCardDto.setCardId(bimCarddata.getCardId());
	    	bimCardDto.setPersonId(bimCarddata.getPersonId());
	    	bimCardService.insertCardInfo(bimCardDto);
	    
					StringBuffer sbstr=new StringBuffer();
					String[] arr=bimCardDto.getSubSystemStr().split(",");
					List syslist=Arrays.asList(arr);
		            for(int i=0;i<syslist.size();i++){
		            	String str1=(String) syslist.get(i);
		            	if(i>0&&i<syslist.size()){
		            		sbstr.append(",");
		            	}
		            	String a=str1.trim();
		            	switch(a){
						case "0":sbstr.append("门禁");
						break;
						case "1":sbstr.append("梯控");
						break;
						case "2":sbstr.append("考勤");
						break;
						case "3":sbstr.append("停车场");
						break;
						case "4":sbstr.append("消费");
						break;
						}
		            		 
					}
		            bimCardDto.setSubSystemStr(sbstr.toString());
	    	
	    	operateResult.setResult(true);
			operateResult.setMsg("开卡成功");
			AjaxUtil.ajaxWriteObject(operateResult,bimCardDto,response);
		} catch (JSONException e) {
			
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("开卡失败");
			
		}
		
	}
	
	/*
	 * 换卡
	 */
	@RequestMapping("/Card/updateswapCardInfo.do")
	@ResponseBody
	private void UpdateCardInfo() {
		OperateResult operateResult=new OperateResult();
		try{
			String cardId=request.getParameter("cardId");
			String bimCardId=request.getParameter("bimCardId");
			bimCardService.updateCardInfo(Integer.valueOf(bimCardId),cardId);
			operateResult.setResult(true);
			operateResult.setMsg("换卡成功");
		}catch (Exception e) {
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("换卡失败");
		}
		AjaxUtil.ajaxWrite(operateResult,response);
		
	}
	/*
	 * 挂失
	 */
	@RequestMapping("/Card/updatelossCardInfo.do")
	@ResponseBody
	private void UpdatelossCardInfo() {
		OperateResult operateResult=new OperateResult();
		Integer type=0;
		try{
			String bimCardId=request.getParameter("bimCardId");
			bimCardService.updateStatusCardInfo(Integer.valueOf(bimCardId),type);
			operateResult.setResult(true);
			operateResult.setMsg("挂失成功");
		}catch (Exception e) {
			
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("挂失失败");
		}
		AjaxUtil.ajaxWrite(operateResult,response);
		
	}
	
	/*
	 * 解挂
	 */
	@RequestMapping("/Card/updateunlossCardInfo.do")
	@ResponseBody
	private void UpdateunlossCardInfo() {
		OperateResult operateResult=new OperateResult();
		Integer type=1;
		try{
			String bimCardId=request.getParameter("bimCardId");
			bimCardService.updateStatusCardInfo(Integer.valueOf(bimCardId),type);
			operateResult.setResult(true);
			operateResult.setMsg("解挂成功");
		}catch (Exception e) {
			
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("解挂失败");
		}
		AjaxUtil.ajaxWrite(operateResult,response);
		
	}
	
	/*
	 * 退卡
	 */
	@RequestMapping("/Card/returnCardInfo.do")
	@ResponseBody
	private void ReturnCardInfo() {
		OperateResult operateResult=new OperateResult();
		//Integer type=1;
		List<BimCard> list=null;
		try{
			String bimCardId=request.getParameter("bimCardId");
			String personId=request.getParameter("personId");
			bimCardService.deleteCardInfo(Integer.valueOf(bimCardId));
			list=bimCardService.selectCardInfoByPage(Integer.valueOf(personId));
			operateResult.setResult(true);
			operateResult.setMsg("退卡成功");
			
		}catch (Exception e) {
			
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("退卡失败");
		}
		AjaxUtil.ajaxWriteObject(operateResult,list, response);
		
	}
	
	/*
	 * 身份管理
	 */
	@RequestMapping("/personnel/bimcate.do")
	@ResponseBody
	private void BimCateInfo() {
		List<BimCate> bimCate=bimCateService.selectAllInfo(0);
		AjaxUtil.ajaxWriteObject(bimCate,response);
	}
	
	
	/*
	 * 添加身份管理		
	 */
	@RequestMapping("/personnel/addcate.do")
	@ResponseBody
	private void AddCate() {
		OperateResult operateResult=new OperateResult();
		BimCate bimCate=new BimCate();
		try {
			int count=bimCateService.selectRandom();//随机出来的身份信息custom1是0
			bimCate.setCateName("新增身份"+(count+1));
			bimCate.setCateType(0);
			bimCate.setNote("无");
			bimCate.setCustom1(0);
			bimCate.setCreateTime(new Date());
			bimCateService.insertCateInfo(bimCate);
			operateResult.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			operateResult.setResult(false);
			operateResult.setMsg("新增身份失败");
		}
		
		AjaxUtil.ajaxWriteObject(operateResult,bimCate,response);
		
		
		
	}
	/*
	 * 修改身份管理信息
	 */
	@RequestMapping("/personnel/updatecate.do")
	@ResponseBody
	private void UpdateCate() {
		OperateResult operateResult=new OperateResult();
		try {
			String bimCateId=request.getParameter("bimCateId");
			String cateName=request.getParameter("cateName");
			String note=request.getParameter("note");
			BimCate bimCate=bimCateService.selectByCateName(cateName);
			int b=Integer.valueOf(bimCateId);
			if(bimCate!=null){
			//if(bimCate!=null&&bimCate.getBimCateId().equals(Integer.valueOf(bimCateId))){
				int a=bimCate.getBimCateId();
				if(a!=b){
					operateResult.setMsg("身份类型名称不能重复");
					operateResult.setResult(false);
					AjaxUtil.ajaxWrite(operateResult,response);
					return ;
				}
				
			}
			bimCateService.updateCateInfo(Integer.valueOf(bimCateId),cateName,note,new Date());
			operateResult.setResult(true);
			operateResult.setMsg("修改成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("修改失败");
		}
		
		AjaxUtil.ajaxWrite(operateResult,response);
		
	}
	
	/*
	 * 删除身份信息 custom1=0 代表删除的信息
	 */
	@RequestMapping("/personnel/delcate.do")
	@ResponseBody
	private void DeleteCate() {
		OperateResult operateResult=new OperateResult();
		List<BimCate> bimCate=null;
		try {
			String bimCateId=request.getParameter("bimCateId");
			//List<Integer> list=bimCateService.selectByPersonId(Integer.valueOf(bimCateId));
			//bimCateService.deleteInfo(Integer.valueOf(bimCateId),list);
			bimCateService.deleteByCateId(Integer.valueOf(bimCateId));
			bimCate=bimCateService.selectAllInfo(0);
			operateResult.setResult(true);
			operateResult.setMsg("删除身份类型成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("删除身份类型失败");
		}
		AjaxUtil.ajaxWriteObject(operateResult,bimCate,response);
		
	}
/*
 * 指纹授权显示	
 */
	@RequestMapping("/personnel/fingersq.do")
	@ResponseBody
	private void SelectFingerInfo(){
		String personId=request.getParameter("personId");
		BimPersonPhotoDto bimPersonPhoto=bimPersonPhotoService.selectFingerSq(Integer.valueOf(personId));
		if(bimPersonPhoto!=null&&bimPersonPhoto.getFingerprint1Num()!=null){
			if(bimPersonPhoto.getFingerprint1Num()!=null&&bimPersonPhoto.getFingerprint1Num()<5){
				//左手
				int flag=bimPersonPhoto.getFingerprint1Num();
				
				switch (flag) {
				case 0:
					bimPersonPhoto.setFingerprint1("左手大拇指");
					break;
				case 1:
					bimPersonPhoto.setFingerprint1("左手食指");
					break;
				case 2:
					bimPersonPhoto.setFingerprint1("左手中指");
					break;
				case 3:
					bimPersonPhoto.setFingerprint1("左手无名指");
					break;
				case 4:
					bimPersonPhoto.setFingerprint1("左手小拇指");
					break;
				default:
					break;
				}
			}else{
				//右手
				int flag=bimPersonPhoto.getFingerprint1Num();
				switch (flag) {
				case 5:
					bimPersonPhoto.setFingerprint1("右手大拇指");
					break;
				case 6:
					bimPersonPhoto.setFingerprint1("右手食指");
					break;
				case 7:
					bimPersonPhoto.setFingerprint1("右手中指");
					break;
				case 8:
					bimPersonPhoto.setFingerprint1("右手无名指");
					break;
				case 9:
					bimPersonPhoto.setFingerprint1("右手小拇指");
					break;
				default:
					break;
				}	
			}
		}
		
		if(bimPersonPhoto!=null&&bimPersonPhoto.getFingerprint2Num()!=null){
			if(bimPersonPhoto.getFingerprint2Num()<5){
				//左手
				int flag=bimPersonPhoto.getFingerprint2Num();
				switch (flag) {
				case 0:
					bimPersonPhoto.setFingerprint2("左手小拇指");
					break;
				case 1:
					bimPersonPhoto.setFingerprint2("左手无名指指");
					break;
				case 2:
					bimPersonPhoto.setFingerprint2("左手中指");
					break;
				case 3:
					bimPersonPhoto.setFingerprint2("左手食指");
					break;
				case 4:
					bimPersonPhoto.setFingerprint2("左手大拇指");
					break;
				default:
					break;
				}
			}else{
				//右手
				int flag=bimPersonPhoto.getFingerprint2Num();
				switch (flag) {
				case 5:
					bimPersonPhoto.setFingerprint2("右手大拇指");
					break;
				case 6:
					bimPersonPhoto.setFingerprint2("右手食指");
					break;
				case 7:
					bimPersonPhoto.setFingerprint2("右手中指");
					break;
				case 8:
					bimPersonPhoto.setFingerprint2("右手无名指");
					break;
				case 9:
					bimPersonPhoto.setFingerprint2("右手小拇指");
					break;
				default:
					break;
				}	
			}
		}
		
		if(bimPersonPhoto!=null&&bimPersonPhoto.getUseSystem()!=null){
			String str=bimPersonPhoto.getUseSystem();
			StringBuffer sb=new StringBuffer();
			String[] arr=str.split(",");
			List syslist=Arrays.asList(arr);
            for(int i=0;i<syslist.size();i++){
            	String str1=(String) syslist.get(i);
            	if(i>0&&i<syslist.size()){
            		sb.append(",");
            	}
            	
			switch (str1.trim()) {
			case "0":
				sb.append("门禁");
				break;
			case "2":
				sb.append("考勤");
				break;
			default:
				break;
			}
			
		}
		
            bimPersonPhoto.setUseSystem(sb.toString());
		}
		AjaxUtil.ajaxWriteObject(bimPersonPhoto,response);
	}
	
	/*
	 * 点击指纹授权操作
	 */
	@RequestMapping("/Card/fingersqInfo.do")
	@ResponseBody
	private void FingerSq(){
		
		OperateResult operateResult=new OperateResult();
		BimCardDto bimCardDto=new BimCardDto();
		StringBuffer sb=new StringBuffer(); 
		String formdata=request.getParameter("formdata");
		JSONObject jsonobj;
		try {
			jsonobj = new JSONObject(formdata);
			if(jsonobj.has("subSystem")){
				List<String> usesystemlist=new ArrayList<String>();
				if(!(jsonobj.get("subSystem") instanceof JSONArray)){
					usesystemlist.add((String)jsonobj.get("subSystem").toString());
					jsonobj.put("subSystem", usesystemlist);
				}
			}
			formdata=jsonobj.toString();
			BimCardDto bimCarddata = JacksonUtil.toObject(formdata, BimCardDto.class);
			if(bimCarddata.getSubSystem()!=null){
				
				int count=1;
				for(String temp:bimCarddata.getSubSystem()){
					sb.append(temp);
					if(count<bimCarddata.getSubSystem().size()){
						sb.append(",");
						count++;
					}
				}
				bimCardDto.setSubSystemStr(sb.toString().trim());
		}
			bimCardDto.setCardStatus(0);
			bimCardDto.setCardType(1);
			bimCardDto.setBimCompanyId(LoginInfo.getCompanyId());
			bimCardDto.setCardId(bimCarddata.getCardId());
	    	bimCardDto.setPersonId(bimCarddata.getPersonId());
	    	bimCardDto.setUpdateTime(new Date());
	    	int count=bimCardService.selectFinger(bimCarddata.getPersonId(),bimCarddata.getCardId());//查询卡信息中是否已存在指纹授权信息
	    	if(count==0){
	    		//不存在，就往里面插入一条信息
	    		bimCardService.insertCardInfo(bimCardDto);
	    	}else{
	    		//存在，就更改这条信息
	    		bimCardService.updateFingerSq(bimCardDto);
	    	}
	    	
	    	
	    	
	    if(bimCardDto.getSubSystemStr()!=null){
	    	StringBuffer sbstr=new StringBuffer();
			String[] arr=bimCardDto.getSubSystemStr().split(",");
			List syslist=Arrays.asList(arr);
            for(int i=0;i<syslist.size();i++){
            	String str1=(String) syslist.get(i);
            	if(i>0&&i<syslist.size()){
            		sbstr.append(",");
            	}
            	String a=str1.trim();
            	switch(a){
				case "0":sbstr.append("门禁");
				break;
				case "2":sbstr.append("考勤");
				break;
				
				}
            		 
			}
            bimCardDto.setSubSystemStr(sbstr.toString());
	    }
					
					
					
					
	    	
	    	operateResult.setResult(true);
			operateResult.setMsg("指纹授权成功");
			AjaxUtil.ajaxWriteObject(operateResult,bimCardDto,response);
		} catch (JSONException e) {
			
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("指纹授权失败");
			
		}
		
	}
	
	/**
	 * 车牌授权显示
	 * @param 人员id personId 
	 * @return 卡信息 bim_card
	 */
	@RequestMapping("/personnel/cardsq.do")
	@ResponseBody
	private void SelectCardByCarnumber(){
		String personId=request.getParameter("personId");
		//OperateResult operateResult=new OperateResult();
		List<BimCardDto> bimCardDto=bimCardService.selectCardByCarNumber(Integer.valueOf(personId));
		//try {
			
			
			//StringBuffer sb=new StringBuffer();
			if(bimCardDto.size()>0){
				for(BimCardDto temp:bimCardDto){
					if(temp.getSubSystemStr()!=null){
						temp.setSubSystemStr("停车场");
					}
		            			 
					}
					
				}
			//operateResult.setResult(true);
			//operateResult.setMsg("车辆授权成功");
			AjaxUtil.ajaxWriteObject(bimCardDto,response);
		/*} catch (Exception e) {
			// TODO: handle exception
		
			e.printStackTrace();
			operateResult.setResult(false);
			operateResult.setMsg("车辆授权失败");
			AjaxUtil.ajaxWriteObject(operateResult,bimCardDto,response);
		
		
		}*/
		
			
			
		}
	/**
	 * 车牌授权
	 * 
	 * @throws JSONException
	 */
	@RequestMapping("/personnel/addCarsq.do")
	@ResponseBody
	public void Carnumbersq() throws JSONException{
		OperateResult operateResult=new OperateResult();
		BimCardDto bimCardDto=new BimCardDto(); 
		String formdata=request.getParameter("formdata");
		BimCardDto bimCarddata = JacksonUtil.toObject(formdata, BimCardDto.class);
		bimCardDto.setSubSystemStr("3");
		bimCardDto.setCardType(2);
		bimCardDto.setBimCompanyId(LoginInfo.getCompanyId());
		bimCardDto.setCardId(bimCarddata.getCardId());
		bimCardDto.setPersonId(bimCarddata.getPersonId());
		bimCardService.insertCardInfo(bimCardDto);
		bimCardDto.setSubSystemStr("停车场");
		operateResult.setResult(true);
		operateResult.setMsg("车牌授权成功");
		AjaxUtil.ajaxWriteObject(operateResult,bimCardDto,response);
		
		
		
	}
	
	/**
	 * 解除车牌授权
	 */
	@RequestMapping("/personnel/cancelsq.do")
	@ResponseBody
	public void Cancelsq(){
		String bimCardId=request.getParameter("bimCardId");
		bimCardService.deleteCardInfo(Integer.valueOf(bimCardId));
		AjaxUtil.ajaxWrite(true,response);
	}
	

}
