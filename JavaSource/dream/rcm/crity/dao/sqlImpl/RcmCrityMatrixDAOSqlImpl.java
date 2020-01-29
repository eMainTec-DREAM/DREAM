package dream.rcm.crity.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityMatrixDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityMatrixDTO;

/**
 * Criticality Matrix Page - Matrix DAO implements
 * @author kim21017
 * @version $Id: RcmCrityMatrixDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityMatrixDAOTarget"
 * @spring.txbn id="rcmCrityMatrixDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityMatrixDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityMatrixDAO
{
	public String[][] findCol(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		x.critycol_id											AS crityColId		");
        query.append("		,x.col_name												AS crityColDesc		");
        query.append("FROM TACRITYCOL x																	");
    	query.append("WHERE  1=1																		");
    	query.getAndQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("ORDER BY x.ord_no																	");
        
        return QuerySqlBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
    } 
	public String[][] findVal(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityMatrixDTO rcmCrityMatrixDTO, User user) throws Exception
    { 
		QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		x.crityvalue											AS crityVaiueDesc	");
        query.append("		,x.critycolor											AS crityColor		");
        query.append("		,x.crityrow_id											AS crityRowId		");
        query.append("		,x.row_name												AS crityRowDesc		");
        query.append("		,y.ord_no												AS ordNo			");
        query.append("		,x.critycol_id+x.crityrow_id							AS uniqueId			");
        query.append("FROM TACRITYVALUE x INNER JOIN TACRITYROW y										");
    	query.append("ON x.comp_no = y.comp_no															");
    	query.append("AND x.crityrow_id = y.crityrow_id													");
    	query.append("WHERE 1=1																			");
    	query.getAndQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("ORDER BY y.ord_no, x.critycol_id													");
        
        return QuerySqlBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
    } 
}