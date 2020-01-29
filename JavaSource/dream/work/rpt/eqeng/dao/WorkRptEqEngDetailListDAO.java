package dream.work.rpt.eqeng.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.form.WorkRptEqEngDetailListForm;

/**
 * ��������뷮(����) �� ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptEqEngDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptEqEngDetailListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(WorkRptEqEngDetailListForm workRptEqEngDetailListForm, User loginUser);
    
}