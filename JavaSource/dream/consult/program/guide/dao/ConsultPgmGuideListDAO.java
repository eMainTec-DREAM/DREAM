package dream.consult.program.guide.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;

/**
 * Guide Page - List DAO
 * @author kim21017
 * @version $Id: ConsultPgmGuideListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface ConsultPgmGuideListDAO
{
	/**
	 * FIND LIST
	 * @param consultPgmGuideCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPgmGuideList(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePgmGuideList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param consultPgmGuideCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception;
    
}