package dream.rcm.taskmap.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.form.RcmTaskMapItemDetailForm;
import dream.rcm.taskmap.service.RcmTaskMapItemDetailService;

/**
 * 응답 상세
 * @author  kim21017
 * @version $Id: RcmTaskMapItemDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmTaskMapItemDetail" name="rcmTaskMapItemDetailForm"
 *                input="/dream/rcm/taskmap/rcmTaskMapItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmTaskMapItemDetail" path="/dream/rcm/taskmap/rcmTaskMapItemDetail.jsp"
 *                        redirect="false"
 */
public class RcmTaskMapItemDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    
    public static final int RCM_NEXT_NO 				= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmTaskMapItemDetailForm rcmTaskMapItemDetailForm = (RcmTaskMapItemDetailForm) form;
        
        super.updateAudit(rcmTaskMapItemDetailForm.getRcmTaskMapItemDetailDTO().getAuditKey()==""?rcmTaskMapItemDetailForm.getRcmTaskMapItemListDTO().getAuditKey():rcmTaskMapItemDetailForm.getRcmTaskMapItemDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmTaskMapItemDetailForm.getStrutsAction())
        {
            case RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmTaskMapItemDetailForm);
                returnActionForward = mapping.findForward("rcmTaskMapItemDetail");
                break;
            case RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(rcmTaskMapItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(rcmTaskMapItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmTaskMapItemDetailAction.RCM_NEXT_NO:
            	findNextNo(rcmTaskMapItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmTaskMapItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: RcmTaskMapItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmTaskMapItemDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmTaskMapItemDetailForm rcmTaskMapItemDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmTaskMapItemDetailService rcmTaskMapItemDetailService = (RcmTaskMapItemDetailService)getBean("rcmTaskMapItemDetailService");
    	RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapItemDetailForm.getRcmTaskMapCommonDTO();
    	RcmTaskMapItemListDTO rcmTaskMapItemListDTO = rcmTaskMapItemDetailForm.getRcmTaskMapItemListDTO();
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO = rcmTaskMapItemDetailService.findDetail(rcmTaskMapItemListDTO,rcmTaskMapCommonDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        rcmTaskMapItemDetailForm.setRcmTaskMapItemDetailDTO(rcmTaskMapItemDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmTaskMapItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailForm
     * @param request
     */
    private void updateDetail(RcmTaskMapItemDetailForm rcmTaskMapItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmTaskMapItemDetailService rcmTaskMapItemDetailService = (RcmTaskMapItemDetailService) getBean("rcmTaskMapItemDetailService");
        
        RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO = rcmTaskMapItemDetailForm.getRcmTaskMapItemDetailDTO();
        RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapItemDetailForm.getRcmTaskMapCommonDTO();
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmTaskMapItemDetailService.updateDetail(rcmTaskMapItemDetailDTO,rcmTaskMapCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmTaskMapItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailForm
     * @param request
     */
    private void insertDetail(RcmTaskMapItemDetailForm rcmTaskMapItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmTaskMapItemDetailService rcmTaskMapItemDetailService = (RcmTaskMapItemDetailService) getBean("rcmTaskMapItemDetailService");
        
        RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO = rcmTaskMapItemDetailForm.getRcmTaskMapItemDetailDTO();
        
        RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapItemDetailForm.getRcmTaskMapCommonDTO();
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmTaskMapItemDetailService.insertDetail(rcmTaskMapItemDetailDTO, rcmTaskMapCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * valid PtStock
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtStckDetailForm
     * @param request
     */
    private void findNextNo(RcmTaskMapItemDetailForm rcmTaskMapItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmTaskMapItemDetailService rcmTaskMapItemDetailService = (RcmTaskMapItemDetailService) getBean("rcmTaskMapItemDetailService");
        
    	RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapItemDetailForm.getRcmTaskMapCommonDTO();
        
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        String pmTaskmapNo = rcmTaskMapItemDetailService.taskNo(rcmTaskMapCommonDTO);
        
        setAjaxDesc(request, pmTaskmapNo);
    }
}
