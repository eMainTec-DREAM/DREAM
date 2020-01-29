package dream.mgr.mail.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dto.MaMailCommonDTO;

/**
 * 메일수신자설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaMailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaMailListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return List
     */
    public List findMailList(MaMailCommonDTO maMailCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMailListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMail(String id);
    
    public String findTotalCount(MaMailCommonDTO maMailCommonDTO, User user);

}