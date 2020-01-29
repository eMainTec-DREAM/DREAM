package dream.mgr.cdsys.dao;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysFieldListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysFieldListDTO
     * @return List
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysFieldListDTO mgrCdSysFieldListDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysFieldListDTO mgrCdSysFieldListDTO);
}