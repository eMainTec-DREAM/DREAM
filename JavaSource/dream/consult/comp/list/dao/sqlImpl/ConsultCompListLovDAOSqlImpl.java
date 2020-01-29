package dream.consult.comp.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
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
public class ConsultCompListLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompListLovDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT							");
        query.append("       comp_no code 				");
        query.append("       ,description     			");
        query.append("FROM   TACOMP     				");
        query.append("WHERE  1=1				    	");
        query.getLikeQuery("description", consultCompListLovDTO.getDescription());
        query.getLikeQuery("comp_no", consultCompListLovDTO.getCode());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findCompAcList(ConsultCompListLovDTO consultCompListLovDTO,
            User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("       comp_no 	                ");
        query.append("       ,description               ");
        query.append("FROM   TACOMP                     ");
        query.append("WHERE  1=1                        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
}