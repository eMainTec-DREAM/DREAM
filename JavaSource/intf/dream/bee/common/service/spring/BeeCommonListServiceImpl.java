package intf.dream.bee.common.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import intf.dream.bee.common.dao.BeeCommonListDAO;
import intf.dream.bee.common.service.BeeCommonListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeCommonListServiceTarget"
 * @spring.txbn id="beeCommonListService"
 * @spring.property name="beeCommonListDAO" ref="beeCommonListDAO"
 */
public class BeeCommonListServiceImpl implements BeeCommonListService
{
    private BeeCommonListDAO beeCommonListDAO = null;

	public BeeCommonListDAO getBeeCommonListDAO() {
		return beeCommonListDAO;
	}
	public void setBeeCommonListDAO(BeeCommonListDAO beeCommonListDAO) {
		this.beeCommonListDAO = beeCommonListDAO;
	}
	
	public List findNextVal(Map map) throws Exception
	{      
		return beeCommonListDAO.findNextVal(map);
	}
	public List findConfigVal(Map map) throws Exception
	{      
		return beeCommonListDAO.findConfigVal(map);
	}
	@Override
	public void insertTraceLog(Map<String, Object> data) throws Exception{
		
		String traceLogId = beeCommonListDAO.getNextSequence("SQATRACELOG_ID");
		data.put("traceLogId", traceLogId);
		
		//Header insert
		beeCommonListDAO.insertTraceLogHeader(data);
		
		//Detail insert
		if("D".equals(CommonUtil.convertString(data.get("dataCudType")))) return;
		List<Map<String,String>> results = new ArrayList<>();
		Map<String, String> tempData = null;
		
		for(Map.Entry<String, Object> entry : data.entrySet()){
			tempData = new HashMap<String, String>();
			tempData.put("key", entry.getKey());
			tempData.put("value", (String)entry.getValue());
            results.add(tempData);
		}
		beeCommonListDAO.insertTraceLogDetail(traceLogId, results);
	}
}

