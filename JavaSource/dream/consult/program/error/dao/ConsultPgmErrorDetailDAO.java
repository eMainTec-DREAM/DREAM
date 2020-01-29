package dream.consult.program.error.dao;

import common.bean.User;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;

/**
 * Error Page - Detail DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface ConsultPgmErrorDetailDAO
{
    /**
     * FIND DETAIL
     * @param consultPgmErrorCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultPgmErrorDetailDTO findPgmErrorDetail(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param consultPgmErrorDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePgmErrorDetail(ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO, User user) throws Exception;
    
}