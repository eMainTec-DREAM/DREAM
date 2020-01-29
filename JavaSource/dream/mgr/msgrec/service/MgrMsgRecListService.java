
package dream.mgr.msgrec.service;

import java.util.List;

import common.bean.User;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;

/**
 * 로그인 시도 로그 리스트 Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrMsgRecListService
{
    /**
     * FIND LIST
     * @param mgrMsgRecCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception;

    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrMsgRecCommonDTO
     * @return
     */
    public String findTotalCount(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception;
    
    public int deleteList(String[] deleteRows, User user) throws Exception;
}
