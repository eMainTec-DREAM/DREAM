package dream.consult.comp.wrkcal.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalLovDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;

/**
 * �ٹ��޷� �˾�
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalLovDAOTarget"
 * @spring.txbn id="consultCompWrkcalLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalLovDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompWrkcalLovDAO
{
    /**
     *  �˻�
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT							");
        query.append("       comp_no  					");
        query.append("       ,wrkcallist_no code 		");
        query.append("       ,description description	");
        query.append("       ,wrkcallist_id wrkcalListId");
        query.append("FROM   TAWRKCALLIST  				");
        query.append("WHERE  1=1				    	");
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
        QueryBuffer query = new QueryBuffer();

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