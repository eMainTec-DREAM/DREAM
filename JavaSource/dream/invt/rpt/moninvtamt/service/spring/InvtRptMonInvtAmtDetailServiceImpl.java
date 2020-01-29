package dream.invt.rpt.moninvtamt.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.moninvtamt.dao.InvtRptMonInvtAmtDetailDAO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;
import dream.invt.rpt.moninvtamt.service.InvtRptMonInvtAmtDetailService;
import dream.invt.rpt.moninvtamt.dao.InvtRptMonInvtAmtDetailDAO;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtDetailForm;

/**
 * InvtRptMonInvtAmt Page - Detail Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="invtRptMonInvtAmtDetailServiceTarget"
 * @spring.txbn id="invtRptMonInvtAmtDetailService"
 * @spring.property name="invtRptMonInvtAmtDetailDAO" ref="invtRptMonInvtAmtDetailDAO"
 */
public class InvtRptMonInvtAmtDetailServiceImpl implements InvtRptMonInvtAmtDetailService
{
	 private InvtRptMonInvtAmtDetailDAO invtRptMonInvtAmtDetailDAO = null;
	    
	    public InvtRptMonInvtAmtDetailDAO getInvtRptMonInvtAmtDetailDAO()
	    {
	        return invtRptMonInvtAmtDetailDAO;
	    }
	    
	    public void setInvtRptMonInvtAmtDetailDAO(
	            InvtRptMonInvtAmtDetailDAO invtRptMonInvtAmtDetailDAO)
	    {
	        this.invtRptMonInvtAmtDetailDAO = invtRptMonInvtAmtDetailDAO;
	    }
	    
	    public List findDetailList(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm, User loginUser)
	    {
	        return invtRptMonInvtAmtDetailDAO.findDetailList(invtRptMonInvtAmtDetailForm, loginUser);
	        
	    }
}
