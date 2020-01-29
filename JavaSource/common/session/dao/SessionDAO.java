package common.session.dao;

import common.bean.User;
import common.session.dao.SessionDAO;

/**
 * Session DAOImpl
 * @author javaworker
 * @version $Id: SessionDAO.java,v 1.1 2013/08/30 09:09:51 javaworker Exp $
 * @since 1.0
 */
public interface SessionDAO
{
    /**
     * 로그아웃 정보 기록
     * @author  javaworker
     * @version $Id: SessionDAO.java,v 1.1 2013/08/30 09:09:51 javaworker Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    public void saveLogoutHist(User logoutUser);

    /**
     * Gaia Log Out Info
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    public void saveGaiaLogoutHist(User logoutUser);
}
