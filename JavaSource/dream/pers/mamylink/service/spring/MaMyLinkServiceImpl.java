package dream.pers.mamylink.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dream.pers.mamylink.dao.MaMyLinkDAO;
import dream.pers.mamylink.dto.MaMyLinkDTO;
import dream.pers.mamylink.service.MaMyLinkService;

/**
 * 권한그룹 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maMyLinkServiceTarget"
 * @spring.txbn id="maMyLinkService"
 * @spring.property name="maMyLinkDAO" ref="maMyLinkDAO"
 */
public class MaMyLinkServiceImpl implements MaMyLinkService
{
    private MaMyLinkDAO maMyLinkDAO = null;
    
    private static int cntNum = 1;

    public MaMyLinkDAO getMaMyLinkDAO() 
    {
		return maMyLinkDAO;
	}

	public void setMaMyLinkDAO(MaMyLinkDAO maMyLinkDAO) 
	{
		this.maMyLinkDAO = maMyLinkDAO;
	}

	public List findUseGrpAuthList(MaMyLinkDTO maMyLinkDTO)
    {      
	    //메뉴 리스트
        List list       = maMyLinkDAO.findUsrGrpAuthList(maMyLinkDTO);
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

    public int deleteList(MaMyLinkDTO maMyLinkDTO, String[] menuIdArr, String[] stateArr) throws Exception
    {
        int result = 0;

        if(!menuIdArr.equals(null))
            for(int i = 0; menuIdArr.length > i ; i++)
            {   
                    //메뉴 권한 추가
                    if(stateArr[i].equals("true"))
                    {  
                        maMyLinkDAO.insertMenuAuth(menuIdArr[i], maMyLinkDTO);
                    }
                    else
                    {
                        //메뉴권한 삭제
                        maMyLinkDAO.deleteMenuAuth(menuIdArr[i], maMyLinkDTO);
                    }
            }  
        return result;
    }
}