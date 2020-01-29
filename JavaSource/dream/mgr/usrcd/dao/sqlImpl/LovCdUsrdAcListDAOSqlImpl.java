package dream.mgr.usrcd.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrcd.dao.LovCdUsrdAcListDAO;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;

/**
 * 상세코드 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovCdUsrdAcListDAOTarget"
 * @spring.txbn id="lovCdUsrdAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovCdUsrdAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovCdUsrdAcListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovCdUsrdAcListDTO
     * @param loginUser
     * @return
     */
    public List findCdUsrdList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                         ");
        query.append("       cdusrd_id               ");
        query.append("      , cdusrd_no              ");
        query.append("      , description            ");
        query.append("		, remark				 ");
        query.append("FROM   TACDUSRD                ");
        query.append("WHERE 1=1						 ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("cdusrm_id", lovCdUsrdAcListDTO.getCdUsrmId());

        query.append("ORDER by cdusrd_no             ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findCdUsrdAcList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User user, Map<String, String> conditionMap) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                                                               ");
        query.append("        x.cdusrd_id                                                AS ID              ");
        query.append("      , x.cdusrd_id                                                AS CDUSRD_ID       ");
        query.append("      , x.cdusrd_no                                                AS CDUSRD_NO       ");
        query.append("      , x.description                                              AS DESCRIPTION     ");
        query.append("      , x.full_desc                                                AS FULL_DESC       ");
        query.append("      , x.p_cdusrd_id                                              AS P_CDUSRD_ID     ");
        query.append("      , MIN(y.LVL) OVER( ORDER BY ISNULL(x.cdusrd_id, '99999999')) AS MINLVL          ");
        query.append("      , y.lvl                                                      AS LVL             ");
        query.append("      , x.remark                                                   AS REMARK	        ");	
        query.append("  FROM TACDUSRD x, (SELECT * FROM dbo.SFACDUSRD_ALL('"+user.getCompNo()+"','0')) y    ");
        query.append(" WHERE x.cdusrd_id = y.cdusrd_id                                                      ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.append("   AND cdusrm_id IN (SELECT cdusrm_id                                                 ");
        query.append("                       FROM TACDUSRM                                                  ");
        query.append("                      WHERE 1 = 1                                                     ");
        query.getAndQuery("dir_type", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("cdusrm_id", conditionMap);
        query.append("                    )                                                                 ");
        query.getLikeQuery("x.cdusrm_id", lovCdUsrdAcListDTO.getCdUsrmId());
        query.getLikeQuery("x.cdusrd_no", lovCdUsrdAcListDTO.getCdUsrdNo());
        query.getLikeQuery("x.description", lovCdUsrdAcListDTO.getCdUsrdDesc());
        query.append(" ORDER BY x.ord_no                                                                    ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}