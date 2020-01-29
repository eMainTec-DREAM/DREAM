package dream.asset.categ.list.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;

import dream.asset.categ.list.dao.LovEqCtgListDAO;
import dream.asset.categ.list.dto.LovEqCtgListDTO;
import dream.asset.categ.list.form.LovEqCtgListForm;
import dream.asset.categ.list.service.LovEqCtgListService;

/**
 * 설비종류팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEqCtgListServiceTarget"
 * @spring.txbn id="lovEqCtgListService"
 * @spring.property name="lovEqCtgListDAO" ref="lovEqCtgListDAO"
 */
public class LovEqCtgListServiceImpl implements LovEqCtgListService
{
    /** 설비종류팝업 DAO */
    private LovEqCtgListDAO lovEqCtgListDAO = null;

    public LovEqCtgListDAO getLovEqCtgListDAO() 
    {
		return lovEqCtgListDAO;
	}

	public void setLovEqCtgListDAO(LovEqCtgListDAO lovEqCtgListDAO) 
	{
		this.lovEqCtgListDAO = lovEqCtgListDAO;
	}

	public List findEqCtgList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser)
    {
        List list = null;
        list = lovEqCtgListDAO.findEqCtgList(lovEqCtgListDTO, loginUser);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQCTGID"));
            String pLocId = String.valueOf(resultM.get("PEQCTGID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PEQCTGID", "EQCTGID");
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
	    //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqLocId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}

	@Override
	public List findEqCtgAcList(LovEqCtgListDTO lovEqCtgListDTO, User user, LovEqCtgListForm lovEqCtgListForm) {
		String jsonParam    = lovEqCtgListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		List list =  lovEqCtgListDAO.findEqCtgAcList(lovEqCtgListDTO, user ,conditionMap);
		
		List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQCTG_ID"));
            String pLocId = String.valueOf(resultM.get("P_EQCTG_ID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "P_EQCTG_ID", "EQCTG_ID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
        
	}
}