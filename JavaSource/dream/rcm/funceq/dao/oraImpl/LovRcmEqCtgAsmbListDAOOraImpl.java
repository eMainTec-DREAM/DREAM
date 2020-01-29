package dream.rcm.funceq.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.rcm.funceq.dao.LovRcmEqCtgAsmbListDAO;
import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * 설비종류 작업부위 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovRcmEqCtgAsmbListDAOTarget"
 * @spring.txbn id="lovRcmEqCtgAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovRcmEqCtgAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovRcmEqCtgAsmbListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRcmEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgAsmbList(LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT rcmeqasmb_id AS EQASMBID,							");
        query.append("       description	AS description,							");
        query.append("       remark			AS remark								");
        query.append("FROM   TARCMEQASMB x											");
        query.append("WHERE  comp_no  ='"+ loginUser.getCompNo()+"'					");
        
        if(!"".equals(lovRcmEqCtgAsmbListDTO.getExtCode1()))
        {
        	query.append("  AND  rcmeq_id  ='"+ lovRcmEqCtgAsmbListDTO.getExtCode1()+"'	");
        }                
        query.getLikeQuery("description", lovRcmEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        query.append("ORDER BY rcmeqasmb_id												");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}