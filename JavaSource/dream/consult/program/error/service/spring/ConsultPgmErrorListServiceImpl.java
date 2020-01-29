package dream.consult.program.error.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.error.dao.ConsultPgmErrorListDAO;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.service.ConsultPgmErrorListService;

/**
 * Error Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmErrorListServiceTarget"
 * @spring.txbn id="consultPgmErrorListService"
 * @spring.property name="consultPgmErrorListDAO" ref="consultPgmErrorListDAO"
 */
public class ConsultPgmErrorListServiceImpl implements ConsultPgmErrorListService
{
	private ConsultPgmErrorListDAO consultPgmErrorListDAO = null;

	public List findPgmErrorList(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception
    {      
        return consultPgmErrorListDAO.findPgmErrorList(consultPgmErrorCommonDTO,user);
    }

	public int deletePgmErrorList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultPgmErrorListDAO.deletePgmErrorList(id, user);
            }
        return result;
    }

	public ConsultPgmErrorListDAO getConsultPgmErrorListDAO() {
		return consultPgmErrorListDAO;
	}

	public void setConsultPgmErrorListDAO(ConsultPgmErrorListDAO consultPgmErrorListDAO) {
		this.consultPgmErrorListDAO = consultPgmErrorListDAO;
	}
	public String findTotalCount(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO,User user) throws Exception
    {
        return consultPgmErrorListDAO.findTotalCount(consultPgmErrorCommonDTO, user);
    }
}

