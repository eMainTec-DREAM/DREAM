package dream.mgr.cdsys.dao;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� detail-code ��� dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysCodeListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysCodeListDTO
     * @return List
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysCodeListDTO mgrCdSysCodeListDTO);
    
}