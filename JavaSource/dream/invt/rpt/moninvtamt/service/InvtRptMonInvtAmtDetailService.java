package dream.invt.rpt.moninvtamt.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtDetailForm;

/**
 * InvtRptMonInvtAmt Page - Detail Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface InvtRptMonInvtAmtDetailService
{
	  /**
     *  grid find
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @throws Exception
     */
    public List findDetailList(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm, User loginUser);
}
