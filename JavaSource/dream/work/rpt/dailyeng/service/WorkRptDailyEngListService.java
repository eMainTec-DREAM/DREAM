package dream.work.rpt.dailyeng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngListForm;

/**
 * ��������뷮(�Ϻ�) ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptDailyEngListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptDailyEngListForm workRptDailyEngListForm, User loginUser);
    
}
