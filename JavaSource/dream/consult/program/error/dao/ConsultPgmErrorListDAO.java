package dream.consult.program.error.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;

/**
 * Error Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface ConsultPgmErrorListDAO
{
	/**
	 * FIND LIST
	 * @param consultPgmErrorCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPgmErrorList(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePgmErrorList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param consultPgmErrorCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
    
}