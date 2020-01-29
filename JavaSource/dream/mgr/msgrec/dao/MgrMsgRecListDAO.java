package dream.mgr.msgrec.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;

/**
 * �޽��� ���ż��� ����Ʈ Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrMsgRecListDAO
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
     * FIND TOTAL LIST
     * @param mgrMsgRecCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception;
 
    public int deleteList(String id, User user) throws Exception;
}
