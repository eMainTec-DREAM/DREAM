package dream.rcm.fmea.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;

/**
 *  dao
 * @author  kim21017
 * @version $Id: RcmFmeaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFmeaListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFmeaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @param user 
     * @return List
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user);

	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user);
}