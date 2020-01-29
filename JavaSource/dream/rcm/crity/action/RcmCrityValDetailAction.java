package dream.rcm.crity.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityValDetailDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
import dream.rcm.crity.form.RcmCrityValDetailForm;
import dream.rcm.crity.service.RcmCrityValDetailService;

/**
 * Criticality Matrix Val Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityValDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityValDetail" name="rcmCrityValDetailForm"
 *                input="/dream/rcm/crity/rcmCrityValDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityValDetail" path="/dream/rcm/crity/rcmCrityValDetail.jsp"
 *                        redirect="false"
 */
public class RcmCrityValDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityValDetailForm rcmCrityValDetailForm = (RcmCrityValDetailForm) form;
        
        super.updateAudit(rcmCrityValDetailForm.getRcmCrityValDetailDTO().getAuditKey()==""?rcmCrityValDetailForm.getRcmCrityValListDTO().getAuditKey():rcmCrityValDetailForm.getRcmCrityValDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmCrityValDetailForm.getStrutsAction())
        {
            case RcmCrityValDetailAction.DETAIL_INIT:
                this.findDetail(request, response, rcmCrityValDetailForm);
                returnActionForward = mapping.findForward("rcmCrityValDetail");
                break;
            case RcmCrityValDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, rcmCrityValDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmCrityValDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param rcmCrityValDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityValDetailForm rcmCrityValDetailForm) throws Exception 
    {   
    	RcmCrityValDetailService rcmCrityValDetailService = (RcmCrityValDetailService)getBean("rcmCrityValDetailService");
    	
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityValDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityValListDTO rcmCrityValListDTO = rcmCrityValDetailForm.getRcmCrityValListDTO();
    	RcmCrityValDetailDTO rcmCrityValDetailDTO = rcmCrityValDetailService.findDetail(rcmCrityCommonDTO,rcmCrityValListDTO, getUser(request));
    	rcmCrityValDetailForm.setRcmCrityValDetailDTO(rcmCrityValDetailDTO);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param rcmCrityValDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityValDetailForm rcmCrityValDetailForm) throws Exception
    {
    	RcmCrityValDetailService rcmCrityValDetailService = (RcmCrityValDetailService)getBean("rcmCrityValDetailService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityValDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityValDetailDTO  rcmCrityValDetailDTO = rcmCrityValDetailForm.getRcmCrityValDetailDTO();
    	rcmCrityValDetailService.updateDetail(rcmCrityCommonDTO, rcmCrityValDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}