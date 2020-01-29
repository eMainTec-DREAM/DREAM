package dream.org.vendor.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.org.vendor.dao.MaVendorDetailDAO;
import dream.org.vendor.dto.MaVendorDetailDTO;

/**
 * 관련업체 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maVendorDetailDAOTarget"
 * @spring.txbn id="maVendorDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaVendorDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaVendorDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaVendorDetailDTO findDetail(String compNo, String vendorId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                        compNo,     ");
        query.append("       x.vendor_id                            vendorId,   ");
        query.append("       x.vendor_no                            vendorNo,   ");
        query.append("       x.description		                    description,");
        query.append("       x.address                              address,    ");
        query.append("       x.repname                              repName,    ");
        query.append("       x.office                               office,     ");
        query.append("       x.person                               person,     ");
        query.append("       x.email                                email,      ");
        query.append("       x.mobile                               mobile,     ");
        query.append("       x.remark                               remark,     ");
        query.append("       x.is_use                               isUse       ");
        query.append("       ,x.vendor_type                         vendorType  ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.vendor_type , 'VENDOR_TYPE', 'USR', '"+compNo+"','"+user.getLangId()+"') vendorTypeDesc         ");
        query.append("FROM   TAVENDOR x						                    ");
        query.append("WHERE  x.comp_no   = '"+compNo+"'	                        ");
        query.append("  AND  x.vendor_id = '"+vendorId+"'	                    ");
    
        MaVendorDetailDTO maVendorDetailDTO = 
        		(MaVendorDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaVendorDetailDTO()));
        
        return maVendorDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public int insertDetail(MaVendorDetailDTO maVendorDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAVENDOR(                                     ");
    	query.append("   comp_no,      vendor_id,  vendor_no,   description,	");
    	query.append("   address,      repname,    office,      person,		    ");
    	query.append("   email,        mobile,     remark,      is_use,        	");
    	query.append("   vendor_type                                            ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,	           ?,          ?,			?,              ");
    	query.append("   ?                                                      ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maVendorDetailDTO.getCompNo(),
    			maVendorDetailDTO.getVendorId(),
    			maVendorDetailDTO.getVendorNo(),
    			maVendorDetailDTO.getDescription(),
    			maVendorDetailDTO.getAddress(),
    			maVendorDetailDTO.getRepName(),
    			maVendorDetailDTO.getOffice(),
    			maVendorDetailDTO.getPerson(),
    			maVendorDetailDTO.getEmail(),
    			maVendorDetailDTO.getMobile(),
    			maVendorDetailDTO.getRemark(),
    			maVendorDetailDTO.getIsUse(),
    			maVendorDetailDTO.getVendorType()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public int updateDetail(MaVendorDetailDTO  maVendorDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAVENDOR SET	                ");
    	query.append("	     description    = ?,	        ");
        query.append("       address        = ?,            ");
        query.append("       repname        = ?,            ");
        query.append("       office         = ?,            ");
        query.append("       person         = ?,            ");
        query.append("       email          = ?,            ");
        query.append("       mobile         = ?,            ");
        query.append("       remark         = ?,            ");
        query.append("       vendor_type    = ?,            ");
        query.append("       is_use         = ?             ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  vendor_id      = ?	            ");
    	
    	Object[] objects = new Object[] {
                maVendorDetailDTO.getDescription(),
                maVendorDetailDTO.getAddress(),
                maVendorDetailDTO.getRepName(),
                maVendorDetailDTO.getOffice(),
                maVendorDetailDTO.getPerson(),
                maVendorDetailDTO.getEmail(),
                maVendorDetailDTO.getMobile(),
                maVendorDetailDTO.getRemark(),
                maVendorDetailDTO.getVendorType(),
                maVendorDetailDTO.getIsUse(),
                maVendorDetailDTO.getCompNo(),
                maVendorDetailDTO.getVendorId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public String validVendorNo(MaVendorDetailDTO maVendorDetailDTO)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAVENDOR x                                         ");
        query.append("WHERE  x.comp_no  = '" + maVendorDetailDTO.getCompNo()  + "'");
        query.append("  AND  x.vendor_no= '" + maVendorDetailDTO.getVendorNo()+ "'");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}