package dream.consult.consult.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;
import dream.consult.consult.user.form.McUserDetailForm;
import dream.consult.consult.user.service.McUserDetailService;

/**
 * ����� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mcUserDetail" name="mcUserDetailForm"
 *                input="/dream/consult/consult/user/mcUserDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcUserDetail" path="/dream/consult/consult/user/mcUserDetail.jsp"
 *                        redirect="false"
 */
public class McUserDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int USER_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int USER_DETAIL_UPDATE 		= 1003;
    /** �ߺ� üũ */
    public static final int USER_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        McUserDetailForm mcUserDetailForm = (McUserDetailForm) form;
        
        switch (mcUserDetailForm.getStrutsAction())
        {
            case McUserDetailAction.USER_DETAIL_INIT:
                this.findDetail(request, mcUserDetailForm);
                returnActionForward = mapping.findForward("mcUserDetail");
                break;
            case McUserDetailAction.USER_DETAIL_INPUT:
            	insertDetail(mcUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case McUserDetailAction.USER_DETAIL_UPDATE:
            	updateDetail(mcUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case McUserDetailAction.USER_DETAIL_CHECK:
            	validUserNo(mcUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mcUserDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ����� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mcUserDetailForm
     */
    private void findDetail(HttpServletRequest request, McUserDetailForm mcUserDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	McUserDetailService mcUserDetailService = (McUserDetailService)getBean("mcUserDetailService");

        // �Ѱ��� ����ڹ�ȣ ����
    	McUserCommonDTO mcUserCommonDTO = mcUserDetailForm.getMcUserCommonDTO();
        
        // ��ȸ�� �� �κ�
        McUserDetailDTO mcUserDetailDTO = mcUserDetailService.findDetail(mcUserCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        mcUserDetailForm.setMcUserDetailDTO(mcUserDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mcUserDetailForm
     * @param request
     */
    private void insertDetail(McUserDetailForm mcUserDetailForm, HttpServletRequest request) throws Exception
    {
        McUserDetailService mcUserDetailService = (McUserDetailService) getBean("mcUserDetailService");
        
        McUserDetailDTO mcUserDetailDTO = mcUserDetailForm.getMcUserDetailDTO();
        
        mcUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        mcUserDetailService.insertDetail(mcUserDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailForm
     * @param request
     */
    private void updateDetail(McUserDetailForm mcUserDetailForm, HttpServletRequest request) throws Exception
    {
    	McUserDetailService mcUserDetailService = (McUserDetailService) getBean("mcUserDetailService");
        
        McUserDetailDTO mcUserDetailDTO = mcUserDetailForm.getMcUserDetailDTO();
        
        mcUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        mcUserDetailService.updateDetail(mcUserDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid user id
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailForm
     * @param request
     */
    private void validUserNo(McUserDetailForm mcUserDetailForm, HttpServletRequest request) throws Exception
    {
    	McUserDetailService mcUserDetailService = (McUserDetailService) getBean("mcUserDetailService");
        
        McUserDetailDTO mcUserDetailDTO = mcUserDetailForm.getMcUserDetailDTO();
        
        mcUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = mcUserDetailService.validUserNo(mcUserDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}