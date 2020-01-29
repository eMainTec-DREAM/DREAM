package common.config.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import common.util.QueryBuffer;

/**
 * �⺻���� ȯ�漳���� DB���� ��ȸ�Ҷ� 
 * �������� ���ȴ�.
 * @author  javaworker
 * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since   1.0
 */
public interface ConfigDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * T4CONFIG �� ������ ��ȸ�Ѵ�. 
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param name
     * @return
     * @throws Exception
     */
    public String[][] getConfigInfo(String name);
    
    /**
     * Security Table �� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findSecurityList();
    
    /**
     * ��� page �� button�� �ε��Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findPageButton(String pageId);
    
    /**
     * Security Table �� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllTabPages();
    
    /**
     * DB�� ��ϵ� ��ü page_id �� menu_path�� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllMenuPath();
    
    /**
     * ���� Log ���
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param user
     * @param path
     * @param sessionId 
     */
    public void saveAccessLog(User user, String path);

    /**
     * Grid Height
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

    public List findAllPages();

    /**
     * Find Page Field Info
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public List findPageFields();

	public String findLanguage();
	
	public List<Map> findLanguages();
	
	public List<Map> findCompanies();

	/**
	 * ȸ�纰 Decorator map ���� Bean ID ����
	 * @return
	 */
	public List<Map> findDecoMap();

	public List findPageLinkes();

	public List findSysCodes();

	public void saveErrorLog(String errorlogId, String sTstr, User loginUser, String url);

	public int updateErrorLog(String errorlogId, String sTstr, User loginUser, String url);

    public Map<String, String> executeBatch(int stActInt, String auditKey, String fileName, Map<String, String> auditMap, User user);

    public List<Map> findDecoJspMap();
}