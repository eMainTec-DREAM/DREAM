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
 * mainȭ���� member button�� user detail ȭ��
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
    /** �� page ��ȸ �̵� */
	public static final int MEMBER_INIT   = 1001;
	/** ���� ���� */
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
	 * ���� �� ��ȸ
	 * @author  freeze
	 * @version $Id: MemberPopupAction.java,v 1.1 2013/08/30 09:10:46 javaworker Exp $
	 * @since   1.0
	 * 
	 * @param memberPopupForm
	 * @throws Exception
	 */
    private void findDetail(MemberPopupForm memberPopupForm, User loginUser)
    {
        // Service ��ü ����
    	MemberPopupService memberPopupService = (MemberPopupService) getBean("memberPopupService"); 
        
        // �Ѱ��� UserID ����
        String userID = loginUser.getUserId();
        
        // ��ȸ�� �� �ش��κ� 
        MemberPopupDTO memberPopupDTO = memberPopupService.findDetail(userID);
        
        // ��ȸ�� �� �ش��� �����Ѵ�.
        memberPopupForm.setMemberPopupDTO(memberPopupDTO);
    }
    
    /**
     * ��������
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
