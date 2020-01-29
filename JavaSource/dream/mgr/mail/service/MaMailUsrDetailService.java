package dream.mgr.mail.service;

import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 - detail
 * @author  kim210117
 * @version $Id: MaMailUsrDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaMailUsrDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrListDTO
     * @param maMailCommonDTO
     * @return
     * @throws Exception
     */
    public MaMailUsrDetailDTO findDetail(MaMailUsrListDTO maMailUsrListDTO, MaMailCommonDTO maMailCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailUsrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO) throws Exception;
}
