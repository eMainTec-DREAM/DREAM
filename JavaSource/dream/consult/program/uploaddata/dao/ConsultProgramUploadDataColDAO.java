package dream.consult.program.uploaddata.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ÄÃ·³ - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface ConsultProgramUploadDataColDAO
{
    /**
     * FIND
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @param consultProgramUploadDataColDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
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
     * @param consultProgramUploadDataColDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    /**
     * INSERT
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @param consultProgramUploadDataColDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    /**
     * UPDATE
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @param consultProgramUploadDataColDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
}
