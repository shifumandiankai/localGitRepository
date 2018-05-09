package com.wadejerry.scms.modules.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.common.dao.CommonDAO;
import com.wadejerry.scms.modules.common.dao.ComonDao;

/**
 * 
 * <p>通用服务类</p>
 * @author jiangdahui Aug 23, 2013 9:11:11 AM
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} Aug 23, 2013
 * @modify by reason:{方法名}:{原因}
 */
@SuppressWarnings("unchecked")
@Service("commonService")
public class CommonService {
	
	@Autowired(required = false)
	private ComonDao commonDAO;

    /**
     * 根据完整的sql得到一个map列表
     * @author jiangdahui Aug 23, 2013 9:11:24 AM
     * @param sql
     * @param params
     * @return
     * @modificationHistory=========================逻辑或功能性重大变更记录
     * @modify by user: {修改人} Aug 23, 2013
     * @modify by reason:{原因}
     */
	public List<Map<String, Object>> findPartitionList(String sql) {
		return commonDAO.findPartitionList(sql);
	}
	
	public boolean excuteSQL(String sql){
		return commonDAO.excuteSQL(sql)>0?true:false;
	}

}
