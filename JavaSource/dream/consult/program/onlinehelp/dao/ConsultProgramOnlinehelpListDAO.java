package dream.consult.program.onlinehelp.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;

/**
 * 도움말 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultProgramOnlinehelpListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpCommonDTO
     * @return List
     */
    public List findHelpList(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDTOList
     * @return
     */
    public int deleteHelp(String id);

    public String findTotalCount(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user) throws Exception;
}