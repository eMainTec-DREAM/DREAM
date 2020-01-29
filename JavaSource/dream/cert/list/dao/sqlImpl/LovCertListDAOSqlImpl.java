package dream.cert.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.cert.list.dao.LovCertListDAO;
import dream.cert.list.dto.LovCertListDTO;

/**
 * 자격증 팝업
 * @author  hyosung
 * @version $Id: LovCertListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 * @spring.bean id="lovCertListDAOTarget"
 * @spring.txbn id="lovCertListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovCertListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovCertListDAO
{
    /**
     * 자격증 검색
     * @author  hyosung
     * @version $Id: LovCertListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovCertListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findCertList(LovCertListDTO lovCertListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                ");
        query.append("       x.certlist_id                                                  ");
        query.append("       ,x.cert_no                                                     ");
        query.append("       ,x.description                                                 ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.cert_type,'CERT_TYPE','USR','"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"') cert_type ");
        query.append("       ,x.is_use                                                      ");
        query.append("       ,x.cert_agency                                                 ");
        query.append("       ,x.cert_name                                                   ");
        query.append("       ,x.comp_no                                                     ");
        query.append("FROM TACERTLIST x                                                     ");
        query.append("WHERE 1=1                                                             ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("cert_name", lovCertListDTO.getCertName());
        query.getLikeQuery("cert_no", lovCertListDTO.getCertNo());
        query.getAndQuery("is_use", lovCertListDTO.getIsUse());
        return getJdbcTemplate().queryForList(query.toString());
    }
}