package dream.consult.program.linkedfunc.service;

import common.bean.User;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncDetailDTO;

/**
 * PgmLinkedFunc Page - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmLinkedFuncDetailService
{
    /**
     * FIND DETAIL
     * @param consultPgmLinkedFuncCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultPgmLinkedFuncDetailDTO findPgmLinkedFuncDetail(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO, User user) throws Exception;
    /**
     * INSERT DETAIL
     * @param consultPgmLinkedFuncDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param consultPgmLinkedFuncDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePgmLinkedFuncDetail(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO, User user) throws Exception;
}
