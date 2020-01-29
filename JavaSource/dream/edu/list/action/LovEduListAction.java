package dream.edu.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.edu.list.dto.LovEduListDTO;
import dream.edu.list.form.LovEduListForm;
import dream.edu.list.service.LovEduListService;

/**
 * 교육과정 LOV
 * @author  hyosung
 * @version $Id: LovEduListAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovEduList" name="lovEduListForm"
 *                input="/dream/edu/list/lovEduList.jsp" scope="request"
 *                validate="false"
 */
public class LovEduListAction extends AuthAction
{
    public static final int LOV_DEFAULT 	= 1001;
    public static final int LOV_AC_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEduListForm lovEduListForm = (LovEduListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEduListForm.getStrutsAction())
        {
            case LovEduListAction.LOV_DEFAULT :
                returnActionForward = mapping.findForward("lovEduList");
                break;
            case LovEduListAction.LOV_AC_FIND :
                findEduList(request, lovEduListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEduListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEduListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEduListForm lovEduListForm) throws IOException
    {
        super.setHeader(request, response, lovEduListForm.getListId(),lovEduListForm.getCurrentPageId()); 
    }

    /**
     * TACOURSE을 검색한다.
     * @author  hyosung
     * @version $Id: LovEduListAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovEduListForm
     */
    private void findEduList(HttpServletRequest request, LovEduListForm lovEduListForm,HttpServletResponse response) throws IOException
    {
        LovEduListService lovEduListService = (LovEduListService)getBean("lovEduListService");
        LovEduListDTO lovEduListDTO = lovEduListForm.getLovEduListDTO();
        List resultList = lovEduListService.findEduList(lovEduListDTO, getUser(request),lovEduListForm);
        
        super.makeJsonResult(resultList, request, response, lovEduListForm.getListId());
    	
    }

}