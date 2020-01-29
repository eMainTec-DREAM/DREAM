package intf.dream.android.online.pmi.day.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.android.online.pmi.day.dao.AnOnPmiDayListDAO;
import intf.dream.android.online.pmi.day.service.AnOnPmiDayListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnPmiDayListServiceTarget"
 * @spring.txbn id="anOnPmiDayListService"
 * @spring.property name="anOnPmiDayListDAO" ref="anOnPmiDayListDAO"
 */
public class AnOnPmiDayListServiceImpl implements AnOnPmiDayListService
{
    private AnOnPmiDayListDAO anOnPmiDayListDAO = null;
    
	public AnOnPmiDayListDAO getAnOnPmiDayListDAO() {
		return anOnPmiDayListDAO;
	}
	public void setAnOnPmiDayListDAO(AnOnPmiDayListDAO anOnPmiDayListDAO) {
		this.anOnPmiDayListDAO = anOnPmiDayListDAO;
	}
	
	public List findPmiDayList(Map map) throws Exception
	{      
		return anOnPmiDayListDAO.findPmiDayList(map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return anOnPmiDayListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return anOnPmiDayListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return anOnPmiDayListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return anOnPmiDayListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			anOnPmiDayListDAO.insertPoint(map);
			
			String wopointCount = anOnPmiDayListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiDayListDAO.getPmpointCount(map);
			
			if("1".equals(wopointCount)){
				//시작시간 업데이트
				anOnPmiDayListDAO.updateStartDate(map);
			}
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiDayListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiDayListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
	            
	            if(!"".equals(pminsdlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
            		anOnPmiDayListDAO.updatePmSched(map);
            		anOnPmiDayListDAO.updatePmPointSchedStatus(map);
            		anOnPmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            		anOnPmiDayListDAO.updatePminsList(map);
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
			anOnPmiDayListDAO.updatePoint(map);
			
			
			String wopointCount = anOnPmiDayListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiDayListDAO.getPmpointCount(map);
			
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiDayListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiDayListDAO.mergeAbnormalRslt(map);
	    			}
	            }else{
	            	map.put("woStatus", "PRW");
	            }
	            
	            if(!"".equals(pminsdlistId))
	            {
	            	String workTime = "";
	                String startDate = CommonUtil.convertString(map.get("startDate"));
	                String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
	                String endDate = CommonUtil.convertString(map.get("endDate"));
	                String endTime = CommonUtil.convertString(map.get("endTime"));
	                
	                workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
	                map.put("workTime", workTime);
	                
                	anOnPmiDayListDAO.updatePmSched(map);
	                anOnPmiDayListDAO.updatePmPointSchedStatus(map);
	                anOnPmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	anOnPmiDayListDAO.updatePminsList(map);
	            }
			}
		}
		
		
		return resultQty;
	}
}

