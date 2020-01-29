package intf.dream.bee.pmi.routine.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.bee.pmi.routine.dao.BeePmiRoutineListDAO;
import intf.dream.bee.pmi.routine.dto.BeePmiRoutineCommonDTO;
import intf.dream.bee.pmi.routine.service.BeePmiRoutineListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beePmiRoutineListServiceTarget"
 * @spring.txbn id="beePmiRoutineListService"
 * @spring.property name="beePmiRoutineListDAO" ref="beePmiRoutineListDAO"
 */
public class BeePmiRoutineListServiceImpl implements BeePmiRoutineListService
{
    private BeePmiRoutineListDAO beePmiRoutineListDAO = null;
    
	public BeePmiRoutineListDAO getBeePmiRoutineListDAO() {
		return beePmiRoutineListDAO;
	}
	public void setBeePmiRoutineListDAO(BeePmiRoutineListDAO beePmiRoutineListDAO) {
		this.beePmiRoutineListDAO = beePmiRoutineListDAO;
	}
	
	public List findPmiRoutineList(BeePmiRoutineCommonDTO beePmiRoutineCommonDTO, Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findPmiRoutineList(beePmiRoutineCommonDTO, map);
	}
	
	public List findPmiRoutineCount(BeePmiRoutineCommonDTO beePmiRoutineCommonDTO, Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findPmiRoutineCount(beePmiRoutineCommonDTO, map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return beePmiRoutineListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beePmiRoutineListDAO.insertPoint(map);
				beePmiRoutineListDAO.updateStartDate(map);
				beePmiRoutineListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiRoutineListDAO.mergeAbnormalRslt(map);
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
                	beePmiRoutineListDAO.updatePmSched(map);
	            	beePmiRoutineListDAO.updatePmPointSchedStatus(map);
	            	beePmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	beePmiRoutineListDAO.updatePminsList(map);
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
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beePmiRoutineListDAO.updatePoint(map);
				beePmiRoutineListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
					if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
						beePmiRoutineListDAO.mergeAbnormalRslt(map);
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
	                
	            	beePmiRoutineListDAO.updatePmSched(map);
	            	beePmiRoutineListDAO.updatePmPointSchedStatus(map);
	            	beePmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	beePmiRoutineListDAO.updatePminsList(map);
	            }
			}
		}
		
		return resultQty;
	}
}

