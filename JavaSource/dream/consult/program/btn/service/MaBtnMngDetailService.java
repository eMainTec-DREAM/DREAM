package dream.consult.program.btn.service;

import dream.consult.program.btn.dto.MaBtnMngDetailDTO;

/**
 * 버튼 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaBtnMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaBtnMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBtnMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     * @throws Exception
     */
    public MaBtnMngDetailDTO findDetail(String buttonId)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBtnMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaBtnMngDetailDTO maBtnMngDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBtnMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaBtnMngDetailDTO maBtnMngDetailDTO) throws Exception;
}
