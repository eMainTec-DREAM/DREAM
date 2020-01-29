package dream.consult.comp.dept.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.dept.dao.ConsultCompDeptListDAO;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.service.ConsultCompDeptListService;

/**
 * 보전부서 - 목록 serviceimpl
 * @author hyosung
 * @version
 * @since 1.0
 * 
 * @spring.bean id="consultCompDeptListServiceTarget"
 * @spring.txbn id="consultCompDeptListService"
 * @spring.property name="consultCompDeptListDAO" ref="consultCompDeptListDAO"
 */
public class ConsultCompDeptListServiceImpl implements ConsultCompDeptListService
{
    
    
    private ConsultCompDeptListDAO consultCompDeptListDAO = null;

    //리스트
    public ConsultCompDeptListDAO getConsultCompDeptListDAO() 
    {
		return consultCompDeptListDAO;
	}

    
	public void setConsultCompDeptListDAO(ConsultCompDeptListDAO consultCompDeptListDAO) 
	{
		this.consultCompDeptListDAO = consultCompDeptListDAO;
	}

	
	//
	public List findDeptList(ConsultCompDeptCommonDTO consultCompDeptCommonDTO,User user)
    {      
        List list = null;
        list = consultCompDeptListDAO.findDeptList(consultCompDeptCommonDTO,user);
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

	  @Override
	    public int deleteList(String[] deleteRows, String[] deleteRowsExt)
	            throws Exception
	    {
	      int result = 0;

	        if(!deleteRows.equals(null))
	            for(int i=0;i<deleteRows.length;i++)
	            {
	                result = result + consultCompDeptListDAO.deleteDept(deleteRows[i], deleteRowsExt[i]);
	            }
	        
	        return result;
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

