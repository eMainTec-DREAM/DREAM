package dream.work.rpt.pm.monthnew.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.rpt.pm.monthnew.dao.WorkRptPmMonthNewListDAO;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;
import dream.work.rpt.pm.monthnew.service.WorkRptPmMonthNewListService;

/**
 * 신규점검등록현황 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmMonthNewListServiceTarget"
 * @spring.txbn id="workRptPmMonthNewListService"
 * @spring.property name="workRptPmMonthNewListDAO" ref="workRptPmMonthNewListDAO"
 */
public class WorkRptPmMonthNewListServiceImpl implements WorkRptPmMonthNewListService
{
    private WorkRptPmMonthNewListDAO workRptPmMonthNewListDAO = null;

    public WorkRptPmMonthNewListDAO getWorkRptPmMonthNewListDAO() {
		return workRptPmMonthNewListDAO;
	}

	public void setWorkRptPmMonthNewListDAO(WorkRptPmMonthNewListDAO workRptPmMonthNewListDAO) {
		this.workRptPmMonthNewListDAO = workRptPmMonthNewListDAO;
	}
	public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser)
    {      
		List list = workRptPmMonthNewListDAO.findConnList(workRptPmMonthNewListDTO, loginUser);
		List resultList = new ArrayList();
		
		if(list != null)
	        for(Object resultMap : list)
	        {
	            Map resultM = (Map)resultMap;
	            
	            String deptId  = String.valueOf(resultM.get("DEPTID"));
	            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
	            String minLvl  = String.valueOf(resultM.get("MINLVL"));
	            String curLvl  = String.valueOf(resultM.get("LVL"));
	            
	            if(curLvl.equals(minLvl))
	            {
	                List subList = getSubList(deptId, list, "PDEPTID", "DEPTID");
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
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String deptId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(deptId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}