/**
 * 
 */
package dream.consult.program.guide.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
/**
 * Guide Page - List Service
 * @author kim21017
 * @version $Id: ConsultPgmGuideListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultPgmGuideListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param consultPgmGuideCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPgmGuideList(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePgmGuideList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param consultPgmGuideCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
}
