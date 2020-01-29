package intf.dream.android.offline.pmi.day.service.spring;

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
import intf.dream.android.offline.pmi.day.dao.AnIfPmiDayListDAO;
import intf.dream.android.offline.pmi.day.service.AnIfPmiDayListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPmiDayListServiceTarget"
 * @spring.txbn id="anIfPmiDayListService"
 * @spring.property name="anIfPmiDayListDAO" ref="anIfPmiDayListDAO"
 */
public class AnIfPmiDayListServiceImpl implements AnIfPmiDayListService
{
    private AnIfPmiDayListDAO anIfPmiDayListDAO = null;
	public AnIfPmiDayListDAO getAnIfPmiDayListDAO() {
		return anIfPmiDayListDAO;
	}
	public void setAnIfPmiDayListDAO(AnIfPmiDayListDAO anIfPmiDayListDAO) {
		this.anIfPmiDayListDAO = anIfPmiDayListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return anIfPmiDayListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return anIfPmiDayListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return anIfPmiDayListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return anIfPmiDayListDAO.findPmpointList(map);
	}
	
	public int saveInsdList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(CommonValues.USED_INSPECTION_STATES, anIfPmiDayListDAO.getWoStatusOfInspection(map))){
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
            String pminsdlistId = CommonUtil.convertString(map.get("pminsdlistId"));
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
            		String pminsdpointId = "";
            		pminsdpointId = anIfPmiDayListDAO.getInsdpoint(pointMap);
            		if("".equals(pminsdpointId)){
            			pminsdpointId = anIfPmiDayListDAO.getNextSequence("SQAPMINSDPOINT_ID");
            		}
            		
            		anIfPmiDayListDAO.saveInsdpoint(pointMap,pminsdpointId); //점검 업로드
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) anIfPmiDayListDAO.mergeAbnormalRslt(pointMap,pminsdpointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
        	anIfPmiDayListDAO.updatePmSched(map);
            anIfPmiDayListDAO.updatePmPointSchedStatus(map);
            anIfPmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            anIfPmiDayListDAO.updatePminsdList(map);
        }
        return resultQty;
	}
}

