package intf.dream.android.online.pmi.part.service.spring;

import java.util.List;
import java.util.Map;

import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import intf.dream.android.online.pmi.part.dao.AnOnPmiPartListDAO;
import intf.dream.android.online.pmi.part.service.AnOnPmiPartListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnPmiPartListServiceTarget"
 * @spring.txbn id="anOnPmiPartListService"
 * @spring.property name="anOnPmiPartListDAO" ref="anOnPmiPartListDAO"
 */
public class AnOnPmiPartListServiceImpl implements AnOnPmiPartListService
{
    private AnOnPmiPartListDAO anOnPmiPartListDAO = null;
    
	public AnOnPmiPartListDAO getAnOnPmiPartListDAO() {
		return anOnPmiPartListDAO;
	}
	public void setAnOnPmiPartListDAO(AnOnPmiPartListDAO anOnPmiPartListDAO) {
		this.anOnPmiPartListDAO = anOnPmiPartListDAO;
	}
	
	public List findPmiPartList(Map map) throws Exception
	{      
		return anOnPmiPartListDAO.findPmiPartList(map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return anOnPmiPartListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return anOnPmiPartListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return anOnPmiPartListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return anOnPmiPartListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			anOnPmiPartListDAO.insertPoint(map);
			
			String wopointCount = anOnPmiPartListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiPartListDAO.getPmpointCount(map);
			
			if("1".equals(wopointCount)){
				//시작시간 업데이트
				anOnPmiPartListDAO.updateStartDate(map);
			}
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiPartListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiPartListDAO.mergeAbnormalRslt(map);
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
	                
            		anOnPmiPartListDAO.updatePmSched(map);
            		anOnPmiPartListDAO.updatePmPointSchedStatus(map);
            		anOnPmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            		anOnPmiPartListDAO.updatePminsList(map);
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
			anOnPmiPartListDAO.updatePoint(map);
			String wopointCount = anOnPmiPartListDAO.getWopointCount(map);
			String pmpointCount = anOnPmiPartListDAO.getPmpointCount(map);
			
			if(pmpointCount.equals(wopointCount)){
				//종료시간, 작업시간 업데이트
				anOnPmiPartListDAO.updateEndDate(map);
				//완료
				String userId = CommonUtil.convertString(map.get("userId"));
	            String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if("Y".equals(CommonUtil.convertString(map.get("isConfirm")))){
	            	map.put("woStatus", "C");
	            	
	            	//이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				anOnPmiPartListDAO.mergeAbnormalRslt(map);
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
	                
	            	anOnPmiPartListDAO.updatePmSched(map);
	            	anOnPmiPartListDAO.updatePmPointSchedStatus(map);
	            	anOnPmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
	            	anOnPmiPartListDAO.updatePminsList(map);
	            }
			}
		}
		
		
		return resultQty;
	}
}

