package dream.consult.program.uploaddata.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataService;

/**
 * 업로드데이타 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultProgramUploadDataServiceTarget"
 * @spring.txbn id="consultProgramUploadDataService"
 * @spring.property name="consultProgramUploadDataDAO" ref="consultProgramUploadDataDAO"
 */
public class ConsultProgramUploadDataServiceImpl implements ConsultProgramUploadDataService
{
    private ConsultProgramUploadDataDAO consultProgramUploadDataDAO = null;

    public ConsultProgramUploadDataDAO getConsultProgramUploadDataDAO() {
		return consultProgramUploadDataDAO;
	}

	public void setConsultProgramUploadDataDAO(ConsultProgramUploadDataDAO consultProgramUploadDataDAO) {
		this.consultProgramUploadDataDAO = consultProgramUploadDataDAO;
	}

	public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {      
        return consultProgramUploadDataDAO.findList(consultProgramUploadDataDTO,user);
    }
	
	public int delete(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultProgramUploadDataDAO.delete(id);
            }
        return result;
    }

    @Override
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {
        return consultProgramUploadDataDAO.findTotalCount(consultProgramUploadDataDTO, user);
    }
    
    public ConsultProgramUploadDataDTO findDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)throws Exception
    {
        Map mapDto = (Map) consultProgramUploadDataDAO.findList(consultProgramUploadDataDTO, user).get(0);
        return (ConsultProgramUploadDataDTO) CommonUtil.makeDTO(mapDto, ConsultProgramUploadDataDTO.class);
    }
    
    public int insertDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {        
        return consultProgramUploadDataDAO.insertDetail(consultProgramUploadDataDTO, user);
    }
    
    public int updateDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {        
        return consultProgramUploadDataDAO.updateDetail(consultProgramUploadDataDTO, user);
    }

    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {
        return consultProgramUploadDataDAO.checkTableExist(consultProgramUploadDataDTO, user);
    }
    
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {
        consultProgramUploadDataDAO.completeDetail(consultProgramUploadDataDTO, user);
//        consultProgramUploadDataDAO.setComments(consultProgramUploadDataDTO, user);
        
        return consultProgramUploadDataDAO.updateStatus(consultProgramUploadDataDTO, user);
    }
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {
        String result = "";
        
        result = consultProgramUploadDataDAO.checkTableExist(consultProgramUploadDataDTO, user);
        
        if("0".equals(result))
        {
            consultProgramUploadDataDAO.updateStatusAsW(consultProgramUploadDataDTO, user);
        }
        else
        {
            consultProgramUploadDataDAO.dropTable(consultProgramUploadDataDTO, user);
            consultProgramUploadDataDAO.updateStatusAsW(consultProgramUploadDataDTO, user);
        }
        return 0;
    }
}

