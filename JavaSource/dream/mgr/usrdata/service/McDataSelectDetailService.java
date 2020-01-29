package dream.mgr.usrdata.service;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 상세 service
 * 
 * @author kim21017
 * @version $Id: McDataSelectDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface McDataSelectDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: McDataSelectDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     * @throws Exception
     */
    public McDataSelectDetailDTO findDetail(String menuId, String lang)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: McDataSelectDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(McDataSelectDetailDTO mcDataSelectDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: McDataSelectDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(McDataSelectDetailDTO mcDataSelectDetailDTO, User loginUser) throws Exception;
}
