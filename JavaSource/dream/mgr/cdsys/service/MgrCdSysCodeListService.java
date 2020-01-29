package dream.mgr.cdsys.service;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� detail - code ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysCodeListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysCodeListDTO
     * @throws Exception
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysCodeListDTO mgrCdSysCodeListDTO);
}
