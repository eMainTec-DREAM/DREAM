package dream.consult.program.menu.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;

/**
 * 메뉴 - 목록 service
 * @author  kim21017
 * @version $Id: MaMenuMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaMenuMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaMenuMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maMenuMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMenuList(MaMenuMngCommonDTO maMenuMngCommonDTO, User loginUser);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMenuMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteMenu(String[] deleteRows) throws Exception;

}
