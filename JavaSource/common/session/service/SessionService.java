package common.session.service;

import common.bean.User;

/**
 * Session Service
 * @author javaworker
 * @version $Id: SessionService.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
 * @since 1.0
 */
public interface SessionService
{
    /**
     * Log Out 정보 기록
     * @author  javaworker
     * @version $Id: SessionService.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    void saveLogoutHist(User logoutUser);

    /**
     * Record Gaia Log Out Info
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    void saveGaiaLogoutHist(User logoutUser);
}
