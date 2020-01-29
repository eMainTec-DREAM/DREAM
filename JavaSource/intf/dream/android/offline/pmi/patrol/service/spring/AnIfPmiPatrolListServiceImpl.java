package intf.dream.android.offline.pmi.patrol.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.bean.MwareConfig;
import common.util.CommonUtil;
import common.util.DateUtil;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.pmi.patrol.dao.AnIfPmiPatrolListDAO;
import intf.dream.android.offline.pmi.patrol.service.AnIfPmiPatrolListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPmiPatrolListServiceTarget"
 * @spring.txbn id="anIfPmiPatrolListService"
 * @spring.property name="anIfPmiPatrolListDAO" ref="anIfPmiPatrolListDAO"
 */
public class AnIfPmiPatrolListServiceImpl implements AnIfPmiPatrolListService
{
    private AnIfPmiPatrolListDAO anIfPmiPatrolListDAO = null;
	public AnIfPmiPatrolListDAO getAnIfPmiPatrolListDAO() {
		return anIfPmiPatrolListDAO;
	}
	public void setAnIfPmiPatrolListDAO(AnIfPmiPatrolListDAO anIfPmiPatrolListDAO) {
		this.anIfPmiPatrolListDAO = anIfPmiPatrolListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return anIfPmiPatrolListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return anIfPmiPatrolListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return anIfPmiPatrolListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return anIfPmiPatrolListDAO.findPmpointList(map);
	}
	
	public int saveInspList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(CommonValues.USED_INSPECTION_STATES, anIfPmiPatrolListDAO.getWoStatusOfInspection(map))){
        		continue;
        	}
        	
			//시간계산
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
            String endDate = CommonUtil.convertString(map.get("endDate"));
            String endTime = CommonUtil.convertString(map.get("endTime"));
            
            workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
            map.put("workTime", workTime);
            
            String userId = CommonUtil.convertString(map.get("userId"));
            String pmptrlrsltlistId = CommonUtil.convertString(map.get("pmptrlrsltlistId"));
            String compNo = CommonUtil.convertString(map.get("compNo"));
            
            String wopointJson = CommonUtil.convertString(map.get("POINT"));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();
            
            List wopointList = gson.fromJson(wopointJson, type);
            
            if(wopointList.size() != 0)
            {
            	for(Object pointObj : wopointList)
                {
            		Map pointMap = (Map)pointObj;   
            		String pmPointRsltStatus = CommonUtil.convertString(pointMap.get("PM_POINT_RSLT_STATUS"));
            		String pmptrlrsltpointId = "";
            		pmptrlrsltpointId = anIfPmiPatrolListDAO.getInsppoint(pointMap);
            		if("".equals(pmptrlrsltpointId)){
            			pmptrlrsltpointId = anIfPmiPatrolListDAO.getNextSequence("SQAPMPTRLRSLTPOINT_ID");
            		}
            		
            		anIfPmiPatrolListDAO.saveInsppoint(pointMap,pmptrlrsltpointId); //점검 업로드
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) anIfPmiPatrolListDAO.mergeAbnormalRslt(pointMap,pmptrlrsltpointId);
                }
            }
            
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
            
        	anIfPmiPatrolListDAO.updatePmSched(map);
        	anIfPmiPatrolListDAO.updatePmPointSchedStatus(map);
        	anIfPmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
        	anIfPmiPatrolListDAO.updatePminspList(map);
        }
        return resultQty;
	}
}

