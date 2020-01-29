package dream.work.pm.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmListShiftDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;

/**
 * 교대조 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmListShiftDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmListShiftDetailDAOTarget"
 * @spring.txbn id="workPmListShiftDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListShiftDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmListShiftDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param pmId
     * @param pmWkrShiftId
     * @param compNo
     * @return
     */
    public WorkPmListShiftDetailDTO findDetail(String pmId, String pmWrkShiftId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                                                                                          ");
        query.append("       x.pm_wrkshift_id       pmWrkShiftId,               ");
        query.append("       x.shift_type           shiftType,                  ");
        query.append("       dbo.SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+ loginUser.getLangId() +"') shiftTypeDesc,   ");
        query.append("       x.is_active            isActive,                   ");
        query.append("       x.REMARK               remark                      ");
        query.append("FROM   TAPMWRKSHIFT x                                     ");
        query.append("WHERE  x.pm_wrkshift_id   = '"+pmWrkShiftId+"'            ");
        query.append("  AND  x.comp_no          = '"+loginUser.getCompNo()+"'   ");
    
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = 
        		(WorkPmListShiftDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkPmListShiftDetailDTO()));
        
        return workPmListShiftDetailDTO;
    }
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMWRKSHIFT SET         ");
        query.append("  pm_id                   = ?,  ");
        query.append("  shift_type              = ?,  ");
        query.append("  is_active               = ?,  ");
        query.append("  remark                  = ?   ");
        query.append("WHERE pm_wrkshift_id      = ?   ");
        query.append("  AND comp_no             = ?   ");
        
        Object[] objects = new Object[] {
                maPmMstrCommonDTO.getPmId(),
                workPmListShiftDetailDTO.getShiftType(),
                workPmListShiftDetailDTO.getIsActive(),
                workPmListShiftDetailDTO.getRemark(),
                workPmListShiftDetailDTO.getPmWrkShiftId(),
                maPmMstrCommonDTO.getCompNo()
        };
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMWRKSHIFT              ");
        query.append("  (comp_no,   pm_wrkshift_id, pm_id,  ");
        query.append("   shift_type,    is_active,  remark  ");
        query.append("  )VALUES                             ");
        query.append("  (?,             ?,          ?,      ");
        query.append("   ?,             ?,          ?       ");
        query.append("  )                                   ");
        
        Object[] objects = new Object[] {
                maPmMstrCommonDTO.getCompNo(),
                workPmListShiftDetailDTO.getPmWrkShiftId(),
                maPmMstrCommonDTO.getPmId(),
                workPmListShiftDetailDTO.getShiftType(),
                workPmListShiftDetailDTO.getIsActive(),
                workPmListShiftDetailDTO.getRemark()
        };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}