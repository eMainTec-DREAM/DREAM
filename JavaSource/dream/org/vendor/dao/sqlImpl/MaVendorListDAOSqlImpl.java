package dream.org.vendor.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.vendor.dao.MaVendorListDAO;
import dream.org.vendor.dto.MaVendorCommonDTO;

/**
 * ���þ�ü - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maVendorListDAOTarget"
 * @spring.txbn id="maVendorListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaVendorListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaVendorListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorCommonDTO
     * @return List
     */
    public List findList(MaVendorCommonDTO maVendorCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.comp_no                              compNo,     ");
        query.append("       x.vendor_id                            vendorId,   ");
        query.append("       x.vendor_no                            vendorNo,   ");
        query.append("       x.description                          vendorDesc, ");
        query.append("       x.address                              address,    ");
        query.append("       x.repname                              repName,    ");
        query.append("       x.office                               office,     ");
        query.append("       x.person                               person,     ");
        query.append("       x.email                                email,      ");
        query.append("       x.mobile                               mobile,     ");
        query.append("       x.remark,                                          ");
        query.append("       x.is_use								isUse       ");
        //query.append("       ,dbo.SFACODE_TO_DESC(x.vendor_type , 'VENDOR_TYPE', 'USR', '"+user.getCompNo()+"','"+user.getLangId()+"') vendorTypeDesc         ");
        query.append("FROM   TAVENDOR x                                         ");
    	query.append("WHERE  1=1												");
        query.append(this.getWhere(maVendorCommonDTO, user));
        query.getOrderByQuery("x.vendor_id","x.vendor_no", maVendorCommonDTO.getOrderBy(), maVendorCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maVendorCommonDTO.getIsLoadMaxCount(), maVendorCommonDTO.getFirstRow()));
        
    } 
    
    /**
     * Filter ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaVendorCommonDTO maVendorCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(maVendorCommonDTO.getVendorId()))
        {
            query.getAndQuery("x.vendor_id", maVendorCommonDTO.getVendorId());
            return query.toString();
        }
        
        //�ŷ�ó��
        query.getLikeQuery("x.description", maVendorCommonDTO.getFilterVendorDesc());
        
        //query.getUsrCdQuery("x.vendor_type", maVendorCommonDTO.getFilterVendorType(), maVendorCommonDTO.getFilterVendorTypeDesc(), "VENDOR_TYPE", user.getCompNo(), user.getLangId());
        
        //���
        query.getLikeQuery("x.remark", maVendorCommonDTO.getFilterRemark());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaVendorCommonDTO maVendorCommonDTO, User user) {
		// TODO Auto-generated method stub
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT count(*)							");
        query.append("FROM   TAVENDOR x							");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(maVendorCommonDTO, user));
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    /**
     * ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param vendorId
     * @return
     */
    public int deleteParts(String compNo, String vendorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAVENDOR                                           ");
        query.append("WHERE  comp_no   = ?                                      ");
        query.append("  AND  vendor_id = ?                                      ");      
        
        Object[] objects = new Object[] {   
                compNo,
                vendorId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}