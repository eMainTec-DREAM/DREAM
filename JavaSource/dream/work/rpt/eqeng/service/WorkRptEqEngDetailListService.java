package dream.work.rpt.eqeng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.form.WorkRptEqEngDetailListForm;

/**
 * ��������뷮(����) �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptEqEngDetailListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptEqEngListForm
     * @param loginUser
     * @throws Exception
     */
    public List findDetailList(WorkRptEqEngDetailListForm workRptEqEngDetailListForm, User loginUser);
    
}
