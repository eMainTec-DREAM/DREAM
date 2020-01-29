package dream.rcm.ffail.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.form.RcmFfailItemDetailForm;
import dream.rcm.ffail.service.RcmFfailItemDetailService;

/**
 * 응답 상세
 * @author  kim21017
 * @version $Id: RcmFfailItemDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFfailItemDetail" name="rcmFfailItemDetailForm"
 *                input="/dream/rcm/ffail/rcmFfailItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFfailItemDetail" path="/dream/rcm/ffail/rcmFfailItemDetail.jsp"
 *                        redirect="false"
 */
public class RcmFfailItemDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFfailItemDetailForm rcmFfailItemDetailForm = (RcmFfailItemDetailForm) form;
        
        super.updateAudit(rcmFfailItemDetailForm.getRcmFfailItemDetailDTO().getAuditKey()==""?rcmFfailItemDetailForm.getRcmFfailItemListDTO().getAuditKey():rcmFfailItemDetailForm.getRcmFfailItemDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFfailItemDetailForm.getStrutsAction())
        {
            case RcmFfailItemDetailAction.QNA_ANS_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmFfailItemDetailForm);
                returnActionForward = mapping.findForward("rcmFfailItemDetail");
                break;
            case RcmFfailItemDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(rcmFfailItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFfailItemDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(rcmFfailItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFfailItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: RcmFfailItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFfailItemDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFfailItemDetailForm rcmFfailItemDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmFfailItemDetailService rcmFfailItemDetailService = (RcmFfailItemDetailService)getBean("rcmFfailItemDetailService");
    	RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailItemDetailForm.getRcmFfailCommonDTO();
    	RcmFfailItemListDTO rcmFfailItemListDTO = rcmFfailItemDetailForm.getRcmFfailItemListDTO();
    	rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        RcmFfailItemDetailDTO rcmFfailItemDetailDTO = rcmFfailItemDetailService.findDetail(rcmFfailItemListDTO,rcmFfailCommonDTO);
        
        // 조회된 상세  셋팅한다.
        rcmFfailItemDetailForm.setRcmFfailItemDetailDTO(rcmFfailItemDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFfailItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailForm
     * @param request
     */
    private void updateDetail(RcmFfailItemDetailForm rcmFfailItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFfailItemDetailService rcmFfailItemDetailService = (RcmFfailItemDetailService) getBean("rcmFfailItemDetailService");
        
        RcmFfailItemDetailDTO rcmFfailItemDetailDTO = rcmFfailItemDetailForm.getRcmFfailItemDetailDTO();
        RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailItemDetailForm.getRcmFfailCommonDTO();
    	rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFfailItemDetailService.updateDetail(rcmFfailItemDetailDTO,rcmFfailCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFfailItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailForm
     * @param request
     */
    private void insertDetail(RcmFfailItemDetailForm rcmFfailItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFfailItemDetailService rcmFfailItemDetailService = (RcmFfailItemDetailService) getBean("rcmFfailItemDetailService");
        
        RcmFfailItemDetailDTO rcmFfailItemDetailDTO = rcmFfailItemDetailForm.getRcmFfailItemDetailDTO();
        
        RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailItemDetailForm.getRcmFfailCommonDTO();
    	rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFfailItemDetailService.insertDetail(rcmFfailItemDetailDTO, rcmFfailCommonDTO);
        
        setAjaxStatus(request);
    }
}