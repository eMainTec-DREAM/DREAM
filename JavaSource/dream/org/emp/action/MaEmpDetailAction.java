package dream.org.emp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.MaEmpDetailDTO;
import dream.org.emp.form.MaEmpDetailForm;
import dream.org.emp.service.MaEmpDetailService;

/**
 * ��� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maEmpDetail" name="maEmpDetailForm"
 *                input="/dream/org/emp/maEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEmpDetail" path="/dream/org/emp/maEmpDetail.jsp"
 *                        redirect="false"
 */
public class MaEmpDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EMP_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int EMP_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int EMP_DETAIL_UPDATE 		= 6003;
    /** �ߺ� üũ */
    public static final int EMP_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEmpDetailForm maEmpDetailForm = (MaEmpDetailForm) form;
        
        super.updateAudit(maEmpDetailForm.getMaEmpDetailDTO().getAuditKey()==""?maEmpDetailForm.getMaEmpCommonDTO().getAuditKey():maEmpDetailForm.getMaEmpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maEmpDetailForm.getStrutsAction())
        {
            case MaEmpDetailAction.EMP_DETAIL_INIT:
                this.findDetail(request, maEmpDetailForm);
                returnActionForward = mapping.findForward("maEmpDetail");
                break;
            case MaEmpDetailAction.EMP_DETAIL_INPUT:
            	insertDetail(maEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEmpDetailAction.EMP_DETAIL_UPDATE:
            	updateDetail(maEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEmpDetailAction.EMP_DETAIL_CHECK:
            	validEmpNo(request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEmpDetail");
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
     * @param maEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEmpDetailForm maEmpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEmpDetailService maEmpDetailService = (MaEmpDetailService)getBean("maEmpDetailService");
    	MaEmpCommonDTO maEmpCommonDTO = maEmpDetailForm.getMaEmpCommonDTO();
        
        // ��ȸ�� �� �κ�
        MaEmpDetailDTO maEmpDetailDTO = maEmpDetailService.findDetail(maEmpCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEmpDetailForm.setMaEmpDetailDTO(maEmpDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void insertDetail(MaEmpDetailForm maEmpDetailForm, HttpServletRequest request) throws Exception
    {
        MaEmpDetailService maEmpDetailService = (MaEmpDetailService) getBean("maEmpDetailService");
        
        MaEmpDetailDTO maEmpDetailDTO = maEmpDetailForm.getMaEmpDetailDTO();
        
        maEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maEmpDetailService.insertDetail(maEmpDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void updateDetail(MaEmpDetailForm maEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEmpDetailService maEmpDetailService = (MaEmpDetailService) getBean("maEmpDetailService");
        
        MaEmpDetailDTO maEmpDetailDTO = maEmpDetailForm.getMaEmpDetailDTO();
        
        maEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maEmpDetailService.updateDetail(maEmpDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid emp id
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validEmpNo(HttpServletRequest request) throws Exception
    {
    	MaEmpDetailService maEmpDetailService = (MaEmpDetailService) getBean("maEmpDetailService");
        
        User user = getUser(request);
        
        String empId = request.getParameterValues("empId")[0];
		String empNo = request.getParameterValues("empNo")[0];
        
        String isValid = maEmpDetailService.validEmpNo(empId, empNo, user);        
        
        setAjaxDesc(request, isValid);
    }

}