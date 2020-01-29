package dream.rcm.taskmap.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;
import dream.rcm.taskmap.form.RcmTaskMapDetailForm;
import dream.rcm.taskmap.service.RcmTaskMapDetailService;

/**
 * ���� - �� action
 * 
 * @author kim2107
 * @version $Id: RcmTaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmTaskMapDetail" name="rcmTaskMapDetailForm"
 *                input="/dream/rcm/taskmap/rcmTaskMapDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmTaskMapDetail" path="/dream/rcm/taskmap/rcmTaskMapDetail.jsp"
 *                        redirect="false"
 */
public class RcmTaskMapDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmTaskMapDetailForm rcmTaskMapDetailForm = (RcmTaskMapDetailForm) form;
        
        super.updateAudit(rcmTaskMapDetailForm.getRcmTaskMapDetailDTO().getAuditKey()==""?rcmTaskMapDetailForm.getRcmTaskMapCommonDTO().getAuditKey():rcmTaskMapDetailForm.getRcmTaskMapDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmTaskMapDetailForm.getStrutsAction())
        {
            case RcmTaskMapDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, rcmTaskMapDetailForm);
                returnActionForward = mapping.findForward("rcmTaskMapDetail");
                break;
            case RcmTaskMapDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(rcmTaskMapDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmTaskMapDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(rcmTaskMapDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmTaskMapDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author kim2107
     * @version $Id: RcmTaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmTaskMapDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmTaskMapDetailForm rcmTaskMapDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmTaskMapDetailService rcmTaskMapDetailService = (RcmTaskMapDetailService)getBean("rcmTaskMapDetailService");
    	RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapDetailForm.getRcmTaskMapCommonDTO();
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmTaskMapDetailDTO rcmTaskMapDetailDTO = rcmTaskMapDetailService.findDetail(rcmTaskMapCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmTaskMapDetailForm.setRcmTaskMapDetailDTO(rcmTaskMapDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmTaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailForm
     * @param request
     */
    private void insertDetail(RcmTaskMapDetailForm rcmTaskMapDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmTaskMapDetailService rcmTaskMapDetailService = (RcmTaskMapDetailService) getBean("rcmTaskMapDetailService");
        
        RcmTaskMapDetailDTO rcmTaskMapDetailDTO = rcmTaskMapDetailForm.getRcmTaskMapDetailDTO();
        rcmTaskMapDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmTaskMapDetailService.insertDetail(rcmTaskMapDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmTaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailForm
     * @param request
     */
    private void updateDetail(RcmTaskMapDetailForm rcmTaskMapDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmTaskMapDetailService rcmTaskMapDetailService = (RcmTaskMapDetailService) getBean("rcmTaskMapDetailService");
        
        RcmTaskMapDetailDTO rcmTaskMapDetailDTO = rcmTaskMapDetailForm.getRcmTaskMapDetailDTO();
        rcmTaskMapDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmTaskMapDetailService.updateDetail(rcmTaskMapDetailDTO);
        
        setAjaxStatus(request);
    }

}