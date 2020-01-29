package dream.invt.prc.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;
import dream.invt.prc.form.InvtPrcTpDetailForm;
import dream.invt.prc.service.InvtPrcTpDetailService;

/**
 * �������� - �� action
 * 
 * @author kim2107
 * @version $Id: InvtPrcTpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/invtPrcTpDetail" name="invtPrcTpDetailForm"
 *                input="/dream/invt/prc/invtPrcTpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcTpDetail" path="/dream/invt/prc/invtPrcTpDetail.jsp"
 *                        redirect="false"
 */
public class InvtPrcTpDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int INVTPRC_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int INVTPRC_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int INVTPRC_DETAIL_INPUT 		= 5003;
    /** ��û�Ϸ� */
    public static final int INVTPRC_DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcTpDetailForm invtPrcTpDetailForm = (InvtPrcTpDetailForm) form;
        
        super.updateAudit(invtPrcTpDetailForm.getInvtPrcTpDetailDTO().getAuditKey()==""?invtPrcTpDetailForm.getInvtPrcTpCommonDTO().getAuditKey():invtPrcTpDetailForm.getInvtPrcTpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPrcTpDetailForm.getStrutsAction())
        {
            case InvtPrcTpDetailAction.INVTPRC_DETAIL_INIT:
                this.findDetail(request, invtPrcTpDetailForm);
                returnActionForward = mapping.findForward("invtPrcTpDetail");
                break;
            case InvtPrcTpDetailAction.INVTPRC_DETAIL_UPDATE:
            	updateDetail(invtPrcTpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcTpDetailAction.INVTPRC_DETAIL_INPUT:
            	insertDetail(invtPrcTpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcTpDetailAction.INVTPRC_DETAIL_CONFIRM:
            	confirmDetail(invtPrcTpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("invtPrcTpDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author kim2107
     * @version $Id: InvtPrcTpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcTpDetailForm
     */
    private void findDetail(HttpServletRequest request, InvtPrcTpDetailForm invtPrcTpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	InvtPrcTpDetailService invtPrcTpDetailService = (InvtPrcTpDetailService)getBean("invtPrcTpDetailService");
    	InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpDetailForm.getInvtPrcTpCommonDTO();
    	invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        InvtPrcTpDetailDTO invtPrcTpDetailDTO = invtPrcTpDetailService.findDetail(invtPrcTpCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        invtPrcTpDetailForm.setInvtPrcTpDetailDTO(invtPrcTpDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: InvtPrcTpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailForm
     * @param request
     */
    private void insertDetail(InvtPrcTpDetailForm invtPrcTpDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcTpDetailService invtPrcTpDetailService = (InvtPrcTpDetailService) getBean("invtPrcTpDetailService");
        
        InvtPrcTpDetailDTO invtPrcTpDetailDTO = invtPrcTpDetailForm.getInvtPrcTpDetailDTO();
        invtPrcTpDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcTpDetailService.insertDetail(invtPrcTpDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: InvtPrcTpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailForm
     * @param request
     */
    private void updateDetail(InvtPrcTpDetailForm invtPrcTpDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcTpDetailService invtPrcTpDetailService = (InvtPrcTpDetailService) getBean("invtPrcTpDetailService");
        
        InvtPrcTpDetailDTO invtPrcTpDetailDTO = invtPrcTpDetailForm.getInvtPrcTpDetailDTO();
        invtPrcTpDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcTpDetailService.updateDetail(invtPrcTpDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: InvtPrcTpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailForm
     * @param request
     */
    private void confirmDetail(InvtPrcTpDetailForm invtPrcTpDetailForm, HttpServletRequest request) throws Exception
    {
    	InvtPrcTpDetailService invtPrcTpDetailService = (InvtPrcTpDetailService) getBean("invtPrcTpDetailService");
        
        InvtPrcTpDetailDTO invtPrcTpDetailDTO = invtPrcTpDetailForm.getInvtPrcTpDetailDTO();
        invtPrcTpDetailDTO.setCompNo(getUser(request).getCompNo());
        
        invtPrcTpDetailService.confirmDetail(invtPrcTpDetailDTO);
        
        setAjaxStatus(request);
    }
}