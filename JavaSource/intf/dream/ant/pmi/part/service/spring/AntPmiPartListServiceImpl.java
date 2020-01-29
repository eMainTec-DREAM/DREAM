package intf.dream.ant.pmi.part.service.spring;

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
import intf.dream.ant.pmi.part.dao.AntPmiPartListDAO;
import intf.dream.ant.pmi.part.service.AntPmiPartListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPmiPartListServiceTarget"
 * @spring.txbn id="antPmiPartListService"
 * @spring.property name="antPmiPartListDAO" ref="antPmiPartListDAO"
 */
public class AntPmiPartListServiceImpl implements AntPmiPartListService
{
    private AntPmiPartListDAO antPmiPartListDAO = null;
	public AntPmiPartListDAO getAntPmiPartListDAO() {
		return antPmiPartListDAO;
	}
	public void setAntPmiPartListDAO(AntPmiPartListDAO antPmiPartListDAO) {
		this.antPmiPartListDAO = antPmiPartListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return antPmiPartListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return antPmiPartListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return antPmiPartListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return antPmiPartListDAO.findPmpointList(map);
	}
	
	public int saveInsdList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_INSPECTION_STATES, antPmiPartListDAO.getWoStatusOfInspection(map))){
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
            		pminsdpointId = antPmiPartListDAO.getInsdpoint(pointMap);
            		if("".equals(pminsdpointId)){
            			pminsdpointId = antPmiPartListDAO.getNextSequence("SQAPMINSDPOINT_ID");
            		}
            		
            		antPmiPartListDAO.saveInsdpoint(pointMap,pminsdpointId); //점검 업로드
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) antPmiPartListDAO.mergeAbnormalRslt(pointMap,pminsdpointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
            
        	antPmiPartListDAO.updatePmSched(map);
        	antPmiPartListDAO.updatePmPointSchedStatus(map);
        	antPmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
        	antPmiPartListDAO.updatePminsdList(map);
        }
        return resultQty;
	}
}

