package common.config.service.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.dao.ConfigDAO;
import common.config.service.ConfigService;
import common.util.CommonUtil;

/**
 * ȯ�� ������ Service
 * @author  javaworker
 * @version $Id: ConfigServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
 * @since   1.0
 *
 * @spring.bean id="configServiceTarget"
 * @spring.txbn id="configService"
 * @spring.property name="configDAO" ref="configDAO"
 */
public class ConfigServiceImpl implements ConfigService
{
    private ConfigDAO configDAO = null;

    public ConfigDAO getConfigDAO()
    {
        return configDAO;
    }

    public void setConfigDAO(ConfigDAO configDAO)
    {
        this.configDAO = configDAO;
    }

    public void loadConfig()
    {
        String [][] configSet = configDAO.getConfigInfo(null);
        
        Method configMethod = null;
        
        /*
         * TACONFIG �� name �� ������ MwareConfig method ȣ��
         */
        for(int i=0; i<configSet.length; i++)
        {
            String methodName = getMethodName(configSet[i][0]);
            try
            {
                configMethod = MwareConfig.class.getMethod(methodName, new Class[]{java.lang.String.class});
                configMethod.invoke(MwareConfig.class.getInterfaces(), new Object[]{new String(configSet[i][1])});
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
        
        MwareConfig.setLanguages(configDAO.findLanguages());
        MwareConfig.setCompanies(configDAO.findCompanies());
        MwareConfig.setDecoMapList(configDAO.findDecoMap());
//        MwareConfig.setDecoJspMapList(configDAO.findDecoJspMap());
        
/*
        // pageSize
        MwareConfig.setPageSize(getConfigInfo(configSet, "PAGE_SIZE"));
        
        // File ���� ��θ� �����Ͽ� �ش�.
        MwareConfig.setFileDir(getConfigInfo(configSet, "FILE_SAVE_DIR")); 
        //MwareConfig.setFileDir("D:\\mware_data\\file");
        
        // ����� ���� �߼� ����
        MwareConfig.setSendMail(getConfigInfo(configSet, "SEND_MAIL"));
        
        // ���� ���� �ּ�
        MwareConfig.setMailServer(getConfigInfo(configSet, "MAIL_SERVER"));
        
        // SUPER ADMIN PASSWORD
        MwareConfig.setSuperMan(getConfigInfo(configSet, "SUPER_MAN"));
        
        MwareConfig.setImageTempPath(getConfigInfo(configSet, "WEB_IMAGE_PATH"));
        //MwareConfig.setImageTempPath("E:\\Project\\lgmicron\\WebContent\\tempimagefile\\");
        
        // ��������
        MwareConfig.setVesselNm(getConfigInfo(configSet, "VESSEL_NAME"));
        MwareConfig.setVoyageNo(getConfigInfo(configSet, "VOYAGE_NUMBER"));
        
        // ���ڵ���� ����Ʈ
        MwareConfig.setPrintIp(getConfigInfo(configSet, "PRINT_IP"));
        MwareConfig.setPrintPort(getConfigInfo(configSet, "PRINT_PORT"));
        
        // �ӽ����� ����
        MwareConfig.setTempWebDir(getConfigInfo(configSet, "TEMP_WEB_DIR"));
*/
    }
    
    @Override
    public void loadSecurityTable()
    {
        MwareConfig.setSecurityTable(this.findSecurityTable());
    }

    @Override
    public void loadPageButtons()
    {
        MwareConfig.setButtonTable(this.findPageButtons());
    }

    @Override
    public void loadTabPages()
    {
        MwareConfig.setTabPagesTable(this.findTabPages());
    }

    @Override
    public void loadPages()
    {
        MwareConfig.setPagesTable(this.findPages());
    }

    @Override
    public void loadMenuPathTable()
    {
        MwareConfig.setMenuPathTable(this.findMenuPathTable());
    }

    @Override
    public void loadPageLinkes()
    {
        MwareConfig.setLinkedTable(this.findPageLinkes());
    }

    @Override
    public void loadSysCodeJson()
    {
        try
        {
            MwareConfig.setSysCodeJson(CommonUtil.makeJsonString(CommonUtil.makeJsonNoId(this.findSysCodes())));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * MwareConfig ȣ�� Method Name ����
     * @author  javaworker
     * @version $Id: ConfigServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
     * @since   1.0
     * 
     * @param string
     * @return
     */
    private String getMethodName(String configName)
    {
        StringTokenizer stConfigName = new StringTokenizer(configName, "_");
        
        String methodName = "";
        while (stConfigName.hasMoreTokens())
        {
            String nameToken = stConfigName.nextToken();
            
            methodName = methodName + nameToken.substring(0, 1).toUpperCase() + nameToken.substring(1).toLowerCase();
        }
               
        return "set" + methodName;
    }

    /**
     * ��� Config Set �߿���
     * name �� ��ġ�ϴ� ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
     * @since   1.0
     * 
     * @param configSet
     * @param name
     * @return
    private String getConfigInfo(String [][] configSet, String name)
    {
        String value = "";
        String tempName = "";
        
        int rows = configSet.length;
        for (int i=0; i<rows; i++)
        {
            tempName = configSet[i][0];
            
            if (tempName.equals(name))
            {
                value = configSet[i][1];
                break;
            }
        }

        return value;
    }
     */
    
    public Hashtable findPageButtons()
    {
        Hashtable pageButton = new Hashtable();

        List allPageButton = configDAO.findPageButton("");

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
    
    public Hashtable findPageButtons(String pageId)
    {
        Hashtable pageButton = new Hashtable();

//        String [][] allPageButton = configDAO.findPageButton(pageId);
//        
//        int topButtonCount = 0;
//        int botButtonCount = 0;
//        String [] topTempButtons = new String[30]; // max ������ ũ�� ��´�.
//        String [] botTempButtons = new String[30]; // max ������ ũ�� ��´�.
//        
//        // �� ��ư �Ǽ���ŭ ��ȸ�Ѵ�.
//        for (int i=0; i<allPageButton.length; i++)
//        {
//            String buttonId   = allPageButton[i][1];
//            String buttonType = allPageButton[i][2];
//            
//            if ("TOP".equals(buttonType))
//            {
//                // ���� page_id �� button_id ����
//                topTempButtons[topButtonCount] = buttonId;
//                
//                // button �� �߰� �ǹǷ� count �� 1 ���� �Ѵ�.
//                topButtonCount++;
//            }
//            else if ("BOT".equals(buttonType))
//            {
//                // ���� page_id �� button_id ����
//                botTempButtons[botButtonCount] = buttonId;
//                
//                // button �� �߰� �ǹǷ� count �� 1 ���� �Ѵ�.
//                botButtonCount++;
//            }
//        }
//        
//        // �ٷ� ������ ����Ǿ��� button id�� �ٷ����� page id�� �����Ѵ�.
//        pageButton.put(pageId+"_TOP", StringUtil.arrayLength(topTempButtons, topButtonCount));
//        pageButton.put(pageId+"_BOT", StringUtil.arrayLength(botTempButtons, botButtonCount));
        
        return pageButton;
    }
    
    public Hashtable findSecurityTable()
    {
        List securityList = configDAO.findSecurityList();

        Hashtable securityTable = new Hashtable();
        int securitySize = securityList.size();
        
        // �ش� row map
        Map tempMap = null;
        
        // List ����ŭ �ݺ��Ѵ�.
        for (int i=0; i<securitySize; i++)
        {
            tempMap = (Map)securityList.get(i);
            
            // �ش� row map �� user_group �� object_type �� �����Ѵ�.
            String userGroup  = String.valueOf(tempMap.get("USERGROUP"));
            String objectType = String.valueOf(tempMap.get("OBJECTTYPE"));
            String objectId   = String.valueOf(tempMap.get("OBJECTID"));
            
            /*
             * hashtable �� user_group ���� hashtable �����ؾ��Ѵ�.
             * securityTable �� �ش� user_group �� Key�� ����Ǿ� �ִٸ� �����Ͽ� �����ϰ�,
             * ���ٸ�, ���� user_group �� Key�� ���� �����Ѵ�.
             */
            /*
            Hashtable groupTable = (Hashtable)securityTable.get(userGroup);
            if (groupTable == null)
            {
                groupTable = new Hashtable();
            }
            
            Hashtable objectTable = (Hashtable)groupTable.get(objectType);
            if (objectTable == null)
            {
                objectTable = new Hashtable();
            }            
            */
            
            // user_group.obejct_type.p_object_id �� Key�� �����Ѵ�.
            String securityKey = userGroup + "." + objectType; //24.BUTTON...22.BUTTON or 24.TAB...
            
            setSecurityValue(securityTable, securityKey, objectId); //objectId : pgpage_id or pgbtn_id 
        }
        
        return securityTable;
    }
    
    /**
     * p_object_key �� �ش� ������ �����Ѵ�.
     * ���� Ű������ ���� �ִٸ� �迭�� �ڿ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
     * @since   1.0
     * 
     * @param securityTable
     * @param securityKey
     * @param objectId
     */
    private void setSecurityValue(Hashtable securityTable, 
            String securityKey, String objectId)
    {
        String [] newObjArray = null;
        String [] objectArray = (String [])securityTable.get(securityKey);
        
        // ���� ���� ���ٸ� 1�� �迭�� �����Ѵ�.
        if (objectArray == null)
        {
            newObjArray = new String[1];
            newObjArray[0] = objectId;
        }
        else
        {
            // ���� ���õ� ���ڿ� �迭�� �߰��Ѵ�.
            int oldLength = objectArray.length;
            newObjArray = new String[oldLength + 1];

            for (int i=0; i<oldLength ; i++)
            {
                newObjArray[i] = objectArray[i];
            }
            
            newObjArray[oldLength] = objectId;
        }
        
        securityTable.put(securityKey, newObjArray);
    }
    
    public Hashtable findTabPages()
    {
        Hashtable tabPages = new Hashtable();

        List allTabPage = configDAO.findAllTabPages();

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
        
//        Hashtable tabPageHash = new Hashtable();
//        
//        String [][] allTabPages = configDAO.findAllTabPages();
//        
//        String lastMenuId = allTabPages[0][0];  // ���� ó�� p_menu id �� �����Ѵ�.
//        
//        int tabCount = 0;
//        String tempTabPages[] = new String[20]; // max ������ ũ�� ��´�.
//        String tempTabTitles[] = new String[20]; // max ������ ũ�� ��´�.
//        
//        // �� ��ư �Ǽ���ŭ ��ȸ�Ѵ�.
//        for (int i=0; i<allTabPages.length; i++)
//        {
//            /*
//             * �ٷ����� ó���� p_menu_id �� ���ٸ�
//             * tabpages �� �״�� �Է��Ѵ�.
//             */
//            if (lastMenuId.equals(allTabPages[i][0]))
//            {
//                // ���� p_menu_id ��  tab page �� temp tab page �� �����Ѵ�.
//                tempTabPages[tabCount] = allTabPages[i][1];
//                tempTabTitles[tabCount] = allTabPages[i][2];
//                
//                // tab �� �߰� �ǹǷ� count �� 1 ���� �Ѵ�.
//                tabCount++;
//            }
//            /*
//             * page_id �� �ٸ��� ���� �������� �����ϰ� reset �Ѵ�.
//             */
//            else
//            {
//                // �ٷ� ������ ����Ǿ��� tab page �� �ٷ����� p_menu_id �� �����Ѵ�.
//                tabPageHash.put(lastMenuId, StringUtil.arrayLength(tempTabPages, tabCount));
//                tabPageHash.put(lastMenuId+"_TITLE", StringUtil.arrayLength(tempTabTitles, tabCount));
//
//                // ���� p_menu_id �� �ٷ��� p_menu_id �� �����Ѵ�.
//                lastMenuId = allTabPages[i][0];
//
//                // reset max size
//                tempTabPages = new String[20];
//                // reset max size
//                tempTabTitles = new String[20];
//                
//                // ���� p_menu_id �� tab page �� temp tab page �� �����Ѵ�.
//                tempTabPages[0] = allTabPages[i][1];
//                tempTabTitles[0] = allTabPages[i][2];
//                
//                // ��ư �Ѱ����� 1�� �����Ѵ�.
//                tabCount = 1;
//            }
//            
//            // ������ loop �� ���
//            if (i == (allTabPages.length-1))
//            {
//                tempTabPages[tabCount] = allTabPages[i][1];
//                tempTabTitles[tabCount] = allTabPages[i][2];
//                
//                // �ٷ� ������ ����Ǿ��� tab page �� �ٷ����� p_menu_id �� �����Ѵ�.
//                tabPageHash.put(lastMenuId, StringUtil.arrayLength(tempTabPages, tabCount));
//                tabPageHash.put(lastMenuId+"_TITLE", StringUtil.arrayLength(tempTabTitles, tabCount));
//            }
//        }
//        
//        return tabPageHash;
    }
    
    
    public Hashtable findPages()
    {
        Hashtable pages = new Hashtable();

        List allPage = configDAO.findAllPages();

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
        List pageMenuList = configDAO.findAllMenuPath();
        
        Hashtable menuPathTable = new Hashtable();
        
        int pageMenuSize = pageMenuList.size();

        Map tempMap = null;
        
        // List ����ŭ �ݺ��Ѵ�.
        for (int i=0; i<pageMenuSize; i++)
        {
            tempMap = (Map)pageMenuList.get(i);
            
            // �ش� row map �� user_group �� object_type �� �����Ѵ�.
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
    
    public void saveAccessLog(User user, String path)
    {
        
    	if("/common/jsp/jsonPage.jsp".equals(path)
    		|| "/common/ajax/ajaxXmlVal.jsp".equals(path)	
    	   ){
    		//no works
    	}else{
    		configDAO.saveAccessLog(user, path);
    	}
    }

    @Override
    public List getHeader(User user, String currentPageId, String ListId)
    {
    	return configDAO.getHeader(user, currentPageId, ListId);
    }

	@Override
	public List getUserHeader(User user,String listId, String currentPageId) 
	{
		return configDAO.getUserHeader(user,listId, currentPageId);
	}

    @Override
    public String getGrdHeight(User user, String currentPageId, String listId)
    {
        return configDAO.getGrdHeight(user, currentPageId, listId);
    }

    @Override
    public Hashtable findPageFields()
    {
        Hashtable pageFields = new Hashtable();

        List allPageFields = configDAO.findPageFields();
        
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
            
            //�� �������� �ʵ� ����Ʈ�� ���õ� 
            pageFields.put(fileName, resultList);
        }
        return pageFields;
    }
    
	public String findLanguage() 
	{
		return configDAO.findLanguage();
	}

    @Override
    public List getGaiaUserHeader(User user, String listId, String currentPageId)
    {
        return configDAO.getGaiaUserHeader(user,listId, currentPageId);
    }

	@Override
	public String saveErrorLog(Exception e, String stackTrace, User loginUser, String url) 
	{
	    int strLen = 1500;
		String errStr = e.toString();
		String[] errArr = null;
		if(errStr.indexOf(":") > 0)
		{
			errArr = errStr.split(":");
		}
		
		if(errArr != null && errArr.length > 1)
		{
			//errArr[0] = errArr[0].substring(0, errArr[0].length() < 499?errArr[0].length():499);
			//errArr[1] = errArr[1].substring(0, errArr[1].length() < 499?errArr[1].length():499);
		}	
		else 
		{
			errArr = new String[2];
			errArr[0] = "";
			errArr[1] = "";
		}
		
		String errorlogId = configDAO.getNextSequence("SQAERRORLOG_ID");
		
		if(stackTrace == null || "null".equals(stackTrace)) stackTrace = "";
		
		
		stackTrace = errArr[0]+ " " + errArr[1] + stackTrace;
		
		if(stackTrace.length() > strLen)
		{
			int cnt = stackTrace.length() / strLen;
			String sTstr = null;
			int bIdx = 0;
			int eIdx = 0;
			for(int i = 0 ; i <= cnt ; i++)
			{
				bIdx = i * strLen;
				eIdx = (i+1)* strLen - 1;
				
				if(eIdx > stackTrace.length()) eIdx = stackTrace.length();
				
				sTstr = stackTrace.substring(bIdx, eIdx);
				
				if(i == 0)
					configDAO.saveErrorLog(errorlogId, sTstr, loginUser, url);
				else
					configDAO.updateErrorLog(errorlogId, sTstr, loginUser, url);
			}
		}
		else
			configDAO.saveErrorLog(errorlogId, stackTrace, loginUser, url);
		
		return errorlogId;
	}

	@Override
	public Hashtable findPageLinkes() 
	{
		Hashtable pageLinks = new Hashtable();

        List allPageLinks = configDAO.findPageLinkes();

        String pageId;
        for(Object links: allPageLinks)
        {
            Map link = (Map)links;
            pageId = String.valueOf(link.get("FILENAME"));
            
            if(link.containsKey(pageId)) continue;
            
            ArrayList resultList = new ArrayList();

            for(Object linkTems: allPageLinks)
            {
                Map linkTem = (Map)linkTems;
                
                if(pageId.equals(linkTem.get("FILENAME")))
                {
                    resultList.add(linkTem);
                }
            }
            
            pageLinks.put(pageId, resultList);
        }

        return pageLinks;
	}

	@Override
	public List findSysCodes() 
	{
//		Map sysCodes = new HashMap();

        List allSysCodes = configDAO.findSysCodes();

//        String listType;
//        for(Object codes: allSysCodes)
//        {
//            Map code = (Map)codes;
//            listType = String.valueOf(code.get("LISTTYPE"));
//            
//            if(code.containsKey(listType)) continue;
//            
//            ArrayList resultList = new ArrayList();
//
//            for(Object codeTems: allSysCodes)
//            {
//                Map codeTem = (Map)codeTems;
//                
//                if(listType.equals(codeTem.get("LISTTYPE")))
//                {
//                    resultList.add(codeTem);
//                }
//            }
//            
//            sysCodes.put(listType, resultList);
//        }

        return allSysCodes;
	}


    public Map<String,String> saveAudit(String auditKey, String fileName, Map<String,String> auditMap, User user)
    {
        // TODO Auto-generated method stub
        
        String stAct = String.valueOf(auditMap.get("strutsAction"));

        //�ߺ� strutsAction�� ���  ù��° strutsAction�� ������ ����
        if(stAct.indexOf("^|^") > 0) stAct = stAct.split("\\^\\|\\^")[0];
        
        if(stAct  == null || "null".equals(stAct))stAct ="0";
        int stActInt = Integer.parseInt("".equals(stAct)?"0":stAct);
        
        //auditKey, tracelogId
        Map<String,String> rtnMap = null;
//        System.out.println("!!!!�����̳�?"+MwareConfig.getIsUseAuditTrail());
        
        if("Y".equals(MwareConfig.getIsUseAuditTrail())) rtnMap = configDAO.executeBatch(stActInt, auditKey, fileName, auditMap, user);
   
        return rtnMap;
    }
}
