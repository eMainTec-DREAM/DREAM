package dream.invt.prc.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.form.InvtPrcTpItemDetailForm;
import dream.invt.prc.service.InvtPrcTpItemDetailService;

/**
 * 구매절차 Item 상세
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailAction.java,v 1.0 2015/12/04 09:09:30 hyosung Exp $
 * @since   1.0
 * @struts:action path="/invtPrcTpItemDetail" name="invtPrcTpItemDetailForm"
 *                input="/dream/invt/prc/invtPrcTpItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcTpItemDetail" path="/dream/invt/prc/invtPrcTpItemDetail.jsp"
 *                        redirect="false"
 */
public class InvtPrcTpItemDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int INVT_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int INVT_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcTpItemDetailForm invtPrcTpItemDetailForm = (InvtPrcTpItemDetailForm) form;
        
        super.updateAudit(invtPrcTpItemDetailForm.getInvtPrcTpItemDetailDTO().getAuditKey()==""?invtPrcTpItemDetailForm.getInvtPrcTpItemListDTO().getAuditKey():invtPrcTpItemDetailForm.getInvtPrcTpItemDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPrcTpItemDetailForm.getStrutsAction())
        {
            case InvtPrcTpItemDetailAction.INVT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, invtPrcTpItemDetailForm);
                returnActionForward = mapping.findForward("invtPrcTpItemDetail");
                break;
            case InvtPrcTpItemDetailAction.INVT_DETAIL_UPDATE:
            	updateDetail(invtPrcTpItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcTpItemDetailAction.INVT_DETAIL_INPUT:
            	insertDetail(invtPrcTpItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("invtPrcTpItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구매절차 상세 - 상세 조회
     * @author hyosung
     * @version $Id: InvtPrcTpItemDetailAction.java,v 1.2 2015/12/02 01:21:30 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcTpItemDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtPrcTpItemDetailForm invtPrcTpItemDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	InvtPrcTpItemDetailService invtPrcTpItemDetailService = (InvtPrcTpItemDetailService)getBean("invtPrcTpItemDetailService");
    	InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpItemDetailForm.getInvtPrcTpCommonDTO();
    	InvtPrcTpItemListDTO invtPrcTpItemListDTO = invtPrcTpItemDetailForm.getInvtPrcTpItemListDTO();
    	invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
    	// 조회된 상세 부분
        InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO = invtPrcTpItemDetailService.findDetail(invtPrcTpItemListDTO,invtPrcTpCommonDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        invtPrcTpItemDetailForm.setInvtPrcTpItemDetailDTO(invtPrcTpItemDetailDTO);
    }
    /**
     * detail update
     * @author  hyosung
     * @version $Id: InvtPrcTpItemDetailAction.java,v 1.2 2015/12/02 01:21:30 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailForm
     * @param request
     */
    private void updateDetail(InvtPrcTpItemDetailForm invtPrcTpItemDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcTpItemDetailService invtPrcTpItemDetailService = (InvtPrcTpItemDetailService) getBean("invtPrcTpItemDetailService");
        
        InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO = invtPrcTpItemDetailForm.getInvtPrcTpItemDetailDTO();
        InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpItemDetailForm.getInvtPrcTpCommonDTO();
    	invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
    	invtPrcTpItemDetailDTO.setInvtPrcPhId(invtPrcTpItemDetailForm.getInvtPrcTpItemListDTO().getInvtPrcPhId());
        invtPrcTpItemDetailService.updateDetail(invtPrcTpItemDetailDTO,invtPrcTpCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  hyosung
     * @version $Id: InvtPrcTpItemDetailAction.java,v 1.2 2015/12/02 01:21:30 hyosung Exp $
     * @since   1.0
     * 
     * @param invtPrcTpItemDetailForm
     * @param request
     */
    private void insertDetail(InvtPrcTpItemDetailForm invtPrcTpItemDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcTpItemDetailService invtPrcTpItemDetailService = (InvtPrcTpItemDetailService) getBean("invtPrcTpItemDetailService");
        InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO = invtPrcTpItemDetailForm.getInvtPrcTpItemDetailDTO();
        InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpItemDetailForm.getInvtPrcTpCommonDTO();
        invtPrcTpItemDetailDTO.setInvtPrcPhId(invtPrcTpItemDetailForm.getInvtPrcTpItemListDTO().getInvtPrcPhId());
        invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
        invtPrcTpItemDetailService.insertDetail(invtPrcTpItemDetailDTO, invtPrcTpCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}