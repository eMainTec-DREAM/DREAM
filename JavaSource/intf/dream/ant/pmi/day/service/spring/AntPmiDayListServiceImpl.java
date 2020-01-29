package intf.dream.ant.pmi.day.service.spring;

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
import intf.dream.ant.pmi.day.dao.AntPmiDayListDAO;
import intf.dream.ant.pmi.day.service.AntPmiDayListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPmiDayListServiceTarget"
 * @spring.txbn id="antPmiDayListService"
 * @spring.property name="antPmiDayListDAO" ref="antPmiDayListDAO"
 */
public class AntPmiDayListServiceImpl implements AntPmiDayListService
{
    private AntPmiDayListDAO antPmiDayListDAO = null;
	public AntPmiDayListDAO getAntPmiDayListDAO() {
		return antPmiDayListDAO;
	}
	public void setAntPmiDayListDAO(AntPmiDayListDAO antPmiDayListDAO) {
		this.antPmiDayListDAO = antPmiDayListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return antPmiDayListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return antPmiDayListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return antPmiDayListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return antPmiDayListDAO.findPmpointList(map);
	}
	
	public int saveInsdList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O ����üũ �� ���°� ���ε� ����� �ƴϸ� Skip
        	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_INSPECTION_STATES, antPmiDayListDAO.getWoStatusOfInspection(map))){
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
            		pminsdpointId = antPmiDayListDAO.getInsdpoint(pointMap);
            		if("".equals(pminsdpointId)){
            			pminsdpointId = antPmiDayListDAO.getNextSequence("SQAPMINSDPOINT_ID");
            		}
            		
            		antPmiDayListDAO.saveInsdpoint(pointMap,pminsdpointId); //���� ���ε�
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) antPmiDayListDAO.mergeAbnormalRslt(pointMap,pminsdpointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
        	antPmiDayListDAO.updatePmSched(map);
            antPmiDayListDAO.updatePmPointSchedStatus(map);
            antPmiDayListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
            antPmiDayListDAO.updatePminsdList(map);
        }
        return resultQty;
	}
}

