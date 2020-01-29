package dream.work.rpt.toteng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.toteng.dao.WorkRptTotEngListDAO;
import dream.work.rpt.toteng.form.WorkRptTotEngListForm;
import dream.work.rpt.toteng.service.WorkRptTotEngListService;

/**
 * 에너지사용량(집계) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptTotEngListServiceTarget"
 * @spring.txbn id="workRptTotEngListService"
 * @spring.property name="workRptTotEngListDAO" ref="workRptTotEngListDAO"
 */
public class WorkRptTotEngListServiceImpl implements WorkRptTotEngListService
{
    private WorkRptTotEngListDAO workRptTotEngListDAO = null;
    
	public WorkRptTotEngListDAO getWorkRptTotEngListDAO()
    {
        return workRptTotEngListDAO;
    }
	
    public void setWorkRptTotEngListDAO(
            WorkRptTotEngListDAO workRptTotEngListDAO)
    {
        this.workRptTotEngListDAO = workRptTotEngListDAO;
    }
    
    public List findList(WorkRptTotEngListForm workRptTotEngListForm, User loginUser)
    {
    	return workRptTotEngListDAO.findPlantList(workRptTotEngListForm, loginUser);
    }
	
    public List findChart(WorkRptTotEngListForm workRptTotEngListForm, User loginUser)
    {
    	return workRptTotEngListDAO.findChart(workRptTotEngListForm, loginUser);
    }
}

