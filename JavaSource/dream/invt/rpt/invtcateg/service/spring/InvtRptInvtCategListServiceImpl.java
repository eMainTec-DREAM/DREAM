package dream.invt.rpt.invtcateg.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.dao.InvtRptInvtCategListDAO;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;
import dream.invt.rpt.invtcateg.service.InvtRptInvtCategListService;

/**
 * 투자구분분석 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="invtRptInvtCategListServiceTarget"
 * @spring.txbn id="invtRptInvtCategListService"
 * @spring.property name="invtRptInvtCategListDAO" ref="invtRptInvtCategListDAO"
 */
public class InvtRptInvtCategListServiceImpl implements InvtRptInvtCategListService
{
    private InvtRptInvtCategListDAO invtRptInvtCategListDAO = null;
    
	public InvtRptInvtCategListDAO getInvtRptInvtCategListDAO()
    {
        return invtRptInvtCategListDAO;
    }
	
    public void setInvtRptInvtCategListDAO(
            InvtRptInvtCategListDAO invtRptInvtCategListDAO)
    {
        this.invtRptInvtCategListDAO = invtRptInvtCategListDAO;
    }
    
    public List findList(InvtRptInvtCategListForm invtRptInvtCategListForm, User loginUser)
    {
    	return invtRptInvtCategListDAO.findPlantList(invtRptInvtCategListForm, loginUser);
    }
}

