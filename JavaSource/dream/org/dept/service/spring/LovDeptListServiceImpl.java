package dream.org.dept.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.org.dept.dao.LovDeptListDAO;
import dream.org.dept.dto.LovDeptListDTO;
import dream.org.dept.form.LovDeptListForm;
import dream.org.dept.service.LovDeptListService;

/**
 * 부서팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovDeptListServiceTarget"
 * @spring.txbn id="lovDeptListService"
 * @spring.property name="lovDeptListDAO" ref="lovDeptListDAO"
 */
public class LovDeptListServiceImpl implements LovDeptListService
{
    /** 부서팝업 DAO */
    private LovDeptListDAO lovDeptListDAO = null;

    public LovDeptListDAO getLovDeptListDAO() 
    {
		return lovDeptListDAO;
	}

	public void setLovDeptListDAO(LovDeptListDAO lovDeptListDAO) 
	{
		this.lovDeptListDAO = lovDeptListDAO;
	}

	public List findDeptList(LovDeptListDTO lovDeptListDTO, User loginUser)
    {
        List list = null;
        list = lovDeptListDAO.findDeptList(lovDeptListDTO, loginUser);
        
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

	@Override
	public List findDeptAcList(LovDeptListDTO lovDeptListDTO, User user, LovDeptListForm lovDeptListForm) {
		
		String code         = lovDeptListForm.getCode();    //Search Value
        String keyCode      = lovDeptListForm.getKeyCode();  //Search Column
        String codeType     = lovDeptListForm.getCodeType(); //Table
        String jsonParam    = lovDeptListForm.getParam();  //Condition
        String jaonCol      = lovDeptListForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

		List list = null;
        list = lovDeptListDAO.findDeptAcList(lovDeptListDTO, user, columnMap ,conditionMap);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String deptId = String.valueOf(resultM.get("DEPT_ID"));
            String pDeptId = String.valueOf(resultM.get("P_DEPT_ID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(deptId, list, "P_DEPT_ID", "DEPT_ID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
	}


}