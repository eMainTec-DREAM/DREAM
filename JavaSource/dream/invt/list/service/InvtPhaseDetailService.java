package dream.invt.list.service;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;

/**
 * detail
 * @author  kim210117
 * @version $Id: InvtPhaseDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface InvtPhaseDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPhaseDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseListDTO
     * @param invtCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public InvtPhaseDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPhaseDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public void updateDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPhaseDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception;
}
