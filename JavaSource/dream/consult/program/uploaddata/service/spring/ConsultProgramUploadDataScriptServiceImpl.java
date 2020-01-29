package dream.consult.program.uploaddata.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataScriptDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataScriptService;

/**
 * ø¢ºø ¬¸¡∂µ•¿Ã≈∏ - Service implements
 * @author ghlee
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultProgramUploadDataScriptServiceTarget"
 * @spring.txbn id="consultProgramUploadDataScriptService"
 * @spring.property name="consultProgramUploadDataScriptDAO" ref="consultProgramUploadDataScriptDAO"
 */
public class ConsultProgramUploadDataScriptServiceImpl implements ConsultProgramUploadDataScriptService
{
    private ConsultProgramUploadDataScriptDAO consultProgramUploadDataScriptDAO = null;

    public ConsultProgramUploadDataScriptDAO getConsultProgramUploadDataScriptDAO() {
        return consultProgramUploadDataScriptDAO;
    }
    
    public void setConsultProgramUploadDataScriptDAO(ConsultProgramUploadDataScriptDAO consultProgramUploadDataScriptDAO) {
        this.consultProgramUploadDataScriptDAO = consultProgramUploadDataScriptDAO;
    }
    
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception
    {      
        return consultProgramUploadDataScriptDAO.findList(consultProgramUploadDataDTO,consultProgramUploadDataScriptDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultProgramUploadDataScriptDAO.deleteList(id, user);
            }
        return result;
    }
    
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user)  throws Exception
    {
        return consultProgramUploadDataScriptDAO.findTotalCount(consultProgramUploadDataDTO, consultProgramUploadDataScriptDTO, user);
    }
    
    public ConsultProgramUploadDataScriptDTO findDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception
    {
        Map mapDto = (Map) consultProgramUploadDataScriptDAO.findList(new ConsultProgramUploadDataDTO(),consultProgramUploadDataScriptDTO,user).get(0);
        return (ConsultProgramUploadDataScriptDTO) CommonUtil.makeDTO(mapDto, ConsultProgramUploadDataScriptDTO.class);
    }
    
    public int insertDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception
    {
        return consultProgramUploadDataScriptDAO.insertDetail(consultProgramUploadDataScriptDTO, user);
    }
    
    public int updateDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception
    {
        return consultProgramUploadDataScriptDAO.updateDetail(consultProgramUploadDataScriptDTO, user);
    }
}
