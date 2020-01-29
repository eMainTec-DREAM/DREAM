package dream.consult.consult.menu.service;

import common.bean.User;
import dream.consult.consult.menu.dto.McMenuDetailDTO;


/**
 * 메뉴 - 상세 service
 * 
 * @author kim21017
 * @version $Id: McMenuDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface McMenuDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: McMenuDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     * @throws Exception
     */
    public McMenuDetailDTO findDetail(String menuId, String lang)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: McMenuDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(McMenuDetailDTO mcMenuDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: McMenuDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(McMenuDetailDTO mcMenuDetailDTO, User loginUser) throws Exception;
}
