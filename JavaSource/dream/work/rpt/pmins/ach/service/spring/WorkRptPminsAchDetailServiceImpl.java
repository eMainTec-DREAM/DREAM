package dream.work.rpt.pmins.ach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dao.WorkRptPminsAchDetailDAO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;
import dream.work.rpt.pmins.ach.service.WorkRptPminsAchDetailService;

/**
 * 예방점검 이행율(담당자) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsAchDetailServiceTarget"
 * @spring.txbn id="workRptPminsAchDetailService"
 * @spring.property name="workRptPminsAchDetailDAO" ref="workRptPminsAchDetailDAO"
 */
public class WorkRptPminsAchDetailServiceImpl implements WorkRptPminsAchDetailService
{
    private WorkRptPminsAchDetailDAO workRptPminsAchDetailDAO = null;
    
    public WorkRptPminsAchDetailDAO getWorkRptPminsAchDetailDAO()
    {
        return workRptPminsAchDetailDAO;
    }
    
    public void setWorkRptPminsAchDetailDAO(
            WorkRptPminsAchDetailDAO workRptPminsAchDetailDAO)
    {
        this.workRptPminsAchDetailDAO = workRptPminsAchDetailDAO;
    }
    
    public List findDetail(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User loginUser)
    {
        return workRptPminsAchDetailDAO.findDetail(workRptPminsAchDetailDTO, loginUser);
        
    }
    
    public String findTotalCount(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User user)
    {
        return workRptPminsAchDetailDAO.findTotalCount(workRptPminsAchDetailDTO, user);
    }
}

