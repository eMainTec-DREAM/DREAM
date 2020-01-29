package dream.mgr.mail.service;

import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;

/**
 * 메일수신자설정 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaMailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaMailDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     * @throws Exception
     */
    public MaMailDetailDTO findDetail(MaMailCommonDTO maMailCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaMailDetailDTO maMailDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaMailDetailDTO maMailDetailDTO) throws Exception;
    
    /**
     * Send Message
     * @author hankyul
     * @version $Id: MaMailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     * @throws Exception
     */
    public int sendMessage(MaMailDetailDTO maMailDetailDTO) throws Exception;
    public int sendMessageCheck(MaMailDetailDTO maMailDetailDTO) throws Exception;
    
    
    public void sendMessageAll() throws Exception;
    public int updateMailSendTime(MaMailDetailDTO maMailDetailDTO) throws Exception;
    
}
