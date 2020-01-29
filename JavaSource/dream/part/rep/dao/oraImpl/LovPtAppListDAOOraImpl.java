package dream.part.rep.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rep.dao.LovPtAppListDAO;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * 수리기안품의서 팝업
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="lovPtAppListDAOTarget"
 * @spring.txbn id="lovPtAppListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPtAppListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPtAppListDAO
{
    /**
     * 수리기안품의서 검색
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovPtAppListDTO
     * @param loginUser
     * @return
     */
    public List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 1          AS seqNo,			");
        query.append("		ptapp_id   AS ptAppId,			");
        query.append("		title      AS title,			");
        query.getDate("rec_date", "recDate,");
        query.append("		eq_desc    AS eqDesc,			");
        query.append("		tot_amt    AS totAmt,			");
        query.append("		contents   AS contents			");
        query.append("FROM TEPTAPPLIST						");
        query.append("WHERE 1=1								");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("ptapp_id", lovPtAppListDTO.getPtAppId());
        query.getLikeQuery("title", lovPtAppListDTO.getTitle());
        query.getAndQuery("rec_date", lovPtAppListDTO.getRecDate());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}