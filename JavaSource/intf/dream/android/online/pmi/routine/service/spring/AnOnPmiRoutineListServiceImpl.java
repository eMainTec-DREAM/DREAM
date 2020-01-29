package intf.dream.android.online.pmi.routine.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.android.online.pmi.routine.dao.AnOnPmiRoutineListDAO;
import intf.dream.android.online.pmi.routine.service.AnOnPmiRoutineListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnPmiRoutineListServiceTarget"
 * @spring.txbn id="anOnPmiRoutineListService"
 * @spring.property name="anOnPmiRoutineListDAO" ref="anOnPmiRoutineListDAO"
 */
public class AnOnPmiRoutineListServiceImpl implements AnOnPmiRoutineListService
{
    private AnOnPmiRoutineListDAO anOnPmiRoutineListDAO = null;
    
	public AnOnPmiRoutineListDAO getAnOnPmiRoutineListDAO() {
		return anOnPmiRoutineListDAO;
	}
	public void setAnOnPmiRoutineListDAO(AnOnPmiRoutineListDAO anOnPmiRoutineListDAO) {
		this.anOnPmiRoutineListDAO = anOnPmiRoutineListDAO;
	}
	
	public List findPmiRoutineList(Map map) throws Exception
	{      
		return anOnPmiRoutineListDAO.findPmiRoutineList(map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return anOnPmiRoutineListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return anOnPmiRoutineListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return anOnPmiRoutineListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return anOnPmiRoutineListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				anOnPmiRoutineListDAO.insertPoint(map);
				anOnPmiRoutineListDAO.updateStartDate(map);
				anOnPmiRoutineListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiRoutineListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
				
				String pminslistId = CommonUtil.convertString(map.get("pminslistId"));
	            if(!"".equals(pminslistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	            	anOnPmiRoutineListDAO.updatePmSched(map);
	            	anOnPmiRoutineListDAO.updatePmPointSchedStatus(map);
	            	anOnPmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	anOnPmiRoutineListDAO.updatePminsList(map);
	            }
			}
			
			
			
		}
		return resultQty;
	}
	
	public int updatePoint(List list)  throws Exception
	{
		int resultQty = 0;
		for(Object obj : list){
			Map map = (Map)obj;
			
			//항목 데이터 저장 
			anOnPmiRoutineListDAO.updatePoint(map);
			//종료시간, 작업시간 업데이트
			anOnPmiRoutineListDAO.updateEndDate(map);
			
			if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
            	map.put("woStatus", "C");
            	//완료면 이상점검 insert 
				if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
					anOnPmiRoutineListDAO.mergeAbnormalRslt(map);
				}
            }else{
            	map.put("woStatus", "PRW");
            }
			
			String pminslistId = CommonUtil.convertString(map.get("pminslistId"));
			if(!"".equals(pminslistId))
            {
				String workTime = "";
	            String startDate = CommonUtil.convertString(map.get("startDate"));
	            String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	            String endDate = CommonUtil.convertString(map.get("endDate"));
	            String endTime = CommonUtil.convertString(map.get("endTime"));
	            
	            workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	            map.put("workTime", workTime);
	            
				anOnPmiRoutineListDAO.updatePmSched(map);
				anOnPmiRoutineListDAO.updatePmPointSchedStatus(map);
				anOnPmiRoutineListDAO.updatePminsList(map);
				anOnPmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            }
		}
		
		return resultQty;
	}
}

