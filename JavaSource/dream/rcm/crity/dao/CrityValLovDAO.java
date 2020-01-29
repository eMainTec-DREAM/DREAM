package dream.rcm.crity.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.rcm.crity.dto.CrityValLovDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Page - List DAO
 * @author kim21017
 * @version $Id: CrityValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface CrityValLovDAO
{
    /**
     * Criticality °Ë»ö
     * @author  kim21017
     * @version $Id: CrityValLovDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param crityValLovDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findList(CrityValLovDTO crityValLovDTO,User loginUser, Map<String, String> conditionMap);
    
}