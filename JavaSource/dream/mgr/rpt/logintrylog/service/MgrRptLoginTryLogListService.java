
package dream.mgr.rpt.logintrylog.service;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;

/**
 * 로그인 시도 로그 리스트 Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrRptLoginTryLogListService
{
    /**
     * FIND LIST
     * @param mgrRptLoginTryLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) throws Exception;

    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrRptLoginTryLogCommonDTO
     * @return
     */
    public String findTotalCount(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) throws Exception;
}
