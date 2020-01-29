package dream.mgr.usrcd.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovCdUsrdAcListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovCdUsrdAcListDAO
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
        QueryBuffer query = new QueryBuffer();
        
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
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("      cdusrd_id    AS ID      ");
        query.append("      ,cdusrd_id      ");
        query.append("      ,cdusrd_no      ");
        query.append("      ,description        ");
        query.append("      ,full_desc        ");
        query.append("      ,p_cdusrd_id        ");
        query.append("      ,MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl         ");
        query.append("      ,LEVEL lvl      ");
        query.append("		,remark AS REMARK	");	
        query.append("FROM  TACDUSRD        ");
        query.append("WHERE 1=1     ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.append("AND cdusrm_id IN (SELECT cdusrm_id         ");
        query.append("                                FROM TACDUSRM         ");
        query.append("                                WHERE 1=1         ");
        query.getAndQuery("dir_type", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("cdusrm_id", conditionMap);
        query.append("                                )     ");
        query.getLikeQuery("cdusrm_id", lovCdUsrdAcListDTO.getCdUsrmId());
        query.getLikeQuery("cdusrd_no", lovCdUsrdAcListDTO.getCdUsrdNo());
        query.getLikeQuery("description", lovCdUsrdAcListDTO.getCdUsrdDesc());
        query.append("START WITH p_cdusrd_id = 0                                            ");
        query.append("CONNECT BY PRIOR cdusrd_id = p_cdusrd_id              ");
        query.append("ORDER BY ord_no                    ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}