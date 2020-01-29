package dream.invt.rpt.invtprecon.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConDetailDTO;
import dream.invt.rpt.invtprecon.form.InvtRptInvtPreConDetailForm;
import dream.invt.rpt.invtprecon.service.InvtRptInvtPreConDetailService;

/**
 * InvtRptInvtPreCon Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/invtRptInvtPreConDetail" name="invtRptInvtPreConDetailForm"
 *                input="/dream/invt/rpt/invtprecon/invtRptInvtPreConDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptInvtPreConDetail" path="/dream/invt/rpt/invtprecon/invtRptInvtPreConDetail.jsp"
 *                        redirect="false"
 */
public class InvtRptInvtPreConDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptInvtPreConDetailForm invtRptInvtPreConDetailForm = (InvtRptInvtPreConDetailForm) form;
        
        switch (invtRptInvtPreConDetailForm.getStrutsAction())
        {
            case InvtRptInvtPreConDetailAction.DETAIL_INIT:
                this.findDetail(request, response, invtRptInvtPreConDetailForm);
                returnActionForward = mapping.findForward("invtRptInvtPreConDetail");
                break;
            default:
                returnActionForward = mapping.findForward("invtRptInvtPreConDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param invtRptInvtPreConDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, InvtRptInvtPreConDetailForm invtRptInvtPreConDetailForm) throws Exception 
    {   
        InvtRptInvtPreConDetailService invtRptInvtPreConDetailService = (InvtRptInvtPreConDetailService)getBean("invtRptInvtPreConDetailService");
        
        InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = invtRptInvtPreConDetailForm.getInvtRptInvtPreConCommonDTO(); 

        User user = getUser(request);
        InvtRptInvtPreConDetailDTO invtRptInvtPreConDetailDTO = invtRptInvtPreConDetailService.findDetail(invtRptInvtPreConCommonDTO, user);
        invtRptInvtPreConDetailForm.setInvtRptInvtPreConDetailDTO(invtRptInvtPreConDetailDTO);
    }
}
