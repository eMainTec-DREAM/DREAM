package dream.part.rep.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rep.dao.MaPtRepAppDetailDAO;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPtRepAppDetailDAOTarget"
 * @spring.txbn id="maPtRepAppDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRepAppDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRepAppDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtRepAppDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       x.ptrepairlist_id      ptRepairListId,             ");
        query.append("       x.ptrprapplist_id      ptRprAppListId,             ");
        query.append("       x.ptapp_id             ptAppId,                    ");
        query.append("       y.rec_date             recDate,                    ");
        query.append("       y.eq_desc              eqDesc,                     ");
        query.append("       y.tot_amt              totAmt,                     ");
        query.append("       y.title                title,                      ");
        query.append("       y.contents             contents                    ");
        query.append("FROM   TAPTRPRAPPLIST x, TEPTAPPLIST y                    ");
        query.append("WHERE  x.comp_no   = y.comp_no                            ");
        query.append("  AND  x.ptapp_id  = y.ptapp_id                           ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.ptrprapplist_id", maPtRepCommonDTO.getPtRprAppListId());
    
        return (MaPtRepAppDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtRepAppDetailDTO()));
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @return
     */
    public int insertDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAPTRPRAPPLIST (                              ");
        query.append("   comp_no,               ptrprapplist_id,                ");
        query.append("   ptrepairlist_id,       ptapp_id                        ");
        query.append(")  VALUES  (                                              ");
        query.append("   ?,                     ?,                              ");
        query.append("   ?,                     ?                               ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                maPtRepAppDetailDTO.getCompNo(),
                maPtRepAppDetailDTO.getPtRprAppListId(),
                maPtRepAppDetailDTO.getPtRepairListId(),
                maPtRepAppDetailDTO.getPtAppId()
        };
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @return
     */
    public int updateDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
        query.append("UPDATE TAPTRPRAPPLIST SET          ");
        query.append("       ptapp_id            = ?     ");
        query.append("WHERE  ptrprapplist_id     = ?     ");
        query.append("  AND  comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                maPtRepAppDetailDTO.getPtAppId(),
                maPtRepAppDetailDTO.getPtRprAppListId(),
                maPtRepAppDetailDTO.getCompNo()
        };
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
       
}