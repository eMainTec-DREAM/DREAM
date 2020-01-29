package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaPtMstrVendorListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrVendorListDAO
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
        QueryBuffer query = new QueryBuffer();
        
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
        query.getOrderByQuery("NVL(y.vendor_no,x.part_id)", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());

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
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAPTVENDOR                                   ");
    	query.append("WHERE  comp_no     = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  ptvendor_id = '"+ptVendorId+"'			           ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.part_id", maPtMstrCommonDTO.getPartId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPtMstrCommonDTO.getPtVendorId()))
        {
            query.getAndQuery("x.ptvendor_id", maPtMstrCommonDTO.getPtVendorId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws Exception 
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPTVENDOR x, TAVENDOR y                           ");
        query.append("WHERE  x.vendor_id = y.vendor_id	                        ");
        query.append("  AND  x.comp_no  = y.comp_no                             ");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    	
    }
}