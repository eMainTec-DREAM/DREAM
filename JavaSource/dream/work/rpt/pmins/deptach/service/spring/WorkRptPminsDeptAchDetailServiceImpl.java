package dream.work.rpt.pmins.deptach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.deptach.dao.WorkRptPminsDeptAchDetailDAO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;
import dream.work.rpt.pmins.deptach.service.WorkRptPminsDeptAchDetailService;

/**
 * 예방점검 이행율(부서) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsDeptAchDetailServiceTarget"
 * @spring.txbn id="workRptPminsDeptAchDetailService"
 * @spring.property name="workRptPminsDeptAchDetailDAO" ref="workRptPminsDeptAchDetailDAO"
 */
public class WorkRptPminsDeptAchDetailServiceImpl implements WorkRptPminsDeptAchDetailService
{
    private WorkRptPminsDeptAchDetailDAO workRptPminsDeptAchDetailDAO = null;
    
    public WorkRptPminsDeptAchDetailDAO getWorkRptPminsDeptAchDetailDAO()
    {
        return workRptPminsDeptAchDetailDAO;
    }
    
    public void setWorkRptPminsDeptAchDetailDAO(
            WorkRptPminsDeptAchDetailDAO workRptPminsDeptAchDetailDAO)
    {
        this.workRptPminsDeptAchDetailDAO = workRptPminsDeptAchDetailDAO;
    }
    
    public List findDetail(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User loginUser)
    {
        return workRptPminsDeptAchDetailDAO.findDetail(workRptPminsDeptAchDetailDTO, loginUser);
        
    }
    
    @Override
    public String findTotalCount(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User user)
    {
        return workRptPminsDeptAchDetailDAO.findTotalCount(workRptPminsDeptAchDetailDTO, user);
    }
}

