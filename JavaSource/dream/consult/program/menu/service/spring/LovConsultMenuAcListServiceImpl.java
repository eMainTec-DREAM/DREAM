package dream.consult.program.menu.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.menu.dao.LovConsultMenuAcListDAO;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;
import dream.consult.program.menu.form.LovConsultMenuAcListForm;
import dream.consult.program.menu.service.LovConsultMenuAcListService;

/**
 * 컨설트용 메뉴팝업 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovConsultMenuAcListServiceTarget"
 * @spring.txbn id="lovConsultMenuAcListService"
 * @spring.property name="lovConsultMenuAcListDAO" ref="lovConsultMenuAcListDAO"
 */
public class LovConsultMenuAcListServiceImpl implements LovConsultMenuAcListService
{
    /** 메뉴 팝업 DAO */
    private LovConsultMenuAcListDAO lovConsultMenuAcListDAO = null;
    public LovConsultMenuAcListDAO getLovConsultMenuAcListDAO() 
    {
		return lovConsultMenuAcListDAO;
	}

	public void setLovConsultMenuAcListDAO(LovConsultMenuAcListDAO lovConsultMenuAcListDAO) 
	{
		this.lovConsultMenuAcListDAO = lovConsultMenuAcListDAO;
	}

	public List findMenuList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User loginUser)
    {
		//메뉴 리스트
        List list       = lovConsultMenuAcListDAO.findMenuList(lovConsultMenuAcListDTO, loginUser);
        List resultList = new ArrayList();
/*        System.out.println("실행");*/
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("MENUID"));
            String pLocId = String.valueOf(resultM.get("PMENUID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            String desc = String.valueOf(resultM.get("DESCRIPTION"));

            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PMENUID", "MENUID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }           
        }
       // System.out.println("리스트값");
       // System.out.println(resultList);
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
    public List findMenuAcList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User user,
            LovConsultMenuAcListForm lovConsultMenuAcListForm)
    {
        String jsonParam    = lovConsultMenuAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
      //메뉴 리스트
        List list       = lovConsultMenuAcListDAO.findMenuAcList(lovConsultMenuAcListDTO, user,conditionMap);
        List resultList = new ArrayList();
/*        System.out.println("실행");*/
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("MENU_ID"));
            String pLocId = String.valueOf(resultM.get("P_MENU_ID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));

            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "P_MENU_ID", "MENU_ID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }           
        }
       // System.out.println("리스트값");
       // System.out.println(resultList);
        return resultList;
    }
}