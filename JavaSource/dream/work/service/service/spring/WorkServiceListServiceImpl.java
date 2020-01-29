package dream.work.service.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.service.dao.WorkServiceListDAO;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.service.WorkServiceListService;

/**
 * 서비스 마스터 - List Service implements
 * @author cjscjs9
 * @version $Id: WorkServiceListServiceImpl.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @spring.bean id="workServiceListServiceTarget"
 * @spring.txbn id="workServiceListService"
 * @spring.property name="workServiceListDAO" ref="workServiceListDAO"
 */
public class WorkServiceListServiceImpl implements WorkServiceListService
{
	private WorkServiceListDAO workServiceListDAO = null;

	public List findWorkServiceList(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception
    {      
        return workServiceListDAO.findWorkServiceList(workServiceCommonDTO,user);
    }

	public int deleteWorkServiceList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workServiceListDAO.deleteWorkServiceList(id, user);
            }
        return result;
    }

	public WorkServiceListDAO getWorkServiceListDAO() {
		return workServiceListDAO;
	}

	public void setWorkServiceListDAO(WorkServiceListDAO workServiceListDAO) {
		this.workServiceListDAO = workServiceListDAO;
	}
	public String findTotalCount(WorkServiceCommonDTO workServiceCommonDTO,User user) throws Exception
    {
        return workServiceListDAO.findTotalCount(workServiceCommonDTO, user);
    }
}

