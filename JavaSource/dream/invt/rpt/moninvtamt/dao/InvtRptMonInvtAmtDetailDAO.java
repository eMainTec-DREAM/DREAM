package dream.invt.rpt.moninvtamt.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtDetailForm;

/**
 * InvtRptMonInvtAmt Page - Detail DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface InvtRptMonInvtAmtDetailDAO
{
	 /**
     * grid find
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptMonInvtAmtDetailListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm, User loginUser);
}
