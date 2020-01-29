package dream.consult.comp.eqmgr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;
import dream.consult.comp.eqmgr.form.MaEqMngDetailForm;
import dream.consult.comp.eqmgr.service.MaEqMngDetailService;

/**
 * 설비담당자변경 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaEqMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqMngDetail" name="maEqMngDetailForm"
 *                input="/dream/consult/comp/eqmgr/maEqMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMngDetail" path="/dream/consult/comp/eqmgr/maEqMngDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMngDetailAction extends AuthAction
{
    /** 수정 */
    public static final int EQ_MNG_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMngDetailForm maEqMngDetailForm = (MaEqMngDetailForm) form;
        
        switch (maEqMngDetailForm.getStrutsAction())
        {
            case MaEqMngDetailAction.EQ_MNG_UPDATE:
                this.updateEqMng(maEqMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMngDetail");
                break;
        }

        return returnActionForward;
    }


    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailForm
     * @param request
     */
    private void updateEqMng(MaEqMngDetailForm maEqMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMngDetailService maEqMngDetailService = (MaEqMngDetailService) getBean("maEqMngDetailService");
        
        MaEqMngDetailDTO maEqMngDetailDTO = maEqMngDetailForm.getMaEqMngDetailDTO();
        
        maEqMngDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMngDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMngDetailService.updateEqMng(maEqMngDetailDTO);
        
        setAjaxStatus(request);
    }

}