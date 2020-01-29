package dream.consult.consult.menu.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.consult.menu.dao.EhMenuValLovDAO;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;
import dream.consult.consult.menu.form.EhMenuValLovForm;
import dream.consult.consult.menu.service.EhMenuValLovService;

/**
 * 컨설트 메뉴 LOV - List Service implements
 * @author kim21017
 * @version $Id: EhMenuValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="ehMenuValLovServiceTarget"
 * @spring.txbn id="ehMenuValLovService"
 * @spring.property name="ehMenuValLovDAO" ref="ehMenuValLovDAO"
 */
public class EhMenuValLovServiceImpl implements EhMenuValLovService
{
	private EhMenuValLovDAO ehMenuValLovDAO = null;

	public List findList(EhMenuValLovForm ehMenuValLovForm, User user) throws Exception
    {      
		EhMenuValLovDTO ehMenuValLovDTO = ehMenuValLovForm.getEhMenuValLovDTO();
		
		String code         = ehMenuValLovForm.getCode();    //Search Value
        String keyCode      = ehMenuValLovForm.getKeyCode();  //Search Column
        String codeType     = ehMenuValLovForm.getCodeType(); //Table
        String jsonParam    = ehMenuValLovForm.getParam();  //Condition
        String jaonCol      = ehMenuValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

        List list = null;
        list = ehMenuValLovDAO.findList(ehMenuValLovDTO,user, columnMap ,conditionMap);
        
        List resultList = new ArrayList();
        if(list != null){
        	for(Object resultMap : list)
            {
                Map resultM = (Map)resultMap;
                
                String menuId = String.valueOf(resultM.get("EHMENU_ID"));
                String pmenuId = String.valueOf(resultM.get("P_EHMENU_ID"));
                String minLvl = String.valueOf(resultM.get("MINLVL"));
                String curLvl = String.valueOf(resultM.get("LEVEL"));

                if(curLvl.equals(minLvl))
                {
                    List subList = getSubList(menuId, list, "P_EHMENU_ID", "EHMENU_ID");
                    if(subList.size() > 0) resultM.put("rows", subList);

                    resultList.add(resultM);
                }           
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
                String menuId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(menuId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }

	public EhMenuValLovDAO getEhMenuValLovDAO() {
		return ehMenuValLovDAO;
	}

	public void setEhMenuValLovDAO(EhMenuValLovDAO ehMenuValLovDAO) {
		this.ehMenuValLovDAO = ehMenuValLovDAO;
	}
	
}

