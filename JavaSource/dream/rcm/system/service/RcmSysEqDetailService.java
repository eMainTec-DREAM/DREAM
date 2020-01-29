package dream.rcm.system.service;

import common.bean.User;

import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비설정 상세
 * @author  kim21017
 * @version $Id: RcmSysEqDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public RcmSysEqDetailDTO findDetail(String rcmListId, String rcmEqId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
}
