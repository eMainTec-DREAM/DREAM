package dream.mgr.cdsys.service;

import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;

/**
 * �ý����ڵ� - �� service
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrCdSysDetailService
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     * @throws Exception
     */
    public MgrCdSysDetailDTO findDetail(String cdSysMId, String lang)throws Exception;
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrCdSysDetailDTO mgrCdSysDetailDTO) throws Exception;
}
