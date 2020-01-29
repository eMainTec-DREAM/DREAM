package dream.rcm.taskmap.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.taskmap.dao.LovTaskMapListDAO;
import dream.rcm.taskmap.dto.LovTaskMapListDTO;

/**
 * 질의 팝업
 * @author  hyosung
 * @version $Id: LovTaskMapListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 * @spring.bean id="lovTaskMapListDAOTarget"
 * @spring.txbn id="lovTaskMapListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovTaskMapListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovTaskMapListDAO
{
    /**
     * 질의 검색
     * @author  hyosung
     * @version $Id: LovTaskMapListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovTaskMapListDTO
     * @param loginUser
     * @return
     */
    public List findTaskMapList(LovTaskMapListDTO lovTaskMapListDTO,User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       pmtaskmap_no                                       ");
        query.append("       ,description                                       ");
        query.append("       ,pmtaskmap_id                                      ");
        query.append("FROM TAPMTASKMAP                                          ");
        query.append("WHERE 1=1                                                 ");
        query.getAndQuery("comp_no",loginUser.getCompNo());
        query.getLikeQuery("description", lovTaskMapListDTO.getTaskMapDesc());
        query.getAndQuery("pmtaskmaplist_id", conditionMap);
        query.append("AND pmtaskmap_id !=?                      ");
        query.append("ORDER BY pmtaskmap_no                                         ");
        
        
        Object[] objects = new Object[] {
                conditionMap.get("pmtaskmap_id"),
        };
        
        return getJdbcTemplate().queryForList(query.toString(),objects);
    }

  
    
}