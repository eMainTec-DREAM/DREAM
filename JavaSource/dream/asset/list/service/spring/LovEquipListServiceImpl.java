package dream.asset.list.service.spring;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.list.dao.LovEquipListDAO;
import dream.asset.list.dto.LovEquipListDTO;
import dream.asset.list.form.LovEquipListForm;
import dream.asset.list.service.LovEquipListService;

/**
 * ¼³ºñÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEquipListServiceTarget"
 * @spring.txbn id="lovEquipListService"
 * @spring.property name="lovEquipListDAO" ref="lovEquipListDAO"
 */
public class LovEquipListServiceImpl implements LovEquipListService
{
    /** ¼³ºñÆË¾÷ DAO */
    private LovEquipListDAO lovEquipListDAO = null;

    public LovEquipListDAO getLovEquipListDAO() 
    {
		return lovEquipListDAO;
	}

	public void setLovEquipListDAO(LovEquipListDAO lovEquipListDAO) 
	{
		this.lovEquipListDAO = lovEquipListDAO;
	}

	public List findEquipList(LovEquipListDTO lovEquipListDTO, User loginUser, LovEquipListForm lovEquipListForm)
    {
        List resultList = null;
        
        String jsonParam    = lovEquipListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        resultList = lovEquipListDAO.findEquipList(lovEquipListDTO, loginUser, conditionMap);
        
        return resultList;
    }
	
	public LovEquipListDTO setJsonParm(LovEquipListDTO lovEquipListDTO) throws ClassNotFoundException, ParseException {
		
		String extCode = lovEquipListDTO.getExtCode1();
    	
    	Class<?> clss = LovEquipListDTO.class;
    	
        Class<?> cls = Class.forName(clss.getName());
        //Object obj = cls.newInstance();
        
    	if(!"".equals(extCode))
    	{
    		JSONParser jp = new JSONParser();
	    	JSONObject jsonObj = (JSONObject)jp.parse(extCode);
	    	Iterator<?> keys = jsonObj.keySet().iterator();
	    	Method configMethod = null;
	    	
	    	while( keys.hasNext() ) {
	    	    String key = (String)keys.next();
	    	    
	    	    String[] keyArray = new String[2];
	    	    if(key.indexOf(".") > 0) keyArray = key.split("\\.");
	    	    else keyArray[1] = key;
	    	    
	    	    keyArray[1] = "set"+keyArray[1].substring(0, 1).toUpperCase() + keyArray[1].substring(1);

	            try
	            {
	                configMethod = cls.getMethod(keyArray[1], new Class[]{java.lang.String.class});
	                configMethod.invoke(lovEquipListDTO, new Object[]{new String((String)jsonObj.get(key))});
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	            }
	            
	    	}
    	}
		return lovEquipListDTO;
	}

	@Override
	public List findEquipAcList(LovEquipListDTO lovEquipListDTO, User user, LovEquipListForm lovEquipListForm) {
		String jsonParam    = lovEquipListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovEquipListDAO.findEquipAcList(lovEquipListDTO, user ,conditionMap);
	}
	
	public String findTotalCount(LovEquipListDTO lovEquipListDTO, User loginUser, LovEquipListForm lovEquipListForm)  throws Exception
    {
		String jsonParam    = lovEquipListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        return lovEquipListDAO.findTotalCount(lovEquipListDTO, loginUser, conditionMap);
    }
}