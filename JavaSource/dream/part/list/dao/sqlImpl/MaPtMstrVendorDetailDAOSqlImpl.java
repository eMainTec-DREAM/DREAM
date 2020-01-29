package dream.part.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrVendorDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;

/**
 * 何前芭贰贸 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrVendorDetailDAOTarget"
 * @spring.txbn id="maPtMstrVendorDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrVendorDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtMstrVendorDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtMstrVendorDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT x.comp_no                  compNo,                 ");
        query.append("       x.ptvendor_id	            ptVendorId,				");
        query.append("       x.part_id                  partId,					");
        query.append("       x.vendor_id	            vendorId,				");
        query.append("       y.vendor_no	            vendorNo,				");
        query.append("       y.description	            vendorDesc,	            ");
        query.append("       y.address+', '+y.repname	addrNrepName,			");
        query.append("       y.person                   person,					");
        query.append("       y.office                   office,     			");
        query.append("       y.mobile                   mobile,					");
        query.append("       y.email                    email					");
        query.append("FROM   TAPTVENDOR x, TAVENDOR y                           ");
        query.append("WHERE  x.vendor_id   = y.vendor_id			            ");
        query.append("  AND  x.comp_no     = '"+loginUser.getCompNo()+"'             ");
        query.append("  AND  x.ptvendor_id = '"+maPtMstrCommonDTO.getPtVendorId()+"' ");
    
        MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO = 
        		(MaPtMstrVendorDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtMstrVendorDetailDTO()));
        
        return maPtMstrVendorDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @return
     */
    public int updateVendor(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAVENDOR SET		");
    	query.append("	     person	    = ?,	");
    	query.append("	     office	    = ?,	");
    	query.append("	     mobile	    = ?,	");
    	query.append("	     email	    = ?  	");
    	query.append("WHERE  comp_no    = ?		");
    	query.append("  AND  vendor_id  = ?		");
    	
    	Object[] objects = new Object[] {
    			maPtMstrVendorDetailDTO.getPerson(),
    			maPtMstrVendorDetailDTO.getOffice(),
    			maPtMstrVendorDetailDTO.getMobile(),
    			maPtMstrVendorDetailDTO.getEmail(),
    			maPtMstrVendorDetailDTO.getCompNo(),
    			maPtMstrVendorDetailDTO.getVendorId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @return
     */
    public int insertPtVendor(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTVENDOR (				                    ");
    	query.append("	 comp_no,   ptvendor_id,    part_id,    vendor_id       ");
    	query.append(")	VALUES (                                                ");
    	query.append("	 ?,			?,	            ?,          ?               ");
    	query.append(")									                        ");
    	
    	Object[] objects = new Object[] {
    	        maPtMstrVendorDetailDTO.getCompNo(),
    			maPtMstrVendorDetailDTO.getPtVendorId(),
    			maPtMstrVendorDetailDTO.getPartId(),
    			maPtMstrVendorDetailDTO.getVendorId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}