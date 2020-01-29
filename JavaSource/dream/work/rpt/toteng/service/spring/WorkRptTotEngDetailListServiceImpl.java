package dream.work.rpt.toteng.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.toteng.dao.WorkRptTotEngDetailListDAO;
import dream.work.rpt.toteng.form.WorkRptTotEngDetailListForm;
import dream.work.rpt.toteng.service.WorkRptTotEngDetailListService;

/**
 * 에너지사용량(집계) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptTotEngDetailListServiceTarget"
 * @spring.txbn id="workRptTotEngDetailListService"
 * @spring.property name="workRptTotEngDetailListDAO" ref="workRptTotEngDetailListDAO"
 */
public class WorkRptTotEngDetailListServiceImpl implements WorkRptTotEngDetailListService
{
    private WorkRptTotEngDetailListDAO workRptTotEngDetailListDAO = null;
    
    public WorkRptTotEngDetailListDAO getWorkRptTotEngDetailListDAO()
    {
        return workRptTotEngDetailListDAO;
    }
    
    public void setWorkRptTotEngDetailListDAO(
            WorkRptTotEngDetailListDAO workRptTotEngDetailListDAO)
    {
        this.workRptTotEngDetailListDAO = workRptTotEngDetailListDAO;
    }
    
    public List findDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	String tabDepth = workRptTotEngDetailListForm.getWorkRptTotEngDetailListDTO().getTabDepth();
    	List result = null;
    	
    	switch(tabDepth)
    	{
    		case "L1" :
    			result = workRptTotEngDetailListDAO.findEqLocDetailList(workRptTotEngDetailListForm, loginUser);
    			break;
    		case "L2" :
    			result = workRptTotEngDetailListDAO.findUsageDeptDetailList(workRptTotEngDetailListForm, loginUser);
    			break;
    		case "L3" :
    			result = workRptTotEngDetailListDAO.findEqCtgDetailList(workRptTotEngDetailListForm, loginUser);
    			break;
			default :
				result = workRptTotEngDetailListDAO.findEqLocDetailList(workRptTotEngDetailListForm, loginUser);
				break;
    	}
        return result;
    }
    
    public List findDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser)
    {
    	String tabDepth = workRptTotEngDetailListForm.getWorkRptTotEngDetailListDTO().getTabDepth();
    	List result = null;
    	
    	switch(tabDepth)
    	{
    		case "L1" :
    			result = workRptTotEngDetailListDAO.findEqLocDetailChart(workRptTotEngDetailListForm, loginUser);
    			break;
    		case "L2" :
    			result = workRptTotEngDetailListDAO.findUsageDeptDetailChart(workRptTotEngDetailListForm, loginUser);
    			break;
    		case "L3" :
    			result = workRptTotEngDetailListDAO.findEqCtgDetailChart(workRptTotEngDetailListForm, loginUser);
    			break;
			default :
				result = workRptTotEngDetailListDAO.findEqLocDetailChart(workRptTotEngDetailListForm, loginUser);
				break;
    	}
    	return result;
    }
	
}

