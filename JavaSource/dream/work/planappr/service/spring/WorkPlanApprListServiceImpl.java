package dream.work.planappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dao.WorkPlanApprListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.service.WorkPlanApprListService;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ¸ñ·Ï serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workPlanApprListServiceTarget"
 * @spring.txbn id="workPlanApprListService"
 * @spring.property name="workPlanApprListDAO" ref="workPlanApprListDAO"
 */
public class WorkPlanApprListServiceImpl implements WorkPlanApprListService
{
    private WorkPlanApprListDAO workPlanApprListDAO = null;
    
	public WorkPlanApprListDAO getWorkPlanApprListDAO() {
		return workPlanApprListDAO;
	}
	public void setWorkPlanApprListDAO(WorkPlanApprListDAO workPlanApprListDAO) {
		this.workPlanApprListDAO = workPlanApprListDAO;
	}
	public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {      
        return workPlanApprListDAO.findList(workPlanApprCommonDTO,user);
    }
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPlanApprListDAO.deleteList(id,user);
            }
        return result;
    }
	
    public String findTotalCount( WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {
        return workPlanApprListDAO.findTotalCount(workPlanApprCommonDTO,user);
    }
}

