package intf.dream.bee.pmi.day.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.bee.pmi.day.dao.BeePmiDayListDAO;
import intf.dream.bee.pmi.day.dto.BeePmiDayCommonDTO;
import intf.dream.bee.pmi.day.service.BeePmiDayListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beePmiDayListServiceTarget"
 * @spring.txbn id="beePmiDayListService"
 * @spring.property name="beePmiDayListDAO" ref="beePmiDayListDAO"
 */
public class BeePmiDayListServiceImpl implements BeePmiDayListService
{
    private BeePmiDayListDAO beePmiDayListDAO = null;
    
	public BeePmiDayListDAO getBeePmiDayListDAO() {
		return beePmiDayListDAO;
	}
	public void setBeePmiDayListDAO(BeePmiDayListDAO beePmiDayListDAO) {
		this.beePmiDayListDAO = beePmiDayListDAO;
	}
	
	public List findPmiDayList(BeePmiDayCommonDTO beePmiDayCommonDTO, Map map) throws Exception
	{      
		return beePmiDayListDAO.findPmiDayList(beePmiDayCommonDTO, map);
	}
	public List findPmiDayCount(BeePmiDayCommonDTO beePmiDayCommonDTO, Map map) throws Exception
	{      
		return beePmiDayListDAO.findPmiDayCount(beePmiDayCommonDTO, map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return beePmiDayListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return beePmiDayListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return beePmiDayListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return beePmiDayListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beePmiDayListDAO.insertPoint(map);
				beePmiDayListDAO.updateStartDate(map);
				beePmiDayListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiDayListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
				
				String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
				if(!"".equals(pminsdlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
            		beePmiDayListDAO.updatePmSched(map);
            		beePmiDayListDAO.updatePmPointSchedStatus(map);
            		beePmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            		beePmiDayListDAO.updatePminsList(map);
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
				
				beePmiDayListDAO.updatePoint(map);
				beePmiDayListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiDayListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
				
				String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
				if(!"".equals(pminsdlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
                	beePmiDayListDAO.updatePmSched(map);
	                beePmiDayListDAO.updatePmPointSchedStatus(map);
	                beePmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	beePmiDayListDAO.updatePminsList(map);
	            }
			}
		}
		
		return resultQty;
	}
}

