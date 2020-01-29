package dream.rcm.pmtask.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.form.RcmPmtaskCndtDetailForm;
import dream.rcm.pmtask.service.RcmPmtaskCndtDetailService;

/**
 * ��
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmPmtaskCndtDetail" name="rcmPmtaskCndtDetailForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskCndtDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskCndtDetail" path="/dream/rcm/pmtask/rcmPmtaskCndtDetail.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskCndtDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PMTASK_CNDT_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int PMTASK_CNDT_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int PMTASK_CNDT_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskCndtDetailForm rcmPmtaskCndtDetailForm = (RcmPmtaskCndtDetailForm) form;
        
        super.updateAudit(rcmPmtaskCndtDetailForm.getRcmPmtaskCndtDetailDTO().getAuditKey()==""?rcmPmtaskCndtDetailForm.getRcmPmtaskCndtListDTO().getAuditKey():rcmPmtaskCndtDetailForm.getRcmPmtaskCndtDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmPmtaskCndtDetailForm.getStrutsAction())
        {
            case RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, rcmPmtaskCndtDetailForm);
                returnActionForward = mapping.findForward("rcmPmtaskCndtDetail");
                break;
            case RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_UPDATE:
            	updateDetail(rcmPmtaskCndtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_INPUT:
            	insertDetail(rcmPmtaskCndtDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskCndtDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: RcmPmtaskCndtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskCndtDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmPmtaskCndtDetailForm rcmPmtaskCndtDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmPmtaskCndtDetailService rcmPmtaskCndtDetailService = (RcmPmtaskCndtDetailService)getBean("rcmPmtaskCndtDetailService");
    	RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCommonDTO();
    	RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCndtListDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO = rcmPmtaskCndtDetailService.findDetail(rcmPmtaskCndtListDTO,rcmPmtaskCommonDTO);

        // ��ȸ�� ��  �����Ѵ�.
        rcmPmtaskCndtDetailForm.setRcmPmtaskCndtDetailDTO(rcmPmtaskCndtDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmPmtaskCndtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailForm
     * @param request
     */
    private void updateDetail(RcmPmtaskCndtDetailForm rcmPmtaskCndtDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskCndtDetailService rcmPmtaskCndtDetailService = (RcmPmtaskCndtDetailService) getBean("rcmPmtaskCndtDetailService");
        
        RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCndtDetailDTO();
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskCndtDetailService.updateDetail(rcmPmtaskCndtDetailDTO,rcmPmtaskCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmPmtaskCndtDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailForm
     * @param request
     */
    private void insertDetail(RcmPmtaskCndtDetailForm rcmPmtaskCndtDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskCndtDetailService rcmPmtaskCndtDetailService = (RcmPmtaskCndtDetailService) getBean("rcmPmtaskCndtDetailService");
        
        RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCndtDetailDTO();
        
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskCndtDetailForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskCndtDetailService.insertDetail(rcmPmtaskCndtDetailDTO, rcmPmtaskCommonDTO);
        
        setAjaxStatus(request);
    }
}