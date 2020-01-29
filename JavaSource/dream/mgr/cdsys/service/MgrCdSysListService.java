package dream.mgr.cdsys.service;

import java.util.List;

import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * �ý����ڵ� - ��� service
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
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findListTypeList(MgrCdSysCommonDTO mgrCdSysCommonDTO);

    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO);    
}
