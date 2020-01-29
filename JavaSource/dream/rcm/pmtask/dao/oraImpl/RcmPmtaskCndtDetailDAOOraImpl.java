package dream.rcm.pmtask.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskCndtDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskCndtDetailDAOTarget"
 * @spring.txbn id="rcmPmtaskCndtDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskCndtDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmPmtaskCndtDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskCndtDetailDTO findDetail(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("       taskcndt									");
        query.append("       ,taskefinfo								");
        query.append("       ,remark									");
        query.append("       ,comp_no compNo							");
        query.append("       ,rcmpmtaskcndt_id rcmpmtaskcndtId			");
        query.append("       ,rcmpmtask_id rcmpmtaskId					");
        query.append("       ,rcmlist_id rcmlistId						");
        query.append("FROM TARCMPMTASKCNDT								");
        query.append("WHERE  comp_no = '"+compNo+"'					");
        query.getAndQuery("rcmpmtaskcndt_Id",rcmPmtaskCndtListDTO.getRcmpmtaskcndtId());
    
        RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO1 = 
        		(RcmPmtaskCndtDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmPmtaskCndtDetailDTO()));
        
        return rcmPmtaskCndtDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int updateDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMPMTASKCNDT SET        ");
    	query.append("      taskcndt           	= ?,	");
    	query.append("      taskefinfo          = ?,	");
    	query.append("      remark           	= ?		");
    	query.append("WHERE rcmpmtaskcndt_id    = ?     ");
    	query.append("  AND comp_no             = ?     ");

    	
    	Object[] objects = new Object[] {
    			rcmPmtaskCndtDetailDTO.getTaskcndt()
    			,rcmPmtaskCndtDetailDTO.getTaskefinfo()
    			,rcmPmtaskCndtDetailDTO.getRemark()
    			,rcmPmtaskCndtDetailDTO.getRcmpmtaskcndtId()
    			,rcmPmtaskCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int insertDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMPMTASKCNDT (                                     ");
    	query.append("    comp_no,        rcmpmtaskcndt_id,    rcmpmtask_id,            ");
    	query.append("    rcmlist_id,     taskcndt,            taskefinfo,              ");
    	query.append("    ord_no,     remark             								");
    	query.append("    )    VALUES (                                					");
    	query.append("    ?,              ?,                	?,                      ");
    	query.append("    ?,              ?,                	?,                    	");
    	query.append("    ?,              ?                                            	");
    	query.append("    )                                                          	");


    	
    	Object[] objects = new Object[] {
    			rcmPmtaskCommonDTO.getCompNo(),
    			rcmPmtaskCndtDetailDTO.getRcmpmtaskcndtId(),
    			rcmPmtaskCommonDTO.getRcmpmtaskId(),
    			rcmPmtaskCommonDTO.getRcmlistId(),
    			rcmPmtaskCndtDetailDTO.getTaskcndt(),
    			rcmPmtaskCndtDetailDTO.getTaskefinfo(),
    			rcmPmtaskCndtDetailDTO.getOrdNo(),
    			rcmPmtaskCndtDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}