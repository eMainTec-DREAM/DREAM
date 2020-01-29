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
 * ����Ʈ�� �޴��˾� ServiceImpl
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
    /** �޴� �˾� DAO */
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
		//�޴� ����Ʈ
        List list       = lovConsultMenuAcListDAO.findMenuList(lovConsultMenuAcListDTO, loginUser);
        List resultList = new ArrayList();
/*        System.out.println("����");*/
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
       // System.out.println("����Ʈ��");
       // System.out.println(resultList);
        return resultList;
    }
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
		List subList = new ArrayList();
	    Map rMap = null;
	    //Parent Equip Location Code �� 0�� Equipment�� ã���ּ���.
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
        
      //�޴� ����Ʈ
        List list       = lovConsultMenuAcListDAO.findMenuAcList(lovConsultMenuAcListDTO, user,conditionMap);
        List resultList = new ArrayList();
/*        System.out.println("����");*/
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
       // System.out.println("����Ʈ��");
       // System.out.println(resultList);
        return resultList;
    }
}