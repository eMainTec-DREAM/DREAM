package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;

/**
 * 교대조 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPmListShiftDetailService
{    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmListShiftDetailDTO findDetail(String pmId, String pmWrkShiftId,User loginUser)throws Exception;
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListShiftDetailDTO workPmListShiftDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
}
