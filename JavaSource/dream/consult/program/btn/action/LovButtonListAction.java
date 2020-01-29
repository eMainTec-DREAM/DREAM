package dream.consult.program.btn.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.btn.dto.LovButtonListDTO;
import dream.consult.program.btn.form.LovButtonListForm;
import dream.consult.program.btn.service.LovButtonListService;

/**
 * 버튼 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovButtonList" name="lovButtonListForm"
 *                input="/dream/consult/program/btn/lovButtonList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovButtonPopup" path="/dream/consult/program/btn/lovButtonPopup.jsp"
 *                        redirect="false"
 */
public class LovButtonListAction extends AuthAction
{
    public static final int LOV_BUTTON_DEFAULT 	= 1001;
    public static final int LOV_BUTTON_FIND     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovButtonListForm lovButtonListForm = (LovButtonListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovButtonListForm.getStrutsAction())
        {
            case LovButtonListAction.LOV_BUTTON_DEFAULT :
                returnActionForward = mapping.findForward("lovButtonPopup");
                break;
            case LovButtonListAction.LOV_BUTTON_FIND :
                findButtonList(request, lovButtonListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovButtonListAction.BASE_SET_HEADER:
                setHeader(request, response, lovButtonListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovButtonListForm lovButtonListForm) throws IOException
    {
        super.setHeader(request, response, lovButtonListForm.getListId(),lovButtonListForm.getCurrentPageId()); 
    }

    /**
     * TABUTTON 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovButtonListForm
     */
    private void findButtonList(HttpServletRequest request,
            LovButtonListForm lovButtonListForm,HttpServletResponse response) throws IOException
    {
        LovButtonListService lovButtonListService = (LovButtonListService)getBean("lovButtonListService");
        
        LovButtonListDTO lovButtonListDTO = lovButtonListForm.getLovButtonListDTO();
        List resultList = lovButtonListService.findButtonList(lovButtonListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovButtonListForm.getListId());
    	
    }

}