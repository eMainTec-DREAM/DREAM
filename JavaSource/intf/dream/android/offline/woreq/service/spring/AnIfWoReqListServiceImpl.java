package intf.dream.android.offline.woreq.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.util.CommonUtil;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.woreq.dao.AnIfWoReqListDAO;
import intf.dream.android.offline.woreq.service.AnIfWoReqListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfWoReqListServiceTarget"
 * @spring.txbn id="anIfWoReqListService"
 * @spring.property name="anIfWoReqListDAO" ref="anIfWoReqListDAO"
 */
public class AnIfWoReqListServiceImpl implements AnIfWoReqListService
{
    private AnIfWoReqListDAO anIfWoReqListDAO = null;

	public AnIfWoReqListDAO getAnIfWoReqListDAO() {
		return anIfWoReqListDAO;
	}
	public void setAnIfWoReqListDAO(AnIfWoReqListDAO anIfWoReqListDAO) {
		this.anIfWoReqListDAO = anIfWoReqListDAO;
	}
	
	public List findWoReqList(Map map) throws Exception
	{      
		return anIfWoReqListDAO.findWoReqList(map);
	}
	public List findWoReqFileList(Map map) throws Exception
	{      
		return anIfWoReqListDAO.findWoReqFileList(map);
	}
	public List findWoReqResList(Map map) throws Exception
	{      
		return anIfWoReqListDAO.findWoReqResList(map);
	}

	public int saveWoReqList(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			
            String woReqId = anIfWoReqListDAO.getNextSequence("SQAWOREQ_ID");
            anIfWoReqListDAO.insertWoReqList(map, woReqId);
        	//change Sequence
            anIfWoReqListDAO.changeFileSeq(map, woReqId);
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
            
            anIfWoReqListDAO.updateWoResList(map);
            
            //처리사항 insert
            if(woReqResList.size() != 0)
            {
            	for(Object woReqResObj : woReqResList)
                {
            		Map woReqResMap = (Map)woReqResObj;
            		anIfWoReqListDAO.insertWoReqResList(woReqResMap);
            		
            		//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
                	if(!CommonUtil.isUseStringInArray(CommonValues.USED_WORES_STATES, anIfWoReqListDAO.getWoReqStatus(woReqResMap))){
                		continue;
                	}
            		anIfWoReqListDAO.updateWoReqStatus(woReqResMap);
            		
                }
            }
            
        }
        return resultQty;
	}
}

