package dream.part.rep.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rep.dao.MaPtRepAppListDAO;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepAppListDAOTarget"
 * @spring.txbn id="maPtRepAppListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRepAppListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRepAppListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param maPtRepAppListDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO,MaPtRepAppListDTO maPtRepAppListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       1                      seqNo,                      ");
        query.append("       ''                     isDelCheck,                 ");
        query.append("       x.comp_no              compNo,                     ");
        query.append("       x.ptrprapplist_id      ptRprAppListId,             ");
        query.append("       x.ptrepairlist_id      ptRepairListId,             ");
        query.append("       x.ptapp_id             ptAppId,                    ");
        query.append("       y.title                title,                      ");
        query.getDate("y.rec_date", "recDate,");
        query.append("       y.eq_desc              eqDesc,                     ");
        query.append("       y.tot_amt              totAmt                      ");
        query.append("FROM   TAPTRPRAPPLIST x, TEPTAPPLIST y                    ");
        query.append("WHERE  x.comp_no  = y.comp_no                             ");
        query.append("  AND  x.ptapp_id = y.ptapp_id                            ");
        query.append(this.getWhere(maPtRepCommonDTO,maPtRepAppListDTO,loginUser));
        query.append("ORDER BY x.ptrepairlist_id                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     */
    public int deleteList(MaPtRepAppListDTO maPtRepAppListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTRPRAPPLIST                                       ");
    	query.append("WHERE  comp_no          = '"+maPtRepAppListDTO.getCompNo()+"'	   ");
    	query.append("  AND  ptrprapplist_id  = '"+maPtRepAppListDTO.getPtRprAppListId()+"'   ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    private String getWhere(MaPtRepCommonDTO maPtRepCommonDTO,MaPtRepAppListDTO maPtRepAppListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.ptrepairlist_id", maPtRepCommonDTO.getPtRepairListId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPtRepAppListDTO.getPtRprAppListId()))
        {
            query.getAndQuery("x.ptrprapplist_id", maPtRepAppListDTO.getPtRprAppListId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
}