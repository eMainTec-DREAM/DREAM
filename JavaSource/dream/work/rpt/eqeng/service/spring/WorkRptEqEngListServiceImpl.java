package dream.work.rpt.eqeng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.dao.WorkRptEqEngListDAO;
import dream.work.rpt.eqeng.form.WorkRptEqEngListForm;
import dream.work.rpt.eqeng.service.WorkRptEqEngListService;

/**
 * 에너지사용량(설비별) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptEqEngListServiceTarget"
 * @spring.txbn id="workRptEqEngListService"
 * @spring.property name="workRptEqEngListDAO" ref="workRptEqEngListDAO"
 */
public class WorkRptEqEngListServiceImpl implements WorkRptEqEngListService
{
    private WorkRptEqEngListDAO workRptEqEngListDAO = null;
    
	public WorkRptEqEngListDAO getWorkRptEqEngListDAO()
    {
        return workRptEqEngListDAO;
    }
	
    public void setWorkRptEqEngListDAO(
            WorkRptEqEngListDAO workRptEqEngListDAO)
    {
        this.workRptEqEngListDAO = workRptEqEngListDAO;
    }
    
    public List findList(WorkRptEqEngListForm workRptEqEngListForm, User loginUser)
    {
        List result = null;
        List totalUsageList = workRptEqEngListDAO.findTotalCount(workRptEqEngListForm, loginUser);
        workRptEqEngListForm.getWorkRptEqEngCommonDTO().setTotalUsageList(totalUsageList);
        
    	result = workRptEqEngListDAO.findPlantList(workRptEqEngListForm, loginUser);
        
        return result;      
        
    }
	
}

