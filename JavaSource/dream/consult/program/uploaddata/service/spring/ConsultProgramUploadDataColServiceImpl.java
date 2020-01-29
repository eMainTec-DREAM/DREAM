package dream.consult.program.uploaddata.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataColDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataColService;

/**
 * ÄÃ·³ - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultProgramUploadDataColServiceTarget"
 * @spring.txbn id="consultProgramUploadDataColService"
 * @spring.property name="consultProgramUploadDataColDAO" ref="consultProgramUploadDataColDAO"
 */
public class ConsultProgramUploadDataColServiceImpl implements ConsultProgramUploadDataColService
{
    private ConsultProgramUploadDataColDAO consultProgramUploadDataColDAO = null;

    public ConsultProgramUploadDataColDAO getConsultProgramUploadDataColDAO() {
        return consultProgramUploadDataColDAO;
    }
    
    public void setConsultProgramUploadDataColDAO(ConsultProgramUploadDataColDAO consultProgramUploadDataColDAO) {
        this.consultProgramUploadDataColDAO = consultProgramUploadDataColDAO;
    }
    
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception
    {      
        return consultProgramUploadDataColDAO.findList(consultProgramUploadDataDTO,consultProgramUploadDataColDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultProgramUploadDataColDAO.deleteList(id, user);
            }
        return result;
    }
    
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user)  throws Exception
    {
        return consultProgramUploadDataColDAO.findTotalCount(consultProgramUploadDataDTO, consultProgramUploadDataColDTO, user);
    }
    
    public ConsultProgramUploadDataColDTO findDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception
    {
        Map mapDto = (Map) consultProgramUploadDataColDAO.findList(new ConsultProgramUploadDataDTO(),consultProgramUploadDataColDTO,user).get(0);
        return (ConsultProgramUploadDataColDTO) CommonUtil.makeDTO(mapDto, ConsultProgramUploadDataColDTO.class);
    }
    
    public int insertDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception
    {
        return consultProgramUploadDataColDAO.insertDetail(consultProgramUploadDataColDTO, user);
    }
    
    public int updateDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception
    {
        return consultProgramUploadDataColDAO.updateDetail(consultProgramUploadDataColDTO, user);
    }
}
