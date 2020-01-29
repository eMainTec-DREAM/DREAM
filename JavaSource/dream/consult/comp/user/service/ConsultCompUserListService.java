package dream.consult.comp.user.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;

/**
 * CompUser Page - List Service
 * @author youngjoo38
 * @version $Id: ConsultCompUserListService.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface ConsultCompUserListService
{
    /**
     * FIND Comp User LIST
     * @param consultCompUserCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findCompUserList(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception;
    /**
     * DELETE Comp User LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCompUserList(String[] deleteRows, User user, ConsultCompUserCommonDTO consultCompUserCommonDTO) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompUserCommonDTO
     * @return
     */
    public String findTotalCount(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception;
    
    /**
     * RESET Comp User PASSWORD
     * @param resetRows
     * @param userNo
     * @param compNo
     * @return
     * @throws Exception
     */
    public int resetCompUserPw(String[] resetRows, String[] userNo, String[] compNo) throws Exception;
}
