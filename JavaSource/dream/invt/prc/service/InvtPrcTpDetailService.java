package dream.invt.prc.service;

import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;

/**
 * 구매절차 - 상세 service
 * 
 * @author kim21017
 * @version $Id: InvtPrcTpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface InvtPrcTpDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcTpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return
     * @throws Exception
     */
    public InvtPrcTpDetailDTO findDetail(InvtPrcTpCommonDTO invtPrcTpCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcTpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcTpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: InvtPrcTpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO) throws Exception;
}
