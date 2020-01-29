package mobile.dream.work.pm.list.service.spring;

import java.util.List;
import common.bean.User;
import mobile.dream.work.pm.list.dao.WorkPmWorkListDAO;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkListForm;
import mobile.dream.work.pm.list.service.WorkPmWorkListService;

/**
 * 계획작업 - 목록 serviceimpl
 * @author jung7126
 * @version $Id: WorkPmWorkListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmWorkListServiceTarget"
 * @spring.txbn id="workPmWorkListService"
 * @spring.property name="workPmWorkListDAO" ref="workPmWorkListDAO"
 */
public class WorkPmWorkListServiceImpl implements WorkPmWorkListService
{
    private WorkPmWorkListDAO workPmWorkListDAO = null;

    public WorkPmWorkListDAO getWorkPmWorkListDAO() 
    {
		return workPmWorkListDAO;
	}

	public void setWorkPmWorkListDAO(WorkPmWorkListDAO workPmWorkListDAO) 
	{
		this.workPmWorkListDAO = workPmWorkListDAO;
	}

	public List findList(WorkPmWorkCommonDTO workPmWorkCommonDTO, User user)
    {      
        return workPmWorkListDAO.findList(workPmWorkCommonDTO,user);
    }

	public String findTotalCount(WorkPmWorkListForm workPmWorkListForm, User user) 
	{
		return workPmWorkListDAO.findTotalCount(workPmWorkListForm,user);
	}
}

