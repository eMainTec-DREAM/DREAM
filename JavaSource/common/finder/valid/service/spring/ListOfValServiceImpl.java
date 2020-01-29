package common.finder.valid.service.spring;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.finder.valid.dao.ListOfValDAO;
import common.finder.valid.dto.ListOfValDTO;
import common.finder.valid.form.ListOfValForm;
import common.finder.valid.service.ListOfValService;

/**
 * List Of Value ServiceImpl
 * @author  javaworker
 * @version $Id: ListOfValServiceImpl.java,v 1.5 2015/01/09 00:16:42 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="listOfValServiceTarget"
 * @spring.txbn id="listOfValService"
 * @spring.property name="listOfValDAO" ref="listOfValDAO"
 */
public class ListOfValServiceImpl implements ListOfValService
{
    /** List Of Value DAO */
    private ListOfValDAO listOfValDAO = null;

    public ListOfValDAO getListOfValDAO()
    {
        return listOfValDAO;
    }

    public void setListOfValDAO(ListOfValDAO listOfValDAO)
    {
        this.listOfValDAO = listOfValDAO;
    }

    public List findUsrDirList(ListOfValDTO listOfValDTO)
    {
        List resultList = null;
		resultList = listOfValDAO.findUsrDirList(listOfValDTO);
        
        return resultList;
    }
    
    public List findSysDirList(ListOfValDTO listOfValDTO, User user) throws ParseException 
    {
    	List resultList = null;
		resultList = listOfValDAO.findSysDirList(listOfValDTO, user);
        
        return resultList;
    }
    
    public List findCompList(ListOfValDTO listOfValDTO, User user) throws ParseException 
    {
    	List resultList = null;
    	resultList = listOfValDAO.findCompList(listOfValDTO, user);
    	
    	return resultList;
    }

    public List findTableList(ListOfValDTO listOfValDTO, User user)
    {
        String tableType = listOfValDTO.getCodeType();
        List resultList = null;
        
        // Table Type

        if ("TAMENU".equals(tableType))
        {
            resultList = listOfValDAO.findMenuList(listOfValDTO);
        }
        else if ("TACDUSRD".equals(tableType))
        {
        	resultList = listOfValDAO.findCdUsrCdList(listOfValDTO);
        }
        else if ("TAEMPNAME".equals(tableType))
        {
            resultList = listOfValDAO.findEmpNameList(listOfValDTO);
        }
        else if ("TAEMPID".equals(tableType))
        {
            resultList = listOfValDAO.findEmpNoList(listOfValDTO);
        }
        else if ("TAUSRGRP".equals(tableType))
        {
            resultList = listOfValDAO.findUsrGrpList(listOfValDTO);
        }
        else if ("TADEPT".equals(tableType))
        {
            resultList = listOfValDAO.findDeptList(listOfValDTO);
        }
        else if ("TAEQLOC".equals(tableType))
        {
            resultList = listOfValDAO.findEqLocList(listOfValDTO);
        }
        else if ("TAEQLOCFULL".equals(tableType))
        {
            resultList = listOfValDAO.findEqLocFullList(listOfValDTO);
        }
        else if ("TAEQCTG".equals(tableType))
        {
            resultList = listOfValDAO.findEqCtgList(listOfValDTO);
        }
        else if ("TAEQCTGFULL".equals(tableType))
        {
            resultList = listOfValDAO.findEqCtgFullList(listOfValDTO);
        }
        else if ("YN".equals(tableType))
        {
            resultList = listOfValDAO.findYnList(listOfValDTO);
        }
        else if ("LEVEL".equals(tableType))
        {
            resultList = listOfValDAO.findLevelList(listOfValDTO);
        }
        else if ("TAUSER".equals(tableType))
        {
            resultList = listOfValDAO.findUserList(listOfValDTO);
        }
        else if ("TAFAILUREFS".equals(tableType)||"TAFAILUREMO".equals(tableType)||"TAFAILURECA".equals(tableType)||"TAFAILURERE".equals(tableType))
        {
            resultList = listOfValDAO.findFailureList(listOfValDTO,user);
        }
        else if ("TAWAREHOUSE".equals(tableType))
        {
            resultList = listOfValDAO.findWcodeList(listOfValDTO);
        }
        else if ("PM_TYPE".equals(tableType))
        {
            resultList = listOfValDAO.findPmTypeList(listOfValDTO,user);
        }
        else if ("TAPLANT".equals(tableType))
        {
            resultList = listOfValDAO.findPlantList(listOfValDTO);
        }
        else if ("TAEQTOOL".equals(tableType))
        {
            resultList = listOfValDAO.findEqToolList(listOfValDTO);
        }
        else if ("TACRITYLIST".equals(tableType))
        {
            resultList = listOfValDAO.findCrityList(listOfValDTO);
        }
        else if ("TAPMTASKMAPLIST".equals(tableType))
        {
            resultList = listOfValDAO.findTaskMapList(listOfValDTO);
        }
        return resultList;
    }

	@Override
	public ListOfValDTO setJsonParm(ListOfValDTO listOfValDTO) throws ClassNotFoundException, ParseException {
		
		String extCode = listOfValDTO.getExtCode1();
    	
    	Class<?> clss = ListOfValDTO.class;
    	
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
	                configMethod.invoke(listOfValDTO, new Object[]{new String((String)jsonObj.get(key))});
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	            }
	            
	    	}
    	}
		return listOfValDTO;
	}

	@Override
	public List findAcSysDirList(ListOfValDTO listOfValDTO, User user, ListOfValForm listOfValForm) {
		
        String jsonParam    = listOfValForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return listOfValDAO.findAcSysDirList(listOfValDTO, user ,conditionMap);
	}
}