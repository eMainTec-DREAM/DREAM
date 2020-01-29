package dream.rcm.crity.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.form.RcmCrityColDetailForm;
import dream.rcm.crity.service.RcmCrityColDetailService;

/**
 * Criticality Matrix Col Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityColDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityColDetail" name="rcmCrityColDetailForm"
 *                input="/dream/rcm/crity/rcmCrityColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityColDetail" path="/dream/rcm/crity/rcmCrityColDetail.jsp"
 *                        redirect="false"
 */
public class RcmCrityColDetailAction extends AuthAction
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
        RcmCrityColDetailForm rcmCrityColDetailForm = (RcmCrityColDetailForm) form;
        
        super.updateAudit(rcmCrityColDetailForm.getRcmCrityColDetailDTO().getAuditKey()==""?rcmCrityColDetailForm.getRcmCrityColListDTO().getAuditKey():rcmCrityColDetailForm.getRcmCrityColDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmCrityColDetailForm.getStrutsAction())
        {
            case RcmCrityColDetailAction.DETAIL_INIT:
                this.findDetail(request, response, rcmCrityColDetailForm);
                returnActionForward = mapping.findForward("rcmCrityColDetail");
                break;
            case RcmCrityColDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, rcmCrityColDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmCrityColDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, rcmCrityColDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmCrityColDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param rcmCrityColDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityColDetailForm rcmCrityColDetailForm) throws Exception 
    {   
    	RcmCrityColDetailService rcmCrityColDetailService = (RcmCrityColDetailService)getBean("rcmCrityColDetailService");
    	
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityColDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityColListDTO rcmCrityColListDTO = rcmCrityColDetailForm.getRcmCrityColListDTO();
    	RcmCrityColDetailDTO rcmCrityColDetailDTO = rcmCrityColDetailService.findDetail(rcmCrityCommonDTO,rcmCrityColListDTO, getUser(request));
    	rcmCrityColDetailForm.setRcmCrityColDetailDTO(rcmCrityColDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param rcmCrityColDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityColDetailForm rcmCrityColDetailForm) throws Exception
    {
    	RcmCrityColDetailService rcmCrityColDetailService = (RcmCrityColDetailService)getBean("rcmCrityColDetailService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityColDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityColDetailDTO  rcmCrityColDetailDTO = rcmCrityColDetailForm.getRcmCrityColDetailDTO(); 
    	rcmCrityColDetailService.insertDetail(rcmCrityCommonDTO,rcmCrityColDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param rcmCrityColDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, RcmCrityColDetailForm rcmCrityColDetailForm) throws Exception
    {
    	RcmCrityColDetailService rcmCrityColDetailService = (RcmCrityColDetailService)getBean("rcmCrityColDetailService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityColDetailForm.getRcmCrityCommonDTO(); 
    	RcmCrityColDetailDTO  rcmCrityColDetailDTO = rcmCrityColDetailForm.getRcmCrityColDetailDTO();
    	rcmCrityColDetailService.updateDetail(rcmCrityCommonDTO, rcmCrityColDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}