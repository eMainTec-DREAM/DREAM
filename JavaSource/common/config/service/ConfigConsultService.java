package common.config.service;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.bean.User;

public interface ConfigConsultService
{     
    
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
     * page_id 별 menu path 경로를 조회하여,
     * Hashtable 형태로 저장한다.
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
     * 각 Page Field 정보 조회
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @return
     */
    public Hashtable findPageFields();
    
    public String findLanguage();
}
