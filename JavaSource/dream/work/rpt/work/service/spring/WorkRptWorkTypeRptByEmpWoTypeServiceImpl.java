package dream.work.rpt.work.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpWoTypeDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpWoTypeService;

/**
 * 담당자별작업현황 - 담당자 작업종류별현황 탭 목록 Seriveimpl 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWorkTypeRptByEmpWoTypeServiceTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpWoTypeService"
 * @spring.property name="workRptWorkTypeRptByEmpWoTypeDAO" ref="workRptWorkTypeRptByEmpWoTypeDAO"
 */
public class WorkRptWorkTypeRptByEmpWoTypeServiceImpl implements WorkRptWorkTypeRptByEmpWoTypeService
{
    private WorkRptWorkTypeRptByEmpWoTypeDAO workRptWorkTypeRptByEmpWoTypeDAO = null;
    
    public WorkRptWorkTypeRptByEmpWoTypeDAO getWorkRptWorkTypeRptByEmpWoTypeDAO()
    {
        return workRptWorkTypeRptByEmpWoTypeDAO;
    }
    
    public void setWorkRptWorkTypeRptByEmpWoTypeDAO(
            WorkRptWorkTypeRptByEmpWoTypeDAO workRptWorkTypeRptByEmpWoTypeDAO)
    {
        this.workRptWorkTypeRptByEmpWoTypeDAO = workRptWorkTypeRptByEmpWoTypeDAO;
    }

	@Override
	public List findWoTypeList(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user) {
		return workRptWorkTypeRptByEmpWoTypeDAO.findWoTypeList(workRptWorkTypeRptByEmpWoTypeDTO, user);
	}

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user) {
		return workRptWorkTypeRptByEmpWoTypeDAO.findTotalCount(workRptWorkTypeRptByEmpWoTypeDTO, user);
	}
    
	
}

