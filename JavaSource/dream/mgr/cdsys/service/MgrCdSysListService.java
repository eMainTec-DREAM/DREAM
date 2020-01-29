package dream.mgr.cdsys.service;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 - 목록 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param mgrCdSysCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findListTypeList(MgrCdSysCommonDTO mgrCdSysCommonDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO);    
}
