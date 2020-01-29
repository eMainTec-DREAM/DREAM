package dream.mgr.mail.service;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 목록
 * @author  kim21017
 * @version $Id: MaMailUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaMailUsrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaMailUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @param maMailUsrListDTO
     * @throws Exception
     */
    public List findUsrList(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaMailUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteUsrList(String[] m_id, String[] d_id) throws Exception;

    public String findTotalCount(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user) throws Exception;

}
