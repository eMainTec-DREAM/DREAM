package dream.work.rpt.work.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpListDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpListService;

/**
 * 담당자별작업현황 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWorkTypeRptByEmpListServiceTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpListService"
 * @spring.property name="workRptWorkTypeRptByEmpListDAO" ref="workRptWorkTypeRptByEmpListDAO"
 */
public class WorkRptWorkTypeRptByEmpListServiceImpl implements WorkRptWorkTypeRptByEmpListService
{
    private WorkRptWorkTypeRptByEmpListDAO workRptWorkTypeRptByEmpListDAO = null;
    
	public WorkRptWorkTypeRptByEmpListDAO getWorkRptWorkTypeRptByEmpListDAO()
    {
        return workRptWorkTypeRptByEmpListDAO;
    }
	
    public void setWorkRptWorkTypeRptByEmpListDAO(
            WorkRptWorkTypeRptByEmpListDAO workRptWorkTypeRptByEmpListDAO)
    {
        this.workRptWorkTypeRptByEmpListDAO = workRptWorkTypeRptByEmpListDAO;
    }
    
    @Override
    public List findList(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user)
    {
        return workRptWorkTypeRptByEmpListDAO.findList(workRptWorkTypeRptByEmpCommonDTO, user);
    }

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user) {
        return workRptWorkTypeRptByEmpListDAO.findTotalCount(workRptWorkTypeRptByEmpCommonDTO, user);
	}

}

