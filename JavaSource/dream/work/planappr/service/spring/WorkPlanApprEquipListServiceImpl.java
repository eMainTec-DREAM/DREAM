package dream.work.planappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dao.WorkPlanApprEquipListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.service.WorkPlanApprEquipListService;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ÀÛ¾÷°èÈ¹ serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPlanApprEquipListServiceTarget"
 * @spring.txbn id="workPlanApprEquipListService"
 * @spring.property name="workPlanApprEquipListDAO" ref="workPlanApprEquipListDAO"
 */
public class WorkPlanApprEquipListServiceImpl implements WorkPlanApprEquipListService
{
    private WorkPlanApprEquipListDAO workPlanApprEquipListDAO = null;

    
	public WorkPlanApprEquipListDAO getWorkPlanApprEquipListDAO() {
		return workPlanApprEquipListDAO;
	}


	public void setWorkPlanApprEquipListDAO(WorkPlanApprEquipListDAO workPlanApprEquipListDAO) {
		this.workPlanApprEquipListDAO = workPlanApprEquipListDAO;
	}


	public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {      
        return workPlanApprEquipListDAO.findList(workPlanApprCommonDTO,workPlanApprDetailDTO,user);
    }

	public void insertEquip(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
	{
	    workPlanApprEquipListDAO.deleteWoPlanApprList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
	    
	    workPlanApprEquipListDAO.insertWoPlanApprList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
	}

	@Override
	public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,
			WorkPlanApprDetailDTO workPlanApprDetailDTO, User user) throws Exception {
		return workPlanApprEquipListDAO.findTotalCount(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
	}

}