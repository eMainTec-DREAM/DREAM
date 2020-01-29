package dream.consult.program.linkedfunc.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;


/**
 * PgmLinkedFunc Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface ConsultPgmLinkedFuncListDAO
{
    /**
     * FIND LIST
     * @param consultPgmLinkedFuncCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findPgmLinkedFuncList(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePgmLinkedFuncList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param consultPgmLinkedFuncCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception;
    
}
