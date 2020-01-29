package dream.work.rpt.dailyeng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.dao.WorkRptDailyEngDetailListDAO;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngDetailListForm;
import dream.work.rpt.dailyeng.service.WorkRptDailyEngDetailListService;

/**
 * 에너지사용량(일별) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptDailyEngDetailListServiceTarget"
 * @spring.txbn id="workRptDailyEngDetailListService"
 * @spring.property name="workRptDailyEngDetailListDAO" ref="workRptDailyEngDetailListDAO"
 */
public class WorkRptDailyEngDetailListServiceImpl implements WorkRptDailyEngDetailListService
{
    private WorkRptDailyEngDetailListDAO workRptDailyEngDetailListDAO = null;
    
    public WorkRptDailyEngDetailListDAO getWorkRptDailyEngDetailListDAO()
    {
        return workRptDailyEngDetailListDAO;
    }
    
    public void setWorkRptDailyEngDetailListDAO(
            WorkRptDailyEngDetailListDAO workRptDailyEngDetailListDAO)
    {
        this.workRptDailyEngDetailListDAO = workRptDailyEngDetailListDAO;
    }
    
    public List findDetailList(WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm, User loginUser)
    {
        return workRptDailyEngDetailListDAO.findDetailList(workRptDailyEngDetailListForm, loginUser);
        
    }
	
}

