package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysDetailDTO;
import dream.rcm.system.form.RcmSysDetailForm;
import dream.rcm.system.service.RcmSysDetailService;

/**
 * System�м� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/rcmSysDetail" name="rcmSysDetailForm"
 *                input="/dream/rcm/system/rcmSysDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysDetail" path="/dream/rcm/system/rcmSysDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int RCM_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int RCM_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int RCM_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysDetailForm rcmSysDetailForm = (RcmSysDetailForm) form;
        
        super.updateAudit(rcmSysDetailForm.getRcmSysDetailDTO().getAuditKey()==""?rcmSysDetailForm.getRcmSysCommonDTO().getAuditKey():rcmSysDetailForm.getRcmSysDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysDetailForm.getStrutsAction())
        {
            case RcmSysDetailAction.RCM_DETAIL_INIT:
                this.findDetail(request, rcmSysDetailForm);
                returnActionForward = mapping.findForward("rcmSysDetail");
                break;
            case RcmSysDetailAction.RCM_DETAIL_INPUT:
            	insertDetail(rcmSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysDetailAction.RCM_DETAIL_UPDATE:
            	updateDetail(rcmSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmSysDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysDetailForm rcmSysDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	RcmSysDetailService rcmSysDetailService = (RcmSysDetailService)getBean("rcmSysDetailService");

        // �Ѱ��� �����ȣ ����
        String compNo = rcmSysDetailForm.getRcmSysCommonDTO().getCompNo();
        String id  = rcmSysDetailForm.getRcmSysCommonDTO().getRcmListId();
        
        // ��ȸ�� �� �κ�
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailService.findDetail(getUser(request), id);
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmSysDetailForm.setRcmSysDetailDTO(rcmSysDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param rcmSysDetailForm
     * @param request
     */
    private void insertDetail(RcmSysDetailForm rcmSysDetailForm, HttpServletRequest request) throws Exception
    {
        RcmSysDetailService rcmSysDetailService = (RcmSysDetailService) getBean("rcmSysDetailService");
        
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailForm.getRcmSysDetailDTO();
        
        rcmSysDetailService.insertDetail(getUser(request), rcmSysDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailForm
     * @param request
     */
    private void updateDetail(RcmSysDetailForm rcmSysDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysDetailService rcmSysDetailService = (RcmSysDetailService) getBean("rcmSysDetailService");
        
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailForm.getRcmSysDetailDTO();
        
        rcmSysDetailService.updateDetail(getUser(request), rcmSysDetailDTO);
        
        setAjaxStatus(request);
    }
}