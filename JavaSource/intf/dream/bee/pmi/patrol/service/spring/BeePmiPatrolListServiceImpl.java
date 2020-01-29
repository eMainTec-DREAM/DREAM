package intf.dream.bee.pmi.patrol.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.bee.pmi.patrol.dao.BeePmiPatrolListDAO;
import intf.dream.bee.pmi.patrol.dto.BeePmiPatrolCommonDTO;
import intf.dream.bee.pmi.patrol.service.BeePmiPatrolListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beePmiPatrolListServiceTarget"
 * @spring.txbn id="beePmiPatrolListService"
 * @spring.property name="beePmiPatrolListDAO" ref="beePmiPatrolListDAO"
 */
public class BeePmiPatrolListServiceImpl implements BeePmiPatrolListService
{
    private BeePmiPatrolListDAO beePmiPatrolListDAO = null;
    
	public BeePmiPatrolListDAO getBeePmiPatrolListDAO() {
		return beePmiPatrolListDAO;
	}
	public void setBeePmiPatrolListDAO(BeePmiPatrolListDAO beePmiPatrolListDAO) {
		this.beePmiPatrolListDAO = beePmiPatrolListDAO;
	}
	
	public List findPmiPatrolList(BeePmiPatrolCommonDTO beePmiPatrolCommonDTO, Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findPmiPatrolList(beePmiPatrolCommonDTO, map);
	}
	public List findPmiPatrolCount(BeePmiPatrolCommonDTO beePmiPatrolCommonDTO, Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findPmiPatrolCount(beePmiPatrolCommonDTO, map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return beePmiPatrolListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beePmiPatrolListDAO.insertPoint(map);
				beePmiPatrolListDAO.updateStartDate(map);
				beePmiPatrolListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiPatrolListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
				
				String pmptrlrsltlistId = CommonUtil.convertString(map.get("pmptrlrsltlistId"));
				if(!"".equals(pmptrlrsltlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
                	beePmiPatrolListDAO.updatePmSched(map);
                	beePmiPatrolListDAO.updatePmPointSchedStatus(map);
                	beePmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
                	beePmiPatrolListDAO.updatePminsList(map);
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
				
				beePmiPatrolListDAO.updatePoint(map);
				beePmiPatrolListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiPatrolListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
				
				String pmptrlrsltlistId = CommonUtil.convertString(map.get("pmptrlrsltlistId"));
				if(!"".equals(pmptrlrsltlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
	            	beePmiPatrolListDAO.updatePmSched(map);
	            	beePmiPatrolListDAO.updatePmPointSchedStatus(map);
	            	beePmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	beePmiPatrolListDAO.updatePminsList(map);
	            }
			}
		}
		
		return resultQty;
	}
}

