package mobile.dream.org.dept.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import mobile.dream.org.dept.dao.OrgDeptLovListDAO;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;
import mobile.dream.org.dept.service.OrgDeptLovListService;

/**
 * 부서팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="orgDeptLovListServiceTarget"
 * @spring.txbn id="orgDeptLovListService"
 * @spring.property name="orgDeptLovListDAO" ref="orgDeptLovListDAO"
 */
public class OrgDeptLovListServiceImpl implements OrgDeptLovListService
{
    /** 부서팝업 DAO */
    private OrgDeptLovListDAO orgDeptLovListDAO = null;

    public OrgDeptLovListDAO getOrgDeptLovListDAO() 
    {
		return orgDeptLovListDAO;
	}

	public void setOrgDeptLovListDAO(OrgDeptLovListDAO orgDeptLovListDAO) 
	{
		this.orgDeptLovListDAO = orgDeptLovListDAO;
	}

	public List findDeptList(OrgDeptLovListDTO orgDeptLovListDTO, User loginUser)
    {
        List list = null;
        list = orgDeptLovListDAO.findDeptList(orgDeptLovListDTO, loginUser);
        
//        List resultList = new ArrayList();
//
//        if(list != null)
//        for(Object resultMap : list)
//        {
//            Map resultM = (Map)resultMap;
//            
//            String deptId = String.valueOf(resultM.get("DEPTID"));
//            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
//            String minLvl = String.valueOf(resultM.get("MINLVL"));
//            String curLvl = String.valueOf(resultM.get("LEVEL"));
//            
//            if(curLvl.equals(minLvl))
//            {
//                List subList = getSubList(deptId, list, "PDEPTID", "DEPTID");
//                if(subList.size() > 0) resultM.put("rows", subList);
//
//                resultList.add(resultM);
//            }
//            
//        }
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
                String eqDeptId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqDeptId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}