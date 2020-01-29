package dream.work.rpt.eqeng.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.eqeng.form.WorkRptEqEngListForm;

/**
 * 에너지사용량(설비별) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptEqEngListService
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
    public List findList(WorkRptEqEngListForm workRptEqEngListForm, User loginUser);
    
}
