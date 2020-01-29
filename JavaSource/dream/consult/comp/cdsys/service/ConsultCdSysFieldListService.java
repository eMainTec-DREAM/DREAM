package dream.consult.comp.cdsys.service;

import java.util.List;

import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultCdSysFieldListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param consultCdSysFieldListDTO
     * @throws Exception
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, ConsultCdSysFieldListDTO consultCdSysFieldListDTO);
}
