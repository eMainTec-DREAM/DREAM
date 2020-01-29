package dream.part.iss.emg.req.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;
import dream.part.iss.emg.req.form.PartIssEmgReqPartsDetailForm;
import dream.part.iss.emg.req.service.PartIssEmgReqPartsDetailService;

/**
 * 긴급출고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
  * @struts:action path="/partIssEmgReqPartsDetail" name="partIssEmgReqPartsDetailForm"
 *                input="/dream/part/iss/emg/req/partIssEmgReqPartsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssEmgReqPartsDetail" path="/dream/part/iss/emg/req/partIssEmgReqPartsDetail.jsp"
 *                        redirect="false"
 */
public class PartIssEmgReqPartsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTISSEMG_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTISSEMG_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTISSEMG_DETAIL_UPDATE       = 6003;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssEmgReqPartsDetailForm partIssEmgReqPartsDetailForm = (PartIssEmgReqPartsDetailForm) form;

        super.updateAudit(partIssEmgReqPartsDetailForm.getPartIssEmgReqPartsDetailDTO().getAuditKey()==""?partIssEmgReqPartsDetailForm.getPartIssEmgReqCommonDTO().getAuditKey():partIssEmgReqPartsDetailForm.getPartIssEmgReqPartsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        

        switch (partIssEmgReqPartsDetailForm.getStrutsAction())
        {
            case PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, partIssEmgReqPartsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_INPUT:
            	insertDetail(partIssEmgReqPartsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_UPDATE:
            	updateDetail(partIssEmgReqPartsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    

    /**
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partIssEmgReqPartsDetailForm
     */
    private void findDetail(HttpServletRequest request, PartIssEmgReqPartsDetailForm partIssEmgReqPartsDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	PartIssEmgReqPartsDetailService partIssEmgReqPartsDetailService = (PartIssEmgReqPartsDetailService)getBean("partIssEmgReqPartsDetailService");
    	
    	PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = partIssEmgReqPartsDetailForm.getPartIssEmgReqCommonDTO();
    	
        // 조회된 상세 부분
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailService.findDetail(partIssEmgReqCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        partIssEmgReqPartsDetailForm.setPartIssEmgReqPartsDetailDTO(partIssEmgReqPartsDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssEmgReqPartsDetailForm
     * @param request
     */
    private void insertDetail(PartIssEmgReqPartsDetailForm partIssEmgReqPartsDetailForm, HttpServletRequest request) throws Exception
    {
        PartIssEmgReqPartsDetailService partIssEmgReqPartsDetailService = (PartIssEmgReqPartsDetailService) getBean("partIssEmgReqPartsDetailService");
        
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailForm.getPartIssEmgReqPartsDetailDTO();
        
        partIssEmgReqPartsDetailService.insertDetail(partIssEmgReqPartsDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssEmgReqPartsDetailForm
     * @param request
     */
    private void updateDetail(PartIssEmgReqPartsDetailForm partIssEmgReqPartsDetailForm, HttpServletRequest request) throws Exception
    {
    	PartIssEmgReqPartsDetailService partIssEmgReqPartsDetailService = (PartIssEmgReqPartsDetailService) getBean("partIssEmgReqPartsDetailService");
        
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailForm.getPartIssEmgReqPartsDetailDTO();
        
        
        partIssEmgReqPartsDetailService.updateDetail(partIssEmgReqPartsDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}