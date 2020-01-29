package dream.work.rpt.dailyeng.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngDetailListForm;

/**
 * 에너지사용량(일별) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptDailyEngDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngDetailListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm, User loginUser);
    
}