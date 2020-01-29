package dream.fail.library.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.fail.library.dto.FailLibraryDetailDTO;
import dream.fail.library.form.FailLibraryDetailForm;
import dream.fail.library.service.FailLibraryDetailService;

/**
 * ����library - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/failLibraryDetail" name="failLibraryDetailForm"
 *                input="/dream/fail/library/failLibraryDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="failLibraryDetail" path="/dream/fail/library/failLibraryDetail.jsp"
 *                        redirect="false"
 */
public class FailLibraryDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int FAILLIB_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int FAILLIB_DETAIL_INPUT 	= 5002;
    /** ���� */
    public static final int FAILLIB_DETAIL_UPDATE 	= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        FailLibraryDetailForm failLibraryDetailForm = (FailLibraryDetailForm) form;
        
        super.updateAudit(failLibraryDetailForm.getFailLibraryDetailDTO().getAuditKey()==""?failLibraryDetailForm.getFailLibraryCommonDTO().getAuditKey():failLibraryDetailForm.getFailLibraryDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (failLibraryDetailForm.getStrutsAction())
        {
            case FailLibraryDetailAction.FAILLIB_DETAIL_INIT:
                this.findDetail(request, failLibraryDetailForm);
                returnActionForward = mapping.findForward("failLibraryDetail");
                break;
            case FailLibraryDetailAction.FAILLIB_DETAIL_INPUT:
            	insertDetail(failLibraryDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case FailLibraryDetailAction.FAILLIB_DETAIL_UPDATE:
            	updateDetail(failLibraryDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("failLibraryDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����ڵ� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param failLibraryDetailForm
     */
    private void findDetail(HttpServletRequest request, FailLibraryDetailForm failLibraryDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	FailLibraryDetailService failLibraryDetailService = (FailLibraryDetailService)getBean("failLibraryDetailService");

        // GET Parameter
        String compNo = failLibraryDetailForm.getFailLibraryCommonDTO().getCompNo();
        String failureId = failLibraryDetailForm.getFailLibraryCommonDTO().getFailsetlistId();
        
        // ��ȸ�� �� �κ�
        FailLibraryDetailDTO failLibraryDetailDTO = failLibraryDetailService.findDetail(getUser(request), failureId);
        
        // ��ȸ�� ��  �����Ѵ�.
        failLibraryDetailForm.setFailLibraryDetailDTO(failLibraryDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param failLibraryDetailForm
     * @param request
     */
    private void insertDetail(FailLibraryDetailForm failLibraryDetailForm, HttpServletRequest request) throws Exception
    {
        FailLibraryDetailService failLibraryDetailService = (FailLibraryDetailService) getBean("failLibraryDetailService");
        
        FailLibraryDetailDTO failLibraryDetailDTO = failLibraryDetailForm.getFailLibraryDetailDTO();
        
        failLibraryDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        failLibraryDetailService.insertDetail(failLibraryDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailForm
     * @param request
     */
    private void updateDetail(FailLibraryDetailForm failLibraryDetailForm, HttpServletRequest request) throws Exception
    {
    	FailLibraryDetailService failLibraryDetailService = (FailLibraryDetailService) getBean("failLibraryDetailService");
        
        FailLibraryDetailDTO failLibraryDetailDTO = failLibraryDetailForm.getFailLibraryDetailDTO();
        
        failLibraryDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        failLibraryDetailService.updateDetail(failLibraryDetailDTO);
        
        setAjaxStatus(request);
    }
    
}