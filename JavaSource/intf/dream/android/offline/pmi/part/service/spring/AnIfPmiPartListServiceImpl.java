package intf.dream.android.offline.pmi.part.service.spring;

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
import intf.dream.android.offline.pmi.part.dao.AnIfPmiPartListDAO;
import intf.dream.android.offline.pmi.part.service.AnIfPmiPartListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPmiPartListServiceTarget"
 * @spring.txbn id="anIfPmiPartListService"
 * @spring.property name="anIfPmiPartListDAO" ref="anIfPmiPartListDAO"
 */
public class AnIfPmiPartListServiceImpl implements AnIfPmiPartListService
{
    private AnIfPmiPartListDAO anIfPmiPartListDAO = null;
	public AnIfPmiPartListDAO getAnIfPmiPartListDAO() {
		return anIfPmiPartListDAO;
	}
	public void setAnIfPmiPartListDAO(AnIfPmiPartListDAO anIfPmiPartListDAO) {
		this.anIfPmiPartListDAO = anIfPmiPartListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return anIfPmiPartListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return anIfPmiPartListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return anIfPmiPartListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return anIfPmiPartListDAO.findPmpointList(map);
	}
	
	public int saveInsdList(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
			//W/O ����üũ �� ���°� ���ε� ����� �ƴϸ� Skip
        	if(!CommonUtil.isUseStringInArray(CommonValues.USED_INSPECTION_STATES, anIfPmiPartListDAO.getWoStatusOfInspection(map))){
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
            		pminsdpointId = anIfPmiPartListDAO.getInsdpoint(pointMap);
            		if("".equals(pminsdpointId)){
            			pminsdpointId = anIfPmiPartListDAO.getNextSequence("SQAPMINSDPOINT_ID");
            		}
            		
            		anIfPmiPartListDAO.saveInsdpoint(pointMap,pminsdpointId); //���� ���ε�
            		
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) anIfPmiPartListDAO.mergeAbnormalRslt(pointMap,pminsdpointId);
                }
            }
            if("Y".equals(MwareConfig.getIsCompInswrkMoload())){
            	map.put("woStatus", "C");
            }else{
            	map.put("woStatus", "PRW");
            }
            
        	anIfPmiPartListDAO.updatePmSched(map);
        	anIfPmiPartListDAO.updatePmPointSchedStatus(map);
        	anIfPmiPartListDAO.executeSP_PM_UPDATE_LASTCPLT_DATE(map);
        	anIfPmiPartListDAO.updatePminsdList(map);
        }
        return resultQty;
	}
}

