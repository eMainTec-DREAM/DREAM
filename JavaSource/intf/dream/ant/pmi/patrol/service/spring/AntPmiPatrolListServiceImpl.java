package intf.dream.ant.pmi.patrol.service.spring;

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
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.pmi.patrol.dao.AntPmiPatrolListDAO;
import intf.dream.ant.pmi.patrol.service.AntPmiPatrolListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPmiPatrolListServiceTarget"
 * @spring.txbn id="antPmiPatrolListService"
 * @spring.property name="antPmiPatrolListDAO" ref="antPmiPatrolListDAO"
 */
public class AntPmiPatrolListServiceImpl implements AntPmiPatrolListService
{
    private AntPmiPatrolListDAO antPmiPatrolListDAO = null;
	public AntPmiPatrolListDAO getAntPmiPatrolListDAO() {
		return antPmiPatrolListDAO;
	}
	public void setAntPmiPatrolListDAO(AntPmiPatrolListDAO antPmiPatrolListDAO) {
		this.antPmiPatrolListDAO = antPmiPatrolListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return antPmiPatrolListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return antPmiPatrolListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return antPmiPatrolListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return antPmiPatrolListDAO.findPmpointList(map);
	}
	
	public int saveInspList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_INSPECTION_STATES, antPmiPatrolListDAO.getWoStatusOfInspection(map))){
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
            		pmptrlrsltpointId = antPmiPatrolListDAO.getInsppoint(pointMap);
            		if("".equals(pmptrlrsltpointId)){
            			pmptrlrsltpointId = antPmiPatrolListDAO.getNextSequence("SQAPMPTRLRSLTPOINT_ID");
            		}
            		
            		antPmiPatrolListDAO.saveInsppoint(pointMap,pmptrlrsltpointId); //점검 업로드
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) antPmiPatrolListDAO.mergeAbnormalRslt(pointMap,pmptrlrsltpointId);
                }
            }
            
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
            
        	antPmiPatrolListDAO.updatePmSched(map);
        	antPmiPatrolListDAO.updatePmPointSchedStatus(map);
        	antPmiPatrolListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
        	antPmiPatrolListDAO.updatePminspList(map);
        }
        return resultQty;
	}
}

