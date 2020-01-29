package dream.consult.program.error.service;

import common.bean.User;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;
/**
 * Error Page - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmErrorDetailService
{    
	/**
	 * FIND PROGRAM ERROR DETAIL
	 * @param consultPgmErrorCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ConsultPgmErrorDetailDTO findPgmErrorDetail(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM ERROR
     * @param consultPgmErrorDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePgmErrorDetail(ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO, User user) throws Exception;
}
