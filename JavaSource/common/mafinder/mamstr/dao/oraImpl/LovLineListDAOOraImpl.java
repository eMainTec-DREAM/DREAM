package common.mafinder.mamstr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dao.LovLineListDAO;
import common.mafinder.mamstr.dto.LovLineListDTO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * 무정지라인검색 팝업
 * @author  kim21017
 * @version $Id: LovLineListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovLineListDAOTarget"
 * @spring.txbn id="lovLineListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovLineListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovLineListDAO
{
    /**
     * 무정지라인 검색
     * @author  kim21017
     * @version $Id: LovLineListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLineListDTO
     * @param loginUser
     * @return
     */
    public List findLineList(LovLineListDTO lovLineListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								");
        query.append("       pop_line_id lineId,			");
        query.append("       pop_line_no lineNo,			");
        query.append("       pop_line_name lineDesc			");
        query.append("FROM TAPOPLINE						");
        query.append("WHERE 1=1								");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("pop_line_no", lovLineListDTO.getLineNo());
        query.getLikeQuery("pop_line_name", lovLineListDTO.getLineDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}