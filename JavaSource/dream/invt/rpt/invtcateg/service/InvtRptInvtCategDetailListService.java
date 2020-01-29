package dream.invt.rpt.invtcateg.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategDetailListForm;

/**
 * ���ڱ��км� �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface InvtRptInvtCategDetailListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptInvtCategListForm
     * @param loginUser
     * @throws Exception
     */
    public List findDetailList(InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm, User loginUser);
}
