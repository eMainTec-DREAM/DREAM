package dream.rcm.crity.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Page - List DAO
 * @author kim21017
 * @version $Id: RcmCrityValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmCrityValLovDAO
{
	/**
	 * FIND LIST
	 * @param rcmCrityCommonDTO
	 * @param user
	 * @param conditionMap 
	 * @param columnMap 
	 * @return
	 * @throws Exception
	 */
    public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
}