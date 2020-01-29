package dream.mgr.cdsys.dao;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @return List
     */
    public List findListTypeList(MgrCdSysCommonDTO mgrCdSysCommonDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO);
}