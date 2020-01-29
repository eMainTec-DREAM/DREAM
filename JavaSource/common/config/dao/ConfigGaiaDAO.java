package common.config.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;

/**
 * �⺻���� ȯ�漳���� DB���� ��ȸ�Ҷ� 
 * �������� ���ȴ�.
 * @author  javaworker
 * @version $Id: ConfigDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since   1.0
 */
public interface ConfigGaiaDAO extends BaseJdbcDaoSupportIntf
{
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
    public void saveAccessLog(User user, String path, String sessionId);

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

	public String findLanguage() ;
}