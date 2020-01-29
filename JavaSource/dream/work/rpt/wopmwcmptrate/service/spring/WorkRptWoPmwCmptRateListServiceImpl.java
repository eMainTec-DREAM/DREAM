package dream.work.rpt.wopmwcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dao.WorkRptWoPmwCmptRateListDAO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;
import dream.work.rpt.wopmwcmptrate.service.WorkRptWoPmwCmptRateListService;

/**
 * 예방작업 계획대비 실행 비율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptWoPmwCmptRateListServiceTarget"
 * @spring.txbn id="workRptWoPmwCmptRateListService"
 * @spring.property name="workRptWoPmwCmptRateListDAO" ref="workRptWoPmwCmptRateListDAO"
 */
public class WorkRptWoPmwCmptRateListServiceImpl implements WorkRptWoPmwCmptRateListService
{
    private WorkRptWoPmwCmptRateListDAO workRptWoPmwCmptRateListDAO = null;

    public List findList(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception
    {      
        return workRptWoPmwCmptRateListDAO.findList(workRptWoPmwCmptRateCommonDTO,user);
    }

    public WorkRptWoPmwCmptRateListDAO getWorkRptWoPmwCmptRateListDAO() {
        return workRptWoPmwCmptRateListDAO;
    }

    public void setWorkRptWoPmwCmptRateListDAO(WorkRptWoPmwCmptRateListDAO workRptWoPmwCmptRateListDAO) {
        this.workRptWoPmwCmptRateListDAO = workRptWoPmwCmptRateListDAO;
    }    
    
    public String findTotalCount(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO,User user)  throws Exception
    {
        return workRptWoPmwCmptRateListDAO.findTotalCount(workRptWoPmwCmptRateCommonDTO, user);
    }
}
