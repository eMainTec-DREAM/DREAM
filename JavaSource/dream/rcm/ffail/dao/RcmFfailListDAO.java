package dream.rcm.ffail.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * ÁúÀÇ dao
 * @author  kim21017
 * @version $Id: RcmFfailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFfailListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFfailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return List
     */
    public List findQnaList(RcmFfailCommonDTO rcmFfailCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFfailListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);

	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, User user);
}