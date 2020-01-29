package dream.rcm.pmtask.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;
import dream.rcm.pmtask.form.RcmPmtaskMapDetailForm;
import dream.rcm.pmtask.service.RcmPmtaskMapDetailService;

/**
 * ��
 * @author  kim21017
 * @version $Id: RcmPmtaskMapDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmPmtaskMapDetail" name="rcmPmtaskMapDetailForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskMapDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskMapDetail" path="/dream/rcm/pmtask/rcmPmtaskMapDetail.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskMapDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int RCM_FMEA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int RCM_FMEA_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int RCM_FMEA_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskMapDetailForm rcmPmtaskMapDetailForm = (RcmPmtaskMapDetailForm) form;
        
        super.updateAudit(rcmPmtaskMapDetailForm.getRcmPmtaskMapDetailDTO().getAuditKey()==""?rcmPmtaskMapDetailForm.getRcmPmtaskMapListDTO().getAuditKey():rcmPmtaskMapDetailForm.getRcmPmtaskMapDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmPmtaskMapDetailForm.getStrutsAction())
        {
            case RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, rcmPmtaskMapDetailForm);
                returnActionForward = mapping.findForward("rcmPmtaskMapDetail");
                break;
            case RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_UPDATE:
            	updateDetail(rcmPmtaskMapDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_INPUT:
            	insertDetail(rcmPmtaskMapDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskMapDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: RcmPmtaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskMapDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmPmtaskMapDetailForm rcmPmtaskMapDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmPmtaskMapDetailService rcmPmtaskMapDetailService = (RcmPmtaskMapDetailService)getBean("rcmPmtaskMapDetailService");
    	RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapDetailForm.getRcmPmtaskCommonDTO();
    	RcmPmtaskMapListDTO rcmPmtaskMapListDTO = rcmPmtaskMapDetailForm.getRcmPmtaskMapListDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO = rcmPmtaskMapDetailService.findDetail(rcmPmtaskMapListDTO,rcmPmtaskCommonDTO);

        // ��ȸ�� ��  �����Ѵ�.
        rcmPmtaskMapDetailForm.setRcmPmtaskMapDetailDTO(rcmPmtaskMapDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmPmtaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailForm
     * @param request
     */
    private void updateDetail(RcmPmtaskMapDetailForm rcmPmtaskMapDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskMapDetailService rcmPmtaskMapDetailService = (RcmPmtaskMapDetailService) getBean("rcmPmtaskMapDetailService");
        
        RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO = rcmPmtaskMapDetailForm.getRcmPmtaskMapDetailDTO();
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapDetailForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskMapDetailService.updateDetail(rcmPmtaskMapDetailDTO,rcmPmtaskCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmPmtaskMapDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailForm
     * @param request
     */
    private void insertDetail(RcmPmtaskMapDetailForm rcmPmtaskMapDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmPmtaskMapDetailService rcmPmtaskMapDetailService = (RcmPmtaskMapDetailService) getBean("rcmPmtaskMapDetailService");
        
        RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO = rcmPmtaskMapDetailForm.getRcmPmtaskMapDetailDTO();
        
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskMapDetailForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmPmtaskMapDetailService.insertDetail(rcmPmtaskMapDetailDTO, rcmPmtaskCommonDTO);
        
        setAjaxStatus(request);
    }
}