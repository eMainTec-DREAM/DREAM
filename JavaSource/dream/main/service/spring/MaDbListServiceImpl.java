package dream.main.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.main.dao.MaDbListDAO;
import dream.main.dto.MaDbListDTO;
import dream.main.service.MaDbListService;

/**
 * Dashboard - ¸ñ·Ï serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maDbListServiceTarget"
 * @spring.txbn id="maDbListService"
 * @spring.property name="maDbListDAO" ref="maDbListDAO"
 */
public class MaDbListServiceImpl implements MaDbListService
{
    private MaDbListDAO maDbListDAO = null;
    
    private static int cntNum = 1;

    public MaDbListDAO getMaDbListDAO() 
    {
		return maDbListDAO;
	}

	public void setMaDbListDAO(MaDbListDAO maDbListDAO) 
	{
		this.maDbListDAO = maDbListDAO;
	}

	public List findDbList(MaDbListDTO maDbListDTO, User user)
    {      
	    List alarmCode = maDbListDAO.findAlarmCode(user);
	    Map resultMap = new HashMap();
	    Map ordNoMap = new HashMap();
	    
	    List list = new ArrayList() ;
	    for(Object obj : alarmCode)
	    {
	        Map alarmMap = (Map)obj;
	        	        
	        resultMap.put(String.valueOf(alarmMap.get("CODE")), String.valueOf(alarmMap.get("DESCRIPTION")));
	        ordNoMap.put(String.valueOf(alarmMap.get("CODE")), String.valueOf(alarmMap.get("ORDNO")));
	    }

	    if(!resultMap.isEmpty())
	        list = maDbListDAO.findDbList(maDbListDTO, resultMap, ordNoMap, user);
	    
        return list;
    }
}