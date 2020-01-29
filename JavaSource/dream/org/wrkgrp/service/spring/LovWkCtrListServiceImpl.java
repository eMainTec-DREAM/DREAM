package dream.org.wrkgrp.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.util.CommonUtil;
import dream.org.wrkgrp.dao.LovWkCtrListDAO;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;
import dream.org.wrkgrp.form.LovWkCtrListForm;
import dream.org.wrkgrp.service.LovWkCtrListService;

/**
 * 작업그룹 팝업 ServiceImpl
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWkCtrListServiceTarget"
 * @spring.txbn id="lovWkCtrListService"
 * @spring.property name="lovWkCtrListDAO" ref="lovWkCtrListDAO"
 */
public class LovWkCtrListServiceImpl implements LovWkCtrListService
{
    /** 작업그룹 팝업 DAO */
    private LovWkCtrListDAO lovWkCtrListDAO = null;

    public LovWkCtrListDAO getLovWkCtrListDAO() 
    {
		return lovWkCtrListDAO;
	}

	public void setLovWkCtrListDAO(LovWkCtrListDAO lovWkCtrListDAO) 
	{
		this.lovWkCtrListDAO = lovWkCtrListDAO;
	}

	public List findWkCtrList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser)
    {
        List list = null;
        list = lovWkCtrListDAO.findWkCtrList(lovWkCtrListDTO, loginUser);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String wkCtrId = String.valueOf(resultM.get("WKCTRID"));
            String paWkCtrId = String.valueOf(resultM.get("PAWKCTRID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(wkCtrId, list, "PAWKCTRID", "WKCTRID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	
	public List findWkCtrAcList(LovWkCtrListForm lovWkCtrListForm, User loginUser)
    {
		LovWkCtrListDTO lovWkCtrListDTO = lovWkCtrListForm.getLovWkCtrListDTO();
        
		String code         = lovWkCtrListForm.getCode();    //Search Value
        String keyCode      = lovWkCtrListForm.getKeyCode();  //Search Column
        String codeType     = lovWkCtrListForm.getCodeType(); //Table
        String jsonParam    = lovWkCtrListForm.getParam();  //Condition
        String jaonCol      = lovWkCtrListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        List list = null;
        list = CommonUtil.makeJsonNoId(lovWkCtrListDAO.findWkCtrAcList(lovWkCtrListDTO, loginUser, columnMap ,conditionMap));

        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String wkCtrId = String.valueOf(resultM.get("WKCTR_ID"));
            String pWkCtrId = String.valueOf(resultM.get("P_WKCTR_ID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(wkCtrId, list, "P_WKCTR_ID", "WKCTR_ID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	
	public String findTotalCount(LovWkCtrListForm lovWkCtrListForm, User loginUser)
    {      
		LovWkCtrListDTO lovWkCtrListDTO = lovWkCtrListForm.getLovWkCtrListDTO();
        
		String code         = lovWkCtrListForm.getCode();    //Search Value
        String keyCode      = lovWkCtrListForm.getKeyCode();  //Search Column
        String codeType     = lovWkCtrListForm.getCodeType(); //Table
        String jsonParam    = lovWkCtrListForm.getParam();  //Condition
        String jaonCol      = lovWkCtrListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovWkCtrListDAO.findTotalCount(lovWkCtrListDTO,loginUser, columnMap ,conditionMap);
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
                String id = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(id, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}