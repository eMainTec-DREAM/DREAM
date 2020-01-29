package dream.work.rpt.pmicmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dao.WorkRptPmiCmptRateListDAO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;
import dream.work.rpt.pmicmptrate.service.WorkRptPmiCmptRateListService;

/**
 * 예방점검항목 실행 비율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmiCmptRateListServiceTarget"
 * @spring.txbn id="workRptPmiCmptRateListService"
 * @spring.property name="workRptPmiCmptRateListDAO" ref="workRptPmiCmptRateListDAO"
 */
public class WorkRptPmiCmptRateListServiceImpl implements WorkRptPmiCmptRateListService
{
    private WorkRptPmiCmptRateListDAO workRptPmiCmptRateListDAO = null;

    public List findList(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception
    {      
        return workRptPmiCmptRateListDAO.findList(workRptPmiCmptRateCommonDTO,user);
    }

    public WorkRptPmiCmptRateListDAO getWorkRptPmiCmptRateListDAO() {
        return workRptPmiCmptRateListDAO;
    }

    public void setWorkRptPmiCmptRateListDAO(WorkRptPmiCmptRateListDAO workRptPmiCmptRateListDAO) {
        this.workRptPmiCmptRateListDAO = workRptPmiCmptRateListDAO;
    }    
    
    public String findTotalCount(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO,User user)  throws Exception
    {
        return workRptPmiCmptRateListDAO.findTotalCount(workRptPmiCmptRateCommonDTO, user);
    }
}
