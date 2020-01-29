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
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.list.form.InvtDetailForm;
import dream.invt.list.service.InvtDetailService;
import dream.invt.list.service.InvtItemsDetailService;

/**
 * 상세 action
 * 
 * @author kim2107
 * @version $Id: InvtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/invtDetail" name="invtDetailForm"
 *                input="/dream/invt/list/invtDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtDetail" path="/dream/invt/list/invtDetail.jsp"
 *                        redirect="false"
 */
public class InvtDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int INVT_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int INVT_DETAIL_INPUT 		= 5003;
    /** 복사 */
    public static final int DETAIL_COPY 			= 5004;
    /** 완료상태 취소 */
    public static final int INVT_STATUS_CANCEL		= 6003;
    /** 완료  */
    public static final int INVT_CONFIRM            = 6004;
    /** 투자결과 상태 확인 */
    public static final int CHK_INVT_STATUS         = 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtDetailForm invtDetailForm = (InvtDetailForm) form;
        
        super.updateAudit(invtDetailForm.getInvtDetailDTO().getAuditKey()==""?invtDetailForm.getInvtCommonDTO().getAuditKey():invtDetailForm.getInvtDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtDetailForm.getStrutsAction())
        {
            case InvtDetailAction.INVT_DETAIL_INIT:
                this.findDetail(request, invtDetailForm);
                returnActionForward = mapping.findForward("invtDetail");
                break;
            case InvtDetailAction.INVT_DETAIL_UPDATE:
            	updateDetail(invtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtDetailAction.INVT_DETAIL_INPUT:
            	insertDetail(invtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtDetailAction.DETAIL_COPY:
            	copyDetail(invtDetailForm, request,response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtDetailAction.INVT_STATUS_CANCEL:
            	cancelStatus(invtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtDetailAction.INVT_CONFIRM:
                confirmStatus(invtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case InvtDetailAction.CHK_INVT_STATUS:
                checkInvtStatus(request, invtDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void confirmStatus(InvtDetailForm invtDetailForm,HttpServletRequest request) throws Exception
    {
        InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService", request);
        
        InvtDetailDTO invtDetailDTO = invtDetailForm.getInvtDetailDTO();
        invtDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtDetailService.confirmStatus(invtDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: InvtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtDetailForm invtDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	InvtDetailService invtDetailService = (InvtDetailService)getBean("invtDetailService", request);
    	InvtCommonDTO invtCommonDTO = invtDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        InvtDetailDTO invtDetailDTO = invtDetailService.findDetail(invtCommonDTO, getUser(request));

        // 조회된 상세  셋팅한다.
        invtDetailForm.setInvtDetailDTO(invtDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: InvtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailForm
     * @param request
     */
    private void insertDetail(InvtDetailForm invtDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService", request);
        
        InvtDetailDTO invtDetailDTO = invtDetailForm.getInvtDetailDTO();
        invtDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtDetailService.insertDetail(invtDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: InvtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailForm
     * @param request
     */
    private void updateDetail(InvtDetailForm invtDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService", request);
        
        InvtDetailDTO invtDetailDTO = invtDetailForm.getInvtDetailDTO();
        invtDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtDetailService.updateDetail(invtDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(InvtDetailForm invtDetailForm, HttpServletRequest request, HttpServletResponse response)throws Exception
    {
    	InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService", request);
    	
    	InvtItemsDetailService invtItemsDetailService = (InvtItemsDetailService) getBean("invtItemsDetailService", request);
    	
    	InvtCommonDTO invtCommonDTO = invtDetailForm.getInvtCommonDTO();
    	InvtDetailDTO invtDetailDTO = invtDetailForm.getInvtDetailDTO();
    	
    	User user = getUser(request);
    	
    	String result = invtDetailService.copyDetail(invtCommonDTO, invtDetailDTO, user);
    	
    	invtItemsDetailService.copyDetail(invtDetailDTO.getOldInvtlistId(), invtDetailDTO.getInvtlistId(), "", "", user);
    			
    	setAjaxStatus(request, result);
    }
    
    /**
     * Cancel Invt Status
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param invtDetailForm
     * @param request
     */
    private void cancelStatus(InvtDetailForm invtDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService");
        
        InvtDetailDTO invtDetailDTO = invtDetailForm.getInvtDetailDTO();
        invtDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtDetailService.cancelStatus(invtDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void checkInvtStatus(HttpServletRequest request, InvtDetailForm invtDetailForm) throws Exception
    {
        InvtDetailService invtDetailService = (InvtDetailService) getBean("invtDetailService", request);
        InvtCommonDTO invtCommonDTO = invtDetailForm.getInvtCommonDTO();
        
        String status = invtDetailService.changeStatus(invtCommonDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }
}