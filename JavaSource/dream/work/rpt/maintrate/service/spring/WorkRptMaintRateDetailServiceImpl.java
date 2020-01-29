package dream.work.rpt.maintrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maintrate.dao.WorkRptMaintRateDetailDAO;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateDetailDTO;
import dream.work.rpt.maintrate.service.WorkRptMaintRateDetailService;

/**
 * WorkRptMaintRate Page - Detail Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptMaintRateDetailServiceTarget"
 * @spring.txbn id="workRptMaintRateDetailService"
 * @spring.property name="workRptMaintRateDetailDAO" ref="workRptMaintRateDetailDAO"
 */
public class WorkRptMaintRateDetailServiceImpl implements WorkRptMaintRateDetailService
{
    private WorkRptMaintRateDetailDAO workRptMaintRateDetailDAO = null;

    public List findPartDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {      
        return workRptMaintRateDetailDAO.findPartDetail(workRptMaintRateDetailDTO,user);
    }
    public List findTypeDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {      
        return workRptMaintRateDetailDAO.findTypeDetail(workRptMaintRateDetailDTO,user);
    }
    public List findDayChart(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {      
        return workRptMaintRateDetailDAO.findDayChart(workRptMaintRateDetailDTO,user);
    }

    public WorkRptMaintRateDetailDAO getWorkRptMaintRateDetailDAO() {
        return workRptMaintRateDetailDAO;
    }

    public void setWorkRptMaintRateDetailDAO(WorkRptMaintRateDetailDAO workRptMaintRateDetailDAO) {
        this.workRptMaintRateDetailDAO = workRptMaintRateDetailDAO;
    }    
}
