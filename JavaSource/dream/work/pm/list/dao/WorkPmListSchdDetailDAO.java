package dream.work.pm.list.dao;

import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업 일자 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmListSchdDetailDAO
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
    public WorkPmListSchdDetailDTO findDetail(String pmId, String pmEventSchedId, String compNo);

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
    public int updateDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
    
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
    public int insertDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
}