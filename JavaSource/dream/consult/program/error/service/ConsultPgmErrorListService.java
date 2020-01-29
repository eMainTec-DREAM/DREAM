/**
 * 
 */
package dream.consult.program.error.service;

import java.util.List;
import common.bean.User;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
/**
 * Error Page - List Service
 * @author Error
 * @version $Id$
 * @since 1.0
 */
public interface ConsultPgmErrorListService {
	/**
	 * FIND PROGRAM ERROR LIST
	 * @param consultPgmErrorCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPgmErrorList(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM ERROR LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePgmErrorList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM ERROR LIST COUNT
	 * @param consultPgmErrorCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception;
}
