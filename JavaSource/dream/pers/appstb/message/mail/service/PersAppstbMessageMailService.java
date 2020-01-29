package dream.pers.appstb.message.mail.service;

import common.bean.User;

import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 메일 서비스 처리.
 * 
 * @author kim21017
 * @version $Id: PersAppstbMessageMailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
@Deprecated
public interface PersAppstbMessageMailService
{    
    /**
     * Send Message
     * @author kim21017
     * @version $Id: PersAppstbMessageMailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param AppReqDetailDTO, User
     * @return
     * @throws Exception
     */
    @Deprecated
    public int sendMessageMail(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
}


