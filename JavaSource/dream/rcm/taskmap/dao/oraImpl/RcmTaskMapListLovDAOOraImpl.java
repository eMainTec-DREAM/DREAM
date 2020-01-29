package dream.rcm.taskmap.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.taskmap.dao.RcmTaskMapListLovDAO;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;

/**
 * Task Map ÆË¾÷
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="rcmTaskMapListLovDAOTarget"
 * @spring.txbn id="rcmTaskMapListLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmTaskMapListLovDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmTaskMapListLovDAO
{

    @Override
    public List findTaskMapAcList(RcmTaskMapListLovDTO rcmTaskMapListLovDTO, User loginUser,  Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       pmtaskmaplist_id        AS pmTaskMapList_id     ");
        query.append("       ,pmtaskmaplist_no      AS pmTaskMapList_no    ");
        query.append("       ,description               AS description             ");
        query.append("FROM TAPMTASKMAPLIST                                   ");
        query.append("WHERE 1=1                                                      ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        
        query.getLikeQuery("description", rcmTaskMapListLovDTO.getDescription());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}