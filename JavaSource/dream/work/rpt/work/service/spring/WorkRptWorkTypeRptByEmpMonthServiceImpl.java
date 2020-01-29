package dream.work.rpt.work.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpMonthDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpMonthService;

/**
 * 담당자별작업현황 - 담당자 월별작업현황 탭 목록 serviceimpl 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWorkTypeRptByEmpMonthServiceTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpMonthService"
 * @spring.property name="workRptWorkTypeRptByEmpMonthDAO" ref="workRptWorkTypeRptByEmpMonthDAO"
 */
public class WorkRptWorkTypeRptByEmpMonthServiceImpl implements WorkRptWorkTypeRptByEmpMonthService
{
    private WorkRptWorkTypeRptByEmpMonthDAO workRptWorkTypeRptByEmpMonthDAO = null;
    
    public WorkRptWorkTypeRptByEmpMonthDAO getWorkRptWorkTypeRptByEmpMonthDAO()
    {
        return workRptWorkTypeRptByEmpMonthDAO;
    }
    
    public void setWorkRptWorkTypeRptByEmpMonthDAO(
            WorkRptWorkTypeRptByEmpMonthDAO workRptWorkTypeRptByEmpMonthDAO)
    {
        this.workRptWorkTypeRptByEmpMonthDAO = workRptWorkTypeRptByEmpMonthDAO;
    }

	@Override
	public List findMonthList(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user) {
		// TODO Auto-generated method stub
		return workRptWorkTypeRptByEmpMonthDAO.findMonthList(workRptWorkTypeRptByEmpMonthDTO, user);
	}

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user) {
		return workRptWorkTypeRptByEmpMonthDAO.findTotalCount(workRptWorkTypeRptByEmpMonthDTO, user);
	}
}

