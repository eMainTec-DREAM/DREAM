package common.mafinder.mamstr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dao.LovEqAppListDAO;
import common.mafinder.mamstr.dto.LovEqAppListDTO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

/**
 * 설비기안품의서 팝업
 * @author  kim21017
 * @version $Id: LovEqAppListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovEqAppListDAOTarget"
 * @spring.txbn id="lovEqAppListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqAppListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqAppListDAO
{
    /**
     * 설비기안품의서 검색
     * @author  kim21017
     * @version $Id: LovEqAppListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovEqAppListDTO
     * @param loginUser
     * @return
     */
    public List findEqAppList(LovEqAppListDTO lovEqAppListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT															");
        query.append("       1 AS seqNo,												");
        query.append("       eqapp_id AS eqAppId,										");
        query.append("       title AS title,											");
        query.append("       contents AS contents,										");
        query.append("       receiver AS receiver,										");
        query.getDate("req_date", "reqDate");
        query.append(",																	");
        query.getDate("app_date", "appDate");
        query.append(",																	");
        query.append("       eqapp_no AS eqAppNo										");
        query.append("FROM TEEQAPPLIST													");
        query.append("WHERE	1=1															");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("eqapp_no", lovEqAppListDTO.getEqAppId());
        query.getLikeQuery("title", lovEqAppListDTO.getTitle());
        query.getAndQuery("req_date", lovEqAppListDTO.getReqDate());
        query.getAndQuery("app_date", lovEqAppListDTO.getAppDate());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}