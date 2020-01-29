package dream.consult.program.menu.dao;

import common.bean.User;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;

/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaMenuMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaMenuMngDetailDTO findDetail(String menuId, String lang);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     */
    public int insertDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     */
    public int updateDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser);
}