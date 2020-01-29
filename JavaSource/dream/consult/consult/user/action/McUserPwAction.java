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
 * 비밀번호설정 팝업
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_PW_INIT      = 1001;
    /** 수정 */
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
                // 조회
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
     * 시스템코드 분류 조회
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mcUserPwForm
     */
    private void findDetail(HttpServletRequest request, McUserPwForm mcUserPwForm)throws Exception 
    {   
        // Service 객체 생성
    	McUserPwService mcUserPwService = (McUserPwService)getBean("mcUserPwService");
        
    	McUserCommonDTO mcUserCommonDTO = mcUserPwForm.getMcUserCommonDTO();
    	mcUserCommonDTO.setCompNo(getUser(request).getCompNo());
        
        // 조회된 상세 부분
        McUserPwDTO mcUserPwDTO = mcUserPwService.findDetail(mcUserCommonDTO, getUser(request));
        // 조회된 상세  셋팅한다.
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
        
        // 로긴 회사코드를 셋팅한다.
        mcUserPwDTO.setCompNo((getUser(request).getCompNo()));
        mcUserPwDTO.setEnterBy(getUser(request).getUserId());
        
        mcUserPwService.updateDetail(mcUserPwDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}