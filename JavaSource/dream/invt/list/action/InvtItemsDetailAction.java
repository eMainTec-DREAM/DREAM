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
import dream.invt.list.dto.InvtItemsDetailDTO;
import dream.invt.list.form.InvtItemsDetailForm;
import dream.invt.list.service.InvtItemsDetailService;

/**
 * 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/invtItemsDetail" name="invtItemsDetailForm"
 *                input="/dream/invt/list/invtItemsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtItemsDetail" path="/dream/invt/list/invtItemsDetail.jsp"
 *                        redirect="false"
 */
public class InvtItemsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int DETAIL_INPUT 		= 5003;
    /** 복사 */
    public static final int DETAIL_COPY 		= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtItemsDetailForm invtItemsDetailForm = (InvtItemsDetailForm) form;
        
        super.updateAudit(invtItemsDetailForm.getInvtItemsDetailDTO().getAuditKey()==""?invtItemsDetailForm.getInvtCommonDTO().getAuditKey():invtItemsDetailForm.getInvtItemsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtItemsDetailForm.getStrutsAction())
        {
            case InvtItemsDetailAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, invtItemsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case InvtItemsDetailAction.DETAIL_UPDATE:
            	updateDetail(invtItemsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtItemsDetailAction.DETAIL_INPUT:
            	insertDetail(invtItemsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtItemsDetailAction.DETAIL_COPY:
            	copyDetail(invtItemsDetailForm, request, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	/**
     * 구매항목 - 상세 조회
     * @author kim2107
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param invtItemsDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtItemsDetailForm invtItemsDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	InvtItemsDetailService invtItemsDetailService = (InvtItemsDetailService)getBean("invtItemsDetailService",request);
    	InvtCommonDTO invtCommonDTO = invtItemsDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        InvtItemsDetailDTO invtItemsDetailDTO = invtItemsDetailService.findDetail(invtCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        invtItemsDetailForm.setInvtItemsDetailDTO(invtItemsDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailForm
     * @param request
     */
    private void updateDetail(InvtItemsDetailForm invtItemsDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtItemsDetailService invtItemsDetailService = (InvtItemsDetailService) getBean("invtItemsDetailService",request);
        
        InvtItemsDetailDTO invtItemsDetailDTO = invtItemsDetailForm.getInvtItemsDetailDTO();
        InvtCommonDTO invtCommonDTO = invtItemsDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        
        invtItemsDetailService.updateDetail(invtItemsDetailDTO,invtCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailForm
     * @param request
     */
    private void insertDetail(InvtItemsDetailForm invtItemsDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtItemsDetailService invtItemsDetailService = (InvtItemsDetailService) getBean("invtItemsDetailService",request);
        
        InvtItemsDetailDTO invtItemsDetailDTO = invtItemsDetailForm.getInvtItemsDetailDTO();
        
        InvtCommonDTO invtCommonDTO = invtItemsDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        
        invtItemsDetailService.insertDetail(invtItemsDetailDTO, invtCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(InvtItemsDetailForm invtItemsDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	InvtItemsDetailService invtItemsDetailService = (InvtItemsDetailService) getBean("invtItemsDetailService",request);
    	InvtItemsDetailDTO invtItemsDetailDTO = invtItemsDetailForm.getInvtItemsDetailDTO();
    	InvtCommonDTO invtCommonDTO = invtItemsDetailForm.getInvtCommonDTO();
    	
    	String oldInvtId = invtCommonDTO.getInvtlistId();
    	String newInvtId = invtCommonDTO.getInvtlistId();
    	String oldKeyId   = invtItemsDetailDTO.getOldInvtItemsId();
    	String newKeyId   = invtItemsDetailDTO.getInvtItemsId();
    	
    	User user = getUser(request);
    	String result = invtItemsDetailService.copyDetail(oldInvtId, newInvtId, oldKeyId, newKeyId, user);
    	setAjaxStatus(request, result);
    }
}