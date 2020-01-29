package dream.mgr.audtrail.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.audtrail.dao.MgrAudTrailDtlListDAO;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;

/**
 * MgrAudTrailDtl Page - List DAO implements
 * @author youngjoo38
 * @version $Id: MgrAudTrailDtlListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mgrAudTrailDtlListDAOTarget"
 * @spring.txbn id="mgrAudTrailDtlListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrAudTrailDtlListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements MgrAudTrailDtlListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: mgrAudTrailDtlListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param mgrAudTrailDtlListDTO
     * @return List
     */
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                        ");
        query.append("       ''                     seqNo           ");
        query.append("  , x.tracelogdtl_id 			TRACELOGDTLID	");
        query.append("  , x.tracelog_id 			TRACELOGID		");
        query.append("  , (SELECT MAX(a.description) FROM TAPGFIELD a WHERE a.field_id = x.field_id) ITEMDESC     		");
        query.append("  , x.field_id   				FIELDID			");
        query.append("  , '"+mgrAudTrailDtlListDTO.getPrevTracelogId()+"'	prevTracelogId 	");
        query.append("  , (SELECT a.field_value FROM TATRACELOGDTL a WHERE a.tracelog_id = '"+mgrAudTrailDtlListDTO.getPrevTracelogId()+"' AND a.field_id = x.field_id) BEFORECHANGE		");
        query.append("  , x.field_value 			AFTERCHANGE		");
        query.append("FROM TATRACELOGDTL x INNER JOIN TATRACELOG y	");
        query.append("ON x.tracelog_id = y.tracelog_id 				");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(mgrAudTrailCommonDTO, mgrAudTrailDtlListDTO, user));        
        
        query.getOrderByQuery("x.tracelogdtl_id","(SELECT MAX(a.ord_no) FROM TAPGFIELD a WHERE a.field_id = x.field_id and a.display_yn = 'Y' and hidden_yn ='N' )", mgrAudTrailCommonDTO.getOrderBy(), mgrAudTrailCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrAudTrailCommonDTO.getIsLoadMaxCount(), mgrAudTrailCommonDTO.getFirstRow()));

    }

    private String getWhere(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getStringEqualQuery("y.comp_no", user.getCompNo());
        query.getAndQuery("x.tracelog_id", mgrAudTrailCommonDTO.getTracelogId());
        
        String prevKeyId = mgrAudTrailDtlListDTO.getPrevTracelogId();
        query.append("AND (SELECT MAX(a.description) FROM TAPGFIELD a WHERE a.field_id = x.field_id) IS NOT NULL		");
        
        if(!"".equals(prevKeyId))
        	query.append("AND 1 = (CASE WHEN ISNULL(x.field_value,-1) != (SELECT ISNULL(a.field_value,-1) FROM TATRACELOGDTL a WHERE a.tracelog_id = '"+prevKeyId+"' AND a.field_id = x.field_id) THEN 1 ELSE 2 END)");
        
        return query.toString();
    }

    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, 
            MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TATRACELOGDTL x INNER JOIN TATRACELOG y	");
        query.append("ON x.tracelog_id = y.tracelog_id 				");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(mgrAudTrailCommonDTO, mgrAudTrailDtlListDTO, user));        
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	@Override
	public String findPrevKeyId(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 							");
		query.append("    b.tracelog_id					");
		query.append("FROM (							");
		query.append("    SELECT 						");
		query.append("        row_number() OVER(ORDER BY a.tracelog_id DESC) 	rno		");
		query.append("      , a.tracelog_id				");
		query.append("    FROM TATRACELOG a				");
		query.append("    WHERE a.comp_no 	 = ?		");
		query.append("    AND a.tracelog_id <= ?		");
		query.append("    AND a.file_name 	 = ?		");
		query.append("    AND a.object_id    = ? 		");
		query.append(") b								");
		query.append("WHERE b.rno = 2					");

        Object[] objects = new Object[] {
                user.getCompNo()
              , mgrAudTrailCommonDTO.getTracelogId()
              , mgrAudTrailCommonDTO.getFileName()
              , mgrAudTrailCommonDTO.getObjectId()
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}    
}
