package dream.consult.comp.cdsys.dao;

import java.util.List;

import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface ConsultCdSysFieldListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param consultCdSysFieldListDTO
     * @return List
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, ConsultCdSysFieldListDTO consultCdSysFieldListDTO);
}