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
 * 환경 설정용 Service
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
         * TACONFIG 의 name 의 값으로 MwareConfig method 호출
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
        
        // File 저장 경로를 저장하여 준다.
        MwareConfig.setFileDir(getConfigInfo(configSet, "FILE_SAVE_DIR")); 
        //MwareConfig.setFileDir("D:\\mware_data\\file");
        
        // 결재시 메일 발송 여부
        MwareConfig.setSendMail(getConfigInfo(configSet, "SEND_MAIL"));
        
        // 메일 서버 주소
        MwareConfig.setMailServer(getConfigInfo(configSet, "MAIL_SERVER"));
        
        // SUPER ADMIN PASSWORD
        MwareConfig.setSuperMan(getConfigInfo(configSet, "SUPER_MAN"));
        
        MwareConfig.setImageTempPath(getConfigInfo(configSet, "WEB_IMAGE_PATH"));
        //MwareConfig.setImageTempPath("E:\\Project\\lgmicron\\WebContent\\tempimagefile\\");
        
        // 운항정보
        MwareConfig.setVesselNm(getConfigInfo(configSet, "VESSEL_NAME"));
        MwareConfig.setVoyageNo(getConfigInfo(configSet, "VOYAGE_NUMBER"));
        
        // 바코드출력 프린트
        MwareConfig.setPrintIp(getConfigInfo(configSet, "PRINT_IP"));
        MwareConfig.setPrintPort(getConfigInfo(configSet, "PRINT_PORT"));
        
        // 임시저장 폴더
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
     * MwareConfig 호출 Method Name 만듬
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
     * 모든 Config Set 중에서
     * name 과 일치하는 값을 리턴한다.
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
//        String [] topTempButtons = new String[30]; // max 갯수로 크게 잡는다.
//        String [] botTempButtons = new String[30]; // max 갯수로 크게 잡는다.
//        
//        // 총 버튼 건수만큼 조회한다.
//        for (int i=0; i<allPageButton.length; i++)
//        {
//            String buttonId   = allPageButton[i][1];
//            String buttonType = allPageButton[i][2];
//            
//            if ("TOP".equals(buttonType))
//            {
//                // 현재 page_id 의 button_id 저장
//                topTempButtons[topButtonCount] = buttonId;
//                
//                // button 이 추가 되므로 count 를 1 증가 한다.
//                topButtonCount++;
//            }
//            else if ("BOT".equals(buttonType))
//            {
//                // 현재 page_id 의 button_id 저장
//                botTempButtons[botButtonCount] = buttonId;
//                
//                // button 이 추가 되므로 count 를 1 증가 한다.
//                botButtonCount++;
//            }
//        }
//        
//        // 바로 전까지 저장되었던 button id를 바로전의 page id로 저장한다.
//        pageButton.put(pageId+"_TOP", StringUtil.arrayLength(topTempButtons, topButtonCount));
//        pageButton.put(pageId+"_BOT", StringUtil.arrayLength(botTempButtons, botButtonCount));
        
        return pageButton;
    }
    
    public Hashtable findSecurityTable()
    {
        List securityList = configDAO.findSecurityList();

        Hashtable securityTable = new Hashtable();
        int securitySize = securityList.size();
        
        // 해당 row map
        Map tempMap = null;
        
        // List 수만큼 반복한다.
        for (int i=0; i<securitySize; i++)
        {
            tempMap = (Map)securityList.get(i);
            
            // 해당 row map 의 user_group 과 object_type 을 추출한다.
            String userGroup  = String.valueOf(tempMap.get("USERGROUP"));
            String objectType = String.valueOf(tempMap.get("OBJECTTYPE"));
            String objectId   = String.valueOf(tempMap.get("OBJECTID"));
            
            /*
             * hashtable 에 user_group 별로 hashtable 저장해야한다.
             * securityTable 에 해당 user_group 이 Key로 저장되어 있다면 추출하여 저장하고,
             * 없다면, 새로 user_group 을 Key로 새로 저장한다.
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
            
            // user_group.obejct_type.p_object_id 로 Key를 구성한다.
            String securityKey = userGroup + "." + objectType; //24.BUTTON...22.BUTTON or 24.TAB...
            
            setSecurityValue(securityTable, securityKey, objectId); //objectId : pgpage_id or pgbtn_id 
        }
        
        return securityTable;
    }
    
    /**
     * p_object_key 로 해당 내역을 저장한다.
     * 같은 키값으로 값이 있다면 배열로 뒤에 셋팅한다.
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
        
        // 현재 값이 없다면 1차 배열로 저장한다.
        if (objectArray == null)
        {
            newObjArray = new String[1];
            newObjArray[0] = objectId;
        }
        else
        {
            // 현재 셋팅된 값뒤에 배열로 추가한다.
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
//        String lastMenuId = allTabPages[0][0];  // 가장 처음 p_menu id 를 셋팅한다.
//        
//        int tabCount = 0;
//        String tempTabPages[] = new String[20]; // max 갯수로 크게 잡는다.
//        String tempTabTitles[] = new String[20]; // max 갯수로 크게 잡는다.
//        
//        // 총 버튼 건수만큼 조회한다.
//        for (int i=0; i<allTabPages.length; i++)
//        {
//            /*
//             * 바로전에 처리한 p_menu_id 와 같다면
//             * tabpages 에 그대로 입력한다.
//             */
//            if (lastMenuId.equals(allTabPages[i][0]))
//            {
//                // 현재 p_menu_id 의  tab page 를 temp tab page 에 저장한다.
//                tempTabPages[tabCount] = allTabPages[i][1];
//                tempTabTitles[tabCount] = allTabPages[i][2];
//                
//                // tab 이 추가 되므로 count 를 1 증가 한다.
//                tabCount++;
//            }
//            /*
//             * page_id 가 다르면 이전 페이지를 저장하고 reset 한다.
//             */
//            else
//            {
//                // 바로 전까지 저장되었던 tab page 를 바로전의 p_menu_id 로 저장한다.
//                tabPageHash.put(lastMenuId, StringUtil.arrayLength(tempTabPages, tabCount));
//                tabPageHash.put(lastMenuId+"_TITLE", StringUtil.arrayLength(tempTabTitles, tabCount));
//
//                // 현재 p_menu_id 를 바로전 p_menu_id 로 셋팅한다.
//                lastMenuId = allTabPages[i][0];
//
//                // reset max size
//                tempTabPages = new String[20];
//                // reset max size
//                tempTabTitles = new String[20];
//                
//                // 현재 p_menu_id 의 tab page 를 temp tab page 에 저장한다.
//                tempTabPages[0] = allTabPages[i][1];
//                tempTabTitles[0] = allTabPages[i][2];
//                
//                // 버튼 총갯수를 1로 셋팅한다.
//                tabCount = 1;
//            }
//            
//            // 마지막 loop 인 경우
//            if (i == (allTabPages.length-1))
//            {
//                tempTabPages[tabCount] = allTabPages[i][1];
//                tempTabTitles[tabCount] = allTabPages[i][2];
//                
//                // 바로 전까지 저장되었던 tab page 를 바로전의 p_menu_id 로 저장한다.
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
            
            //각 페이지에 필드 리스트가 세팅됨 
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

        //중복 strutsAction의 경우  첫번째 strutsAction의 값으로 진행
        if(stAct.indexOf("^|^") > 0) stAct = stAct.split("\\^\\|\\^")[0];
        
        if(stAct  == null || "null".equals(stAct))stAct ="0";
        int stActInt = Integer.parseInt("".equals(stAct)?"0":stAct);
        
        //auditKey, tracelogId
        Map<String,String> rtnMap = null;
//        System.out.println("!!!!오딧이냐?"+MwareConfig.getIsUseAuditTrail());
        
        if("Y".equals(MwareConfig.getIsUseAuditTrail())) rtnMap = configDAO.executeBatch(stActInt, auditKey, fileName, auditMap, user);
   
        return rtnMap;
    }
}
