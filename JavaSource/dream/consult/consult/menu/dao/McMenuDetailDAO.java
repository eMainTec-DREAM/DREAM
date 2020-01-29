package dream.consult.consult.menu.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.consult.menu.dto.McMenuDetailDTO;


/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface McMenuDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public McMenuDetailDTO findDetail(String menuId, String lang);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(McMenuDetailDTO DetailDTO, User loginUser);

    /**
     * detail update
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(McMenuDetailDTO DetailDTO, User loginUser);
}