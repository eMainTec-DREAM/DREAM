package dream.consult.program.menu.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;

/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaMenuMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaMenuMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMenuMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngCommonDTO
     * @return List
     */
    public List findMenuList(MaMenuMngCommonDTO maMenuMngCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMenuMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMenu(String id);
}