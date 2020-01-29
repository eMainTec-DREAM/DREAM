package dream.asset.list.service.spring;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.list.dao.LovEqAsmbListDAO;
import dream.asset.list.dto.LovEqAsmbListDTO;
import dream.asset.list.form.LovEqAsmbListForm;
import dream.asset.list.service.LovEqAsmbListService;

/**
 * 설비부위팝업 ServiceImpl
 * @author  hyosun
 * @version $Id: LovEqAsmbListServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosun Exp $
 * @since   1.0
 *
 * @spring.bean id="lovEqAsmbListServiceTarget"
 * @spring.txbn id="lovEqAsmbListService"
 * @spring.property name="lovEqAsmbListDAO" ref="lovEqAsmbListDAO"
 */
public class LovEqAsmbListServiceImpl implements LovEqAsmbListService
{
    /** 설비부위팝업 DAO */
    private LovEqAsmbListDAO lovEqAsmbListDAO = null;

    public LovEqAsmbListDAO getLovEqAsmbListDAO() {
		return lovEqAsmbListDAO;
	}

	public void setLovEqAsmbListDAO(LovEqAsmbListDAO lovEqAsmbListDAO) {
		this.lovEqAsmbListDAO = lovEqAsmbListDAO;
	}


	
	public LovEqAsmbListDTO setJsonParm(LovEqAsmbListDTO lovEqAsmbListDTO, LovEqAsmbListForm lovEqAsmbListForm) throws ClassNotFoundException, ParseException {
		
		String extCode = lovEqAsmbListDTO.getExtCode1();
		
		if("".equals(extCode)) extCode =  lovEqAsmbListForm.getExtCode1();
    	
    	Class<?> clss = LovEqAsmbListDTO.class;
    	
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
	                configMethod.invoke(lovEqAsmbListDTO, new Object[]{new String((String)jsonObj.get(key))});
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	            }
	            
	    	}
    	}
		return lovEqAsmbListDTO;
	}

	
	

    public List findEqAsmbAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user,LovEqAsmbListForm lovEqAsmbListForm)
    {
       
        String jsonParam    = lovEqAsmbListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
       
        List list = null;
        list = lovEqAsmbListDAO.findEqAsmbAcList(lovEqAsmbListDTO, user,conditionMap);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String eqAsmbId = String.valueOf(resultM.get("eqasmb_id"));
            String pEqAsmbId = String.valueOf(resultM.get("PEQASMBID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(eqAsmbId, list, "PEQASMBID", "eqasmb_id");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	
    public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
        List subList = new ArrayList();
        Map rMap = null;
        //Parent Dept Id 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqDeptId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqDeptId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }
    
    public List findEqAsmbByPmAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user,LovEqAsmbListForm lovEqAsmbListForm)
    {
       
        String jsonParam    = lovEqAsmbListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
       
        List list = null;
        list = lovEqAsmbListDAO.findEqAsmbByPmAcList(lovEqAsmbListDTO, user,conditionMap);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String eqAsmbId = String.valueOf(resultM.get("eqasmb_id"));
            String pEqAsmbId = String.valueOf(resultM.get("PEQASMBID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(eqAsmbId, list, "PEQASMBID", "eqasmb_id");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	
}