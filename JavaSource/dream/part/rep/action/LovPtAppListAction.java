package dream.part.rep.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rep.dto.LovPtAppListDTO;
import dream.part.rep.form.LovPtAppListForm;
import dream.part.rep.service.LovPtAppListService;

/**
 * 수리기안품의서 팝업
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * 
 * @struts:action path="/lovPtAppList" name="lovPtAppListForm"
 *                input="/dream/part/rep/lovPtAppList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPtAppPopup" path="/dream/part/rep/lovPtAppPopup.jsp"
 *                        redirect="false"
 */
public class LovPtAppListAction extends AuthAction
{
    public static final int LOV_PTAPP_DEFAULT 	= 1001;
    public static final int LOV_PTAPP_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPtAppListForm lovPtAppListForm = (LovPtAppListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPtAppListForm.getStrutsAction())
        {
            case LovPtAppListAction.LOV_PTAPP_DEFAULT :
                returnActionForward = mapping.findForward("lovPtAppPopup");
                break;
            case LovPtAppListAction.LOV_PTAPP_FIND :
                findPtAppList(request, lovPtAppListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPtAppListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPtAppListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPtAppListForm lovPtAppListForm) throws IOException
    {
        super.setHeader(request, response, lovPtAppListForm.getListId(),lovPtAppListForm.getCurrentPageId()); 
    }

    /**
     * 수리기안품의서 리스트 검색.
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param lovPtAppListForm
     */
    private void findPtAppList(HttpServletRequest request,
            LovPtAppListForm lovPtAppListForm,HttpServletResponse response) throws IOException
    {
        LovPtAppListService lovPtAppListService = (LovPtAppListService)getBean("lovPtAppListService");
        
        LovPtAppListDTO lovPtAppListDTO = lovPtAppListForm.getLovPtAppListDTO();
        List resultList = lovPtAppListService.findPtAppList(lovPtAppListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovPtAppListForm.getListId());
    	
    }

}