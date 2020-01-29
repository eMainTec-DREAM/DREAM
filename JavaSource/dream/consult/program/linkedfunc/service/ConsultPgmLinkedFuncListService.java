package dream.consult.program.linkedfunc.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;

/**
 * PgmLinkedFunc Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmLinkedFuncListService
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
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePgmLinkedFuncList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmLinkedFuncCommonDTO
     * @return
     */
    public String findTotalCount(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception;
}
