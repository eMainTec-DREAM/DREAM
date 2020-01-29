package dream.asset.categ.list.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.categ.list.dao.LovEqCtgAsmbListDAO;
import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;
import dream.asset.categ.list.form.LovEqCtgAsmbListForm;
import dream.asset.categ.list.service.LovEqCtgAsmbListService;

/**
 * 설비종류작업부위팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEqCtgAsmbListServiceTarget"
 * @spring.txbn id="lovEqCtgAsmbListService"
 * @spring.property name="lovEqCtgAsmbListDAO" ref="lovEqCtgAsmbListDAO"
 */
public class LovEqCtgAsmbListServiceImpl implements LovEqCtgAsmbListService
{
    /** 설비종류작업부위팝업 DAO */
    private LovEqCtgAsmbListDAO lovEqCtgAsmbListDAO = null;

    public LovEqCtgAsmbListDAO getLovEqCtgAsmbListDAO() 
    {
		return lovEqCtgAsmbListDAO;
	}

	public void setLovEqCtgAsmbListDAO(LovEqCtgAsmbListDAO lovEqCtgAsmbListDAO) 
	{
		this.lovEqCtgAsmbListDAO = lovEqCtgAsmbListDAO;
	}

	public List findEqCtgAsmbList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEqCtgAsmbListDAO.findEqCtgAsmbList(lovEqCtgAsmbListDTO,loginUser);
        
        return resultList;
    }

    @Override
    public List findEqCtgAsmbACList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser, LovEqCtgAsmbListForm lovEqCtgAsmbListForm)
    {
        String jsonParam    = lovEqCtgAsmbListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        List list = null;
        list = lovEqCtgAsmbListDAO.findEqCtgAsmbACList(lovEqCtgAsmbListDTO, loginUser ,conditionMap);

        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String eqCtgAsmbId = String.valueOf(resultM.get("eq_ctg_asmb_id"));
            String pEqCtgAsmbId = String.valueOf(resultM.get("pEqCtgAsmbId"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(eqCtgAsmbId, list, "pEqCtgAsmbId", "eq_ctg_asmb_id");
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
    
    public String findTotalCount(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO,User user)  throws Exception
    {
        return lovEqCtgAsmbListDAO.findTotalCount(lovEqCtgAsmbListDTO, user);
    }
}