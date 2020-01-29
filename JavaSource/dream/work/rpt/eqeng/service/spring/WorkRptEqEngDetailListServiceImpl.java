package dream.work.rpt.eqeng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.dao.WorkRptEqEngDetailListDAO;
import dream.work.rpt.eqeng.form.WorkRptEqEngDetailListForm;
import dream.work.rpt.eqeng.service.WorkRptEqEngDetailListService;

/**
 * 에너지사용량(설비별) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptEqEngDetailListServiceTarget"
 * @spring.txbn id="workRptEqEngDetailListService"
 * @spring.property name="workRptEqEngDetailListDAO" ref="workRptEqEngDetailListDAO"
 */
public class WorkRptEqEngDetailListServiceImpl implements WorkRptEqEngDetailListService
{
    private WorkRptEqEngDetailListDAO workRptEqEngDetailListDAO = null;
    
    public WorkRptEqEngDetailListDAO getWorkRptEqEngDetailListDAO()
    {
        return workRptEqEngDetailListDAO;
    }
    
    public void setWorkRptEqEngDetailListDAO(
            WorkRptEqEngDetailListDAO workRptEqEngDetailListDAO)
    {
        this.workRptEqEngDetailListDAO = workRptEqEngDetailListDAO;
    }
    
    public List findDetailList(WorkRptEqEngDetailListForm workRptEqEngDetailListForm, User loginUser)
    {
        return workRptEqEngDetailListDAO.findDetailList(workRptEqEngDetailListForm, loginUser);
        
    }
	
}

