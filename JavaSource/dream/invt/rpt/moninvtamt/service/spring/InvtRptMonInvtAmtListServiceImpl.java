package dream.invt.rpt.moninvtamt.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dao.InvtRptMonInvtAmtListDAO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;
import dream.invt.rpt.moninvtamt.service.InvtRptMonInvtAmtListService;

/**
 * InvtRptMonInvtAmt Page - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="invtRptMonInvtAmtListServiceTarget"
 * @spring.txbn id="invtRptMonInvtAmtListService"
 * @spring.property name="invtRptMonInvtAmtListDAO" ref="invtRptMonInvtAmtListDAO"
 */
public class InvtRptMonInvtAmtListServiceImpl implements InvtRptMonInvtAmtListService
{
    private InvtRptMonInvtAmtListDAO invtRptMonInvtAmtListDAO = null;

    public List findList(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO, User user) throws Exception
    {      
        return invtRptMonInvtAmtListDAO.findList(invtRptMonInvtAmtCommonDTO,user);
    }

    public InvtRptMonInvtAmtListDAO getInvtRptMonInvtAmtListDAO() {
        return invtRptMonInvtAmtListDAO;
    }

    public void setInvtRptMonInvtAmtListDAO(InvtRptMonInvtAmtListDAO invtRptMonInvtAmtListDAO) {
        this.invtRptMonInvtAmtListDAO = invtRptMonInvtAmtListDAO;
    }    
    
    public String findTotalCount(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO,User user)  throws Exception
    {
        return invtRptMonInvtAmtListDAO.findTotalCount(invtRptMonInvtAmtCommonDTO, user);
    }
}
