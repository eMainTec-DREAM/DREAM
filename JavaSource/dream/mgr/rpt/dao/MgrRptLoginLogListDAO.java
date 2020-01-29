package dream.mgr.rpt.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;

/**
 * 로그인 로그 리스트 Page - List DAO
 * @author euna0207
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrRptLoginLogListDAO
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
     * FIND TOTAL LIST
     * @param mgrRptLoginLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;
 
}
