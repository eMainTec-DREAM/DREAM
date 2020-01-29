package dream.pers.mamymenu.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dream.pers.mamymenu.dao.MaMyMenuDAO;
import dream.pers.mamymenu.dto.MaMyMenuDTO;
import dream.pers.mamymenu.service.MaMyMenuService;

/**
 * 사용자메뉴 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maMyMenuServiceTarget"
 * @spring.txbn id="maMyMenuService"
 * @spring.property name="maMyMenuDAO" ref="maMyMenuDAO"
 */
public class MaMyMenuServiceImpl implements MaMyMenuService
{
    private MaMyMenuDAO maMyMenuDAO = null;
    
    private static int cntNum = 1;

    public MaMyMenuDAO getMaMyMenuDAO() 
    {
		return maMyMenuDAO;
	}

	public void setMaMyMenuDAO(MaMyMenuDAO maMyMenuDAO) 
	{
		this.maMyMenuDAO = maMyMenuDAO;
	}

	public List findUseGrpAuthList(MaMyMenuDTO maMyMenuDTO)
    {      
	    //메뉴 리스트
        List list       = maMyMenuDAO.findUsrGrpAuthList(maMyMenuDTO);
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

    public int deleteList(MaMyMenuDTO maMyMenuDTO, String[] menuIdArr, String[] stateArr) throws Exception
    {
        int result = 0;

        if(!menuIdArr.equals(null))
            for(int i = 0; menuIdArr.length > i ; i++)
            {   
                    //메뉴 권한 추가
                    if(stateArr[i].equals("true"))
                    {  
                        maMyMenuDAO.insertMenuAuth(menuIdArr[i], maMyMenuDTO);
                    }
                    else
                    {
                        //메뉴권한 삭제
                        maMyMenuDAO.deleteMenuAuth(menuIdArr[i], maMyMenuDTO);
                    }
            }  
        return result;
    }
}