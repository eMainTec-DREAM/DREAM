package dream.consult.program.onlinehelp.dao;

import common.bean.User;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;

/**
 * 도움말 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultProgramOnlinehelpDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public ConsultProgramOnlinehelpDetailDTO findDetail(String id, User user);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     */
    public int insertDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO);

    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     */
    public int updateDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO);
}