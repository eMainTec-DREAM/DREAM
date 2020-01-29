package dream.invt.rpt.invtcateg.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtcateg.dao.InvtRptInvtCategDetailListDAO;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategDetailListForm;
import dream.invt.rpt.invtcateg.service.InvtRptInvtCategDetailListService;

/**
 * 투자구분분석 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="invtRptInvtCategDetailListServiceTarget"
 * @spring.txbn id="invtRptInvtCategDetailListService"
 * @spring.property name="invtRptInvtCategDetailListDAO" ref="invtRptInvtCategDetailListDAO"
 */
public class InvtRptInvtCategDetailListServiceImpl implements InvtRptInvtCategDetailListService
{
    private InvtRptInvtCategDetailListDAO invtRptInvtCategDetailListDAO = null;
    
    public InvtRptInvtCategDetailListDAO getInvtRptInvtCategDetailListDAO()
    {
        return invtRptInvtCategDetailListDAO;
    }
    
    public void setInvtRptInvtCategDetailListDAO(
            InvtRptInvtCategDetailListDAO invtRptInvtCategDetailListDAO)
    {
        this.invtRptInvtCategDetailListDAO = invtRptInvtCategDetailListDAO;
    }
    
    public List findDetailList(InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm, User loginUser)
    {
        return invtRptInvtCategDetailListDAO.findDetailList(invtRptInvtCategDetailListForm, loginUser);
    }
}

