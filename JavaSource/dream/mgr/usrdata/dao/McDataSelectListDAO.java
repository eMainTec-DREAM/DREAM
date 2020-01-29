package dream.mgr.usrdata.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface McDataSelectListDAO 
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public List findMenuList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMenu(String id);
    
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;
  
}