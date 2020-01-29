package common.config.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.bean.User;

public interface ConfigService
{     
    /**
     * T4CONFIG ���� loading.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public void loadConfig();
    
    /**
     * T4SECURITY �� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadSecurityTable();
    
    /**
     * DB �� �ִ� �ش� page�� button�� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPageButtons();
    
    /**
     * �ش� User Group�� �ش��ϴ� ��� Tab Page ����� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadTabPages();
    
    /**
     * �ش� User Group�� �ش��ϴ� ��� Page ����� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPages();
    
    /**
     * �ش� User Group�� �ش��ϴ� ��� Menu ����� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadMenuPathTable();
    
    /**
     * DB �� �ִ� �ش� page�� Link�� �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadPageLinkes();
    
    /**
     * DB �� �ִ� �ý����ڵ带 �ε��Ѵ�.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     */
    public void loadSysCodeJson();
    
    /**
     * �ش� User �׷쿡 �ش��ϴ� 
     * ��� Page�� Tab Page����� Hashtable �� page id�� key ������ 
     * �����Ͽ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup : �α� ���� ����
     * @return
     * @throws Exception
     */
    public Hashtable findTabPages();

    /**
     * Page�� ��ư���� �ε��Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception
     */
    public Hashtable findPageButtons();
    
    /**
     * PageId�� button�� ��ȸ �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param pageId : button�� �˻��� pageId
     * @return
     * @throws Exception
     */
    public Hashtable findPageButtons(String pageId);

    /**
     * Security Table �� ��ȸ�Ͽ�,
     * Hashtable ���·� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception
     */
    public Hashtable findSecurityTable();

    /**
     * page_id �� menu path ��θ� ��ȸ�Ͽ�,
     * Hashtable ���·� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public Hashtable findMenuPathTable();


    /**
     * ���� �α׸� ����Ѵ�.
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
     * �� Page Field ���� ��ȸ
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
