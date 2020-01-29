package dream.asset.loc.list.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.loc.list.dao.LovMesLineListDAO;
import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.asset.loc.list.dto.LovMesLineListDTO;
import dream.asset.loc.list.form.LovMesLineListForm;
import dream.asset.loc.list.service.LovMesLineListService;

/**
 * MESLINE 팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovMesLineListServiceTarget"
 * @spring.txbn id="lovMesLineListService"
 * @spring.property name="lovMesLineListDAO" ref="lovMesLineListDAO"
 */
public class LovMesLineListServiceImpl implements LovMesLineListService
{
    /** MESLINE 팝업 DAO */
    private LovMesLineListDAO lovMesLineListDAO = null;

    public LovMesLineListDAO getLovMesLineListDAO() 
    {
		return lovMesLineListDAO;
	}

	public void setLovMesLineListDAO(LovMesLineListDAO lovMesLineListDAO) 
	{
		this.lovMesLineListDAO = lovMesLineListDAO;
	}

	public List findMesLineList(LovMesLineListDTO lovMesLineListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovMesLineListDAO.findMesLineList(lovMesLineListDTO, loginUser);
        
        return resultList;
    }

    @Override
    public List findMesLineACList(LovMesLineListForm lovMesLineListForm, User user)
    {
        LovMesLineListDTO lovMesLineListDTO = lovMesLineListForm.getLovMesLineListDTO();
        
        String jsonParam    = lovMesLineListForm.getParam();  //Condition
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        List list = null;
        list = lovMesLineListDAO.findMesLineACList(lovMesLineListDTO, user ,conditionMap);
        
        List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String mesLineId = String.valueOf(resultM.get("MES_LINE_ID"));
            String pPopMesId = String.valueOf(resultM.get("P_POP_MES_ID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(mesLineId, list, "P_POP_MES_ID", "MES_LINE_ID");
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