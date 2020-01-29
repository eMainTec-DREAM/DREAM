package dream.work.pm.list.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListSchdDetailDAO;
import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업 일자 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmListSchdDetailDAOTarget"
 * @spring.txbn id="workPmListSchdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListSchdDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListSchdDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public WorkPmListSchdDetailDTO findDetail(String pmId, String pmEventSchedId, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT												");
        query.append("       x.pmeventsched_id 				pmEventSchedId,	");
        query.append("       x.plan_date 					planDate		");
        query.append("FROM   TAPMEVENTSCHED x    							");
        query.append("WHERE	 x.pmeventsched_id 	= '"+pmEventSchedId+"'		");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        WorkPmListSchdDetailDTO workPmListSchdDetailDTO = 
        		(WorkPmListSchdDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkPmListSchdDetailDTO()));
        
        return workPmListSchdDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMEVENTSCHED SET			");
    	query.append("	plan_date				= ?		");
    	query.append("WHERE pmeventsched_id		= ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			workPmListSchdDetailDTO.getPlanDate(),
    			workPmListSchdDetailDTO.getPmEventSchedId(),
    			maPmMstrCommonDTO.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPMEVENTSCHED			");
    	query.append("	(comp_no,		pm_id,				");
    	query.append("	 plan_date,		pmeventsched_id		");
    	query.append("	)VALUES								");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maPmMstrCommonDTO.getCompNo(),
    	        maPmMstrCommonDTO.getPmId(),
    	        workPmListSchdDetailDTO.getPlanDate(),
    	        workPmListSchdDetailDTO.getPmEventSchedId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}