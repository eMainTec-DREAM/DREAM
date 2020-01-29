package dream.consult.program.onlinehelp.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.onlinehelp.dao.ConsultProgramOnlinehelpListDAO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;
import dream.consult.program.onlinehelp.service.ConsultProgramOnlinehelpListService;

/**
 * 도움말 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultProgramOnlinehelpListServiceTarget"
 * @spring.txbn id="consultProgramOnlinehelpListService"
 * @spring.property name="consultProgramOnlinehelpListDAO" ref="consultProgramOnlinehelpListDAO"
 */
public class ConsultProgramOnlinehelpListServiceImpl implements ConsultProgramOnlinehelpListService
{
    private ConsultProgramOnlinehelpListDAO consultProgramOnlinehelpListDAO = null;

    public ConsultProgramOnlinehelpListDAO getConsultProgramOnlinehelpListDAO() {
		return consultProgramOnlinehelpListDAO;
	}

	public void setConsultProgramOnlinehelpListDAO(ConsultProgramOnlinehelpListDAO consultProgramOnlinehelpListDAO) {
		this.consultProgramOnlinehelpListDAO = consultProgramOnlinehelpListDAO;
	}

	public List findHelpList(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user)
    {      
        return consultProgramOnlinehelpListDAO.findHelpList(consultProgramOnlinehelpCommonDTO,user);
    }
	
	public int deleteHelp(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultProgramOnlinehelpListDAO.deleteHelp(id);
            }
        return result;
    }

    @Override
    public String findTotalCount(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO, User user) throws Exception
    {
        return consultProgramOnlinehelpListDAO.findTotalCount(consultProgramOnlinehelpCommonDTO, user);
    }
}

