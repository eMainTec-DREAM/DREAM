package dream.budget.plan.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.budget.plan.list.dao.MaPtDeptBgListDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.service.MaPtDeptBgListService;

/**
 * 부서별예산금액 목록
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtDeptBgListServiceTarget"
 * @spring.txbn id="maPtDeptBgListService"
 * @spring.property name="maPtDeptBgListDAO" ref="maPtDeptBgListDAO"
 */
public class MaPtDeptBgListServiceImpl implements MaPtDeptBgListService
{
    private MaPtDeptBgListDAO maPtDeptBgListDAO = null;

	public MaPtDeptBgListDAO getMaPtDeptBgListDAO() 
	{
		return maPtDeptBgListDAO;
	}

	public void setMaPtDeptBgListDAO(MaPtDeptBgListDAO maPtDeptBgListDAO) 
	{
		this.maPtDeptBgListDAO = maPtDeptBgListDAO;
	}
	
	public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
	{      
	    List list =  maPtDeptBgListDAO.findList(maPtBudgetCommonDTO, loginUser);
	    List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String deptId = String.valueOf(resultM.get("DEPTID"));
            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
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
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPtDeptBgListDAO.deleteList(id, loginUser);
            }
        
        return result;
    }
}

