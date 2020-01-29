package dream.rcm.crity.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
import dream.rcm.crity.form.RcmCrityRowDetailForm;
import dream.rcm.crity.service.RcmCrityRowDetailService;

/**
 * Criticality Matrix Row Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityRowDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityRowDetail" name="rcmCrityRowDetailForm"
 *                input="/dream/rcm/crity/rcmCrityRowDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityRowDetail" path="/dream/rcm/crity/rcmCrityRowDetail.jsp"
 *                        redirect="false"
 */
public class RcmCrityRowDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityRowDetailForm rcmCrityRowDetailForm = (RcmCrityRowDetailForm) form;
        
        super.updateAudit(rcmCrityRowDetailForm.getRcmCrityRowDetailDTO().getAuditKey()==""?rcmCrityRowDetailForm.getRcmCrityRowListDTO().getAuditKey():rcmCrityRowDetailForm.getRcmCrityRowDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmCrityRowDetailForm.getStrutsAction())
        {
            case RcmCrityRowDetailAction.DETAIL_INIT:
                this.findDetail(request, response, rcmCrityRowDetailForm);
                returnActionForward = mapping.findForward("rcmCrityRowDetail");
                break;
            case RcmCrityRowDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, rcmCrityRowDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmCrityRowDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, rcmCrityRowDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmCrityRowDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param rcmCrityRowDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityRowDetailForm rcmCrityRowDetailForm) throws Exception 
    {   
    	RcmCrityRowDetailService rcmCrityRowDetailService = (RcmCrityRowDetailService)getBean("rcmCrityRowDetailService");
    	
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityRowDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityRowListDTO rcmCrityRowListDTO = rcmCrityRowDetailForm.getRcmCrityRowListDTO();
    	RcmCrityRowDetailDTO rcmCrityRowDetailDTO = rcmCrityRowDetailService.findDetail(rcmCrityCommonDTO,rcmCrityRowListDTO, getUser(request));
    	rcmCrityRowDetailForm.setRcmCrityRowDetailDTO(rcmCrityRowDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param rcmCrityRowDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityRowDetailForm rcmCrityRowDetailForm) throws Exception
    {
    	RcmCrityRowDetailService rcmCrityRowDetailService = (RcmCrityRowDetailService)getBean("rcmCrityRowDetailService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityRowDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityRowDetailDTO  rcmCrityRowDetailDTO = rcmCrityRowDetailForm.getRcmCrityRowDetailDTO(); 
    	rcmCrityRowDetailService.insertDetail(rcmCrityCommonDTO,rcmCrityRowDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param rcmCrityRowDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityRowDetailForm rcmCrityRowDetailForm) throws Exception
    {
    	RcmCrityRowDetailService rcmCrityRowDetailService = (RcmCrityRowDetailService)getBean("rcmCrityRowDetailService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityRowDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityRowDetailDTO  rcmCrityRowDetailDTO = rcmCrityRowDetailForm.getRcmCrityRowDetailDTO();
    	rcmCrityRowDetailService.updateDetail(rcmCrityCommonDTO, rcmCrityRowDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}