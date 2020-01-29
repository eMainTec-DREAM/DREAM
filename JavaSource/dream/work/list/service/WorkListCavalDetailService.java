package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세
 * @author  kim210117
 * @version $Id: WorkListCavalDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkListCavalDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListCavalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkListCavalDetailDTO findDetail(String wkOrId, String woCalibValueId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListCavalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListCavalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
}
