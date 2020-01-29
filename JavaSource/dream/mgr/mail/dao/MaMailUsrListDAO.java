package dream.mgr.mail.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 목록 dao
 * @author  kim21017
 * @version $Id: MaMailUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaMailUsrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMailUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @param maMailUsrListDTO
     * @return List
     */
    public List findUsrList(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMailUsrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteUsrList(String deleteRow, String deleteRowsExt);
    
    public String findTotalCount(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user) throws Exception;

}