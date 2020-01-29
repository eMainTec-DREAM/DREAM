package dream.work.rpt.pmwrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dao.WorkRptPmwRateListDAO;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;
import dream.work.rpt.pmwrate.service.WorkRptPmwRateListService;

/**
 * 주기정비 실행 비율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmwRateListServiceTarget"
 * @spring.txbn id="workRptPmwRateListService"
 * @spring.property name="workRptPmwRateListDAO" ref="workRptPmwRateListDAO"
 */
public class WorkRptPmwRateListServiceImpl implements WorkRptPmwRateListService
{
    private WorkRptPmwRateListDAO workRptPmwRateListDAO = null;

    public List findList(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception
    {      
        return workRptPmwRateListDAO.findList(workRptPmwRateCommonDTO,user);
    }

    public WorkRptPmwRateListDAO getWorkRptPmwRateListDAO() {
        return workRptPmwRateListDAO;
    }

    public void setWorkRptPmwRateListDAO(WorkRptPmwRateListDAO workRptPmwRateListDAO) {
        this.workRptPmwRateListDAO = workRptPmwRateListDAO;
    }    
    
    public String findTotalCount(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO,User user)  throws Exception
    {
        return workRptPmwRateListDAO.findTotalCount(workRptPmwRateCommonDTO, user);
    }
}
