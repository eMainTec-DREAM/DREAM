package dream.consult.consult.menu.service;

import java.util.List;

import common.bean.User;
import dream.consult.consult.menu.dto.McMenuCommonDTO;


/**
 * 메뉴 - 목록 service
 * @author  kim21017
 * @version $Id: McMenuListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface McMenuListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: McMenuListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param mcMenuCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMenuList(McMenuCommonDTO mcMenuCommonDTO, User loginUser);    
    /**
     * delete
     * @author kim21017
     * @version $Id: McMenuListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteMenu(String[] deleteRows) throws Exception;

}
