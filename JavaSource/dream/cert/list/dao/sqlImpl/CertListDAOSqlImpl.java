package dream.cert.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.cert.list.dao.CertListDAO;
import dream.cert.list.dto.CertCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="certListDAOTarget"
 * @spring.txbn id="certListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CertListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CertListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certCommonDTO
     * @return List
     */
    public List findList(CertCommonDTO certCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT		");
        query.append("       '' seqNo,		");
        query.append("       '' isDelCheck,		");
        query.append("       x.cert_no certNo,		");
        query.append("       x.cert_name certName,		");
        query.append("       dbo.SFACODE_TO_DESC(x.cert_type, 'CERT_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"') certType,		");
        query.append("       x.cert_agency certAgency,		");
        query.append("       x.is_use isUse,		");
        query.append("       x.description description,		");
        query.append("       x.comp_no compNo,		");
        query.append("       x.certlist_id certListId		");
        query.append("FROM TACERTLIST x		");
        query.append("  WHERE 1=1                            ");
        query.append(this.getWhere(certCommonDTO, user));
        query.getOrderByQuery("x.certlist_id", "x.certlist_id", certCommonDTO.getOrderBy(), certCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(certCommonDTO.getIsLoadMaxCount(), certCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String certListId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TACERTLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  certlist_id  = '"+certListId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(CertCommonDTO certCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(certCommonDTO.getCertListId()))
        {
            query.getAndQuery("x.certlist_id", certCommonDTO.getCertListId());
            return query.toString();
        }

        //상태
        query.getCodeLikeQuery("x.cert_type", "dbo.SFACODE_TO_DESC(x.cert_type, 'CERT_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"')", 
                certCommonDTO.getFilterCertType(), certCommonDTO.getFilterCertTypeDesc());
        
        query.getLikeQuery("x.cert_name", certCommonDTO.getFilterCertName());
        return query.toString();
    }

    @Override
    public String findTotalCount(CertCommonDTO certCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("       COUNT(1)                   ");
        query.append("FROM TACERTLIST x     ");
        query.append("  WHERE 1=1                           ");
        query.append(this.getWhere(certCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}