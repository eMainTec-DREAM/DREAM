package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;

/**
 * 교대조 상세 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPmListShiftDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param pmId
     * @param pmShiftId
     * @param compNo
     * @return
     */
    public WorkPmListShiftDetailDTO findDetail(String pmId, String pmWrkShiftId, User loginUser);

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
    public int updateDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
    
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
    public int insertDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
}