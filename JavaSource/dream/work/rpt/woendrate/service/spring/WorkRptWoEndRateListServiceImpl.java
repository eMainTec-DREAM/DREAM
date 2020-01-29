package dream.work.rpt.woendrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dao.WorkRptWoEndRateListDAO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;
import dream.work.rpt.woendrate.service.WorkRptWoEndRateListService;

/**
 * 작업오더 일마감 처리율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptWoEndRateListServiceTarget"
 * @spring.txbn id="workRptWoEndRateListService"
 * @spring.property name="workRptWoEndRateListDAO" ref="workRptWoEndRateListDAO"
 */
public class WorkRptWoEndRateListServiceImpl implements WorkRptWoEndRateListService
{
    private WorkRptWoEndRateListDAO workRptWoEndRateListDAO = null;

    public List findList(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception
    {      
        return workRptWoEndRateListDAO.findList(workRptWoEndRateCommonDTO,user);
    }

    public WorkRptWoEndRateListDAO getWorkRptWoEndRateListDAO() {
        return workRptWoEndRateListDAO;
    }

    public void setWorkRptWoEndRateListDAO(WorkRptWoEndRateListDAO workRptWoEndRateListDAO) {
        this.workRptWoEndRateListDAO = workRptWoEndRateListDAO;
    }    
    
    public String findTotalCount(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO,User user)  throws Exception
    {
        return workRptWoEndRateListDAO.findTotalCount(workRptWoEndRateCommonDTO, user);
    }
}
