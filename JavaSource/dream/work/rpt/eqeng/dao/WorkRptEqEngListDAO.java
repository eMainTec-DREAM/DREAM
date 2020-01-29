package dream.work.rpt.eqeng.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.form.WorkRptEqEngListForm;

/**
 * ��������뷮(����) ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptEqEngListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptEqEngListForm
     * @param loginUser
     * @return List
     */
    public List findPlantList(WorkRptEqEngListForm workRptEqEngListForm, User loginUser);
    
    public List findTotalCount(WorkRptEqEngListForm workRptEqEngListForm, User loginUser);

}