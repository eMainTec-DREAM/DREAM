package dream.rcm.pmtask.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskCndtDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskCndtDetailDAOTarget"
 * @spring.txbn id="rcmPmtaskCndtDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskCndtDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmPmtaskCndtDetailDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}