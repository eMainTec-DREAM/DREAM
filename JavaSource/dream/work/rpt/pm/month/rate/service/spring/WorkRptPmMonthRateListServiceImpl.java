package dream.work.rpt.pm.month.rate.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dream.work.rpt.pm.month.rate.dao.WorkRptPmMonthRateListDAO;
import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;
import dream.work.rpt.pm.month.rate.service.WorkRptPmMonthRateListService;

/**
 * 월별점검실행율 serviceimpl
 * @author sy.yang
 * @version $Id: WorkRptPmMonthRateListServiceImpl.java,v 1.0 2015/12/02 09:12:51 sy.yang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workRptPmMonthRateListServiceTarget"
 * @spring.txbn id="workRptPmMonthRateListService"
 * @spring.property name="workRptPmMonthRateListDAO" ref="workRptPmMonthRateListDAO"
 */
public class WorkRptPmMonthRateListServiceImpl implements WorkRptPmMonthRateListService
{
    private WorkRptPmMonthRateListDAO workRptPmMonthRateListDAO = null;

    public WorkRptPmMonthRateListDAO getWorkRptPmMonthRateListDAO() {
		return workRptPmMonthRateListDAO;
	}

	public void setWorkRptPmMonthRateListDAO(WorkRptPmMonthRateListDAO workRptPmMonthRateListDAO) {
		this.workRptPmMonthRateListDAO = workRptPmMonthRateListDAO;
	}

	public List findWoList(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO)
    {      
		List list = workRptPmMonthRateListDAO.findWoList(workRptPmMonthRateListDTO);
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