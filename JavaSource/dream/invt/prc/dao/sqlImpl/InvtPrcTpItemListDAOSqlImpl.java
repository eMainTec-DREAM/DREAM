package dream.invt.prc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.prc.dao.InvtPrcTpItemListDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;

/**
 * 구매절차 Item dao
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
 * @since   1.0
 * @spring.bean id="invtPrcTpItemListDAOTarget"
 * @spring.txbn id="invtPrcTpItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcTpItemListDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcTpItemListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 2015/12/02 09:14:12 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @param invtPrcTpItemListDTO
     * @return List
     */
    public List findItemList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                                                                   ");
        query.append("         ''                                                                             SEQNO                            ");
        query.append("        ,''                                                                             ISDELCHECK                       ");
        query.append("        ,a.comp_no                                                                      COMPNO                           ");
        query.append("        ,a.invtprcph_id                                                                 INVTPRCPHID                      ");
        query.append("        ,a.invtprctp_id                                                                 INVTPRCTPID                      ");
        query.append("        ,dbo.SFACODE_TO_DESC(a.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+compNo+"','"+user.getLangId()+"')   LTYPE          ");
        query.append("        ,dbo.SFACODE_TO_DESC(a.invt_proc_stype,'INVT_PROC_STYPE','USR','"+compNo+"','"+user.getLangId()+"')   STYPE          ");
        query.append("        ,a.ref_depart                                                                   REFDEPT                          ");
        query.append("        ,a.ref_doc                                                                      REFDOC                           ");
        query.append("        ,a.ord_no                                                                       ORDNO                            ");
        query.append("        ,a.is_use                                                                       ISUSE                            ");
        query.append("        ,a.remark                                                                       REMARK                           ");
        query.append("        ,a.doc_prefix                                                                   DOCPREFIX                        ");
        query.append("FROM TAINVTPRCPH a                                                                                                       ");
        query.append("WHERE 1=1                                                                                                                ");
        query.getAndNumKeyQuery("a.comp_no", compNo);
        query.append(this.getWhere(invtPrcTpCommonDTO,invtPrcTpItemListDTO, user));                                                              
        query.getOrderByQuery("a.invtprcph_id", "a.ord_no", invtPrcTpItemListDTO.getOrderBy(), invtPrcTpItemListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtPrcTpItemListDTO.getIsLoadMaxCount(), invtPrcTpItemListDTO.getFirstRow()));
    }
    
    private String getWhere(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        /**조건 추가*/
        query.getAndQuery("a.invtprctp_id", invtPrcTpCommonDTO.getInvtPrcTpId());
        /**reload 할 때       */
        query.getAndQuery("a.invtprcph_id", invtPrcTpItemListDTO.getInvtPrcPhId());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author hyosung
     * @version $Id: InvtPrcTpItemListDAO.java,v 1.0 20155/12/02 08:25:47 hyosung Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String invtprcph_id=deleteRow;
    	
    	query.append("DELETE FROM TAINVTPRCPH			");
    	query.append("WHERE invtprcph_id   = ?	        ");
    	query.append("      AND comp_no   = ?           ");
    	
    	Object[] objects = new Object[] {
    			invtprcph_id
    	        ,user.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT						");
        query.append("        COUNT(*)              ");
        query.append("FROM TAINVTPRCPH a            ");
        query.append("WHERE 1=1                     ");
        query.append(this.getWhere(invtPrcTpCommonDTO,invtPrcTpItemListDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
    
}