package intf.dream.ant.woreq.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.util.CommonUtil;
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.woreq.dao.AntWoReqListDAO;
import intf.dream.ant.woreq.service.AntWoReqListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antWoReqListServiceTarget"
 * @spring.txbn id="antWoReqListService"
 * @spring.property name="antWoReqListDAO" ref="antWoReqListDAO"
 */
public class AntWoReqListServiceImpl implements AntWoReqListService
{
    private AntWoReqListDAO antWoReqListDAO = null;

	public AntWoReqListDAO getAntWoReqListDAO() {
		return antWoReqListDAO;
	}
	public void setAntWoReqListDAO(AntWoReqListDAO antWoReqListDAO) {
		this.antWoReqListDAO = antWoReqListDAO;
	}
	
	public List findWoReqList(Map map) throws Exception
	{      
		return antWoReqListDAO.findWoReqList(map);
	}
	public List findWoReqFileList(Map map) throws Exception
	{      
		return antWoReqListDAO.findWoReqFileList(map);
	}
	public List findWoReqResList(Map map) throws Exception
	{      
		return antWoReqListDAO.findWoReqResList(map);
	}

	public int saveWoReqList(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			
            String woReqId = antWoReqListDAO.getNextSequence("SQAWOREQ_ID");
            antWoReqListDAO.insertWoReqList(map, woReqId);
        	//change Sequence
            antWoReqListDAO.changeFileSeq(map, woReqId);
        }
        return resultQty;
	}
	
	public int saveWoResList(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;

            String woReqResJson = CommonUtil.convertString(map.get("WOREQRES"));
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();

            List woReqResList = gson.fromJson(woReqResJson, type);
            
            antWoReqListDAO.updateWoResList(map);
            
            //처리사항 insert
            if(woReqResList.size() != 0)
            {
            	for(Object woReqResObj : woReqResList)
                {
            		Map woReqResMap = (Map)woReqResObj;
            		antWoReqListDAO.insertWoReqResList(woReqResMap);
            		
            		//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
                	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_WORES_STATES, antWoReqListDAO.getWoReqStatus(woReqResMap))){
                		continue;
                	}
            		antWoReqListDAO.updateWoReqStatus(woReqResMap);
            		
                }
            }
            
        }
        return resultQty;
	}
}

