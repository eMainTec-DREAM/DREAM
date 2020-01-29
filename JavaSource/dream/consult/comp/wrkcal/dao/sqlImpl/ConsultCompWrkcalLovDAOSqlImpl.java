package dream.consult.comp.wrkcal.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalLovDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;

/**
 * 근무달력 팝업
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalLovDAOTarget"
 * @spring.txbn id="consultCompWrkcalLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompWrkcalLovDAO
{
    /**
     *  검색
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param consultCompWrkcalLovDTO
     * @param loginUser
     * @return
     */
    public List findWrkcalList(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("       comp_no                    ");
        query.append("       ,wrkcallist_no code        ");
        query.append("       ,description description   ");
        query.append("       ,wrkcallist_id wrkcalListId");
        query.append("FROM   TAWRKCALLIST               ");
        query.append("WHERE  1=1                        ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("description", consultCompWrkcalLovDTO.getWrkcalListDesc());
        query.getAndQuery("plant", consultCompWrkcalLovDTO.getPlantNo());
        query.getLikeQuery("wrkcallist_no", consultCompWrkcalLovDTO.getWrkcalListNo());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findWrkcalAcList(
            ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO, User loginUser,
            Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("       comp_no                    ");
        query.append("       ,wrkcallist_no             ");
        query.append("       ,description               ");
        query.append("       ,wrkcallist_id             ");
        query.append("       ,plant                     ");
        query.append("       ,is_use                    ");
        query.append("FROM   TAWRKCALLIST               ");
        query.append("WHERE  1=1                        ");
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("description", consultCompWrkcalLovDTO.getWrkcalListDesc());
        query.getLikeQuery("plant", consultCompWrkcalLovDTO.getPlantDesc());
        query.getLikeQuery("wrkcallist_no", consultCompWrkcalLovDTO.getWrkcalListNo());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}