package dream.work.rpt.toteng.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.toteng.form.WorkRptTotEngDetailListForm;

/**
 * 에너지사용량(집계) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptTotEngDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptTotEngDetailListForm
     * @param loginUser
     * @return List
     */
    public List findEqLocDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    public List findEqLocDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    public List findUsageDeptDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    public List findUsageDeptDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    public List findEqCtgDetailList(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
    public List findEqCtgDetailChart(WorkRptTotEngDetailListForm workRptTotEngDetailListForm, User loginUser);
}