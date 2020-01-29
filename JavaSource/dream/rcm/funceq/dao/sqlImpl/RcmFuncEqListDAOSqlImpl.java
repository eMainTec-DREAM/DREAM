package dream.rcm.funceq.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.funceq.dao.RcmFuncEqListDAO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 질의 dao
 * @author  kim21017
 * @version $Id: RcmFuncEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFuncEqListDAOTarget"
 * @spring.txbn id="rcmFuncEqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFuncEqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFuncEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return List
     */
    public List findQnaList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 		");
        query.append("   ''                                                            seqNo,		");
        query.append("    ''                                                            isDelCheck,  		");
        query.append("    y.rcmlist_no rcmListNo,		");
        query.append("    y.description rcmDesc,		");
        query.append("    (SELECT z.rcmfunc_no FROM TARCMFUNC z WHERE z.rcmfunc_id = x.rcmfunc_id AND x.comp_no=z.comp_no)funcNo,		");
        query.append("    (SELECT z.description FROM TARCMFUNC z WHERE z.rcmfunc_id = x.rcmfunc_id AND x.comp_no=z.comp_no)funcName,		");
        query.append("    x.rcmffail_no failNo,		");
        query.append("    x.description failName,		");
        query.append("    x.remark remark,		");
        query.append("    x.rcmffail_id rcmFfailId 		");
        query.append("FROM TARCMFFAIL x, TARCMLIST y		");
        query.append("WHERE x.rcmlist_id= y.rcmlist_id		");
        query.append(this.getWhere(rcmFuncEqCommonDTO));
        query.getOrderByQuery("x.rcmfunc_id","y.rcmlist_no", rcmFuncEqCommonDTO.getOrderBy(), rcmFuncEqCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmFuncEqCommonDTO.getIsLoadMaxCount(), rcmFuncEqCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: RcmFuncEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(rcmFuncEqCommonDTO.getRcmFfailId()))
        {
            query.getAndQuery("x.rcmffail_id", rcmFuncEqCommonDTO.getRcmFfailId());
            return query.toString();
        }
        query.getAndQuery("y.rcmlist_id", rcmFuncEqCommonDTO.getFilterRcmListId());
        query.getLikeQuery("y.description", rcmFuncEqCommonDTO.getFilterRcmDesc());
        
        
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFuncEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String rcmFfailId = id;
    	
    	query.append("DELETE FROM TARCMFFAIL			");
    	query.append("WHERE rcmffail_id = '"+rcmFfailId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	

    	return rtnValue;
    }

	@Override
	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("SELECT 			");
        query.append("	COUNT(1) 		");
        query.append("FROM TARCMFFAIL x, TARCMLIST y		");
        query.append("WHERE x.rcmlist_id= y.rcmlist_id		");
        query.append(this.getWhere(rcmFuncEqCommonDTO));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}