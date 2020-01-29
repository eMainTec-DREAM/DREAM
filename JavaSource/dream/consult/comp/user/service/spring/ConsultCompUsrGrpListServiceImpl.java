package dream.consult.comp.user.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dao.ConsultCompUsrGrpListDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.service.ConsultCompUsrGrpListService;

/**
 * ���ѱ׷� - ��� serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="consultCompUsrGrpListServiceTarget"
 * @spring.txbn id="consultCompUsrGrpListService"
 * @spring.property name="consultCompUsrGrpListDAO" ref="consultCompUsrGrpListDAO"
 */
public class ConsultCompUsrGrpListServiceImpl implements ConsultCompUsrGrpListService
{
    private ConsultCompUsrGrpListDAO consultCompUsrGrpListDAO = null;

    public ConsultCompUsrGrpListDAO getConsultCompUsrGrpListDAO() 
    {
		return consultCompUsrGrpListDAO;
	}

	public void setConsultCompUsrGrpListDAO(ConsultCompUsrGrpListDAO consultCompUsrGrpListDAO) 
	{
		this.consultCompUsrGrpListDAO = consultCompUsrGrpListDAO;
	}

	public List findUseGrpList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
    {      
        return consultCompUsrGrpListDAO.findUsrGrpList(consultCompUsrGrpCommonDTO, user);
    }
	
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {   
                // ���ѿ� ���� "�޴� ����" ����
                result = result + consultCompUsrGrpListDAO.deleteUsrGrpMenu(compNo, id);
                // ���� ���� 
                result = result + consultCompUsrGrpListDAO.deleteUsrGrp(compNo, id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
	{
		return consultCompUsrGrpListDAO.findTotalCount(consultCompUsrGrpCommonDTO, user);
	}
}

