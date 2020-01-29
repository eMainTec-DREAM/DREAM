package dream.invt.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;
import dream.invt.list.form.InvtPhaseDetailForm;
import dream.invt.list.service.InvtPhaseDetailService;

/**
 * 상세
 * @author  kim21017
 * @version $Id: InvtPhaseDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/invtPhaseDetail" name="invtPhaseDetailForm"
 *                input="/dream/invt/list/invtPhaseDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPhaseDetail" path="/dream/invt/list/invtPhaseDetail.jsp"
 *                        redirect="false"
 */
public class InvtPhaseDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_PHASE_DETAIL_INIT 			= 8001;
    /** 수정 */
    public static final int INVT_PHASE_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int INVT_PHASE_DETAIL_INPUT 		= 5003;
    /** 복사 */
    public static final int DETAIL_COPY 					= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPhaseDetailForm invtPhaseDetailForm = (InvtPhaseDetailForm) form;
        
        super.updateAudit(invtPhaseDetailForm.getInvtPhaseDetailDTO().getAuditKey()==""?invtPhaseDetailForm.getInvtCommonDTO().getAuditKey():invtPhaseDetailForm.getInvtPhaseDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPhaseDetailForm.getStrutsAction())
        {
            case InvtPhaseDetailAction.INVT_PHASE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, invtPhaseDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case InvtPhaseDetailAction.INVT_PHASE_DETAIL_UPDATE:
            	updateDetail(invtPhaseDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPhaseDetailAction.INVT_PHASE_DETAIL_INPUT:
            	insertDetail(invtPhaseDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPhaseDetailAction.DETAIL_COPY:
            	copyDetail(invtPhaseDetailForm, request, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	/**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: InvtPhaseDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPhaseDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtPhaseDetailForm invtPhaseDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	InvtPhaseDetailService invtPhaseDetailService = (InvtPhaseDetailService)getBean("invtPhaseDetailService");
    	InvtCommonDTO invtCommonDTO = invtPhaseDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        InvtPhaseDetailDTO invtPhaseDetailDTO = invtPhaseDetailService.findDetail(invtCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        invtPhaseDetailForm.setInvtPhaseDetailDTO(invtPhaseDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: InvtPhaseDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailForm
     * @param request
     */
    private void updateDetail(InvtPhaseDetailForm invtPhaseDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPhaseDetailService invtPhaseDetailService = (InvtPhaseDetailService) getBean("invtPhaseDetailService");
        
        InvtPhaseDetailDTO invtPhaseDetailDTO = invtPhaseDetailForm.getInvtPhaseDetailDTO();
        InvtCommonDTO invtCommonDTO = invtPhaseDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        
        invtPhaseDetailService.updateDetail(invtPhaseDetailDTO,invtCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: InvtPhaseDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailForm
     * @param request
     */
    private void insertDetail(InvtPhaseDetailForm invtPhaseDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPhaseDetailService invtPhaseDetailService = (InvtPhaseDetailService) getBean("invtPhaseDetailService");
        
        InvtPhaseDetailDTO invtPhaseDetailDTO = invtPhaseDetailForm.getInvtPhaseDetailDTO();
        
        InvtCommonDTO invtCommonDTO = invtPhaseDetailForm.getInvtCommonDTO();
        
        invtPhaseDetailService.insertDetail(invtPhaseDetailDTO, invtCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(InvtPhaseDetailForm invtPhaseDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	InvtPhaseDetailService invtPhaseDetailService = (InvtPhaseDetailService) getBean("invtPhaseDetailService");
        InvtPhaseDetailDTO invtPhaseDetailDTO = invtPhaseDetailForm.getInvtPhaseDetailDTO();
        InvtCommonDTO invtCommonDTO = invtPhaseDetailForm.getInvtCommonDTO();
        
        String oldInvtId = invtCommonDTO.getInvtlistId();
        String newInvtId = invtCommonDTO.getInvtlistId();
        String oldKeyId = invtPhaseDetailDTO.getInvtphaseId();
        String newKeyId = "";
        
        User user = getUser(request);
        
        String result = invtPhaseDetailService.copyDetail(oldInvtId, newInvtId, oldKeyId, newKeyId, user);
    	setAjaxStatus(request, result);
    }
}