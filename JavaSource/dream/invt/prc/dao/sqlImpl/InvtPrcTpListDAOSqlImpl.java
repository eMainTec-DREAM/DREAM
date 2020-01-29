package dream.invt.prc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.prc.dao.InvtPrcTpListDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * 구매절차 dao
 * @author  kim21017
 * @version $Id: InvtPrcTpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPrcTpListDAOTarget"
 * @spring.txbn id="invtPrcTpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcTpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcTpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtPrcTpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return List
     */
    public List findInvtPrcTpList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
        query.append("SELECT		                                                    ");
        query.append("        ''                                  	seqNo		    	");
        query.append("        ,''                                 	isDelCheck			");
        query.append("        ,invtprctp_no							invtPrctpNo			");
        query.append("        ,description 	                        invtPrctpDesc		");
        query.append("        ,is_use 		                        isUse		    	");
        query.append("        ,reg_date 	                        regDate		    	");
        query.append("        ,remark 		                        remark		    	");
        query.append("        ,invtprctp_id                         invtPrctpId     	");
        query.append("FROM TAINVTPRCTP		                                            ");
        query.append("WHERE 1=1		                                                    ");
        query.append(this.getWhere(invtPrcTpCommonDTO, user));                                                     
//        query.append("ORDER by invtprctp_no desc				                                             ");
        query.getOrderByQuery("invtprctp_id", "invtprctp_no desc", invtPrcTpCommonDTO.getOrderBy(), invtPrcTpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtPrcTpCommonDTO.getIsLoadMaxCount(), invtPrcTpCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: InvtPrcTpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("comp_no", user.getCompNo());
        
        if (!"".equals(invtPrcTpCommonDTO.getInvtPrcTpId()))
        {
            query.getAndQuery("invtprctp_id", invtPrcTpCommonDTO.getInvtPrcTpId());
            return query.toString();
        }
        query.getLikeQuery("description", invtPrcTpCommonDTO.getFilterInvtDesc());

        
        
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: InvtPrcTpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String invtPrcTpId = id;
    	
    	query.append("DELETE FROM TAINVTPRCTP			        ");
    	query.append("WHERE invtprctp_id = '"+invtPrcTpId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	

    	return rtnValue;
    }
    
    
    @Override
    public int deleteTpItem(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        String invtPrcTpId = id;
        
        query.append("DELETE FROM TAINVTPRCPH                                  ");
        query.append("WHERE invtprctp_id = '"+invtPrcTpId+"'                   ");
        query.getAndQuery("comp_no", user.getCompNo());
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        

        return rtnValue;
    }
 
    
    @Override
    public int deleteDoc(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        //invtPrcTpId
        String object_id = id;
        
        query.append("  DELETE FROM TADOCUMENT                                       ");
        query.append("  WHERE 1=1                                                     ");
        query.append("  AND comp_no ='"+user.getCompNo()+"'                         ");
        query.append("  AND doc_id = (SELECT b.doc_id FROM TAOBJDOC b               ");
        query.append("  WHERE b.object_id = '"+object_id+"'                           ");
        query.append("   AND  b.object_type = 'INVTPRC' )                             ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    
    @Override
    public int deleteObjDoc(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        //invtPrcTpId
        String object_id = id;
        
        query.append("DELETE FROM TAOBJDOC                                  ");
        query.append("WHERE object_id = '"+object_id+"'                     ");
        query.getAndQuery("comp_no", user.getCompNo());
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }

	@Override
	public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT		                ");
        query.append("		COUNT(*)		     	");
        query.append("FROM TAINVTPRCTP		    	");
        query.append("WHERE 1=1		                ");
        query.append(this.getWhere(invtPrcTpCommonDTO, user));                                                     
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
        
	}
    
    
    
 
    
}