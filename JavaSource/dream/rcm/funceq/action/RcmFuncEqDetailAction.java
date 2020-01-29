package dream.rcm.funceq.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;
import dream.rcm.funceq.form.RcmFuncEqDetailForm;
import dream.rcm.funceq.service.RcmFuncEqDetailService;

/**
 * 질의 - 상세 action
 * 
 * @author kim2107
 * @version $Id: RcmFuncEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmFuncEqDetail" name="rcmFuncEqDetailForm"
 *                input="/dream/rcm/funceq/rcmFuncEqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFuncEqDetail" path="/dream/rcm/funceq/rcmFuncEqDetail.jsp"
 *                        redirect="false"
 */
public class RcmFuncEqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFuncEqDetailForm rcmFuncEqDetailForm = (RcmFuncEqDetailForm) form;
        
        super.updateAudit(rcmFuncEqDetailForm.getRcmFuncEqDetailDTO().getAuditKey()==""?rcmFuncEqDetailForm.getRcmFuncEqCommonDTO().getAuditKey():rcmFuncEqDetailForm.getRcmFuncEqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFuncEqDetailForm.getStrutsAction())
        {
            case RcmFuncEqDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, rcmFuncEqDetailForm);
                returnActionForward = mapping.findForward("rcmFuncEqDetail");
                break;
            case RcmFuncEqDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(rcmFuncEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFuncEqDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(rcmFuncEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFuncEqDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: RcmFuncEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFuncEqDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFuncEqDetailForm rcmFuncEqDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmFuncEqDetailService rcmFuncEqDetailService = (RcmFuncEqDetailService)getBean("rcmFuncEqDetailService");
    	RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqDetailForm.getRcmFuncEqCommonDTO();
    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        RcmFuncEqDetailDTO rcmFuncEqDetailDTO = rcmFuncEqDetailService.findDetail(rcmFuncEqCommonDTO);
        
        // 조회된 상세  셋팅한다.
        rcmFuncEqDetailForm.setRcmFuncEqDetailDTO(rcmFuncEqDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFuncEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailForm
     * @param request
     */
    private void insertDetail(RcmFuncEqDetailForm rcmFuncEqDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFuncEqDetailService rcmFuncEqDetailService = (RcmFuncEqDetailService) getBean("rcmFuncEqDetailService");
        
        RcmFuncEqDetailDTO rcmFuncEqDetailDTO = rcmFuncEqDetailForm.getRcmFuncEqDetailDTO();
        rcmFuncEqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFuncEqDetailService.insertDetail(rcmFuncEqDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFuncEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailForm
     * @param request
     */
    private void updateDetail(RcmFuncEqDetailForm rcmFuncEqDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFuncEqDetailService rcmFuncEqDetailService = (RcmFuncEqDetailService) getBean("rcmFuncEqDetailService");
        
        RcmFuncEqDetailDTO rcmFuncEqDetailDTO = rcmFuncEqDetailForm.getRcmFuncEqDetailDTO();
        rcmFuncEqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFuncEqDetailService.updateDetail(rcmFuncEqDetailDTO);
        
        setAjaxStatus(request);
    }

}