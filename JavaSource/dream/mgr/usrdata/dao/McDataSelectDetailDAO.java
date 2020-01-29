package dream.mgr.usrdata.dao;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface McDataSelectDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public McDataSelectDetailDTO findDetail(String usrrpt_id, String lang);
    /**
     * detail insert
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(McDataSelectDetailDTO DetailDTO, User loginUser);
    /**
     * detail update
     * @author kim21017
     * @version $Id: McDataSelectDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(McDataSelectDetailDTO DetailDTO, User loginUser);
}