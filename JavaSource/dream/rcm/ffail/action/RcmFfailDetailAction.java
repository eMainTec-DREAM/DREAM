package dream.rcm.ffail.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;
import dream.rcm.ffail.form.RcmFfailDetailForm;
import dream.rcm.ffail.service.RcmFfailDetailService;

/**
 * ���� - �� action
 * 
 * @author kim2107
 * @version $Id: RcmFfailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmFfailDetail" name="rcmFfailDetailForm"
 *                input="/dream/rcm/ffail/rcmFfailDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFfailDetail" path="/dream/rcm/ffail/rcmFfailDetail.jsp"
 *                        redirect="false"
 */
public class RcmFfailDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    /** ��û�Ϸ� */
    public static final int QNA_DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFfailDetailForm rcmFfailDetailForm = (RcmFfailDetailForm) form;
        
        super.updateAudit(rcmFfailDetailForm.getRcmFfailDetailDTO().getAuditKey()==""?rcmFfailDetailForm.getRcmFfailCommonDTO().getAuditKey():rcmFfailDetailForm.getRcmFfailDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFfailDetailForm.getStrutsAction())
        {
            case RcmFfailDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, rcmFfailDetailForm);
                returnActionForward = mapping.findForward("rcmFfailDetail");
                break;
            case RcmFfailDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(rcmFfailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFfailDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(rcmFfailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFfailDetailAction.QNA_DETAIL_CONFIRM:
            	confirmDetail(rcmFfailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFfailDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author kim2107
     * @version $Id: RcmFfailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFfailDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFfailDetailForm rcmFfailDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmFfailDetailService rcmFfailDetailService = (RcmFfailDetailService)getBean("rcmFfailDetailService");
    	RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailDetailForm.getRcmFfailCommonDTO();
    	rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmFfailDetailDTO rcmFfailDetailDTO = rcmFfailDetailService.findDetail(rcmFfailCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmFfailDetailForm.setRcmFfailDetailDTO(rcmFfailDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFfailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailForm
     * @param request
     */
    private void insertDetail(RcmFfailDetailForm rcmFfailDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFfailDetailService rcmFfailDetailService = (RcmFfailDetailService) getBean("rcmFfailDetailService");
        
        RcmFfailDetailDTO rcmFfailDetailDTO = rcmFfailDetailForm.getRcmFfailDetailDTO();
        rcmFfailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFfailDetailService.insertDetail(rcmFfailDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFfailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailForm
     * @param request
     */
    private void updateDetail(RcmFfailDetailForm rcmFfailDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFfailDetailService rcmFfailDetailService = (RcmFfailDetailService) getBean("rcmFfailDetailService");
        
        RcmFfailDetailDTO rcmFfailDetailDTO = rcmFfailDetailForm.getRcmFfailDetailDTO();
        rcmFfailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFfailDetailService.updateDetail(rcmFfailDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: RcmFfailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailForm
     * @param request
     */
    private void confirmDetail(RcmFfailDetailForm rcmFfailDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFfailDetailService rcmFfailDetailService = (RcmFfailDetailService) getBean("rcmFfailDetailService");
        
        RcmFfailDetailDTO rcmFfailDetailDTO = rcmFfailDetailForm.getRcmFfailDetailDTO();
        rcmFfailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFfailDetailService.confirmDetail(rcmFfailDetailDTO);
        
        setAjaxStatus(request);
    }
}