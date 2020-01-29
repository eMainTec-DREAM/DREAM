package intf.dream.bee.pmi.part.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.bee.pmi.part.dao.BeePmiPartListDAO;
import intf.dream.bee.pmi.part.dto.BeePmiPartCommonDTO;
import intf.dream.bee.pmi.part.service.BeePmiPartListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beePmiPartListServiceTarget"
 * @spring.txbn id="beePmiPartListService"
 * @spring.property name="beePmiPartListDAO" ref="beePmiPartListDAO"
 */
public class BeePmiPartListServiceImpl implements BeePmiPartListService
{
    private BeePmiPartListDAO beePmiPartListDAO = null;
    
	public BeePmiPartListDAO getBeePmiPartListDAO() {
		return beePmiPartListDAO;
	}
	public void setBeePmiPartListDAO(BeePmiPartListDAO beePmiPartListDAO) {
		this.beePmiPartListDAO = beePmiPartListDAO;
	}
	
	public List findPmiPartList(BeePmiPartCommonDTO beePmiPartCommonDTO, Map map) throws Exception
	{      
		return beePmiPartListDAO.findPmiPartList(beePmiPartCommonDTO, map);
	}
	public List findPmiPartCount(BeePmiPartCommonDTO beePmiPartCommonDTO, Map map) throws Exception
	{      
		return beePmiPartListDAO.findPmiPartCount(beePmiPartCommonDTO, map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return beePmiPartListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return beePmiPartListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return beePmiPartListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return beePmiPartListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beePmiPartListDAO.insertPoint(map);
				beePmiPartListDAO.updateStartDate(map);
				beePmiPartListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiPartListDAO.mergeAbnormalRslt(map);
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
	                
            		beePmiPartListDAO.updatePmSched(map);
            		beePmiPartListDAO.updatePmPointSchedStatus(map);
            		beePmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            		beePmiPartListDAO.updatePminsList(map);
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
				
				beePmiPartListDAO.updatePoint(map);
				beePmiPartListDAO.updateEndDate(map);
				
				if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beePmiPartListDAO.mergeAbnormalRslt(map);
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
	                
	            	beePmiPartListDAO.updatePmSched(map);
	            	beePmiPartListDAO.updatePmPointSchedStatus(map);
	            	beePmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	beePmiPartListDAO.updatePminsList(map);
	            }
			}
		}
		
		return resultQty;
	}
}

