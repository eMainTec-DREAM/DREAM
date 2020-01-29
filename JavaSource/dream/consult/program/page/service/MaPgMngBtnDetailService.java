package dream.consult.program.page.service;

import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 상세
 * @author  kim210117
 * @version $Id: MaPgMngBtnDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPgMngBtnDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgBtnId
     * @return
     * @throws Exception
     */
	public MaPgMngBtnDetailDTO findDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngBtnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
}
