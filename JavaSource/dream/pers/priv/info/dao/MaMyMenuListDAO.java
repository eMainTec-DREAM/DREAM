package dream.pers.priv.info.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 *  ��� dao
 * @author  jung7126
 * @version $Id: MaMyMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaMyMenuListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaMyMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param page_id
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaMyMenuListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteList(String usrmenuId);
}