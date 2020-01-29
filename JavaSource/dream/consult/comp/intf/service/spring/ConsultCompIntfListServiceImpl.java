package dream.consult.comp.intf.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.intf.dao.ConsultCompIntfListDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.service.ConsultCompIntfListService;

/**
 * Interface Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompIntfListServiceTarget"
 * @spring.txbn id="consultCompIntfListService"
 * @spring.property name="consultCompIntfListDAO" ref="consultCompIntfListDAO"
 */
public class ConsultCompIntfListServiceImpl implements ConsultCompIntfListService
{
	private ConsultCompIntfListDAO consultCompIntfListDAO = null;

	public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception
    {      
        return consultCompIntfListDAO.findList(consultCompIntfCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompIntfListDAO.deleteList(id, user);
            }
        return result;
    }

	public ConsultCompIntfListDAO getConsultCompIntfListDAO() {
		return consultCompIntfListDAO;
	}

	public void setConsultCompIntfListDAO(ConsultCompIntfListDAO consultCompIntfListDAO) {
		this.consultCompIntfListDAO = consultCompIntfListDAO;
	}
	public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO,User user) throws Exception
    {
        return consultCompIntfListDAO.findTotalCount(consultCompIntfCommonDTO, user);
    }
}

