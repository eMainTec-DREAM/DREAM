package dream.consult.program.btn.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.btn.dao.LovButtonListDAO;
import dream.consult.program.btn.dto.LovButtonListDTO;

/**
 * ¹öÆ° ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovButtonListDAOTarget"
 * @spring.txbn id="lovButtonListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovButtonListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovButtonListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovButtonListDTO
     * @param loginUser
     * @return
     */
    public List findButtonList(LovButtonListDTO lovButtonListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       button_id buttonid,						");
        query.append("       button_no buttonno,						");
        query.append("       description buttondesc						");
        query.append("FROM   TABUTTON									");
        query.append("WHERE  1=1										");
        query.getLikeQuery("button_no", lovButtonListDTO.getButtonNo());
        query.getLikeQuery("description", lovButtonListDTO.getButtonDesc());
        query.append("ORDER BY button_no								");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}