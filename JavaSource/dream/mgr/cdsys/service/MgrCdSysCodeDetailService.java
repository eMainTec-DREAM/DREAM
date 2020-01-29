package dream.mgr.cdsys.service;

import common.bean.User;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 - detail-code 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysCodeDetailService
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     * @throws Exception
     */
    public MgrCdSysCodeDetailDTO findDetail(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO, User user)throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailDTO
     * @param mgrCdSysCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user) throws Exception;
}
