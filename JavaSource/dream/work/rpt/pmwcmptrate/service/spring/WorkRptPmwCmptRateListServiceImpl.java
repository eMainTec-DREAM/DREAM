package dream.work.rpt.pmwcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwcmptrate.dao.WorkRptPmwCmptRateListDAO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;
import dream.work.rpt.pmwcmptrate.service.WorkRptPmwCmptRateListService;

/**
 * �ֱ����� ��ȹ��� ���� ���� ��� - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptPmwCmptRateListServiceTarget"
 * @spring.txbn id="workRptPmwCmptRateListService"
 * @spring.property name="workRptPmwCmptRateListDAO" ref="workRptPmwCmptRateListDAO"
 */
public class WorkRptPmwCmptRateListServiceImpl implements WorkRptPmwCmptRateListService
{
    private WorkRptPmwCmptRateListDAO workRptPmwCmptRateListDAO = null;

    public List findList(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception
    {      
        return workRptPmwCmptRateListDAO.findList(workRptPmwCmptRateCommonDTO,user);
    }

    public WorkRptPmwCmptRateListDAO getWorkRptPmwCmptRateListDAO() {
        return workRptPmwCmptRateListDAO;
    }

    public void setWorkRptPmwCmptRateListDAO(WorkRptPmwCmptRateListDAO workRptPmwCmptRateListDAO) {
        this.workRptPmwCmptRateListDAO = workRptPmwCmptRateListDAO;
    }    
    
    public String findTotalCount(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO,User user)  throws Exception
    {
        return workRptPmwCmptRateListDAO.findTotalCount(workRptPmwCmptRateCommonDTO, user);
    }
}
