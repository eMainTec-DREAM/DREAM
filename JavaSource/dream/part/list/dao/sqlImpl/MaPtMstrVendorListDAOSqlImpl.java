package dream.part.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrVendorListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 何前芭贰贸 格废 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrVendorListDAOTarget"
 * @spring.txbn id="maPtMstrVendorListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrVendorListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtMstrVendorListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											        ");
        query.append("       ''                                      seqNo,		");
        query.append("       ''                                      isDelCheck,");
        query.append("       x.comp_no                               compNo,	");
        query.append("       x.ptvendor_id                           ptVendorId,");
        query.append("       x.part_id                               partId,    ");
        query.append("       x.vendor_id 			                 vendorId,	");
        query.append("       y.vendor_no                             vendorNo,  ");
        query.append("       y.description                           vendorDesc,");
        query.append("       y.address                               address,   ");
        query.append("       y.repname                               repName,   ");
        query.append("       y.person                                person,    ");
        query.append("       y.office                                office,    ");
        query.append("       y.mobile                                mobile,    ");
        query.append("       y.email                                 email      ");
        query.append("FROM   TAPTVENDOR x, TAVENDOR y                           ");
        query.append("WHERE  x.vendor_id = y.vendor_id	                        ");
        query.append("  AND  x.comp_no  = y.comp_no                             ");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.ptvendor_id","ISNULL(y.vendor_no,x.PART_ID)", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));
        
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptVendorId
     * @return
     */
    public int deleteList(String ptVendorId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAPTVENDOR                                   ");
    	query.append("WHERE  comp_no     = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  ptvendor_id = '"+ptVendorId+"'			           ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.part_id", maPtMstrCommonDTO.getPartId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPtMstrCommonDTO.getPtVendorId()))
        {
            query.getAndQuery("x.ptvendor_id", maPtMstrCommonDTO.getPtVendorId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

	   	query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM   TAPTVENDOR x, TAVENDOR y           ");
        query.append("WHERE  x.vendor_id = y.vendor_id	        ");
        query.append("  AND  x.comp_no  = y.comp_no             ");
        query.append(this.getWhere(maPtMstrCommonDTO,user));
	        
	    List resultList=  getJdbcTemplate().queryForList(query.toString());
	       
	    return QuerySqlBuffer.listToString(resultList);
    }
}