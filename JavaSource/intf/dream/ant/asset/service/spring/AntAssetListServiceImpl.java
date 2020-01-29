package intf.dream.ant.asset.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.util.CommonUtil;
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.asset.dao.AntAssetListDAO;
import intf.dream.ant.asset.service.AntAssetListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antAssetListServiceTarget"
 * @spring.txbn id="antAssetListService"
 * @spring.property name="antAssetListDAO" ref="antAssetListDAO"
 */
public class AntAssetListServiceImpl implements AntAssetListService
{
    private AntAssetListDAO antAssetListDAO = null;

	public AntAssetListDAO getAntAssetListDAO() {
		return antAssetListDAO;
	}
	public void setAntAssetListDAO(AntAssetListDAO antAssetListDAO) {
		this.antAssetListDAO = antAssetListDAO;
	}
	
	public List findAssetList(Map map) throws Exception
	{      
		return antAssetListDAO.findAssetList(map);
	}
	public List findAssetFileList(Map map) throws Exception
	{      
		return antAssetListDAO.findAssetFileList(map);
	}

	public int saveAssetList(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			
            String equipId = antAssetListDAO.getNextSequence("SQAEQUIP_ID");
            //antAssetListDAO.insertAssetList(map, woReqId);
        	//change Sequence
            antAssetListDAO.changeFileSeq(map, equipId);
        }
        return resultQty;
	}

}

