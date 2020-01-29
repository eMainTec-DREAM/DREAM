package dream.invt.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.invt.list.form.InvtPrcDetailForm;
import dream.invt.list.service.InvtPrcDetailService;

/**
 * 상세 action
 * 
 * @author kim2107
 * @version $Id: InvtPrcDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/invtPrcDetail" name="invtPrcDetailForm"
 *                input="/dream/invt/list/invtPrcDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcDetail" path="/dream/invt/list/invtPrcDetail.jsp"
 *                        redirect="false"
 */
public class InvtPrcDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_PRC_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int INVT_PRC_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int INVT_PRC_DETAIL_INPUT 		= 5003;
    /** 절차 중복 검사 */
    public static final int INVT_PRC_DETAIL_CHECK		= 8004;
    /** 완료 */
    public static final int INVT_PRC_DETAIL_COMPLETED   = 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcDetailForm invtPrcDetailForm = (InvtPrcDetailForm) form;
        
        super.updateAudit(invtPrcDetailForm.getInvtPrcDetailDTO().getAuditKey()==""?invtPrcDetailForm.getInvtCommonDTO().getAuditKey():invtPrcDetailForm.getInvtPrcDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPrcDetailForm.getStrutsAction())
        {
            case InvtPrcDetailAction.INVT_PRC_DETAIL_INIT:
                this.findDetail(request, invtPrcDetailForm);
                returnActionForward = mapping.findForward("invtPrcDetail");
                break;
            case InvtPrcDetailAction.INVT_PRC_DETAIL_UPDATE:
            	updateDetail(invtPrcDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcDetailAction.INVT_PRC_DETAIL_INPUT:
            	insertDetail(invtPrcDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcDetailAction.INVT_PRC_DETAIL_CHECK:
            	checkPrc(invtPrcDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcDetailAction.INVT_PRC_DETAIL_COMPLETED:
                completeDetail(invtPrcDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("invtPrcDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: InvtPrcDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtPrcDetailForm invtPrcDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	InvtPrcDetailService invtPrcDetailService = (InvtPrcDetailService)getBean("invtPrcDetailService");
    	InvtCommonDTO invtCommonDTO = invtPrcDetailForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        InvtPrcDetailDTO invtPrcDetailDTO = invtPrcDetailService.findDetail(invtCommonDTO, getUser(request));

        // 조회된 상세  셋팅한다.
        invtPrcDetailForm.setInvtPrcDetailDTO(invtPrcDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: InvtPrcDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailForm
     * @param request
     */
    private void insertDetail(InvtPrcDetailForm invtPrcDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcDetailService invtPrcDetailService = (InvtPrcDetailService) getBean("invtPrcDetailService");
        
        InvtPrcDetailDTO invtPrcDetailDTO = invtPrcDetailForm.getInvtPrcDetailDTO();
        invtPrcDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcDetailService.insertDetail(invtPrcDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    private void checkPrc(InvtPrcDetailForm invtPrcDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcDetailService invtPrcDetailService = (InvtPrcDetailService) getBean("invtPrcDetailService");
    	InvtCommonDTO invtCommonDTO = invtPrcDetailForm.getInvtCommonDTO();
        InvtPrcDetailDTO invtPrcDetailDTO = invtPrcDetailForm.getInvtPrcDetailDTO();

        String isValid = invtPrcDetailService.checkPrc(invtCommonDTO, invtPrcDetailDTO, getUser(request));

    	setAjaxDesc(request, isValid);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: InvtPrcDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailForm
     * @param request
     */
    private void updateDetail(InvtPrcDetailForm invtPrcDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcDetailService invtPrcDetailService = (InvtPrcDetailService) getBean("invtPrcDetailService");
        
        InvtPrcDetailDTO invtPrcDetailDTO = invtPrcDetailForm.getInvtPrcDetailDTO();
        invtPrcDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcDetailService.updateDetail(invtPrcDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void completeDetail(InvtPrcDetailForm invtPrcDetailForm, HttpServletRequest request) throws Exception
    {
        InvtPrcDetailService invtPrcDetailService = (InvtPrcDetailService) getBean("invtPrcDetailService");
        
        InvtPrcDetailDTO invtPrcDetailDTO = invtPrcDetailForm.getInvtPrcDetailDTO();
        invtPrcDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcDetailService.completeDetail(invtPrcDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}