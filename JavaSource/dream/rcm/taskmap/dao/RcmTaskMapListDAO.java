package dream.rcm.taskmap.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * ���� dao
 * @author  kim21017
 * @version $Id: RcmTaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmTaskMapListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return List
     */
    public List findQnaList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmTaskMapListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);

	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user);
}