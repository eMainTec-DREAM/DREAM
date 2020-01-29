package dream.rcm.system.service;

import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 기능정의 상세
 * @author  kim21017
 * @version $Id: RcmSysFDefDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFDefDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFDefDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public RcmSysFDefDetailDTO findDetail(String rcmListId, String rcmFuncId, String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFDefDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFDefDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
}
