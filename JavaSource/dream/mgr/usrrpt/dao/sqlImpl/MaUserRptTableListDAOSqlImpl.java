package dream.mgr.usrrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrrpt.dao.MaUserRptTableListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptTableListDAOTarget"
 * @spring.txbn id="maUserRptTableListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptTableListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptTableListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    	");
        query.append("       ''                                     seqNo,        	");
        query.append("       ''                                     isDelCheck,		");
        query.append("       step_num                               stepNum,		");
        query.append("       y.table_name      						tableId,		");
        query.append("       y.description                          tableName,  	");
        query.append("       main_sub_type                          mainSubType,	");
        query.append("       usrrpttab_id                           usrrpttabId,	");
        query.append("       usrrptlist_id                          usrrptlistId 	");
        query.append("FROM   TAUSRRPTTAB x, TATABLE y	    						");
        query.append("WHERE  1 = 1													");
        query.append("  AND  x.table_id = y.table_id								");
        query.append(this.getWhere(maUserRptCommonDTO,loginUser));
        query.append("ORDER BY step_num	                                    		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eqPartId
     * @param loginUser
     * @return
     */
    public int deleteList(String eqPartId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAUSRRPTTAB                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+eqPartId+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	

    	if (!"".equals(maUserRptCommonDTO.getUsrrpttabId()))
        {
            query.getAndQuery("x.usrrpttab_id", maUserRptCommonDTO.getUsrrpttabId());
            return query.toString();
        }
    	
    	query.getAndNumKeyQuery("usrrptlist_id", maUserRptCommonDTO.getUsrrptlistId());
    	return query.toString();
    }

	@Override
	public int deleteJoinList(String id, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAUSRRPTJOIN                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+id+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteColList(String id, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAUSRRPTCOL                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+id+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteParamList(String id, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAUSRRPTWHR                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+id+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteOrdList(String id, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAUSRRPTORD                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+id+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}
}