package dream.invt.list.service;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id: InvtPrcDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface InvtPrcDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    public InvtPrcDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user) throws Exception;
    /**
     * check prc
     * @author kim21017
     * @version $Id: InvtPrcDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtPrcDetailDTO
     * @return
     * @throws Exception
     */
    public String checkPrc(InvtCommonDTO invtCommonDTO, InvtPrcDetailDTO invtPrcDetailDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user) throws Exception;

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user);

    public void completeDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user);
}
