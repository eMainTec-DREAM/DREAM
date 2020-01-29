package dream.rcm.pmtask.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;

/**
 *  dao
 * @author  kim21017
 * @version $Id: RcmPmtaskListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param user 
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user);

	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user);
}