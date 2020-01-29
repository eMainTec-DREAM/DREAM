package mobile.dream.main.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;
import mobile.dream.main.dao.MobileMainDAO;
import mobile.dream.main.service.MobileMainService;

/**
 * 메인 Service
 * @author  javaworker
 * @version $Id: MainServiceImpl.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
 * @since   1.0
 * @spring.bean id="mobileMainServiceTarget"
 * @spring.txbn id="mobileMainService"
 * @spring.property name="mobileMainDAO" ref="mobileMainDAO"
 */
public class MobileMainServiceImpl implements MobileMainService
{
    /** Main DAO */
    private MobileMainDAO mobileMainDAO;
    
    /**
     * 행당 User의 그룹권한에 해당하는 
     * Menu를 ArrayList로 리턴한다.
     * 하나의 sub menu는 하나의 HashTable로 구성된다.
     * @author  javaworker
     * @version $Id: MainServiceImpl.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return ArrayList 0 Main Menu
     *                   1 이하 sub Menu
     * @throws Exception
     */
    public ArrayList findMenuList(String userGroup, User loginUser)
    {
    	ArrayList list =  (ArrayList)mobileMainDAO.findMenusList("0", userGroup,loginUser);
        
        return list;
    }
    
    /**
     * parent menu id 의 이하 sub menu를 조회하여 자장한다.
     * @author  javaworker
     * @version $Id: MainServiceImpl.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param parentId  : parent id
     * @param userGroup : 유저 그룹 권한
     * @return sub menu
     * @throws Exception
     */
    private Hashtable findSubMenus(String parentId, String userGroup, User loginUser) 
    {
    	Hashtable menuHash = new Hashtable();
    	
    	// 해당 sub menu의 parent Id를 셋팅한다.
    	menuHash.put("parentId", parentId);
    	
    	// Sub Menu 를 String [][] 로 저장한다. 
    	//String[][] subMenu = mobileMainDAO.findMenus(parentId, userGroup, loginUser);
    	
    	menuHash.put("menu", mobileMainDAO.findMenus(parentId, userGroup, loginUser));
    	
    	return menuHash;
    }
    
    /**
     * Dhtmlx Json Menu
     * @param userGroup
     * @param loginUser
     * @return
     */
    public Map findMenuJsonList(String userGroup, User loginUser)
    {
    	List list =  mobileMainDAO.findMenusJsonList("0", userGroup,loginUser);
        
//    	List rList = CommonUtil.makeJson(list);
    	ArrayList resultList = new ArrayList();

    	Map finalMap = new HashMap();
    	
    	int mainMenuCnt = 0;
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String menuId = String.valueOf(resultM.get("MENUID"));
            String pMenuId = String.valueOf(resultM.get("PMENUID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals("1"))
            {
                List subList = getSubJsonList(menuId, list, "PMENUID", "MENUID");

                resultM.put("USERDATA", makeUserData(resultM));
                if(subList.size() > 0)
                { 
                	resultM.put("ITEM", subList);
                	resultM.put("CHILD", subList.size());
                }

                resultList.add(resultM);
                mainMenuCnt++;
            }
        }
        
        finalMap.put("ID", "0");
        finalMap.put("CHILD", mainMenuCnt);
        finalMap.put("ITEM", resultList);
        
        return finalMap;
    }
    
    public List getSubJsonList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    Map rMap = null;
	    //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
            	rMap.put("USERDATA", makeUserData(rMap));
                String menuId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(menuId, resultList, pCodeCol, codeCol);
                if(list.size() > 0) 
                {
                	rMap.put("ITEM", list);
                	rMap.put("CHILD", list.size());
                }
                subList.add(rMap);
            }
        }
        
        return subList;
	}
    
    public List makeUserData(Map<String,String> resultMap)
    {
    	ArrayList resultList = new ArrayList();
    	Map contMap = null;
    	
    	for( String key : resultMap.keySet() ){
    		
    		if("ID".equals(key) || "TEXT".equals(key)) continue;
    		
    		
    		contMap = new HashMap();
            contMap.put("name", key);
            contMap.put("content", resultMap.get(key));
            resultList.add(contMap);
		}

    	return resultList;
    }
    
    public ArrayList findGrpMenuList(String userGroup, User loginUser)
    {      
        List list =  mobileMainDAO.findMenuList(userGroup,loginUser);
        
        ArrayList resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String menuId = String.valueOf(resultM.get("MENUID"));
            String pMenuId = String.valueOf(resultM.get("PMENUID"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals("0"))
            {
                List subList = getSubList(menuId, list, "PMENUID", "MENUID");
                if(subList.size() > 0) resultM.put("subMenu", subList);

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
                String menuId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(menuId, resultList, pCodeCol, codeCol);
                if(list.size() > 0) rMap.put("subMenu", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}


	public MobileMainDAO getMobileMainDAO() {
		return mobileMainDAO;
	}

	public void setMobileMainDAO(MobileMainDAO mobileMainDAO) {
		this.mobileMainDAO = mobileMainDAO;
	}

	public String[][] findAdminMenu(String userGroup, User loginUser)
    {
        return mobileMainDAO.findAdminMenu(userGroup, loginUser);
    }

    public List findCheckList(User loginUser)
    {
        return mobileMainDAO.findCheckList(loginUser);
    }
    
    public List findNoMenuList(String userGroup)
    {
        return mobileMainDAO.findNoMenuList(userGroup);
    }
    
    @Override
    public List findNoticeList(User loginUser)
    {
        return mobileMainDAO.findNoticeList(loginUser);
    }

    @Override
    public List findBoardList(User loginUser)
    {
        return mobileMainDAO.findBoardList(loginUser);
    }
    
    @Override
    public AppCntDTO getAppCntInfo(User loginUser)
    {
        return mobileMainDAO.getAppCntInfo(loginUser);
    }

    @Override
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser)
    {
        return mobileMainDAO.getMyWorkCntInfo(loginUser);
    }

    @Override
    public WorkNoticeCntDTO getWorkNoticeCntInfo(User loginUser)
    {
        return mobileMainDAO.getWorkNoticeCntInfo();
    }
    
    @Override
    public String getVsPostion()
    {
        return mobileMainDAO.getVsPostion();
    }

    public InspChartDTO getInspChart()
    {
        return mobileMainDAO.getInspChart();
    }
    
    public PartChartDTO getPartChart() throws Exception
    {
        return mobileMainDAO.getPartChart();
    }


    public List findUserMenuList(User loginUser)
    {
        return mobileMainDAO.findUserMenuList(loginUser);
    }

    public String[][] findLinkedMenu(User loginUser)
    {
        return mobileMainDAO.findLinkedMenu(loginUser);
    }

    public Hashtable findPageFields(User loginUser)
    {
        Hashtable pageFields = new Hashtable();

        List allPageFields = mobileMainDAO.findPageFields(loginUser);

        String fileName;
        for(Object ofields: allPageFields)
        {
            Map fields = (Map)ofields;
            fileName = String.valueOf(fields.get("FILENAME"));
            
            if(pageFields.containsKey(fileName)) continue;
            
            ArrayList resultList = new ArrayList();

            for(Object fieldTems: allPageFields)
            {
                Map fieldTem = (Map)fieldTems;
                
                if(fileName.equals(fieldTem.get("FILENAME")))
                {
                    resultList.add(fieldTem);
                }
            }
            
            //각 페이지에 필드 리스트가 세팅됨 
            pageFields.put(fileName, resultList);
        }
        return pageFields;
    }

	public List findMainList(User loginUser) {
		// TODO Auto-generated method stub
		return mobileMainDAO.findMainList(loginUser);
	}
}