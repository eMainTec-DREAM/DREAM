package dream.work.let.categ.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.service.WorkLetCategListService;

/**
 * 안전작업유형 list Page - List Service implements
 * @author euna0207
 * @version $Id: WorkLetCategListServiceImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategListServiceTarget"
 * @spring.txbn id="workLetCategListService"
 * @spring.property name="workLetCategListDAO" ref="workLetCategListDAO"
 */
public class WorkLetCategListServiceImpl implements WorkLetCategListService
{
	private WorkLetCategListDAO workLetCategListDAO = null;

	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    {      
        return workLetCategListDAO.findList(workLetCategCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetCategListDAO.deleteList(id, user);
            }
        return result;
    }

	public WorkLetCategListDAO getWorkLetCategListDAO() {
		return workLetCategListDAO;
	}

	public void setWorkLetCategListDAO(WorkLetCategListDAO workLetCategListDAO) {
		this.workLetCategListDAO = workLetCategListDAO;
	}
	public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO,User user) throws Exception
    {
        return workLetCategListDAO.findTotalCount(workLetCategCommonDTO, user);
    }
}

