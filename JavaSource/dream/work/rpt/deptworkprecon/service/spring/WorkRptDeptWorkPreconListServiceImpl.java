package dream.work.rpt.deptworkprecon.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.rpt.deptworkprecon.dao.WorkRptDeptWorkPreconListDAO;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;
import dream.work.rpt.deptworkprecon.service.WorkRptDeptWorkPreconListService;

/**
 * 부서별 작업진행현황 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workRptDeptWorkPreconListServiceTarget"
 * @spring.txbn id="workRptDeptWorkPreconListService"
 * @spring.property name="workRptDeptWorkPreconListDAO" ref="workRptDeptWorkPreconListDAO"
 */
public class WorkRptDeptWorkPreconListServiceImpl implements WorkRptDeptWorkPreconListService
{
    private WorkRptDeptWorkPreconListDAO workRptDeptWorkPreconListDAO = null;

    public WorkRptDeptWorkPreconListDAO getWorkRptDeptWorkPreconListDAO() {
		return workRptDeptWorkPreconListDAO;
	}

	public void setWorkRptDeptWorkPreconListDAO(WorkRptDeptWorkPreconListDAO workRptDeptWorkPreconListDAO) {
		this.workRptDeptWorkPreconListDAO = workRptDeptWorkPreconListDAO;
	}
	public List findList(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser)
    {      
		List woTypeList = workRptDeptWorkPreconListDAO.findWoTypes(loginUser);
		workRptDeptWorkPreconListDTO.setWoTypeList(woTypeList);
		workRptDeptWorkPreconListDTO.setWoTypeCnt(Integer.toString(woTypeList.size()));
		
		List list = workRptDeptWorkPreconListDAO.findList(workRptDeptWorkPreconListDTO, loginUser);
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
	
	public String findWoTypes(User user)
    {   
		List woTypesList = workRptDeptWorkPreconListDAO.findWoTypes(user);
		String woTypes = ""; 
		
		Map rMap = null;
		for(Object object : woTypesList)
		{
			rMap = (Map)object;
			woTypes += ","+ rMap.get("WOTYPE") +"CNT";
		}
		
		return woTypes;
    }
}