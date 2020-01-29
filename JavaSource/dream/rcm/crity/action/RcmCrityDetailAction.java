package dream.rcm.crity.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityDetailDTO;
import dream.rcm.crity.form.RcmCrityDetailForm;
import dream.rcm.crity.service.RcmCrityDetailService;

/**
 * Criticality Matrix Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityDetail" name="rcmCrityDetailForm"
 *                input="/dream/rcm/crity/rcmCrityDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityDetail" path="/dream/rcm/crity/rcmCrityDetail.jsp"
 *                        redirect="false"
 */
public class RcmCrityDetailAction extends AuthAction
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
        RcmCrityDetailForm rcmCrityDetailForm = (RcmCrityDetailForm) form;
       
        super.updateAudit(rcmCrityDetailForm.getRcmCrityDetailDTO().getAuditKey()==""?rcmCrityDetailForm.getRcmCrityCommonDTO().getAuditKey():rcmCrityDetailForm.getRcmCrityDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmCrityDetailForm.getStrutsAction())
        {
            case RcmCrityDetailAction.DETAIL_INIT:
                this.findDetail(request, response, rcmCrityDetailForm);
                returnActionForward = mapping.findForward("rcmCrityDetail");
                break;
            case RcmCrityDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, rcmCrityDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmCrityDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, rcmCrityDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmCrityDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param rcmCrityDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityDetailForm rcmCrityDetailForm) throws Exception 
    {   
    	RcmCrityDetailService rcmCrityDetailService = (RcmCrityDetailService)getBean("rcmCrityDetailService");
    	
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityDetailDTO rcmCrityDetailDTO = rcmCrityDetailService.findDetail(rcmCrityCommonDTO, getUser(request));
    	rcmCrityDetailForm.setRcmCrityDetailDTO(rcmCrityDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param rcmCrityDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityDetailForm rcmCrityDetailForm) throws Exception
    {
    	RcmCrityDetailService rcmCrityDetailService = (RcmCrityDetailService)getBean("rcmCrityDetailService");
    	RcmCrityDetailDTO  rcmCrityDetailDTO = rcmCrityDetailForm.getRcmCrityDetailDTO(); 
    	rcmCrityDetailService.insertDetail(rcmCrityDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param rcmCrityDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityDetailForm rcmCrityDetailForm) throws Exception
    {
    	RcmCrityDetailService rcmCrityDetailService = (RcmCrityDetailService)getBean("rcmCrityDetailService");
    	RcmCrityDetailDTO  rcmCrityDetailDTO = rcmCrityDetailForm.getRcmCrityDetailDTO();
    	rcmCrityDetailService.updateDetail(rcmCrityDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}