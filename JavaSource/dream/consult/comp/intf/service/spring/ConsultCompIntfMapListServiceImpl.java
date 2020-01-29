package dream.consult.comp.intf.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.intf.dao.ConsultCompIntfMapListDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;
import dream.consult.comp.intf.service.ConsultCompIntfMapListService;

/**
 * Interface Log Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompIntfMapListServiceTarget"
 * @spring.txbn id="consultCompIntfMapListService"
 * @spring.property name="consultCompIntfMapListDAO" ref="consultCompIntfMapListDAO"
 */
public class ConsultCompIntfMapListServiceImpl implements ConsultCompIntfMapListService
{
	private ConsultCompIntfMapListDAO consultCompIntfMapListDAO = null;

	public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception
    {      
        return consultCompIntfMapListDAO.findList(consultCompIntfCommonDTO, consultCompIntfMapListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompIntfMapListDAO.deleteList(id, user);
            }
        return result;
    }

	public ConsultCompIntfMapListDAO getConsultCompIntfMapListDAO() {
		return consultCompIntfMapListDAO;
	}

	public void setConsultCompIntfMapListDAO(ConsultCompIntfMapListDAO consultCompIntfMapListDAO) {
		this.consultCompIntfMapListDAO = consultCompIntfMapListDAO;
	}
	public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO,User user) throws Exception
    {
        return consultCompIntfMapListDAO.findTotalCount(consultCompIntfCommonDTO, consultCompIntfMapListDTO, user);
    }
}

