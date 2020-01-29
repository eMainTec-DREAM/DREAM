package dream.pers.priv.info.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.login.login.action.LoginAction;
import dream.login.login.dto.LoginDTO;
import dream.login.login.service.LoginService;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.form.MaMyInfoForm;
import dream.pers.priv.info.service.MaMyInfoService;

/**
 * 내정보 action
 * 
 * @author kim2107
 * @version $Id: MaMyInfoAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maMyInfo" name="maMyInfoForm"
 *                input="/dream/pers/priv/info/maMyInfo.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMyInfo" path="/dream/pers/priv/info/maMyInfo.jsp"
 *                        redirect="false"
 */
public class MaMyInfoAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MYINFO_DETAIL_INIT			= 1001;
    /** 수정 */
    public static final int MYINFO_DETAIL_UPDATE		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMyInfoForm maMyInfoForm = (MaMyInfoForm) form;
        
        switch (maMyInfoForm.getStrutsAction())
        {
            case MaMyInfoAction.MYINFO_DETAIL_INIT:
                // 조회
                this.findDetail(request, maMyInfoForm);
                returnActionForward = mapping.findForward("maMyInfo");
                break;
            case MaMyInfoAction.MYINFO_DETAIL_UPDATE:
            	updateDetail(maMyInfoForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
            	this.findDetail(request, maMyInfoForm);
                returnActionForward = mapping.findForward("maMyInfo");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 내정보 조회
     * @author kim2107
     * @version $Id: MaMyInfoAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMyInfoForm
     */
    private void findDetail(HttpServletRequest request, MaMyInfoForm maMyInfoForm)throws Exception 
    {   
        // Service 객체 생성
    	MaMyInfoService maMyInfoService = (MaMyInfoService)getBean("maMyInfoService");

        MaMyInfoCommonDTO maMyInfoCommonDTO = maMyInfoForm.getMaMyInfoCommonDTO();
        
        maMyInfoCommonDTO.setUserId(getUser(request).getUserId());
        maMyInfoCommonDTO.setCompNo(getUser(request).getCompNo());
        maMyInfoCommonDTO.setUserLang(getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maMyInfoForm.setMaMyInfoCommonDTO(maMyInfoService.findDetail(maMyInfoCommonDTO));
        maMyInfoForm.setStrutsAction(1000);  /// 조회 Struts Action 강제 입력 
    }
    
    /**A
     * detail update
     * @author  kim21017
     * @version $Id: MaMyInfoAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMyInfoForm
     * @param request
     */
    private void updateDetail(MaMyInfoForm maMyInfoForm, HttpServletRequest request) throws Exception
    {
    	MaMyInfoService maMyInfoService = (MaMyInfoService) getBean("maMyInfoService");
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = maMyInfoForm.getMaMyInfoCommonDTO();
        
        maMyInfoCommonDTO.setEnterBy(getUser(request).getUserId());
        maMyInfoCommonDTO.setCompNo(getUser(request).getCompNo());
        maMyInfoService.updateDetail(maMyInfoCommonDTO);
        
        LoginService loginService = (LoginService) getBean("loginService");
        LoginDTO loginDTO = new LoginDTO();
        User user = getUser(request);
        
            loginDTO.setUserNo(user.getUserNo());
            loginDTO.setLocale(user.getLangId());
            loginDTO.setCompNo(user.getCompNo());

        LoginAction lo = new LoginAction();
        List userInfoList = loginService.findUserInfo(loginDTO);
        lo.setSession(request.getSession(),  user.getLangId(), userInfoList);
        
        setAjaxStatus(request);
    }
}