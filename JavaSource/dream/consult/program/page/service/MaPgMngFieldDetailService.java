package dream.consult.program.page.service;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;

/**
 * 화면별 필드 상세
 * @author  kim210117
 * @version $Id: MaPgMngFieldDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     * @throws Exception
     */
	public MaPgMngFieldDetailDTO findDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception;
}
