package dream.rcm.funceq.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

import dream.rcm.funceq.dao.LovRcmEqCtgAsmbListDAO;
import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * �������� �۾����� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovRcmEqCtgAsmbListDAOTarget"
 * @spring.txbn id="lovRcmEqCtgAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovRcmEqCtgAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovRcmEqCtgAsmbListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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