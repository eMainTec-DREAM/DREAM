package common.config.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.bean.User;

public interface ConfigService
{     
    /**
     * T4CONFIG 설정 loading.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public void loadConfig();
    
    /**
     * T4SECURITY 를 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadSecurityTable();
    
    /**
     * DB 에 있는 해당 page별 button을 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPageButtons();
    
    /**
     * 해당 User Group에 해당하는 모든 Tab Page 목록을 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadTabPages();
    
    /**
     * 해당 User Group에 해당하는 모든 Page 목록을 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPages();
    
    /**
     * 해당 User Group에 해당하는 모든 Menu 목록을 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadMenuPathTable();
    
    /**
     * DB 에 있는 해당 page별 Link를 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPageLinkes();
    
    /**
     * DB 에 있는 시스템코드를 로딩한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadSysCodeJson();
    
    /**
     * 해당 User 그룹에 해당하는 
     * 모든 Page별 Tab Page목록을 Hashtable 에 page id를 key 값으로 
     * 셋팅하여 리턴한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup : 로긴 유저 권한
     * @return
     * @throws Exception
     */
    public Hashtable findTabPages();

    /**
     * Page의 버튼들을 로딩한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception
     */
    public Hashtable findPageButtons();
    
    /**
     * PageId의 button을 조회 셋팅한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param pageId : button을 검색할 pageId
     * @return
     * @throws Exception
     */
    public Hashtable findPageButtons(String pageId);

    /**
     * Security Table 을 조회하여,
     * Hashtable 형태로 저장한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception
     */
    public Hashtable findSecurityTable();

    /**
     * page_id 별 menu path 경로를 조회하여,
     * Hashtable 형태로 저장한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public Hashtable findMenuPathTable();


    /**
     * 접속 로그를 기록한다.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param user
     * @param path
     * @param sessionId 
     */
    public void saveAccessLog(User user, String path);

    public List getHeader(User user, String currentPageId, String ListId);

    public List getUserHeader(User user,String listId, String currentPageId);

    /**
     * Get Grid Height default
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param user
     * @param currentPageId
     * @param listId
     * @return
     */
    public String getGrdHeight(User user, String currentPageId, String listId);

    public Hashtable findPages();

    /**
     * 각 Page Field 정보 조회
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public Hashtable findPageFields();
    
    public String findLanguage();

    /**
     * Gaia 
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param user
     * @param listId
     * @param currentPageId
     * @return
     */
    public List getGaiaUserHeader(User user, String listId,String currentPageId);

	public String saveErrorLog(Exception e, String string, User loginUser, String url);

	/**
	 * Find Link Function of each page
	 * @author  jung7126
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @return
	 */
	public Hashtable findPageLinkes();

	/**
	 * Find System Code for Javascript
	 * @author  jung7126
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @return
	 */
	public List findSysCodes();

    Map<String, String> saveAudit(String auditKey, String fileName, Map<String, String> auditMap, User user);
}
