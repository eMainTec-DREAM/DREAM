package dream.consult.program.onlinehelp.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;

/**
 * 도움말 - 목록 service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultProgramOnlinehelpListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id$
     * @param consultProgramOnlinehelpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findHelpList(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteHelp(String[] deleteRows) throws Exception;

    public String findTotalCount(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user) throws Exception;

}
