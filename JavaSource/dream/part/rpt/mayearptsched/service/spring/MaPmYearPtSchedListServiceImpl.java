package dream.part.rpt.mayearptsched.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.rpt.mayearptsched.dao.MaPmYearPtSchedListDAO;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;
import dream.part.rpt.mayearptsched.service.MaPmYearPtSchedListService;

/**
 * 연간부품사용일정 serviceimpl
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maPmYearPtSchedListServiceTarget"
 * @spring.txbn id="maPmYearPtSchedListService"
 * @spring.property name="maPmYearPtSchedListDAO" ref="maPmYearPtSchedListDAO"
 */
public class MaPmYearPtSchedListServiceImpl implements MaPmYearPtSchedListService
{
    private MaPmYearPtSchedListDAO maPmYearPtSchedListDAO = null;

    public MaPmYearPtSchedListDAO getMaPmYearPtSchedListDAO() 
    {
		return maPmYearPtSchedListDAO;
	}

	public void setMaPmYearPtSchedListDAO(MaPmYearPtSchedListDAO maPmYearPtSchedListDAO) 
	{
		this.maPmYearPtSchedListDAO = maPmYearPtSchedListDAO;
	}

	public List findYearList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user)
    {
		List list = maPmYearPtSchedListDAO.findYearList(maPmYearPtSchedCommonDTO, user);
		
		List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String deptId = String.valueOf(resultM.get("DEPTID"));
            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(deptId, list, "PDEPTID", "DEPTID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	
	public List findPartsList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
    {      
        return maPmYearPtSchedListDAO.findPartsList(maPmYearPtSchedCommonDTO, user);
    }
	
	public List findDateList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
    {      
        return maPmYearPtSchedListDAO.findDateList(maPmYearPtSchedCommonDTO, user);
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

	@Override
	public String findPartsTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
	{
	    return maPmYearPtSchedListDAO.findPartsTotalCount(maPmYearPtSchedCommonDTO, user);
	}

	@Override
	public String findDateTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
	{
	    return maPmYearPtSchedListDAO.findDateTotalCount(maPmYearPtSchedCommonDTO, user);
	}

}