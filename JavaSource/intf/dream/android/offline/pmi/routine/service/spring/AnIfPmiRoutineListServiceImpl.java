package intf.dream.android.offline.pmi.routine.service.spring;

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
import intf.dream.android.offline.pmi.routine.dao.AnIfPmiRoutineListDAO;
import intf.dream.android.offline.pmi.routine.service.AnIfPmiRoutineListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPmiRoutineListServiceTarget"
 * @spring.txbn id="anIfPmiRoutineListService"
 * @spring.property name="anIfPmiRoutineListDAO" ref="anIfPmiRoutineListDAO"
 */
public class AnIfPmiRoutineListServiceImpl implements AnIfPmiRoutineListService
{
    private AnIfPmiRoutineListDAO anIfPmiRoutineListDAO = null;
	public AnIfPmiRoutineListDAO getAnIfPmiRoutineListDAO() {
		return anIfPmiRoutineListDAO;
	}
	public void setAnIfPmiRoutineListDAO(AnIfPmiRoutineListDAO anIfPmiRoutineListDAO) {
		this.anIfPmiRoutineListDAO = anIfPmiRoutineListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return anIfPmiRoutineListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return anIfPmiRoutineListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return anIfPmiRoutineListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return anIfPmiRoutineListDAO.findPmpointList(map);
	}
	
	public int saveInsList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(CommonValues.USED_INSPECTION_STATES, anIfPmiRoutineListDAO.getWoStatusOfInspection(map))){
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
            String wkOrId = CommonUtil.convertString(map.get("pminslistId"));
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
            		String pminspointId = "";
            		pminspointId = anIfPmiRoutineListDAO.getInspoint(pointMap);
            		if("".equals(pminspointId)){
            			pminspointId = anIfPmiRoutineListDAO.getNextSequence("SQAPMINSPOINT_ID");
            		}
            		
            		anIfPmiRoutineListDAO.saveInspoint(pointMap,pminspointId); //점검 업로드
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) anIfPmiRoutineListDAO.mergeAbnormalRslt(pointMap,pminspointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }

        	anIfPmiRoutineListDAO.updatePmSched(map);
            anIfPmiRoutineListDAO.updatePmPointSchedStatus(map);
            anIfPmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            anIfPmiRoutineListDAO.updatePminsList(map);
        }
        return resultQty;
	}
}

