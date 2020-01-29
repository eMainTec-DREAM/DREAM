package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.form.RcmSysFDefDetailForm;
import dream.rcm.system.service.RcmSysFDefDetailService;

/**
 * �������
 * @author  kim21017
 * @version $Id: RcmSysFDefDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysFDefDetail" name="rcmSysFDefDetailForm"
 *                input="/dream/rcm/system/rcmSysFDefDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysFDefDetail" path="/dream/rcm/system/rcmSysFDefDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysFDefDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int RCM_SYS_FDEF_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int RCM_SYS_FDEF_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int RCM_SYS_FDEF_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysFDefDetailForm rcmSysFDefDetailForm = (RcmSysFDefDetailForm) form;
        
        super.updateAudit(rcmSysFDefDetailForm.getRcmSysFDefDetailDTO().getAuditKey()==""?rcmSysFDefDetailForm.getRcmSysFDefListDTO().getAuditKey():rcmSysFDefDetailForm.getRcmSysFDefDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysFDefDetailForm.getStrutsAction())
        {
            case RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, rcmSysFDefDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_UPDATE:
            	updateDetail(rcmSysFDefDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_INPUT:
            	insertDetail(rcmSysFDefDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �� ��ȸ
     * @author kim2107
     * @version $Id: RcmSysFDefDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFDefDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysFDefDetailForm rcmSysFDefDetailForm)throws Exception 
    {   
    	RcmSysFDefDetailService rcmSysFDefDetailService = (RcmSysFDefDetailService)getBean("rcmSysFDefDetailService");

        String rcmListId = rcmSysFDefDetailForm.getRcmSysCommonDTO().getRcmListId();
        String rcmFuncId = rcmSysFDefDetailForm.getRcmSysFDefListDTO().getRcmFuncId();
        
        // ��ȸ�� �� �κ�
        RcmSysFDefDetailDTO rcmSysFDefDetailDTO = rcmSysFDefDetailService.findDetail(rcmListId, rcmFuncId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        rcmSysFDefDetailForm.setRcmSysFDefDetailDTO(rcmSysFDefDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmSysFDefDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailForm
     * @param request
     */
    private void updateDetail(RcmSysFDefDetailForm rcmSysFDefDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysFDefDetailService rcmSysFDefDetailService = (RcmSysFDefDetailService) getBean("rcmSysFDefDetailService");
        
        RcmSysFDefDetailDTO rcmSysFDefDetailDTO = rcmSysFDefDetailForm.getRcmSysFDefDetailDTO();
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysFDefDetailForm.getRcmSysCommonDTO();
        rcmSysFDefDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysFDefDetailService.updateDetail(rcmSysFDefDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmSysFDefDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailForm
     * @param request
     */
    private void insertDetail(RcmSysFDefDetailForm rcmSysFDefDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysFDefDetailService rcmSysFDefDetailService = (RcmSysFDefDetailService) getBean("rcmSysFDefDetailService");
        
        RcmSysFDefDetailDTO rcmSysFDefDetailDTO = rcmSysFDefDetailForm.getRcmSysFDefDetailDTO();
        
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysFDefDetailForm.getRcmSysCommonDTO();
        rcmSysFDefDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysFDefDetailService.insertDetail(rcmSysFDefDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}