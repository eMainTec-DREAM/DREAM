package dream.invt.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtItemsListDAO;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="invtItemsListDAOTarget"
 * @spring.txbn id="invtItemsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtItemsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtItemsListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtItemsListDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 							");
        query.append("    ''                seqNo       ");
        query.append("  , ''                isDelCheck  ");
        query.append("  , x.invtitems_id	INVTITEMSID	");
        query.append("  , x.invtlist_id     INVTLISTID	");
        query.append("  , x.description     ITEMDESC	");
        query.append("  , x.price           AMT			");
        query.append("  , x.ord_no          ORDNO		");
        query.append("  , x.REMARK          REMARK		");
        query.append("FROM TAINVTITEMS x				");
        query.append("WHERE  1 = 1 						");
        query.append(this.getWhere(invtCommonDTO, user));
        query.getOrderByQuery("x.invtitems_id", "x.ord_no", invtCommonDTO.getOrderBy(), invtCommonDTO.getDirection());
            
        return getJdbcTemplate().queryForList(query.toString(invtCommonDTO.getIsLoadMaxCount(), invtCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String id = deleteRow;
        
    	query.append("DELETE FROM TAINVTITEMS			");
    	query.append("WHERE invtitems_id 	= '"+id+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(invtCommonDTO.getInvtItemsId()))
        {
            query.getAndQuery("invtitems_id", invtCommonDTO.getInvtItemsId());
            return query.toString();
        }
    	query.getAndNumKeyQuery("invtlist_id", invtCommonDTO.getInvtlistId());
    	query.getAndQuery("comp_no", user.getCompNo());
    	
        return query.toString();
    }

	@Override
	public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM TAINVTITEMS x		");
        query.append("WHERE  1 = 1 				");
        query.append(this.getWhere(invtCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}