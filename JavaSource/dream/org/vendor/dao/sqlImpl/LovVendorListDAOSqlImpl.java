package dream.org.vendor.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.vendor.dao.LovVendorListDAO;
import dream.org.vendor.dto.LovVendorListDTO;

/**
 * 거래처검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovVendorListDAOTarget"
 * @spring.txbn id="lovVendorListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovVendorListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovVendorListDAO
{
    /**
     * 자재 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovVendorListDTO
     * @param loginUser
     * @return
     */
    public List findVendorList(LovVendorListDTO lovVendorListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       vendor_id              vendorId,                   ");
        query.append("       vendor_no              vendorNo,                   ");
        query.append("       description            vendorDesc,                 ");
        query.append("       address+', '+repname addrNrepName,               ");
        query.append("       person,                                            ");
        query.append("       office,                                            ");
        query.append("       mobile,                                            ");
        query.append("       email                                              ");
        query.append("FROM TAVENDOR												");
        query.append("WHERE 1=1													");
        query.getAndQuery("vendor_no", lovVendorListDTO.getVendorNo());
        query.getLikeQuery("description", lovVendorListDTO.getVendorDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findVendorAcList(LovVendorListDTO lovVendorListDTO, User loginUser, Map<String, String> conditionMap) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                                    ");
        query.append("       vendor_id              ,                   		");
        query.append("       vendor_no              ,                   		");
        query.append("       description            ,                 			");
        query.append("       address+', '+repname addrNrepName,               	");
        query.append("       person,                                            ");
        query.append("       office,                                            ");
        query.append("       mobile,                                            ");
        query.append("       email                                              ");
        query.append("FROM TAVENDOR												");
        query.append("WHERE 1=1													");
        query.append(this.getWhere(lovVendorListDTO, loginUser, conditionMap));
        query.getOrderByQuery("vendor_id","vendor_no", lovVendorListDTO.getOrderBy(), lovVendorListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovVendorListDTO.getIsLoadMaxCount(), lovVendorListDTO.getFirstRow()));

	}
	
	private String getWhere(LovVendorListDTO lovVendorListDTO, User user, Map<String, String> conditionMap){
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        
        query.getAndQuery("comp_no", user.getCompNo());
        query.getLikeQuery("vendor_no", lovVendorListDTO.getVendorNo());
        query.getLikeQuery("description", lovVendorListDTO.getVendorDesc());

        return query.toString();
	}

	@Override
	public String findTotalCount(LovVendorListDTO lovVendorListDTO, User user, Map<String, String> conditionMap) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   TAVENDOR                                               ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(lovVendorListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}