package common.session.dao.sqlImpl;

import common.bean.User;
import common.session.dao.SessionDAO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

/**
 * Session DAOImpl
 * @author javaworker
 * @version $Id: SessionDAO.java,v 1.1 2013/08/30 09:09:51 javaworker Exp $
 * @since 1.0
 * @spring.bean id="sessionDAOTarget"
 * @spring.txbn id="sessionDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class SessionDAOSqlImpl extends BaseJdbcDaoSupportSql implements SessionDAO
{
    /**
     * 로그아웃 정보 기록
     * @author  javaworker
     * @version $Id: SessionDAO.java,v 1.1 2013/08/30 09:09:51 javaworker Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    public void saveLogoutHist(User logoutUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TALOGINCCLOG										");
        query.append("SET	logout_time = GETDATE()								");
        query.append("WHERE	logincclog_id =										");
        query.append("		(SELECT logincclog_id								");
        query.append("		   FROM TALOGINCCLOG a								");
        query.append("		  WHERE a.login_time = (SELECT MAX(login_time)		");
        query.append("								  FROM TALOGINCCLOG b		");
        query.append("								 WHERE a.user_id=b.user_id	");
        query.append("								   AND a.comp_no=b.comp_no	");
        query.append("								)							");
        query.append("		   AND a.comp_no = ?								");
        query.append("		   AND a.user_id = ?								");
        query.append("		)													");
        
        Object [] object = new Object[]{
        		logoutUser.getCompNo(),
                logoutUser.getUserId()
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }

    /**
     * Gaia Log Out Info
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param logoutUser
     */
    public void saveGaiaLogoutHist(User logoutUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TSLOGINLOG                                         ");
        query.append("SET   logout_time = GETDATE()                               ");
        query.append("WHERE sloginlog_id =                                      ");
        query.append("      (SELECT sloginlog_id                                ");
        query.append("         FROM TSLOGINLOG a                                ");
        query.append("        WHERE a.login_time = (SELECT MAX(login_time)      ");
        query.append("                                FROM TSLOGINLOG b         ");
        query.append("                               WHERE a.sdev_id=b.sdev_id  ");
        query.append("                              )                           ");
        query.append("         AND a.sdev_id = ?                                ");
        query.append("      )                                                   ");
        
        Object [] object = new Object[]{
                logoutUser.getUserId()
        };
        
        getJdbcTemplate().update(query.toString(), object);
    }
}
