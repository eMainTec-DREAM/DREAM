package dream.work.planappr.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.planappr.dao.WorkPlanApprDetailDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.service.WorkPlanApprDetailService;
import dream.work.planappr.service.WorkPlanApprEquipListService;
import dream.work.planappr.service.WorkPlanApprWorkListService;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ- »ó¼¼ serviceimpl 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workPlanApprDetailServiceTarget"
 * @spring.txbn id="workPlanApprDetailService"
 * @spring.property name="workPlanApprDetailDAO" ref="workPlanApprDetailDAO"
 */
public class WorkPlanApprDetailServiceImpl implements WorkPlanApprDetailService 
{
    private WorkPlanApprDetailDAO workPlanApprDetailDAO = null;

	public WorkPlanApprDetailDAO getWorkPlanApprDetailDAO() {
		return workPlanApprDetailDAO;
	}

	public void setWorkPlanApprDetailDAO(WorkPlanApprDetailDAO workPlanApprDetailDAO) {
		this.workPlanApprDetailDAO = workPlanApprDetailDAO;
	}

	public WorkPlanApprDetailDTO findDetail(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)throws Exception
    {
        return workPlanApprDetailDAO.findDetail(workPlanApprCommonDTO,user);
    }
	
	public int insertDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user) throws Exception
    {        
		int rtnVal = workPlanApprDetailDAO.insertDetail(workPlanApprDetailDTO, workPlanApprCommonDTO, user);
		
		WorkPlanApprWorkListService workPlanApprWorkListService = (WorkPlanApprWorkListService)CommonUtil.getBean("workPlanApprWorkListService");
		WorkPlanApprEquipListService workPlanApprEquipListService = (WorkPlanApprEquipListService)CommonUtil.getBean("workPlanApprEquipListService");
	
		workPlanApprWorkListService.insertWorkSched(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
		workPlanApprEquipListService.insertEquip(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
		
		return rtnVal;
    }

	public int updateDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO,User user) throws Exception
    {        
	    int rtnVal = workPlanApprDetailDAO.updateDetail(workPlanApprDetailDTO, workPlanApprCommonDTO, user);
        
	    WorkPlanApprWorkListService workPlanApprWorkListService = (WorkPlanApprWorkListService)CommonUtil.getBean("workPlanApprWorkListService");
	    WorkPlanApprEquipListService workPlanApprEquipListService = (WorkPlanApprEquipListService)CommonUtil.getBean("workPlanApprEquipListService");
	    
        workPlanApprWorkListService.insertWorkSched(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
        workPlanApprEquipListService.insertEquip(workPlanApprCommonDTO, workPlanApprDetailDTO, user);
        
        return rtnVal;
    }
	public int updateStatus(WorkPlanApprDetailDTO workPlanApprDetailDTO, User user) throws Exception
    {        
        return workPlanApprDetailDAO.updateStatus(workPlanApprDetailDTO, user);
    }
	public String checkAppr(WorkPlanApprDetailDTO workPlanApprDetailDTO, User user) throws Exception
	{
		return workPlanApprDetailDAO.checkAppr(workPlanApprDetailDTO,user);
	}
	
	@Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus()))
        {
        	WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
        	workPlanApprCommonDTO.setWoPlanApprId(appReqDetailDTO.getObjectId());
        	workPlanApprCommonDTO.setCompNo(user.getCompNo());
            WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailDAO.findDetail(workPlanApprCommonDTO, user);
            
            this.updateStatus(workPlanApprDetailDTO, user);
        }
        else
        {
            workPlanApprDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }
}
