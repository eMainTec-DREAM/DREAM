package dream.rcm.ffail.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.rcm.ffail.dao.RcmFfailListDAO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 질의 dao
 * @author  kim21017
 * @version $Id: RcmFfailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFfailListDAOTarget"
 * @spring.txbn id="rcmFfailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFfailListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFfailListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFfailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return List
     */
    public List findQnaList(RcmFfailCommonDTO rcmFfailCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT		");
        query.append("        ''                                                            seqNo,        		");
        query.append("        ''                                                            isDelCheck,    		");
        query.append("        y.rcmlist_no rcmListNo,		");
        query.append("        y.description rcmDesc,		");
        query.append("        x.rcmfunc_no funcNo,		");
        query.append("        x.description funcName,		");
        query.append("        x.remark remark,		");
        query.append("        x.rcmfunc_id rcmFuncId		");
        query.append(" FROM TARCMFUNC x,  TARCMLIST y		");
        query.append(" WHERE x.rcmlist_id= y.rcmlist_id		");
        query.append(this.getWhere(rcmFfailCommonDTO));
        query.getOrderByQuery("x.rcmlist_id","y.rcmlist_no", rcmFfailCommonDTO.getOrderBy(), rcmFfailCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmFfailCommonDTO.getIsLoadMaxCount(), rcmFfailCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: RcmFfailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmFfailCommonDTO rcmFfailCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(rcmFfailCommonDTO.getRcmFuncId()))
        {
            query.getAndQuery("x.rcmfunc_id", rcmFfailCommonDTO.getRcmFuncId());
            return query.toString();
        }
        query.getAndQuery("y.rcmlist_id", rcmFfailCommonDTO.getFilterRcmListId());
        query.getLikeQuery("y.description", rcmFfailCommonDTO.getFilterRcmDesc());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFfailListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String rcmFuncId = id;
    	
    	query.append("DELETE FROM TARCMFUNC			");
    	query.append("WHERE rcmfunc_id = '"+rcmFuncId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	

    	return rtnValue;
    }

	@Override
	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
    	
        query.append(" SELECT								");
        query.append("   COUNT(1)                           ");
        query.append(" FROM TARCMFUNC x,  TARCMLIST y		");
        query.append(" WHERE x.rcmlist_id= y.rcmlist_id		");
        query.append(this.getWhere(rcmFfailCommonDTO));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}