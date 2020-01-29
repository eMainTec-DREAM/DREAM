package dream.scheduler.autoschedule.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.scheduler.autoschedule.dao.SchAutoSchCommonDAO;

/**
 * Batch DAO
 * @author  kim21017
 * @version $Id: MaBatchDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="schAutoSchCommonDAOTarget"
 * @spring.txbn id="schAutoSchCommonDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class SchAutoSchCommonDAOSqlImpl extends BaseJdbcDaoSupportSql implements SchAutoSchCommonDAO
{
	public String[] getIfCompNo() throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT comp_no			");
        query.append("FROM TACOMP				");
        query.append("WHERE is_use='Y'			");
        
		return QuerySqlBuffer.toStringSingleArray(this.getJdbcTemplate().queryForList(query.toString()));
    }
	public String getIfUserNo() throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT value$							");
        query.append("FROM TACONFIG							");
        query.append("WHERE name='BATCH_EXEC_USER_ID'		");
        
		return QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    }
}