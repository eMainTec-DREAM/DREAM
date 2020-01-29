package intf.dream.ant.pmi.routine.service.spring;

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
import intf.dream.ant.pmi.routine.dao.AntPmiRoutineListDAO;
import intf.dream.ant.pmi.routine.service.AntPmiRoutineListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPmiRoutineListServiceTarget"
 * @spring.txbn id="antPmiRoutineListService"
 * @spring.property name="antPmiRoutineListDAO" ref="antPmiRoutineListDAO"
 */
public class AntPmiRoutineListServiceImpl implements AntPmiRoutineListService
{
    private AntPmiRoutineListDAO antPmiRoutineListDAO = null;
	public AntPmiRoutineListDAO getAntPmiRoutineListDAO() {
		return antPmiRoutineListDAO;
	}
	public void setAntPmiRoutineListDAO(AntPmiRoutineListDAO antPmiRoutineListDAO) {
		this.antPmiRoutineListDAO = antPmiRoutineListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return antPmiRoutineListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return antPmiRoutineListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return antPmiRoutineListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return antPmiRoutineListDAO.findPmpointList(map);
	}
	
	public int saveInsList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O ����üũ �� ���°� ���ε� ����� �ƴϸ� Skip
        	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_INSPECTION_STATES, antPmiRoutineListDAO.getWoStatusOfInspection(map))){
        		continue;
        	}
			//�ð����
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5���� �������
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
            		pminspointId = antPmiRoutineListDAO.getInspoint(pointMap);
            		if("".equals(pminspointId)){
            			pminspointId = antPmiRoutineListDAO.getNextSequence("SQAPMINSPOINT_ID");
            		}
            		
            		antPmiRoutineListDAO.saveInspoint(pointMap,pminspointId); //���� ���ε�
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) antPmiRoutineListDAO.mergeAbnormalRslt(pointMap,pminspointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }

        	antPmiRoutineListDAO.updatePmSched(map);
            antPmiRoutineListDAO.updatePmPointSchedStatus(map);
            antPmiRoutineListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            antPmiRoutineListDAO.updatePminsList(map);
        }
        return resultQty;
	}
}

