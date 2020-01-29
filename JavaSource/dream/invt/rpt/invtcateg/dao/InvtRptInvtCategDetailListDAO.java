package dream.invt.rpt.invtcateg.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategDetailListForm;

/**
 * 투자구분분석 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface InvtRptInvtCategDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptInvtCategDetailListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm, User loginUser);
}