package dream.rcm.pmtask.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskDetailDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmPmtaskDetailDAOTarget"
 * @spring.txbn id="rcmPmtaskDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmPmtaskDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskDetailDTO findDetail(RcmPmtaskCommonDTO rcmPmtaskCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                             	");
        query.append("       '' AS seqNo,                	");
        query.append("       d.item_no equipNo,             ");
        query.append("       d.description equipDesc,    	");
        query.append("       y.description funcDesc,    	");
        query.append("       z.description failDesc,    	");
        query.append("       c.description asmbDesc,    	");
        query.append("       f.mofail_desc mofailDesc,    	");
        query.append("       f.cafail_desc cafailDesc,    	");
        query.append("       f.effail_desc effailDesc, 		");
        query.append("       h.description pmDesc,			");
        query.append("       dbo.SFACODE_TO_DESC(h.rcm_task_type,'RCM_TASK_TYPE','SYS','','ko') rcmTaskTypeDesc,	");
        query.append("       h.rcm_task_type rcmTaskType,	");
        query.append("       dbo.SFACODE_TO_DESC(h.period_type,'PERIOD_TYPE','SYS','','ko') periodTypeDesc,	");
        query.append("       h.remark,						");
        query.append("       h.cycle,						");
        query.append("       h.comp_no compNo,   			");
        query.append("       h.period_type periodType,		");
        query.append("       x.rcmlist_id rcmlistId,    	");
        query.append("       y.rcmfunc_id rcmfuncId,    	");
        query.append("       z.rcmffail_id rcmffailId,    	");
        query.append("       b.rcmeq_id rcmeqId,        	");
        query.append("       c.rcmeqasmb_id rcmeqasmbId,	");
        query.append("       f.rcmfmea_id rcmfmeaId,		");
        query.append("       h.rcmpmtask_id rcmpmtaskId     ");
        query.append("FROM   TARCMLIST x                	");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                            		");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                        	");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id      	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                             	");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and ISNULL(b.rcmeqasmb_id,0) = ISNULL(f.rcmeqasmb_id,0)                       	");
        query.append("       LEFT OUTER JOIN TARCMPMTASK h ON f.rcmfmea_id = h.rcmfmea_id       	");
        query.append("WHERE  1=1       	");
        query.getAndNumKeyQuery("x.rcmlist_id", rcmPmtaskCommonDTO.getRcmlistId());
        query.getAndNumKeyQuery("y.rcmfunc_id", rcmPmtaskCommonDTO.getRcmfuncId());
        query.getAndNumKeyQuery("z.rcmffail_id", rcmPmtaskCommonDTO.getRcmffailId());
        query.getAndNumKeyQuery("b.rcmeq_id", rcmPmtaskCommonDTO.getRcmeqId());
        query.getAndNumKeyQuery("c.rcmeqasmb_id", rcmPmtaskCommonDTO.getRcmeqasmbId());
        query.getAndNumKeyQuery("f.rcmfmea_id", rcmPmtaskCommonDTO.getRcmfmeaId());
        query.getAndNumKeyQuery("h.rcmpmtask_id", rcmPmtaskCommonDTO.getRcmpmtaskId());

    
        RcmPmtaskDetailDTO rcmPmtaskDetailDTO = 
        		(RcmPmtaskDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmPmtaskDetailDTO()));
        
        return rcmPmtaskDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     */
    public int insertDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMPMTASK                                		");
    	query.append("    (comp_no,        	rcmpmtask_id,       rcmlist_id,        	");
    	query.append("     rcmfmea_id,     	description,    	rcm_task_type,      ");
    	query.append("     cycle,    		period_type,        remark              ");
    	query.append("    )    VALUES                                        		");
    	query.append("    (?,               ?,                	?,                	");
    	query.append("     ?,               ?,                	?,                	");
    	query.append("     ?,               ?,                	?                   ");
    	query.append("    )                                                			");

    	
    	Object[] objects = new Object[] {
    			rcmPmtaskDetailDTO.getCompNo(),
    			rcmPmtaskDetailDTO.getRcmpmtaskId(),
    			rcmPmtaskDetailDTO.getRcmlistId(),
    			rcmPmtaskDetailDTO.getRcmfmeaId(),
    			rcmPmtaskDetailDTO.getPmDesc(),
    			rcmPmtaskDetailDTO.getRcmTaskType(),
    			rcmPmtaskDetailDTO.getCycle(),
    			rcmPmtaskDetailDTO.getPeriodType(),
    			rcmPmtaskDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     */
    public int updateDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMPMTASK SET            			");
    	query.append("    description                	= ?,    	");
    	query.append("    rcm_task_type                 = ?,    	");
    	query.append("    cycle                			= ?,    	");
    	query.append("    period_type                	= ?,    	");
    	query.append("    remark                    	= ?        	");
    	query.append("WHERE rcmpmtask_id            	= ?        	");
    	query.append("  AND comp_no             		= ?        	");

    	
    	Object[] objects = new Object[] {
    			rcmPmtaskDetailDTO.getPmDesc(),
    			rcmPmtaskDetailDTO.getRcmTaskType(),
    			rcmPmtaskDetailDTO.getCycle(),
    			rcmPmtaskDetailDTO.getPeriodType(),
    			rcmPmtaskDetailDTO.getRemark(),
    			rcmPmtaskDetailDTO.getRcmpmtaskId(),
    			rcmPmtaskDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}