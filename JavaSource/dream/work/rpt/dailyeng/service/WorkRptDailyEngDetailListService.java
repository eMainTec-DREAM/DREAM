package dream.work.rpt.dailyeng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngDetailListForm;

/**
 * 에너지사용량(일별) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptDailyEngDetailListService
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
    public List findDetailList(WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm, User loginUser);
    
}
