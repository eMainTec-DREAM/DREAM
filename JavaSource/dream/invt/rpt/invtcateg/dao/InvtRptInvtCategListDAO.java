package dream.invt.rpt.invtcateg.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;

/**
 * 투자구분분석 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface InvtRptInvtCategListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptInvtCategListForm
     * @param loginUser
     * @return List
     */
    public List findPlantList(InvtRptInvtCategListForm invtRptInvtCategListForm, User loginUser);
    
}