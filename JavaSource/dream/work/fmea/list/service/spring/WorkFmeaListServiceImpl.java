package dream.work.fmea.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dao.WorkFmeaListDAO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.service.WorkFmeaListService;

/**
 * 고장영향성평가  - List Service implements
 * @author kim21017
 * @version $Id: WorkFmeaListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaListServiceTarget"
 * @spring.txbn id="workFmeaListService"
 * @spring.property name="workFmeaListDAO" ref="workFmeaListDAO"
 */
public class WorkFmeaListServiceImpl implements WorkFmeaListService
{
	private WorkFmeaListDAO workFmeaListDAO = null;

	public List findList(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception
    {      
        return workFmeaListDAO.findList(workFmeaCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workFmeaListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception
    {      
        return workFmeaListDAO.findTotalCount(workFmeaCommonDTO,user);
    }
	public WorkFmeaListDAO getWorkFmeaListDAO() {
		return workFmeaListDAO;
	}

	public void setWorkFmeaListDAO(WorkFmeaListDAO workFmeaListDAO) {
		this.workFmeaListDAO = workFmeaListDAO;
	}
}

