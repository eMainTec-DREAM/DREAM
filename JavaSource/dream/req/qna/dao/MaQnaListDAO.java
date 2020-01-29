package dream.req.qna.dao;

import java.util.List;

import common.bean.User;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * ÁúÀÇ dao
 * @author  kim21017
 * @version $Id: MaQnaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaQnaListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaQnaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return List
     */
    public List findQnaList(MaQnaCommonDTO maQnaCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaQnaListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);
    
    public String findTotalCount(MaQnaCommonDTO maQnaCommonDTO, User user);
}