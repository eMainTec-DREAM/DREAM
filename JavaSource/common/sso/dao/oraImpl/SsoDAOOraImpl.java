package common.sso.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.sso.dao.SsoDAO;
import common.sso.dto.SsoDTO;
import common.util.QueryBuffer;

/**
 * SSO DAO implements
 * @author js.lee
 * @version $Id: Exp $
 * @since 1.0
 * 
 * @spring.bean id="ssoDAOTarget"
 * @spring.txbn id="ssoDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class SsoDAOOraImpl  extends BaseJdbcDaoSupportOra implements SsoDAO
{

	@Override
	public String checkUser(SsoDTO ssoDTO, User user) { 
    	QueryBuffer query = new QueryBuffer(); 
    	
	    query.append("SELECT                ");
		query.append("       user_no		");
		query.append("FROM TAUSER			");
		query.append("WHERE 1=1		        ");
		query.append("  AND user_no = ?     ");
		query.append("  AND comp_no = ?     ");
		
        Object[] objects = new Object[] {   
                user.getUserNo()
                ,user.getCompNo()
       };
		
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),  objects));
    }	

}
