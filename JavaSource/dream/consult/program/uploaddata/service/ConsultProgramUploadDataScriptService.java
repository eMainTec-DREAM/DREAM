package dream.consult.program.uploaddata.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;

/**
 * ø¢ºø ¬¸¡∂µ•¿Ã≈∏ - Service
 * @author ghlee
 * @version $Id$
 * @since 1.0
 */
public interface ConsultProgramUploadDataScriptService
{
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    public ConsultProgramUploadDataScriptDTO findDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    public int insertDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
    
    public int updateDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception;
}
