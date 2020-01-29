package dream.mgr.audtrail.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.audtrail.dao.MgrAudTrailListDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;

/**
 * Audit Trail DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrAudTrailListDAOTarget"
 * @spring.txbn id="mgrAudTrailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAudTrailListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrAudTrailListDAO
{
	/**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAudTrailListDTO
     * @return List
     */
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT  								");
        query.append("      '' 					seqNo 		");
        query.append("    , x.update_time       UPDTIME		");
        //query.append("    , CONVERT (DATETIME, x.update_time)       UPDTIME		");
        query.append("    , x.tracelog_id       TRACELOGID	");
        query.append("    , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_no = x.emp_no) UPDBYDESC		");
        query.append("    , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_no = x.dept_no) DEPTDESC		");
        query.append("    , dbo.SFACODE_TO_DESC(x.data_cud_type, 'DATA_CUD_TYPE', 'SYS', x.comp_no,  '"+user.getLangId()+"') 	DATACUDTYPEDESC		");
        query.append("FROM TATRACELOG x						");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(mgrAudTrailCommonDTO, user));
        query.getOrderByQuery("x.tracelog_id", "x.update_time DESC", mgrAudTrailCommonDTO.getOrderBy(), mgrAudTrailCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrAudTrailCommonDTO.getIsLoadMaxCount(), mgrAudTrailCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAudTrailListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.object_id", mgrAudTrailCommonDTO.getObjectId());
        query.getAndQuery("x.file_name", mgrAudTrailCommonDTO.getFileName());
                
        return query.toString();
    }

	public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                           ");
        query.append("       COUNT(1)                  ");
        query.append("FROM TATRACELOG x				   ");
        query.append("WHERE 1=1 					   ");
        query.append(this.getWhere(mgrAudTrailCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    
}