package dream.consult.comp.emp.service.spring;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.consult.comp.emp.dao.ConsultCompEmpListDAO;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.service.ConsultCompEmpListService;

/**
 * 사원 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="consultCompEmpListServiceTarget"
 * @spring.txbn id="consultCompEmpListService"
 * @spring.property name="consultCompEmpListDAO" ref="consultCompEmpListDAO"
 */
public class ConsultCompEmpListServiceImpl implements ConsultCompEmpListService
{
    private ConsultCompEmpListDAO consultCompEmpListDAO = null;

    public ConsultCompEmpListDAO getConsultCompEmpListDAO() 
    {
		return consultCompEmpListDAO;
	}

	public void setConsultCompEmpListDAO(ConsultCompEmpListDAO consultCompEmpListDAO) 
	{
		this.consultCompEmpListDAO = consultCompEmpListDAO;
	}

	public List findEmpList(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user) throws IOException
    {      
        return consultCompEmpListDAO.findEmpList(consultCompEmpCommonDTO, user);
    }

    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompEmpListDAO.deleteEmp(compNo, id);
            }
        
        return result;
    }

    @Override
    public String findTotalCount(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user)
    {
        return consultCompEmpListDAO.findTotalCount(consultCompEmpCommonDTO, user);
    }
}

