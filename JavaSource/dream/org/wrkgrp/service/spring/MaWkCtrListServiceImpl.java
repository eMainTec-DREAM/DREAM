package dream.org.wrkgrp.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.wrkgrp.dao.MaWkCtrListDAO;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;
import dream.org.wrkgrp.service.MaWkCtrListService;

/**
 * 작업그룹 - 목록 serviceimpl
 * @author kim21017
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maWkCtrListServiceTarget"
 * @spring.txbn id="maWkCtrListService"
 * @spring.property name="maWkCtrListDAO" ref="maWkCtrListDAO"
 */
public class MaWkCtrListServiceImpl implements MaWkCtrListService
{
    private MaWkCtrListDAO maWkCtrListDAO = null;

    public MaWkCtrListDAO getMaWkCtrListDAO() 
    {
		return maWkCtrListDAO;
	}

	public void setMaWkCtrListDAO(MaWkCtrListDAO maWkCtrListDAO) 
	{
		this.maWkCtrListDAO = maWkCtrListDAO;
	}

	public List findWkCtrList(MaWkCtrCommonDTO maWkCtrCommonDTO, User user)
    {      
        List list = null;
        list = maWkCtrListDAO.findWkCtrList(maWkCtrCommonDTO, user);
        
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

	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWkCtrListDAO.deleteWkCtr(compNo, id);
            }
        
        return result;
    }
	
	   
    public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
        List subList = new ArrayList();
        Map rMap = null;
        //Parent WkCtr Id 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String wkCtrId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(wkCtrId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }
}

