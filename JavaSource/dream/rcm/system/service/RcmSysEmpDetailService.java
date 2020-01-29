package dream.rcm.system.service;

import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 자분석 상세
 * @author  kim21017
 * @version $Id: RcmSysEmpDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEmpDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public RcmSysEmpDetailDTO findDetail(String rcmListId, String rcmEmpId, String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception;
}
