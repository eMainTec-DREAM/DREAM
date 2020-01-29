package dream.asset.loc.list.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.loc.list.dao.LovEqLocListDAO;
import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.asset.loc.list.form.LovEqLocListForm;
import dream.asset.loc.list.service.LovEqLocListService;

/**
 * 위치분류팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEqLocListServiceTarget"
 * @spring.txbn id="lovEqLocListService"
 * @spring.property name="lovEqLocListDAO" ref="lovEqLocListDAO"
 */
public class LovEqLocListServiceImpl implements LovEqLocListService
{
    /** 위치분류팝업 DAO */
    private LovEqLocListDAO lovEqLocListDAO = null;

    public LovEqLocListDAO getLovEqLocListDAO() 
    {
		return lovEqLocListDAO;
	}

	public void setLovEqLocListDAO(LovEqLocListDAO lovEqLocListDAO) 
	{
		this.lovEqLocListDAO = lovEqLocListDAO;
	}

	public List findEqLocList(LovEqLocListDTO lovEqLocListDTO, User loginUser)
    {
        List list = lovEqLocListDAO.findEqLocList(lovEqLocListDTO, loginUser);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQLOCID"));
            String pLocId = String.valueOf(resultM.get("PEQLOCID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PEQLOCID", "EQLOCID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }
        return resultList;
    }
	public List findEqLocAcList(LovEqLocListForm lovEqLocListForm, User loginUser)
    {
		LovEqLocListDTO lovEqLocListDTO = lovEqLocListForm.getLovEqLocListDTO();
		
		String code         = lovEqLocListForm.getCode();    //Search Value
        String keyCode      = lovEqLocListForm.getKeyCode();  //Search Column
        String codeType     = lovEqLocListForm.getCodeType(); //Table
        String jsonParam    = lovEqLocListForm.getParam();  //Condition
        String jaonCol      = lovEqLocListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		List list = null;
		list = lovEqLocListDAO.findEqLocAcList(lovEqLocListDTO, loginUser, columnMap ,conditionMap);
        
		List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQLOC_ID"));
            String pLocId = String.valueOf(resultM.get("PEQLOCID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PEQLOCID", "EQLOC_ID");
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
}