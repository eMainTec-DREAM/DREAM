package dream.work.rpt.toteng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.toteng.form.WorkRptTotEngDetailListForm;

/**
 * 에너지사용량(집계) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptTotEngDetailListService
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
    public List findDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    
    public List findDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
}
