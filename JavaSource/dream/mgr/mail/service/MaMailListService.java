package dream.mgr.mail.service;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dto.MaMailCommonDTO;

/**
 * 메일수신자설정 - 목록 service
 * @author  kim21017
 * @version $Id: MaMailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaMailListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaMailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maMailCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMailList(MaMailCommonDTO maMailCommonDTO, User user);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaMailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDTOList
     * @return
     * @throws Exception
     */
    public int deleteMail(String[] deleteRows) throws Exception;
    
    public String findTotalCount(MaMailCommonDTO maMailCommonDTO, User user);


}
