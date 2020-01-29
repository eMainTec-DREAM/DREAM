	package dream.mgr.cdsys.service;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysFieldListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysFieldListDTO
     * @throws Exception
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysFieldListDTO mgrCdSysFieldListDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysFieldListDTO mgrCdSysFieldListDTO);
}
