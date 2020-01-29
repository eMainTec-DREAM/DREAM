package dream.scheduler.list.service;

import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;

/**
 * 버튼 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaBatchMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaBatchMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBatchMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return
     * @throws Exception
     */
    public MaBatchMngDetailDTO findDetail(MaBatchMngCommonDTO maBatchMngCommonDTO)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBatchMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaBatchMngDetailDTO maBatchMngDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBatchMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaBatchMngDetailDTO maBatchMngDetailDTO) throws Exception;
}
