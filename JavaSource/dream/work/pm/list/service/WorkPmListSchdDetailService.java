package dream.work.pm.list.service;

import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업 일자 상세
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmListSchdDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmListSchdDetailDTO findDetail(String pmId, String pmEventSchedId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListSchdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListSchdDetailDTO workPmListSchdDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
}
