package dream.consult.comp.tracelog.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.tracelog.dao.ConsultCompTracelogListDAO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.service.ConsultCompTracelogListService;

/**
 * Screen Trace - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompTracelogListServiceTarget"
 * @spring.txbn id="consultCompTracelogListService"
 * @spring.property name="consultCompTracelogListDAO" ref="consultCompTracelogListDAO"
 */
public class ConsultCompTracelogListServiceImpl implements ConsultCompTracelogListService
{
	private ConsultCompTracelogListDAO consultCompTracelogListDAO = null;

	public List findCompTracelogList(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    {      
        return consultCompTracelogListDAO.findCompTracelogList(consultCompTracelogCommonDTO,user);
    }

	public int deleteCompTracelogList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompTracelogListDAO.deleteCompTracelogList(id, user);
            }
        return result;
    }

	public ConsultCompTracelogListDAO getConsultCompTracelogListDAO() {
		return consultCompTracelogListDAO;
	}

	public void setConsultCompTracelogListDAO(ConsultCompTracelogListDAO consultCompTracelogListDAO) {
		this.consultCompTracelogListDAO = consultCompTracelogListDAO;
	}
	public String findTotalCount(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO,User user) throws Exception
    {
        return consultCompTracelogListDAO.findTotalCount(consultCompTracelogCommonDTO, user);
    }
}

