package dream.invt.rpt.invtcateg.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;

/**
 * 투자구분분석 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface InvtRptInvtCategListService
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
    public List findList(InvtRptInvtCategListForm invtRptInvtCategListForm, User loginUser);
}
