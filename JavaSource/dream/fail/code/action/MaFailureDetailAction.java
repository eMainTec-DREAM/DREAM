package dream.fail.code.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.fail.code.dto.MaFailureDetailDTO;
import dream.fail.code.form.MaFailureDetailForm;
import dream.fail.code.service.MaFailureDetailService;

/**
 * �����ڵ� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maFailureDetail" name="maFailureDetailForm"
 *                input="/dream/fail/code/maFailureDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maFailureDetail" path="/dream/fail/code/maFailureDetail.jsp"
 *                        redirect="false"
 */
public class MaFailureDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int FAILURE_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int FAILURE_DETAIL_INPUT 	= 5002;
    /** ���� */
    public static final int FAILURE_DETAIL_UPDATE 	= 6003;
    /** �ߺ� üũ */
    public static final int FAILURE_DETAIL_CHECK 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaFailureDetailForm maFailureDetailForm = (MaFailureDetailForm) form;
        
        super.updateAudit(maFailureDetailForm.getMaFailureDetailDTO().getAuditKey()==""?maFailureDetailForm.getMaFailureCommonDTO().getAuditKey():maFailureDetailForm.getMaFailureDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maFailureDetailForm.getStrutsAction())
        {
            case MaFailureDetailAction.FAILURE_DETAIL_INIT:
                this.findDetail(request, maFailureDetailForm);
                returnActionForward = mapping.findForward("maFailureDetail");
                break;
            case MaFailureDetailAction.FAILURE_DETAIL_INPUT:
            	insertDetail(maFailureDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaFailureDetailAction.FAILURE_DETAIL_UPDATE:
            	updateDetail(maFailureDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaFailureDetailAction.FAILURE_DETAIL_CHECK:
                validFailureNo(maFailureDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maFailureDetail");
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
     * @param maFailureDetailForm
     */
    private void findDetail(HttpServletRequest request, MaFailureDetailForm maFailureDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaFailureDetailService maFailureDetailService = (MaFailureDetailService)getBean("maFailureDetailService");

        // GET Parameter
        String compNo = maFailureDetailForm.getMaFailureCommonDTO().getCompNo();
        String failureId = maFailureDetailForm.getMaFailureCommonDTO().getFailureId();
        
        // ��ȸ�� �� �κ�
        MaFailureDetailDTO maFailureDetailDTO = maFailureDetailService.findDetail(getUser(request), failureId);
        
        // ��ȸ�� ��  �����Ѵ�.
        maFailureDetailForm.setMaFailureDetailDTO(maFailureDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maFailureDetailForm
     * @param request
     */
    private void insertDetail(MaFailureDetailForm maFailureDetailForm, HttpServletRequest request) throws Exception
    {
        MaFailureDetailService maFailureDetailService = (MaFailureDetailService) getBean("maFailureDetailService");
        
        MaFailureDetailDTO maFailureDetailDTO = maFailureDetailForm.getMaFailureDetailDTO();
        
        maFailureDetailDTO.setCompNo((getUser(request).getCompNo()));
       
        maFailureDetailService.insertDetail(maFailureDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailForm
     * @param request
     */
    private void updateDetail(MaFailureDetailForm maFailureDetailForm, HttpServletRequest request) throws Exception
    {
    	MaFailureDetailService maFailureDetailService = (MaFailureDetailService) getBean("maFailureDetailService");
        
        MaFailureDetailDTO maFailureDetailDTO = maFailureDetailForm.getMaFailureDetailDTO();
        
        maFailureDetailDTO.setCompNo((getUser(request).getCompNo()));
        maFailureDetailDTO.setUserLang(getUser(request).getLangId());
        
        maFailureDetailService.updateDetail(maFailureDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureDetailForm
     * @param request
     */
    private void validFailureNo(MaFailureDetailForm maFailureDetailForm, HttpServletRequest request) throws Exception
    {
    	MaFailureDetailService maFailureDetailService = (MaFailureDetailService) getBean("maFailureDetailService");
        
        MaFailureDetailDTO maFailureDetailDTO = maFailureDetailForm.getMaFailureDetailDTO();
        
        maFailureDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maFailureDetailService.validFailureNo(maFailureDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
}