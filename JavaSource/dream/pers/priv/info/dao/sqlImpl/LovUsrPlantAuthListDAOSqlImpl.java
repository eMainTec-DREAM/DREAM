package dream.pers.priv.info.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.priv.info.dao.LovUsrPlantAuthListDAO;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;

/**
 * ������� �˾�
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="lovUsrPlantAuthListDAOTarget"
 * @spring.txbn id="lovUsrPlantAuthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsrPlantAuthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovUsrPlantAuthListDAO
{
    /**
     *  �˻�
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param lovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    public List findUsrPlantAuthList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT							");
        query.append("       comp_no     				");
        query.append("       ,plant     				");
        query.append("       ,description     			");
        query.append("FROM   TAPLANT     				");
        query.append("WHERE  1=1				    	");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("description", lovUsrPlantAuthListDTO.getDescription());
        query.getLikeQuery("plant", lovUsrPlantAuthListDTO.getPlant());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     *  AC �˻�
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param lovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    public List findPlantAcList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                            ");
        query.append("       x.comp_no                  ");
        query.append("       ,x.plant                   ");
        query.append("       ,x.description             ");
        query.append("FROM   TAPLANT x 					");
        query.append("WHERE  1=1				    	");
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getLikeQuery("x.description", lovUsrPlantAuthListDTO.getDescription());
        query.getLikeQuery("x.plant", lovUsrPlantAuthListDTO.getPlant());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}