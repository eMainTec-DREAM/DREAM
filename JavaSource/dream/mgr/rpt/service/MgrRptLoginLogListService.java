
package dream.mgr.rpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;

/**
 * 로그인 로그 리스트 Page - List Service
 * @author euna0207
 * @version $Id$
 * @since 1.0
 */
public interface MgrRptLoginLogListService
{
    /**
     * FIND LIST
     * @param mgrRptLoginLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;

    /**
     * find Total Count
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrRptLoginLogCommonDTO
     * @return
     */
    public String findTotalCount(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;
}
