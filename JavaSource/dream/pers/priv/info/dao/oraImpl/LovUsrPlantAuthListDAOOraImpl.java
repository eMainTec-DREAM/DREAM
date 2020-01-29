package dream.pers.priv.info.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.LovUsrPlantAuthListDAO;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;

/**
 * 공장권한 팝업
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="lovUsrPlantAuthListDAOTarget"
 * @spring.txbn id="lovUsrPlantAuthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsrPlantAuthListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovUsrPlantAuthListDAO
{
    /**
     *  검색
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
        QueryBuffer query = new QueryBuffer();
        
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
     *  AC 검색
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT							");
        query.append("       x.comp_no     				");
        query.append("       ,x.plant     				");
        query.append("       ,x.description     		");
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