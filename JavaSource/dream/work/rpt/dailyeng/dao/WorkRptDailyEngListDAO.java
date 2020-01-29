package dream.work.rpt.dailyeng.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngListForm;

/**
 * ��������뷮(�Ϻ�) ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptDailyEngListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findPlantList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findEqLocList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findUsageDeptList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findEqCtgList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findEqpList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
}