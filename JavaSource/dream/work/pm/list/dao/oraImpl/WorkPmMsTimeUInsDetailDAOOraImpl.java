package dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmMsTimeUInsDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 상세 dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @spring.bean id="workPmMsTimeUInsDetailDAOTarget"
 * @spring.txbn id="workPmMsTimeUInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmMsTimeUInsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmMsTimeUInsDetailDAO
{
    /**
     * detail find
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public WorkPmMsTimeUInsDetailDTO findDetail(WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT 										");
    	query.append("      x.pmmeasuretime_id       pmMsTimeId		");
    	query.append("    , x.pm_id                  pmId			");
    	query.append("    , x.measure_time           measureTimeId	");
    	query.append("    ,(select key_name																");
        query.append("     from talang																	");
        query.append("     where key_type = 'CODESET' and lang = '"+user.getLangId()+"'					");
        query.append("       and key_no = 'MEASURE_TIME.'||x.measure_time)  			oldMeasureTime	");
        query.append("    ,(select key_name																");
        query.append("     from talang																	");
        query.append("     where key_type = 'CODESET' and lang = '"+user.getLangId()+"'					");
        query.append("       and key_no = 'MEASURE_TIME.'||x.measure_time)  			measureTime		");
    	query.append("    , x.remark                 remark			");
    	query.append("FROM TAPMMEASURETIME x						");
    	query.append("WHERE 1=1										");
        query.append("  AND  x.comp_no			= ?					");
        query.append("  AND  x.pmmeasuretime_id	= ?					");
        
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,workPmMsTimeUInsListDTO.getPmMsTimeId()
        };
    
        WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = (WorkPmMsTimeUInsDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new WorkPmMsTimeUInsDetailDTO()));
        
        return workPmMsTimeUInsDetailDTO;
    }
    
    /**
     * detail update
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMMEASURETIME SET            ");
    	query.append("     measure_time				= ?		");
    	query.append("    ,remark					= ?     ");
    	query.append("WHERE pmmeasuretime_id        = ?     ");
    	query.append("  AND comp_no					= ?     ");

    	
    	Object[] objects = new Object[] {
    			workPmMsTimeUInsDetailDTO.getMeasureTimeId()
    			,workPmMsTimeUInsDetailDTO.getRemark()
    			,workPmMsTimeUInsDetailDTO.getPmMsTimeId()
    			,user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPMMEASURETIME                       ");
    	query.append("    (comp_no,			pmmeasuretime_id,	pm_id,	");
    	query.append("     measure_time,	remark						");
    	query.append("    )VALUES                                       ");
    	query.append("    (?,                ?,            ?,           ");
    	query.append("     ?,                ?                          ");
    	query.append("    )                                             ");

    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workPmMsTimeUInsDetailDTO.getPmMsTimeId()
    			,maPmMstrCommonDTO.getPmId()
    			,workPmMsTimeUInsDetailDTO.getMeasureTimeId()
    			,workPmMsTimeUInsDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String validTime(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAPMMEASURETIME			");
    	query.append("WHERE 1=1						");
    	query.getAndQuery("pm_id", maPmMstrCommonDTO.getPmId());
    	query.getAndQuery("measure_time", workPmMsTimeUInsDetailDTO.getMeasureTimeId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
   
	}
    
    
}