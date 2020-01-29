package dream.consult.program.page.service;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;

/**
 * 화면별 탭페이지 상세
 * @author  kim210117
 * @version $Id: MaPgMngPageDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPgMngPageDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngPageDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgPageId
     * @return
     * @throws Exception
     */
    public MaPgMngPageDetailDTO findDetail(String pageId, String pgPageId,String lang)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngPageDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngPageDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
}
