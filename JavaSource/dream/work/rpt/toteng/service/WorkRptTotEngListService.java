package dream.work.rpt.toteng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.toteng.form.WorkRptTotEngListForm;

/**
 * 에너지사용량(집계) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptTotEngListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptTotEngListForm
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptTotEngListForm workRptTotEngListForm, User loginUser);
    public List findChart(WorkRptTotEngListForm workRptTotEngListForm, User loginUser);
}
