package dream.work.rpt.maintrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maintrate.dao.WorkRptMaintRateListDAO;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;
import dream.work.rpt.maintrate.service.WorkRptMaintRateListService;

/**
 * WorkRptMaintRate Page - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptMaintRateListServiceTarget"
 * @spring.txbn id="workRptMaintRateListService"
 * @spring.property name="workRptMaintRateListDAO" ref="workRptMaintRateListDAO"
 */
public class WorkRptMaintRateListServiceImpl implements WorkRptMaintRateListService
{
    private WorkRptMaintRateListDAO workRptMaintRateListDAO = null;

    public List findList(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) throws Exception
    {      
        return workRptMaintRateListDAO.findList(workRptMaintRateCommonDTO,user);
    }

    public WorkRptMaintRateListDAO getWorkRptMaintRateListDAO() {
        return workRptMaintRateListDAO;
    }

    public void setWorkRptMaintRateListDAO(WorkRptMaintRateListDAO workRptMaintRateListDAO) {
        this.workRptMaintRateListDAO = workRptMaintRateListDAO;
    }    
    
    public String findTotalCount(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO,User user)  throws Exception
    {
        return workRptMaintRateListDAO.findTotalCount(workRptMaintRateCommonDTO, user);
    }
}
