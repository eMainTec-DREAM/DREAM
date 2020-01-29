package dream.mgr.rpt.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;

/**
 * 화면접속로그 리스트 Page - List DAO
 * @author euna0207
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrRptScrnLogListDAO
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
     * FIND TOTAL LIST
     * @param mgrRptScrnLogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception;
 
}
