package dream.work.pm.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
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
public class WorkPmMsTimeUInsDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmMsTimeUInsDetailDAO
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT 										");
    	query.append("      x.pmmeasuretime_id       pmMsTimeId		");
    	query.append("    , x.pm_id                  pmId			");
    	query.append("    , x.measure_time           measureTimeId	");
        query.append("    ,(SELECT key_name																");
        query.append("     FROM talang																	");
        query.append("     WHERE key_type = 'CODESET' and lang = '"+user.getLangId()+"'					");
        query.append("       and key_no = 'MEASURE_TIME.'+x.measure_time)  				oldMeasureTime	");
        query.append("    ,(SELECT key_name																");
        query.append("     FROM talang																	");
        query.append("     WHERE key_type = 'CODESET' and lang = '"+user.getLangId()+"'					");
        query.append("       and key_no = 'MEASURE_TIME.'+x.measure_time)  				measureTime		");
    	query.append("    , x.remark                 remark			");
    	query.append("FROM TAPMMEASURETIME x						");
    	query.append("WHERE 1=1										");
        query.append("  AND  x.comp_no			= ?					");
        query.append("  AND  x.pmmeasuretime_id	= ?					");
        
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,workPmMsTimeUInsListDTO.getPmMsTimeId()
        };
        
        WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = 
        		(WorkPmMsTimeUInsDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new WorkPmMsTimeUInsDetailDTO()));
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String validTime(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAPMMEASURETIME			");
    	query.append("WHERE 1=1						");
    	query.getAndQuery("pm_id", maPmMstrCommonDTO.getPmId());
    	query.getAndQuery("measure_time", workPmMsTimeUInsDetailDTO.getMeasureTimeId());
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
	}

}