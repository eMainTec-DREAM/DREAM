package dream.work.let.categ.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategPointListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;
import dream.work.let.categ.service.WorkLetCategPointListService;

/**
 * 안전작업유형 점검항목 List Page - List Service implements
 * @author euna0207
 * @version $Id: WorkLetCategPointListServiceImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategPointListServiceTarget"
 * @spring.txbn id="workLetCategPointListService"
 * @spring.property name="workLetCategPointListDAO" ref="workLetCategPointListDAO"
 */
public class WorkLetCategPointListServiceImpl implements WorkLetCategPointListService
{
	private WorkLetCategPointListDAO workLetCategPointListDAO = null;

	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception
    {      
        return workLetCategPointListDAO.findList(workLetCategCommonDTO, workLetCategPointListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetCategPointListDAO.deleteList(id, user);
            }
        return result;
    }

	public WorkLetCategPointListDAO getWorkLetCategPointListDAO() {
		return workLetCategPointListDAO;
	}

	public void setWorkLetCategPointListDAO(WorkLetCategPointListDAO workLetCategPointListDAO) {
		this.workLetCategPointListDAO = workLetCategPointListDAO;
	}
	public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO,User user) throws Exception
    {
        return workLetCategPointListDAO.findTotalCount(workLetCategCommonDTO, workLetCategPointListDTO, user);
    }
}

