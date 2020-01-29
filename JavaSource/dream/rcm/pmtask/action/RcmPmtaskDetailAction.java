package dream.rcm.pmtask.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;
import dream.rcm.pmtask.form.RcmPmtaskDetailForm;
import dream.rcm.pmtask.service.RcmPmtaskDetailService;

/**
 * �� action
 * 
 * @author kim2107
 * @version $Id: RcmPmtaskDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmPmtaskDetail" name="rcmPmtaskDetailForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskDetail" path="/dream/rcm/pmtask/rcmPmtaskDetail.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PMTASK_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int PMTASK_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int PMTASK_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskDetailForm rcmPmtaskDetailForm = (RcmPmtaskDetailForm) form;
        
        super.updateAudit(rcmPmtaskDetailForm.getRcmPmtaskDetailDTO().getAuditKey()==""?rcmPmtaskDetailForm.getRcmPmtaskCommonDTO().getAuditKey():rcmPmtaskDetailForm.getRcmPmtaskDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmPmtaskDetailForm.getStrutsAction())
        {
            case RcmPmtaskDetailAction.PMTASK_DETAIL_INIT:
                this.findDetail(request, rcmPmtaskDetailForm);
                returnActionForward = mapping.findForward("rcmPmtaskDetail");
                break;
            case RcmPmtaskDetailAction.PMTASK_DETAIL_UPDATE:
            	updateDetail(rcmPmtaskDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskDetailAction.PMTASK_DETAIL_INPUT:
            	insertDetail(rcmPmtaskDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author kim2107
     * @version $Id: RcmPmtaskDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmPmtaskDetailForm rcmPmtaskDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmPmtaskDetailService rcmPmtaskDetailService = (RcmPmtaskDetailService)getBean("rcmPmtaskDetailService");
    	RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskDetailForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmPmtaskDetailDTO rcmPmtaskDetailDTO = rcmPmtaskDetailService.findDetail(rcmPmtaskCommonDTO);

        if("".equals(rcmPmtaskDetailDTO.getRcmpmtaskId()))rcmPmtaskDetailForm.setStrutsAction(0);
        // ��ȸ�� ��  �����Ѵ�.
        rcmPmtaskDetailForm.setRcmPmtaskDetailDTO(rcmPmtaskDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmPmtaskDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailForm
     * @param request
     */
    private void insertDetail(RcmPmtaskDetailForm rcmPmtaskDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskDetailService rcmPmtaskDetailService = (RcmPmtaskDetailService) getBean("rcmPmtaskDetailService");
        
        RcmPmtaskDetailDTO rcmPmtaskDetailDTO = rcmPmtaskDetailForm.getRcmPmtaskDetailDTO();
        rcmPmtaskDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskDetailService.insertDetail(rcmPmtaskDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmPmtaskDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailForm
     * @param request
     */
    private void updateDetail(RcmPmtaskDetailForm rcmPmtaskDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskDetailService rcmPmtaskDetailService = (RcmPmtaskDetailService) getBean("rcmPmtaskDetailService");
        
        RcmPmtaskDetailDTO rcmPmtaskDetailDTO = rcmPmtaskDetailForm.getRcmPmtaskDetailDTO();
        rcmPmtaskDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskDetailService.updateDetail(rcmPmtaskDetailDTO);
        
        setAjaxStatus(request);
    }
}