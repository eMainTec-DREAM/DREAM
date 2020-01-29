package dream.mgr.rpt.logintrylog.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;

/**
 * 로그인 시도 로그 리스트 Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrRptLoginTryLogListDAO
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
     * FIND TOTAL LIST
     * @param mgrRptLoginTryLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) throws Exception;
 
}
