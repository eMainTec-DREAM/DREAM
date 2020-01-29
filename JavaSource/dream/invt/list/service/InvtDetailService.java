package dream.invt.list.service;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id: InvtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface InvtDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    public InvtDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtDetailDTO invtDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(InvtDetailDTO invtDetailDTO, User user) throws Exception;

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
    public String copyDetail(InvtCommonDTO invtCommonDTO, InvtDetailDTO invtDetailDTO, User user)throws Exception;
    
    /**
     * Cancel Invt Status
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws Exception
     */
    public int cancelStatus(InvtDetailDTO invtDetailDTO, User user) throws Exception;

    /**
     * Confirm Invt Status
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @param user
     * @throws Exception 
     */
    public void confirmStatus(InvtDetailDTO invtDetailDTO, User user) throws Exception;
    
    public String changeStatus(InvtCommonDTO invtCommonDTO, User user) throws Exception;
}
