package dream.rcm.fmea.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;
import dream.rcm.fmea.form.RcmFmeaCrityDetailForm;
import dream.rcm.fmea.service.RcmFmeaCrityDetailService;

/**
 * ��
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFmeaCrityDetail" name="rcmFmeaCrityDetailForm"
 *                input="/dream/rcm/fmea/rcmFmeaCrityDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFmeaCrityDetail" path="/dream/rcm/fmea/rcmFmeaCrityDetail.jsp"
 *                        redirect="false"
 * @struts:action path="/rcmPmtaskCrityDetail" name="rcmFmeaCrityDetailForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskCrityDetail.jsp" scope="request"
 *                validate="false"
 */
public class RcmFmeaCrityDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int RCM_FMEA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int RCM_FMEA_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int RCM_FMEA_DETAIL_INPUT 		= 5003;
    /** Validation */
    public static final int RCM_FMEA_CHECK_VAL 			= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFmeaCrityDetailForm rcmFmeaCrityDetailForm = (RcmFmeaCrityDetailForm) form;
        
        super.updateAudit(rcmFmeaCrityDetailForm.getRcmFmeaCrityDetailDTO().getAuditKey()==""?rcmFmeaCrityDetailForm.getRcmFmeaCrityListDTO().getAuditKey():rcmFmeaCrityDetailForm.getRcmFmeaCrityDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFmeaCrityDetailForm.getStrutsAction())
        {
            case RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, rcmFmeaCrityDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_UPDATE:
            	updateDetail(rcmFmeaCrityDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_INPUT:
            	insertDetail(rcmFmeaCrityDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaCrityDetailAction.RCM_FMEA_CHECK_VAL:
            	checkDup(rcmFmeaCrityDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void checkDup(RcmFmeaCrityDetailForm rcmFmeaCrityDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// Service ��ü ����
    	RcmFmeaCrityDetailService rcmFmeaCrityDetailService = (RcmFmeaCrityDetailService)getBean("rcmFmeaCrityDetailService");
    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityDetailForm.getRcmFmeaCommonDTO();
    	RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO = rcmFmeaCrityDetailForm.getRcmFmeaCrityDetailDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        int cnt = rcmFmeaCrityDetailService.findVal(rcmFmeaCrityDetailDTO,rcmFmeaCommonDTO);

        response.getWriter().print(cnt>0?"NO":"OK");
	}

	/**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: RcmFmeaCrityDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFmeaCrityDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFmeaCrityDetailForm rcmFmeaCrityDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmFmeaCrityDetailService rcmFmeaCrityDetailService = (RcmFmeaCrityDetailService)getBean("rcmFmeaCrityDetailService");
    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityDetailForm.getRcmFmeaCommonDTO();
    	RcmFmeaCrityListDTO rcmFmeaCrityListDTO = rcmFmeaCrityDetailForm.getRcmFmeaCrityListDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO = rcmFmeaCrityDetailService.findDetail(rcmFmeaCrityListDTO,rcmFmeaCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmFmeaCrityDetailForm.setRcmFmeaCrityDetailDTO(rcmFmeaCrityDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFmeaCrityDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailForm
     * @param request
     */
    private void updateDetail(RcmFmeaCrityDetailForm rcmFmeaCrityDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFmeaCrityDetailService rcmFmeaCrityDetailService = (RcmFmeaCrityDetailService) getBean("rcmFmeaCrityDetailService");
        
        RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO = rcmFmeaCrityDetailForm.getRcmFmeaCrityDetailDTO();
        RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityDetailForm.getRcmFmeaCommonDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFmeaCrityDetailService.updateDetail(rcmFmeaCrityDetailDTO,rcmFmeaCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFmeaCrityDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailForm
     * @param request
     */
    private void insertDetail(RcmFmeaCrityDetailForm rcmFmeaCrityDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFmeaCrityDetailService rcmFmeaCrityDetailService = (RcmFmeaCrityDetailService) getBean("rcmFmeaCrityDetailService");
        
        RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO = rcmFmeaCrityDetailForm.getRcmFmeaCrityDetailDTO();
        
        RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityDetailForm.getRcmFmeaCommonDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFmeaCrityDetailService.insertDetail(rcmFmeaCrityDetailDTO, rcmFmeaCommonDTO);
        
        setAjaxStatus(request);
    }
}