package dream.work.planappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dao.WorkPlanApprWorkListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.service.WorkPlanApprWorkListService;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ÀÛ¾÷°èÈ¹ serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPlanApprWorkListServiceTarget"
 * @spring.txbn id="workPlanApprWorkListService"
 * @spring.property name="workPlanApprWorkListDAO" ref="workPlanApprWorkListDAO"
 */
public class WorkPlanApprWorkListServiceImpl implements WorkPlanApprWorkListService
{
    private WorkPlanApprWorkListDAO workPlanApprWorkListDAO = null;

    
	public WorkPlanApprWorkListDAO getWorkPlanApprWorkListDAO() {
		return workPlanApprWorkListDAO;
	}


	public void setWorkPlanApprWorkListDAO(WorkPlanApprWorkListDAO workPlanApprWorkListDAO) {
		this.workPlanApprWorkListDAO = workPlanApprWorkListDAO;
	}


	public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {      
        return workPlanApprWorkListDAO.findList(workPlanApprCommonDTO,workPlanApprDetailDTO,user);
    }

	public void insertWorkSched(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
	{
	    workPlanApprWorkListDAO.deleteWoPlanApprList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
	    
	    workPlanApprWorkListDAO.insertWoPlanApprList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
	}

	@Override
	public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,
			WorkPlanApprDetailDTO workPlanApprDetailDTO, User user) throws Exception {
		return workPlanApprWorkListDAO.findTotalCount(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
	}

}