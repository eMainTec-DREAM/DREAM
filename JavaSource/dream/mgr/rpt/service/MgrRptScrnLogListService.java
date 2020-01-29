
package dream.mgr.rpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;

/**
 * 화면접속로그 리스트 Page - List Service
 * @author euna0207
 * @version $Id$
 * @since 1.0
 */
public interface MgrRptScrnLogListService
{
    /**
     * FIND LIST
     * @param mgrRptScrnLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;

    /**
     * find Total Count
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrRptScrnLogCommonDTO
     * @return
     */
    public String findTotalCount(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;
}
