package dream.work.rpt.dailyeng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.dao.WorkRptDailyEngListDAO;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngListForm;
import dream.work.rpt.dailyeng.service.WorkRptDailyEngListService;

/**
 * 에너지사용량(일별) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptDailyEngListServiceTarget"
 * @spring.txbn id="workRptDailyEngListService"
 * @spring.property name="workRptDailyEngListDAO" ref="workRptDailyEngListDAO"
 */
public class WorkRptDailyEngListServiceImpl implements WorkRptDailyEngListService
{
    private WorkRptDailyEngListDAO workRptDailyEngListDAO = null;
    
	public WorkRptDailyEngListDAO getWorkRptDailyEngListDAO()
    {
        return workRptDailyEngListDAO;
    }
	
    public void setWorkRptDailyEngListDAO(
            WorkRptDailyEngListDAO workRptDailyEngListDAO)
    {
        this.workRptDailyEngListDAO = workRptDailyEngListDAO;
    }
    
    public List findList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser)
    {
        List result = null;
        
        switch (workRptDailyEngListForm.getWorkRptDailyEngCommonDTO().getFilterSeparation()) {
            case "L1":
                result = workRptDailyEngListDAO.findPlantList(workRptDailyEngListForm, loginUser);
                break;
            case "L2":
                result = workRptDailyEngListDAO.findEqLocList(workRptDailyEngListForm, loginUser);
                break;
            case "L3":
                result = workRptDailyEngListDAO.findUsageDeptList(workRptDailyEngListForm, loginUser);
                break;
            case "L4":
                result = workRptDailyEngListDAO.findEqCtgList(workRptDailyEngListForm, loginUser);
                break;
            case "L5":
            	result = workRptDailyEngListDAO.findEqpList(workRptDailyEngListForm, loginUser);
            	break;
            default:
                result = workRptDailyEngListDAO.findPlantList(workRptDailyEngListForm, loginUser);
                break;
        }
        
        return result;      
        
    }
	
}

