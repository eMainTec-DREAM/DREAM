package dream.consult.program.menu.service;

import common.bean.User;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;

/**
 * 메뉴 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaMenuMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaMenuMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMenuMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     * @throws Exception
     */
    public MaMenuMngDetailDTO findDetail(String menuId, String lang)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMenuMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMenuMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser) throws Exception;
}
