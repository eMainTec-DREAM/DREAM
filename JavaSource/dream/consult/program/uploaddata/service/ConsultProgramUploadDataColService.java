package dream.consult.program.uploaddata.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ÄÃ·³ - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultProgramUploadDataColService
{
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    public ConsultProgramUploadDataColDTO findDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    public int insertDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
    
    public int updateDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception;
}
