package dream.scheduler.autoschedule.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.scheduler.autoschedule.dao.SchAutoSchCommonDAO;

/**
 * Batch DAO
 * @author  kim21017
 * @version $Id: Exp $
 * @since   1.0
 * @spring.bean id="schAutoSchCommonDAOTarget"
 * @spring.txbn id="schAutoSchCommonDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class SchAutoSchCommonDAOOraImpl extends BaseJdbcDaoSupportOra implements SchAutoSchCommonDAO
{
	public String[] getIfCompNo() throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT comp_no			");
        query.append("FROM TACOMP				");
        query.append("WHERE IS_USE='Y'			");
        
		return QueryBuffer.toStringSingleArray(this.getJdbcTemplate().queryForList(query.toString()));
    }
	public String getIfUserNo() throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT value$							");
        query.append("FROM TACONFIG							");
        query.append("WHERE name='BATCH_EXEC_USER_ID'		");
        
		return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    }
}