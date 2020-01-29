package dream.consult.program.table.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.table.dao.LovRefColumnListDAO;
import dream.consult.program.table.dto.LovRefColumnListDTO;
import dream.consult.program.table.service.LovRefColumnListService;

/**
 * Ref���̺� �˾� ServiceImpl
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovRefColumnListServiceTarget"
 * @spring.txbn id="lovRefColumnListService"
 * @spring.property name="lovRefColumnListDAO" ref="lovRefColumnListDAO"
 */
public class LovRefColumnListServiceImpl implements LovRefColumnListService
{
    /** �۾��׷� �˾� DAO */
    private LovRefColumnListDAO lovRefColumnListDAO = null;

    public LovRefColumnListDAO getLovRefColumnListDAO() 
    {
		return lovRefColumnListDAO;
	}

	public void setLovRefColumnListDAO(LovRefColumnListDAO lovRefColumnListDAO) 
	{
		this.lovRefColumnListDAO = lovRefColumnListDAO;
	}

	public List findWkCtrList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser)
    {
        List list = null;
        return lovRefColumnListDAO.findWkCtrList(lovRefColumnListDTO, loginUser);
/*        
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
        return resultList;*/
    }
	
	public List findColList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser)
    {
        List list = null;
        return lovRefColumnListDAO.findColList(lovRefColumnListDTO, loginUser);
/*        
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
        return resultList;*/
    }
/*	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    Map rMap = null;
	    //Parent Dept Id �� 0�� Equipment�� ã���ּ���.
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
	}*/
}