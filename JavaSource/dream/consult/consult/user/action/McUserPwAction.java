package dream.consult.consult.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;
import dream.consult.consult.user.form.McUserPwForm;
import dream.consult.consult.user.service.McUserPwService;

/**
 * ��й�ȣ���� �˾�
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mcUserPw" name="mcUserPwForm"
 *                input="/dream/consult/consult/user/mcUserPw.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcUserPw" path="/dream/consult/consult/user/mcUserPw.jsp"
 *                        redirect="false"
 */
public class McUserPwAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_PW_INIT      = 1001;
    /** ���� */
    public static final int USER_PW_UPDATE    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        McUserPwForm mcUserPwForm = (McUserPwForm) form;
        switch (mcUserPwForm.getStrutsAction())
        {
            case McUserPwAction.USER_PW_INIT:
                // ��ȸ
                this.findDetail(request, mcUserPwForm);
                returnActionForward = mapping.findForward("mcUserPw");
                break;
            case McUserPwAction.USER_PW_UPDATE:
            	updateDetail(mcUserPwForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mcUserPw");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �з� ��ȸ
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mcUserPwForm
     */
    private void findDetail(HttpServletRequest request, McUserPwForm mcUserPwForm)throws Exception 
    {   
        // Service ��ü ����
    	McUserPwService mcUserPwService = (McUserPwService)getBean("mcUserPwService");
        
    	McUserCommonDTO mcUserCommonDTO = mcUserPwForm.getMcUserCommonDTO();
    	mcUserCommonDTO.setCompNo(getUser(request).getCompNo());
        
        // ��ȸ�� �� �κ�
        McUserPwDTO mcUserPwDTO = mcUserPwService.findDetail(mcUserCommonDTO, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        mcUserPwForm.setMcUserPwDTO(mcUserPwDTO);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserPwForm
     * @param request
     */
    private void updateDetail(McUserPwForm mcUserPwForm, HttpServletRequest request) throws Exception
    {
    	McUserPwService mcUserPwService = (McUserPwService) getBean("mcUserPwService");
        
        McUserPwDTO mcUserPwDTO = mcUserPwForm.getMcUserPwDTO();
        
        // �α� ȸ���ڵ带 �����Ѵ�.
        mcUserPwDTO.setCompNo((getUser(request).getCompNo()));
        mcUserPwDTO.setEnterBy(getUser(request).getUserId());
        
        mcUserPwService.updateDetail(mcUserPwDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}