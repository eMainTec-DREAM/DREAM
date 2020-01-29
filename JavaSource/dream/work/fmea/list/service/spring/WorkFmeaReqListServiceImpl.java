package dream.work.fmea.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.fmea.list.dao.WorkFmeaReqListDAO;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.service.WorkFmeaReqListService;

/**
 * 고장영향성평가  - List Service implements
 * @author kim21017
 * @version $Id: WorkFmeaReqListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaReqListServiceTarget"
 * @spring.txbn id="workFmeaReqListService"
 * @spring.property name="workFmeaReqListDAO" ref="workFmeaReqListDAO"
 */
public class WorkFmeaReqListServiceImpl implements WorkFmeaReqListService
{
	private WorkFmeaReqListDAO workFmeaReqListDAO = null;

	public List findList(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception
    {      
        return workFmeaReqListDAO.findList(workFmeaReqCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workFmeaReqListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception
    {      
        return workFmeaReqListDAO.findTotalCount(workFmeaReqCommonDTO,user);
    }
	public WorkFmeaReqListDAO getWorkFmeaReqListDAO() {
		return workFmeaReqListDAO;
	}

	public void setWorkFmeaReqListDAO(WorkFmeaReqListDAO workFmeaReqListDAO) {
		this.workFmeaReqListDAO = workFmeaReqListDAO;
	}
}

