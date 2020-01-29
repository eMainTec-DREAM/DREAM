package dream.consult.program.uploaddata.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ø¢ºø ¬¸¡∂µ•¿Ã≈∏ - List DAO
 * @author ghlee
 * @version $Id$
 * @since 1.0
 *
 */
public interface ConsultProgramUploadDataScriptDAO
{
    /**
     * FIND
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @param consultProgramUploadDataScriptDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    /**
     * DELETE
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    
    /**
     * FIND TOTAL COUNT
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @param consultProgramUploadDataScriptDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    /**
     * INSERT
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataScriptDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    /**
     * UPDATE
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataScriptDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
}
