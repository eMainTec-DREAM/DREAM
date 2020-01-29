package dream.consult.comp.user.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;


/**
 * CompUser Page - List DAO
 * @author youngjoo38
 * @version $Id: ConsultCompUserListDAO.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface ConsultCompUserListDAO
{
    /**
     * FIND LIST
     * @param consultCompUserCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findCompUserList(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCompUserList(String id, User user, ConsultCompUserCommonDTO consultCompUserCommonDTO) throws Exception;
    /**
     * DELETE LIST
     * @param consultCompUserCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception;

    /**
     * RESET PASSWORD
     * @param id
     * @param userNo
     * @param compNo
     * @return
     * @throws Exception
     */
    public int resetCompUserPw(String id, String userNo, String compNo) throws Exception;
    
}
