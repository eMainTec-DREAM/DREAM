package common.sso.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.sso.dao.SsoDAO;
import common.sso.dto.SsoDTO;
import common.util.QuerySqlBuffer;

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
public class SsoDAOSqlImpl extends BaseJdbcDaoSupportSql implements SsoDAO
{

	@Override
	public String checkUser(SsoDTO ssoDTO, User user) 
	{   
		QuerySqlBuffer query = new QuerySqlBuffer();

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
		
		List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
		
		return QuerySqlBuffer.listToString(resultList);
	}
	
}
