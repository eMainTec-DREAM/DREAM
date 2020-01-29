package dream.rcm.pmtask.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskMapDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskMapDetailDAOTarget"
 * @spring.txbn id="rcmPmtaskMapDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskMapDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmPmtaskMapDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskMapDetailDTO findDetail(RcmPmtaskMapListDTO rcmPmtaskMapListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT 												");
        query.append("       x.pmtaskmap_no pmtaskmapNo,                	");
        query.append("       x.description,                        			");
        query.append("       x.value,                            			");
        query.append("       x.rslt_task rsltTask,                        	");
        query.append("       x.remark,                                    	");
        query.append("       x.rcmpmtaskmap_id rcmpmtaskmapId,            	");
        query.append("       x.rcmpmtask_id rcmpmtaskId,                	");
        query.append("       x.rcmlist_id rcmlistId,                    	");
        query.append("       x.rcmfmea_id rcmfmeaId                        	");
        query.append("FROM   TARCMPMTASKMAP x                             	");
        query.append("WHERE  comp_no = '"+compNo+"'					");
        query.getAndQuery("rcmpmtaskmap_id",rcmPmtaskMapListDTO.getRcmpmtaskmapId());
    
        RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO1 = 
        		(RcmPmtaskMapDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmPmtaskMapDetailDTO()));
        
        return rcmPmtaskMapDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int updateDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMPMTASKMAP SET         ");
    	query.append("      remark           	= ?		");
    	query.append("WHERE rcmpmtaskmap_id     = ?     ");
    	query.append("  AND comp_no             = ?     ");

    	
    	Object[] objects = new Object[] {
    			rcmPmtaskMapDetailDTO.getRemark(),
    			rcmPmtaskMapDetailDTO.getRcmpmtaskmapId(),
    			rcmPmtaskCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int insertDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMCRITY (                                		");
    	query.append("    comp_no,        rcmcrity_id,      rcmfmea_id,        		");
    	query.append("    rcmlist_id,     col_name,        	row_name,            	");
    	query.append("    crityvalue,     critycolor,      	is_critical,			");
    	query.append("    remark,         crity_lvl                            		");
    	query.append("    )    VALUES                (                            	");
    	query.append("    ?,              ?,                ?,                		");
    	query.append("    ?,              ?,                ?,     					");
    	query.append("    ?,              ?,                ?,            			");
    	query.append("    ?,              ?                                    		");
    	query.append("    )                                          				");

    	
    	Object[] objects = new Object[] {
//    			rcmPmtaskCommonDTO.getCompNo(),
//    			rcmPmtaskMapDetailDTO.getRcmcrityId(),
//    			rcmPmtaskCommonDTO.getRcmfmeaId(),
//    			rcmPmtaskCommonDTO.getRcmlistId(),
//    			rcmPmtaskMapDetailDTO.getColName(),
//    			rcmPmtaskMapDetailDTO.getRowName(),
//    			rcmPmtaskMapDetailDTO.getCrityvalue(),
//    			rcmPmtaskMapDetailDTO.getCritycolor(),
//    			rcmPmtaskMapDetailDTO.getIsCritical(),
//    			rcmPmtaskMapDetailDTO.getRemark(),
//    			rcmPmtaskMapDetailDTO.getCrityLvl()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}