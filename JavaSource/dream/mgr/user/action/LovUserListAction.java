package dream.mgr.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.user.dto.LovUserListDTO;
import dream.mgr.user.form.LovUserListForm;
import dream.mgr.user.service.LovUserListService;

/**
 * 사용자 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovUserList" name="lovUserListForm"
 *                input="/dream/mgr/user/lovUserPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovUserAcList" name="lovUserListForm"
 *                input="/dream/mgr/user/lovUserAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUserPopup" path="/dream/mgr/user/lovUserPopup.jsp"
 *                        redirect="false"
 */
public class LovUserListAction extends AuthAction
{
    public static final int LOV_USER_DEFAULT    = 1001;
    public static final int LOV_USER_FIND       = 1002;
    
    public static final int LOV_USER_AC_FIND    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovUserListForm lovUserListForm = (LovUserListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovUserListForm.getStrutsAction())
        {
            case LovUserListAction.LOV_USER_DEFAULT :
                returnActionForward = mapping.findForward("lovUserPopup");
                break;
            case LovUserListAction.LOV_USER_FIND :
                findUserList(request, lovUserListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUserListAction.LOV_USER_AC_FIND :
                findUserAcList(request, lovUserListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUserListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUserListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findUserAcList(HttpServletRequest request, LovUserListForm lovUserListForm,HttpServletResponse response) throws IOException 
    {
    	LovUserListService lovUserListService = (LovUserListService)getBean("lovUserListService");
        
        LovUserListDTO lovUserListDTO = lovUserListForm.getLovUserListDTO();
        List resultList = lovUserListService.findUserList(lovUserListDTO, getUser(request), lovUserListForm);
        
        super.makeJsonResult(resultList, request, response, lovUserListForm.getListId());
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUserListForm lovUserListForm) throws IOException
    {
        super.setHeader(request, response, lovUserListForm.getListId(),lovUserListForm.getCurrentPageId()); 
    }

    /**
     * TAUSER 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovUserListForm
     */
    private void findUserList(HttpServletRequest request,
            LovUserListForm lovUserListForm,HttpServletResponse response) throws IOException
    {
        LovUserListService lovUserListService = (LovUserListService)getBean("lovUserListService");
        
        LovUserListDTO lovUserListDTO = lovUserListForm.getLovUserListDTO();
        List resultList = lovUserListService.findUserList(lovUserListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovUserListForm.getListId());
    	
    }

}