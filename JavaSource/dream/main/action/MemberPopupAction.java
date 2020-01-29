package dream.main.action;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.main.dto.MemberPopupDTO;
import dream.main.form.MemberPopupForm;
import dream.main.service.MemberPopupService;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupAction.java,v 1.1 2013/08/30 09:10:46 javaworker Exp $
 * @since   1.0
 *
 * @struts:action path="/memberPop" name="memberPopupForm"
 *                input="/dream/main/memberPopup.jsp" scope="request"
 *                validate="false"                        
 * @struts.action-forward name="success"
 *                        path="/main/memberPopup.jsp" redirect="false"
 */
public class MemberPopupAction extends AuthAction
{
    /** 상세 page 조회 이동 */
	public static final int MEMBER_INIT   = 1001;
	/** 유저 수정 */
	public static final int MEMBER_UPDATE = 1002;
	
	protected ActionForward run(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
	{
		MemberPopupForm memberPopupForm = (MemberPopupForm)form;
		ActionForward returnActionForward = null;
		
		User loginUser = getUser(request);
		
		switch (memberPopupForm.getStrutsAction())
		{
			case MEMBER_UPDATE:   
				updateDetail(request, memberPopupForm, loginUser);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
			case MEMBER_INIT:
			default:
				findDetail(memberPopupForm, loginUser);
				returnActionForward = mapping.findForward("success");
				break;
		}
		return returnActionForward;
	}
	
	/**
	 * 유저 상세 조회
	 * @author  freeze
	 * @version $Id: MemberPopupAction.java,v 1.1 2013/08/30 09:10:46 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param memberPopupForm
	 * @throws Exception
	 */
    private void findDetail(MemberPopupForm memberPopupForm, User loginUser)
    {
        // Service 객체 생성
    	MemberPopupService memberPopupService = (MemberPopupService) getBean("memberPopupService"); 
        
        // 넘겨진 UserID 구함
        String userID = loginUser.getUserId();
        
        // 조회된 상세 해더부분 
        MemberPopupDTO memberPopupDTO = memberPopupService.findDetail(userID);
        
        // 조회된 상세 해더를 셋팅한다.
        memberPopupForm.setMemberPopupDTO(memberPopupDTO);
    }
    
    /**
     * 유저수정
     * @author  freeze
     * @version $Id: MemberPopupAction.java,v 1.1 2013/08/30 09:10:46 javaworker Exp $
     * @param request 
     * @since   1.0
     * 
     * @param memberPopupForm
     * @return
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, MemberPopupForm memberPopupForm, User loginUser)
    {
    	MemberPopupService memberPopupService = (MemberPopupService) getBean("memberPopupService"); 
    	MemberPopupDTO memberPopupDTO = memberPopupForm.getMemberPopupDTO();
    	
    	memberPopupService.updateDetail(memberPopupDTO);
    	setAjaxStatus(request);
    }
}
