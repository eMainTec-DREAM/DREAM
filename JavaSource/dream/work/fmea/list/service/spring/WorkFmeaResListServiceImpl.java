package dream.work.fmea.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dao.WorkFmeaResListDAO;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.service.WorkFmeaResListService;

/**
 * 고장영향성평가  - List Service implements
 * @author kim21017
 * @version $Id: WorkFmeaResListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaResListServiceTarget"
 * @spring.txbn id="workFmeaResListService"
 * @spring.property name="workFmeaResListDAO" ref="workFmeaResListDAO"
 */
public class WorkFmeaResListServiceImpl implements WorkFmeaResListService
{
	private WorkFmeaResListDAO workFmeaResListDAO = null;

	public List findList(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception
    {      
        return workFmeaResListDAO.findList(workFmeaResCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workFmeaResListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception
    {      
        return workFmeaResListDAO.findTotalCount(workFmeaResCommonDTO,user);
    }
	public WorkFmeaResListDAO getWorkFmeaResListDAO() {
		return workFmeaResListDAO;
	}

	public void setWorkFmeaResListDAO(WorkFmeaResListDAO workFmeaResListDAO) {
		this.workFmeaResListDAO = workFmeaResListDAO;
	}
}

