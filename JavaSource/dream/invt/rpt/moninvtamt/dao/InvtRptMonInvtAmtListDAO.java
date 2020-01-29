package dream.invt.rpt.moninvtamt.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;

/**
 * InvtRptMonInvtAmt Page - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface InvtRptMonInvtAmtListDAO
{
    /**
     * FIND LIST
     * @param invtRptMonInvtAmtCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param invtRptMonInvtAmtCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) throws Exception;
    
}
