package dream.consult.comp.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.list.dao.ConsultCompListLovDAO;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;

/**
 * 회사코드 팝업
 * @author  hyosung
 * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 * @spring.bean id="consultCompListLovDAOTarget"
 * @spring.txbn id="consultCompListLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompListLovDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompListLovDAO
{
    /**
     *  검색
     * @author  hyosung
     * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param consultCompListLovDTO
     * @param loginUser
     * @return
     */
    public List findCompList(ConsultCompListLovDTO consultCompListLovDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT							");
        query.append("       comp_no code 				");
        query.append("       ,description     			");
        query.append("FROM   TACOMP     				");
        query.append("WHERE  1=1				    	");
        query.getLikeQuery("description", consultCompListLovDTO.getDescription());
        query.getLikeQuery("comp_no", consultCompListLovDTO.getCode());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    
    /**
     * 회사 검색 AC LOV
     * @author  hyosung
     * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param consultCompListLovDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findCompAcList(ConsultCompListLovDTO consultCompListLovDTO,
            User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       comp_no                        ");
        query.append("       ,description                   ");
        query.append("FROM   TACOMP                         ");
        query.append("WHERE  1=1                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    
    
    
}