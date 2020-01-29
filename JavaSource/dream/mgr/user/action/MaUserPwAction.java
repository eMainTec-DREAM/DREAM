package dream.mgr.user.action;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;
import dream.mgr.user.form.MaUserPwForm;
import dream.mgr.user.service.MaUserPwService;
import dream.pers.priv.info.dto.MaChangePwDTO;
import dream.pers.priv.info.service.MaChangePwService;

/**
 * 비밀번호설정 팝업
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maUserPw" name="maUserPwForm"
 *                input="/dream/mgr/user/maUserPw.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserPw" path="/dream/mgr/user/maUserPw.jsp"
 *                        redirect="false"
 */
public class MaUserPwAction extends AuthAction
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
        MaUserPwForm maUserPwForm = (MaUserPwForm) form;
        switch (maUserPwForm.getStrutsAction())
        {
            case MaUserPwAction.USER_PW_INIT:
                // 조회
                this.findDetail(request, maUserPwForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaUserPwAction.USER_PW_UPDATE:
            	updateDetail(maUserPwForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
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
     * @param maUserPwForm
     */
    private void findDetail(HttpServletRequest request, MaUserPwForm maUserPwForm)throws Exception 
    {   
        // Service 객체 생성
    	MaUserPwService maUserPwService = (MaUserPwService)getBean("maUserPwService");
        
    	MaUserCommonDTO maUserCommonDTO = maUserPwForm.getMaUserCommonDTO();
    	if("".equals(maUserCommonDTO.getCompNo())) maUserCommonDTO.setCompNo(getUser(request).getCompNo());
        
        // 조회된 상세 부분
        MaUserPwDTO maUserPwDTO = maUserPwService.findDetail(maUserCommonDTO, getUser(request));
        // 조회된 상세  셋팅한다.
        maUserPwForm.setMaUserPwDTO(maUserPwDTO);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwForm
     * @param request
     */
    private void updateDetail(MaUserPwForm maUserPwForm, HttpServletRequest request) throws Exception
    {
    	MaUserPwService maUserPwService = (MaUserPwService) getBean("maUserPwService");
        
        MaUserPwDTO maUserPwDTO = maUserPwForm.getMaUserPwDTO();
        // 로긴 회사코드를 셋팅한다.
        if("".equals(maUserPwDTO.getCompNo())) maUserPwDTO.setCompNo((getUser(request).getCompNo()));
        maUserPwDTO.setEnterBy(getUser(request).getUserId());
        
        MaChangePwService maChangePwService = (MaChangePwService) getBean("maChangePwService", request);
        
        MaChangePwDTO maChangePwDTO = new MaChangePwDTO();

        maChangePwDTO.setCompNo(maUserPwDTO.getCompNo());
        maChangePwDTO.setUserId(maUserPwDTO.getUserId());
        
        MaChangePwDTO rsltDTO = maChangePwService.findDetail(maChangePwDTO);
        
        maChangePwDTO.setNewPw(maUserPwDTO.getPassword());  //새로운(바꿀) PW
        maChangePwDTO.setConfirmPw(maUserPwDTO.getConfirmPw());
        maChangePwDTO.setOldPw(CommonUtil.aesDecodeString(rsltDTO.getOldPwTemp())); //예전 PW 확인용
        
        Map<String,String> rtnMap = maChangePwService.checkPassword(maChangePwDTO, getUser(request));

        setAjaxDesc(request, rtnMap.get("MESSAGE"));
    }
    
    private String checkPwHist(MaUserPwService maUserPwService, MaUserPwDTO maUserPwDTO, String pwChangeHistCnt) throws Exception
    {
    	return maUserPwService.checkPwHist(maUserPwDTO, pwChangeHistCnt);
    }
}