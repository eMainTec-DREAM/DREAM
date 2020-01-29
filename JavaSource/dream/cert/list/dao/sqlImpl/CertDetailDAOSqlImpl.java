package dream.cert.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.cert.list.dao.CertDetailDAO;
import dream.cert.list.dto.CertDetailDTO;

/**
 * 자격증분류 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="certDetailDAOTarget"
 * @spring.txbn id="certDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CertDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements CertDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public CertDetailDTO findDetail(User user, String certListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT x.comp_no                             compNo,      ");
        query.append("       x.certlist_id                        certListId, ");
        query.append("       x.cert_no                        certNo, ");
        query.append("       x.cert_type                        certType, ");
        query.append("       dbo.SFACODE_TO_DESC(x.cert_type, 'CERT_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"') certTypeDesc, ");
        query.append("       x.cert_name                        certName, ");
        query.append("       x.cert_agency                        certAgency, ");
        query.append("       x.is_use                        isUse, ");
        query.append("       x.description                        certDesc, ");
        query.append("       x.howtoget                        howToGet ");
        query.append("FROM   TACERTLIST x                         ");
        query.append("WHERE  1=1                    ");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    ");
        query.append("  AND  x.certlist_id = '"+certListId+"'                 ");
    
        CertDetailDTO certDetailDTO = 
        		(CertDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new CertDetailDTO()));
        
        return certDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     */
    public int insertDetail(CertDetailDTO certDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TACERTLIST (                                  ");
    	query.append("  comp_no,   certlist_id, cert_no, cert_name,");
    	query.append("  cert_type,   cert_agency,     description,    howtoget,        ");
    	query.append("  is_use 	    											");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?            ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			certDetailDTO.getCompNo(),
    			certDetailDTO.getCertListId(),
    			certDetailDTO.getCertNo(),
    			certDetailDTO.getCertName(),
    			certDetailDTO.getCertType(),
    			certDetailDTO.getCertAgency(),
    			certDetailDTO.getCertDesc(),
    			certDetailDTO.getHowToGet(),
    			certDetailDTO.getIsUse()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailDTO
     * @return
     */
    public int updateDetail(CertDetailDTO certDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TACERTLIST SET		      ");
    	query.append("	     cert_name         = ?,      ");
        query.append("       cert_type         = ?,      ");
        query.append("       cert_agency       = ?,      ");
        query.append("       description       = ?,      ");    
        query.append("       howtoget          = ?,      ");    
        query.append("       is_use            = ?      ");
    	query.append("WHERE  comp_no           = ?	      ");
    	query.append("  AND  certlist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
    			certDetailDTO.getCertName(),
    			certDetailDTO.getCertType(),
    			certDetailDTO.getCertAgency(),
    			certDetailDTO.getCertDesc(),
    			certDetailDTO.getHowToGet(),
    			certDetailDTO.getIsUse(),
    			certDetailDTO.getCompNo(),
    			certDetailDTO.getCertListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

    
}