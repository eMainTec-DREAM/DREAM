package dream.consult.program.page.service;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngDetailDTO;

/**
 * 화면 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaPgMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @return
     * @throws Exception
     */
    public MaPgMngDetailDTO findDetail(String pageId, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPgMngDetailDTO maPgMngDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPgMngDetailDTO maPgMngDetailDTO) throws Exception;
}
