package common.config.service;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.bean.User;

public interface ConfigConsultService
{     
    
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
     * page_id �� menu path ��θ� ��ȸ�Ͽ�,
     * Hashtable ���·� �����Ѵ�.
     * @author  javaworker
     * @version $Id: ConfigService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public Hashtable findMenuPathTable();

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
}
