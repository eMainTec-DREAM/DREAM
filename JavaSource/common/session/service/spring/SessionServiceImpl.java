package common.session.service.spring;

import common.bean.User;
import common.session.dao.SessionDAO;
import common.session.service.SessionService;

/**
 * Session ServiceImpl
 * @author javaworker
 * @version $Id: SessionServiceImpl.java,v 1.1 2013/08/30 09:13:57 javaworker Exp $
 * @since 1.0
 * @spring.bean id="sessionServiceTarget"
 * @spring.txbn id="sessionService"
 * @spring.property name="sessionDAO" ref="sessionDAO"
 */
public class SessionServiceImpl implements SessionService
{
    private SessionDAO sessionDAO = null;

    public SessionDAO getSessionDAO()
    {
        return sessionDAO;
    }

    public void setSessionDAO(SessionDAO sessionDAO)
    {
        this.sessionDAO = sessionDAO;
    }
    
    public void saveLogoutHist(User logoutUser)
    {
        sessionDAO.saveLogoutHist(logoutUser);
    }

    public void saveGaiaLogoutHist(User logoutUser)
    {
        sessionDAO.saveGaiaLogoutHist(logoutUser);
    }
}
