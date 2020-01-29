package dream.consult.comp.plant.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.plant.dao.LovPlantListDAO;
import dream.consult.comp.plant.dto.LovPlantListDTO;

/**
 * 플랜트 팝업
 * @author  kim21017
 * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovPlantListDAOTarget"
 * @spring.txbn id="lovPlantListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPlantListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovPlantListDAO
{
    /**
     *  검색
     * @author  kim21017
     * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPlantListDTO
     * @param loginUser
     * @return
     */
    public List findPlantList(LovPlantListDTO lovPlantListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT							");
        query.append("       comp_no     				");
        query.append("       ,plant     				");
        query.append("       ,description     			");
        query.append("FROM   TAPLANT     				");
        query.append("WHERE  1=1				    	");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("description", lovPlantListDTO.getDescription());
        query.getLikeQuery("plant", lovPlantListDTO.getPlant());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     *  AC 검색
     * @author  kim21017
     * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPlantListDTO
     * @param loginUser
     * @return
     */
    public List findPlantAcList(LovPlantListDTO lovPlantListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                            ");
        query.append("       x.comp_no                  ");
        query.append("       ,x.plant                   ");
        query.append("       ,x.description             ");
        query.append("FROM   TAPLANT x  INNER JOIN TAUSRPLANTAUTH y ON x.plant = y.plant AND y.is_auth= 'Y'       ");
        query.append("WHERE 1=1 						");
        query.getAndQuery("y.user_id", loginUser.getUserId());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getLikeQuery("x.description", lovPlantListDTO.getDescription());
        query.getLikeQuery("x.plant", lovPlantListDTO.getPlant());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}