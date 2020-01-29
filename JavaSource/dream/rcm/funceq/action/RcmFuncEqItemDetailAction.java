package dream.rcm.funceq.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.form.RcmFuncEqItemDetailForm;
import dream.rcm.funceq.service.RcmFuncEqItemDetailService;

/**
 * ���� ��
 * @author  kim21017
 * @version $Id: RcmFuncEqItemDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFuncEqItemDetail" name="rcmFuncEqItemDetailForm"
 *                input="/dream/rcm/funceq/rcmFuncEqItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFuncEqItemDetail" path="/dream/rcm/funceq/rcmFuncEqItemDetail.jsp"
 *                        redirect="false"
 */
public class RcmFuncEqItemDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFuncEqItemDetailForm rcmFuncEqItemDetailForm = (RcmFuncEqItemDetailForm) form;
        
        super.updateAudit(rcmFuncEqItemDetailForm.getRcmFuncEqItemDetailDTO().getAuditKey()==""?rcmFuncEqItemDetailForm.getRcmFuncEqItemListDTO().getAuditKey():rcmFuncEqItemDetailForm.getRcmFuncEqItemDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFuncEqItemDetailForm.getStrutsAction())
        {
            case RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, rcmFuncEqItemDetailForm);
                returnActionForward = mapping.findForward("rcmFuncEqItemDetail");
                break;
            case RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(rcmFuncEqItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(rcmFuncEqItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFuncEqItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: RcmFuncEqItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFuncEqItemDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFuncEqItemDetailForm rcmFuncEqItemDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmFuncEqItemDetailService rcmFuncEqItemDetailService = (RcmFuncEqItemDetailService)getBean("rcmFuncEqItemDetailService");
    	RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqItemDetailForm.getRcmFuncEqCommonDTO();
    	RcmFuncEqItemListDTO rcmFuncEqItemListDTO = rcmFuncEqItemDetailForm.getRcmFuncEqItemListDTO();
    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO = rcmFuncEqItemDetailService.findDetail(rcmFuncEqItemListDTO,rcmFuncEqCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmFuncEqItemDetailForm.setRcmFuncEqItemDetailDTO(rcmFuncEqItemDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFuncEqItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailForm
     * @param request
     */
    private void updateDetail(RcmFuncEqItemDetailForm rcmFuncEqItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFuncEqItemDetailService rcmFuncEqItemDetailService = (RcmFuncEqItemDetailService) getBean("rcmFuncEqItemDetailService");
        
        RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO = rcmFuncEqItemDetailForm.getRcmFuncEqItemDetailDTO();
        RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqItemDetailForm.getRcmFuncEqCommonDTO();
    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFuncEqItemDetailService.updateDetail(rcmFuncEqItemDetailDTO,rcmFuncEqCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFuncEqItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailForm
     * @param request
     */
    private void insertDetail(RcmFuncEqItemDetailForm rcmFuncEqItemDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFuncEqItemDetailService rcmFuncEqItemDetailService = (RcmFuncEqItemDetailService) getBean("rcmFuncEqItemDetailService");
        
        RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO = rcmFuncEqItemDetailForm.getRcmFuncEqItemDetailDTO();
        
        RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqItemDetailForm.getRcmFuncEqCommonDTO();
    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFuncEqItemDetailService.insertDetail(rcmFuncEqItemDetailDTO, rcmFuncEqCommonDTO);
        
        setAjaxStatus(request);
    }
}