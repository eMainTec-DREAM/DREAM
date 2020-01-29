package common.config.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import common.util.QueryBuffer;

/**
 * 기본적인 환경설정을 DB에서 조회할때 
 * 공통으로 사용된다.
 * @author  javaworker
 * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since   1.0
 */
public interface ConfigDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * T4CONFIG 의 설정을 조회한다. 
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
     * Security Table 을 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findSecurityList();
    
    /**
     * 모든 page 와 button을 로딩한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findPageButton(String pageId);
    
    /**
     * Security Table 을 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllTabPages();
    
    /**
     * DB에 등록된 전체 page_id 의 menu_path를 조회한다.
     * @author  javaworker
     * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public List findAllMenuPath();
    
    /**
     * 접속 Log 기록
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
	 * 회사별 Decorator map 적용 Bean ID 정보
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