package dream.rcm.taskmap.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.taskmap.dao.RcmTaskMapListDAO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 질의 dao
 * @author  kim21017
 * @version $Id: RcmTaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmTaskMapListDAOTarget"
 * @spring.txbn id="rcmTaskMapListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmTaskMapListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmTaskMapListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return List
     */
    public List findQnaList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT	                                ");
        query.append("      ''   as SEQNO        	            ");
        query.append("     ,''   as isDelCheck          	    ");
        query.append("     ,pmtaskmaplist_no as pmTaskmaplistNo ");
        query.append("     ,description as description		    ");
        query.append("     ,is_use as isUse		                ");
        query.append("     ,reg_date as regDate		            ");
        query.append("     ,remark as remark		            ");
        query.append("     ,pmtaskmaplist_id as pmTaskmaplistId ");
        query.append(" FROM TAPMTASKMAPLIST		                ");
        query.append("WHERE 1=1	                                ");
        query.append(this.getWhere(rcmTaskMapCommonDTO));
        query.getOrderByQuery("pmtaskmaplist_id","pmtaskmaplist_no desc", rcmTaskMapCommonDTO.getOrderBy(), rcmTaskMapCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmTaskMapCommonDTO.getIsLoadMaxCount(), rcmTaskMapCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: RcmTaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(rcmTaskMapCommonDTO.getPmTaskMapListId()))
        {
            query.getAndQuery("pmtaskmaplist_id", rcmTaskMapCommonDTO.getPmTaskMapListId());
            return query.toString();
        }
        query.getAndQuery("description", rcmTaskMapCommonDTO.getFilterDesc());

        
        
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmTaskMapListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String pmTaskMapListId = id;
    	
    	query.append("DELETE FROM TAPMTASKMAPLIST			");
    	query.append("WHERE pmtaskmaplist_id = '"+pmTaskMapListId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	

    	return rtnValue;
    }

	@Override
	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		    
		query.append("SELECT	                                ");
        query.append("  COUNT(1)			       	            ");
        query.append(" FROM TAPMTASKMAPLIST		                ");
        query.append("WHERE 1=1	                                ");
        query.append(this.getWhere(rcmTaskMapCommonDTO));
        
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QuerySqlBuffer.listToString(resultList);
	}
}