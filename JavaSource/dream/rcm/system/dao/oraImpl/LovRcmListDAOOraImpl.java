package dream.rcm.system.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.system.dao.LovRcmListDAO;
import dream.rcm.system.dto.LovRcmListDTO;

/**
 * 자산검색 팝업
 * @author  kim21017
 * @version $Id: LovRcmListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovRcmListDAOTarget"
 * @spring.txbn id="lovRcmListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovRcmListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovRcmListDAO
{
    /**
     * 자산 검색
     * @author  kim21017
     * @version $Id: LovRcmListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovRcmListDTO
     * @param loginUser
     * @return
     */
    public List findRcmList(LovRcmListDTO lovRcmListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT		");
        query.append("     rcmlist_id rcmListId,		");
        query.append("     description description, 		");
        query.append("     rcmlist_no rcmListNo 		");
        query.append("  FROM TARCMLIST		");
        query.append("WHERE 1=1																");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("description", lovRcmListDTO.getDescription());

        return getJdbcTemplate().queryForList(query.toString());
    }
}