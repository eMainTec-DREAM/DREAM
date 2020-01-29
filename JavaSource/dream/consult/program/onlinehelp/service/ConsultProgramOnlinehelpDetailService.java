package dream.consult.program.onlinehelp.service;

import common.bean.User;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;

/**
 * 도움말 - 상세 service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultProgramOnlinehelpDetailService
{    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public ConsultProgramOnlinehelpDetailDTO findDetail(String id, User user)throws Exception;
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO) throws Exception;
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO) throws Exception;
}
