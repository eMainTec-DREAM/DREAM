package mobile.dream.org.wrkgrp.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import mobile.dream.org.wrkgrp.dao.OrgWkCtrLovListDAO;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;
import mobile.dream.org.wrkgrp.service.OrgWkCtrLovListService;


/**
 * 작업그룹 팝업 ServiceImpl
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="orgWkCtrLovListServiceTarget"
 * @spring.txbn id="orgWkCtrLovListService"
 * @spring.property name="orgWkCtrLovListDAO" ref="orgWkCtrLovListDAO"
 */
public class OrgWkCtrLovListServiceImpl implements OrgWkCtrLovListService
{
    /** 작업그룹 팝업 DAO */
    private OrgWkCtrLovListDAO orgWkCtrLovListDAO = null;

    public OrgWkCtrLovListDAO getOrgWkCtrLovListDAO() 
    {
		return orgWkCtrLovListDAO;
	}

	public void setOrgWkCtrLovListDAO(OrgWkCtrLovListDAO orgWkCtrLovListDAO) 
	{
		this.orgWkCtrLovListDAO = orgWkCtrLovListDAO;
	}

	public List findWkCtrList(OrgWkCtrLovListDTO orgWkCtrLovListDTO, User loginUser)
    {
        List list = null;
        list = orgWkCtrLovListDAO.findWkCtrList(orgWkCtrLovListDTO, loginUser);
        
        /*List resultList = new ArrayList();

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
            
        }*/
        return list;
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