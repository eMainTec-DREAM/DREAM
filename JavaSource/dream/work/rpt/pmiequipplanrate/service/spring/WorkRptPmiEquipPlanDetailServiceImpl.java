package dream.work.rpt.pmiequipplanrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dao.WorkRptPmiEquipPlanDetailDAO;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;
import dream.work.rpt.pmiequipplanrate.service.WorkRptPmiEquipPlanDetailService;

/**
 * 고장TOP(위치) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiEquipPlanDetailServiceTarget"
 * @spring.txbn id="workRptPmiEquipPlanDetailService"
 * @spring.property name="workRptPmiEquipPlanDetailDAO" ref="workRptPmiEquipPlanDetailDAO"
 */
public class WorkRptPmiEquipPlanDetailServiceImpl implements WorkRptPmiEquipPlanDetailService
{
    private WorkRptPmiEquipPlanDetailDAO workRptPmiEquipPlanDetailDAO = null;
    
    public WorkRptPmiEquipPlanDetailDAO getWorkRptPmiEquipPlanDetailDAO()
    {
        return workRptPmiEquipPlanDetailDAO;
    }
    
    public void setWorkRptPmiEquipPlanDetailDAO(
            WorkRptPmiEquipPlanDetailDAO workRptPmiEquipPlanDetailDAO)
    {
        this.workRptPmiEquipPlanDetailDAO = workRptPmiEquipPlanDetailDAO;
    }
    
    public List findDetail(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser) throws Exception
    {
        return workRptPmiEquipPlanDetailDAO.findDetail(workRptPmiEquipPlanDetailDTO, loginUser);
        
    }

	@Override
	public String findTotalCount(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User user) throws Exception
	{
		return workRptPmiEquipPlanDetailDAO.findTotalCount(workRptPmiEquipPlanDetailDTO, user);
	}
	
}

