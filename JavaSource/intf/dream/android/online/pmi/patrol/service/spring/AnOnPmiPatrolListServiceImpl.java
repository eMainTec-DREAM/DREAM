package intf.dream.android.online.pmi.patrol.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.android.online.pmi.patrol.dao.AnOnPmiPatrolListDAO;
import intf.dream.android.online.pmi.patrol.service.AnOnPmiPatrolListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnPmiPatrolListServiceTarget"
 * @spring.txbn id="anOnPmiPatrolListService"
 * @spring.property name="anOnPmiPatrolListDAO" ref="anOnPmiPatrolListDAO"
 */
public class AnOnPmiPatrolListServiceImpl implements AnOnPmiPatrolListService
{
    private AnOnPmiPatrolListDAO anOnPmiPatrolListDAO = null;
    
	public AnOnPmiPatrolListDAO getAnOnPmiPatrolListDAO() {
		return anOnPmiPatrolListDAO;
	}
	public void setAnOnPmiPatrolListDAO(AnOnPmiPatrolListDAO anOnPmiPatrolListDAO) {
		this.anOnPmiPatrolListDAO = anOnPmiPatrolListDAO;
	}
	
	public List findPmiPatrolList(Map map) throws Exception
	{      
		return anOnPmiPatrolListDAO.findPmiPatrolList(map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return anOnPmiPatrolListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return anOnPmiPatrolListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return anOnPmiPatrolListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return anOnPmiPatrolListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			anOnPmiPatrolListDAO.insertPoint(map);
			
			String wopointCount = anOnPmiPatrolListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiPatrolListDAO.getPmpointCount(map);
			
			if("1".equals(wopointCount)){
				//시작시간 업데이트
				anOnPmiPatrolListDAO.updateStartDate(map);
			}
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiPatrolListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pmptrlrsltlistId = CommonUtil.convertString(map.get("pmptrlrsltlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiPatrolListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
	            
	            if(!"".equals(pmptrlrsltlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
                	anOnPmiPatrolListDAO.updatePmSched(map);
                	anOnPmiPatrolListDAO.updatePmPointSchedStatus(map);
                	anOnPmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
                	anOnPmiPatrolListDAO.updatePminsList(map);
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
			anOnPmiPatrolListDAO.updatePoint(map);
			//이상점검 insert 
			
			String wopointCount = anOnPmiPatrolListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiPatrolListDAO.getPmpointCount(map);
			
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiPatrolListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pmptrlrsltlistId = CommonUtil.convertString(map.get("pmptrlrsltlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiPatrolListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
	            
	            if(!"".equals(pmptrlrsltlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
	            	anOnPmiPatrolListDAO.updatePmSched(map);
	            	anOnPmiPatrolListDAO.updatePmPointSchedStatus(map);
	            	anOnPmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	anOnPmiPatrolListDAO.updatePminsList(map);
	            }
			}
		}
		
		
		return resultQty;
	}
}

