package dream.invt.rpt.moninvtamt.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;

/**
 * InvtRptMonInvtAmt Page - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface InvtRptMonInvtAmtListService
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
     * find Total Count
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptMonInvtAmtCommonDTO
     * @return
     */
    public String findTotalCount(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) throws Exception;
}
