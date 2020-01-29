package common.config.service.spring;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import common.bean.User;
import common.config.dao.ConfigConsultDAO;
import common.config.service.ConfigConsultService;

/**
 * 환경 설정용 Service
 * @author  javaworker
 * @version $Id: ConfigServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
 * @since   1.0
 *
 * @spring.bean id="configConsultServiceTarget"
 * @spring.txbn id="configConsultService"
 * @spring.property name="configConsultDAO" ref="configConsultDAO"
 */
public class ConfigConsultServiceImpl implements ConfigConsultService
{
    private ConfigConsultDAO configConsultDAO = null;

    public ConfigConsultDAO getConfigConsultDAO()
    {
        return configConsultDAO;
    }

    public void setConfigConsultDAO(ConfigConsultDAO configConsultDAO)
    {
        this.configConsultDAO = configConsultDAO;
    }
    
    public Hashtable findPageButtons()
    {
        Hashtable pageButton = new Hashtable();

        List allPageButton = configConsultDAO.findPageButton("");

        String pageId;
        for(Object buttons: allPageButton)
        {
            Map button = (Map)buttons;
            pageId = String.valueOf(button.get("FILENAME"));
            
            if(pageButton.containsKey(pageId)) continue;
            
            ArrayList resultList = new ArrayList();

            for(Object buttonsTem: allPageButton)
            {
                Map buttonTem = (Map)buttonsTem;
                
                if(pageId.equals(buttonTem.get("FILENAME")))
                {
                    resultList.add(buttonsTem);
                }
            }
            
            pageButton.put(pageId, resultList);
        }
        
        return pageButton;
    }
   
    public Hashtable findTabPages()
    {
        Hashtable tabPages = new Hashtable();

        List allTabPage = configConsultDAO.findAllTabPages();

        String pageId;
        for(Object tPage: allTabPage)
        {
            Map tabPage = (Map)tPage;
            pageId = String.valueOf(tabPage.get("PPAGENAME"));
            
            if(tabPages.containsKey(pageId)) continue;
            
            ArrayList resultList = new ArrayList();

            for(Object tabTems: allTabPage)
            {
                Map tabTem = (Map)tabTems;
                
                if(pageId.equals(tabTem.get("PPAGENAME")))
                {
                    resultList.add(tabTem);
                }
            }
            
            tabPages.put(pageId, resultList);
        }
        return tabPages;
    }
    
    
    public Hashtable findPages()
    {
        Hashtable pages = new Hashtable();

        List allPage = configConsultDAO.findAllPages();

        String pageId;
        for(Object tPage: allPage)
        {
            Map page = (Map)tPage;
            pageId = String.valueOf(page.get("FILENAME"));

            ArrayList resultList = new ArrayList();
            resultList.add(page);
            
            pages.put(pageId, resultList);
        }
        return pages;
        
    }
    public Hashtable findMenuPathTable()
    {
        List pageMenuList = configConsultDAO.findAllMenuPath();
        
        Hashtable menuPathTable = new Hashtable();
        
        int pageMenuSize = pageMenuList.size();

        Map tempMap = null;
        
        // List 수만큼 반복한다.
        for (int i=0; i<pageMenuSize; i++)
        {
            tempMap = (Map)pageMenuList.get(i);
            
            // 해당 row map 의 user_group 과 object_type 을 추출한다.
            String pageId       = (String)tempMap.get("PAGEID");
            String pMenuId      = (String)tempMap.get("PMENUID");
            String menuId       = (String)tempMap.get("MENUID");
            String menuPageId   = (String)tempMap.get("MENUPAGEID");
            String menuPageDesc = (String)tempMap.get("DESCRIPTION");
            String keyNo        = (String)tempMap.get("KEYNO");
            
            String[] menuPath = new String[6];
            
            menuPath[0] = pMenuId;
            menuPath[1] = menuId;
            menuPath[2] = menuPageId;
            menuPath[3] = menuPageDesc;
            menuPath[4] = keyNo;
            menuPath[5] = pageId;
            
            menuPathTable.put(menuId, menuPath);
        }
        
        return menuPathTable;
    }
    
    @Override
    public List getHeader(User user, String currentPageId, String ListId)
    {
    	return configConsultDAO.getHeader(user, currentPageId, ListId);
    }

	@Override
	public List getUserHeader(User user,String listId, String currentPageId) 
	{
		return configConsultDAO.getUserHeader(user,listId, currentPageId);
	}

    @Override
    public String getGrdHeight(User user, String currentPageId, String listId)
    {
        return configConsultDAO.getGrdHeight(user, currentPageId, listId);
    }

    @Override
    public Hashtable findPageFields()
    {
        Hashtable pageFields = new Hashtable();

        List allPageFields = configConsultDAO.findPageFields();
        
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
    
	public String findLanguage() 
	{
		return configConsultDAO.findLanguage();
	}
}
