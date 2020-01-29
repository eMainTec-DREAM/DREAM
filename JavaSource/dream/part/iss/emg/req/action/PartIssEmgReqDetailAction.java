package dream.part.iss.emg.req.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.part.iss.emg.req.form.PartIssEmgReqDetailForm;
import dream.part.iss.emg.req.service.PartIssEmgReqDetailService;

/**
 * 출고요청 - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partIssEmgReqDetail" name="partIssEmgReqDetailForm"
 *                input="/dream/part/iss/emg/req/partIssEmgReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssEmgReqDetail" path="/dream/part/iss/emg/req/partIssEmgReqDetail.jsp"
 *                        redirect="false"
 */
public class PartIssEmgReqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		    = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		    = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		    = 6003;
    /** 상태업데이트 */
    public static final int REQ_STATUS_UPDATE       = 1004;
    /** 출고처리 */
    public static final int PTISSEMG_ISSUE          = 1005;
    /** 출고취소 */
    public static final int PTISSEMG_ISSUE_CANCEL   = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssEmgReqDetailForm partIssEmgReqDetailForm = (PartIssEmgReqDetailForm) form;

        super.updateAudit(partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO().getAuditKey()==""?partIssEmgReqDetailForm.getPartIssEmgReqCommonDTO().getAuditKey():partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partIssEmgReqDetailForm.getStrutsAction())
        {
            case PartIssEmgReqDetailAction.DETAIL_INIT:
                this.findDetail(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("partIssEmgReqDetail");
                break;
            case PartIssEmgReqDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartIssEmgReqDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartIssEmgReqDetailAction.REQ_STATUS_UPDATE:
                updateStatus(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssEmgReqDetailAction.PTISSEMG_ISSUE:
                issueParts(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssEmgReqDetailAction.PTISSEMG_ISSUE_CANCEL:
                issueCancelParts(request, response, partIssEmgReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("partIssEmgReqDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param partIssEmgReqDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception 
    {   
    	PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService");
    	
    	PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = partIssEmgReqDetailForm.getPartIssEmgReqCommonDTO(); 
    	
    	User user = getUser(request);
    	
    	PartIssEmgReqDetailDTO partIssEmgReqDetailDTO = partIssEmgReqDetailService.findIssReqDetail(partIssEmgReqCommonDTO, user);
    	partIssEmgReqDetailForm.setPartIssEmgReqDetailDTO(partIssEmgReqDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param partIssEmgReqDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception
    {
    	PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService");
    	PartIssEmgReqDetailDTO  partIssEmgReqDetailDTO = partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	partIssEmgReqDetailService.insertIssReqDetail(partIssEmgReqDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param partIssEmgReqDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception
    {
    	PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService");
    	PartIssEmgReqDetailDTO  partIssEmgReqDetailDTO = partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO();
    	
    	User user = getUser(request);
    	
    	partIssEmgReqDetailService.updateIssReqDetail(partIssEmgReqDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * update status
     * @param reqWorkDetailForm
     * @param request
     */
    private void updateStatus(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception
    {
        PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService");
        PartIssEmgReqDetailDTO  partIssEmgReqDetailDTO = partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO();
        
        partIssEmgReqDetailService.updateStatus(partIssEmgReqDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    
    private void issueParts(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception
    {
        PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService",request);
        
        PartIssEmgReqDetailDTO  partIssEmgReqDetailDTO = partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO();
        
        partIssEmgReqDetailDTO.setEnterBy(getUser(request).getUserId());
        partIssEmgReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String[] result = partIssEmgReqDetailService.issueParts(partIssEmgReqDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }
    
    private void issueCancelParts(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqDetailForm partIssEmgReqDetailForm) throws Exception
    {
        PartIssEmgReqDetailService partIssEmgReqDetailService = (PartIssEmgReqDetailService)getBean("partIssEmgReqDetailService",request);
        
        PartIssEmgReqDetailDTO  partIssEmgReqDetailDTO = partIssEmgReqDetailForm.getPartIssEmgReqDetailDTO();
        
        partIssEmgReqDetailDTO.setEnterBy(getUser(request).getUserId());
        partIssEmgReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String[] result = partIssEmgReqDetailService.issueCancelParts(partIssEmgReqDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }

}