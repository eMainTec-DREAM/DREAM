package dream.invt.rpt.invtcateg.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;

/**
 * ���ڱ��км� ��� dao
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