package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;
import dream.work.pm.list.form.LovStdCheckPointListForm;
import dream.work.pm.list.service.LovStdCheckPointListService;

/**
 * 표준점검항목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovStdCheckPointList" name="lovStdCheckPointListForm"
 *                input="/dream/work/pm/list/lovStdCheckPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovStdCheckPointList" path="/dream/work/pm/list/lovStdCheckPointList.jsp"
 *                        redirect="false"
 */
public class LovStdCheckPointListAction extends SuperAuthAction
{
    public static final int LOV_STD_CHECK_POINT_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovStdCheckPointListForm lovStdCheckPointListForm = (LovStdCheckPointListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovStdCheckPointListForm.getStrutsAction())
        {
            case LovStdCheckPointListAction.LOV_STD_CHECK_POINT_AC_FIND :
                findStdCheckPointAcList(request, lovStdCheckPointListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovStdCheckPointListAction.BASE_SET_HEADER:
                setHeader(request, response, lovStdCheckPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovStdCheckPointListForm lovStdCheckPointListForm) throws IOException
    {
        super.setHeader(request, response, lovStdCheckPointListForm.getListId(),lovStdCheckPointListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovStdCheckPointListForm
     */
    private void findStdCheckPointAcList(HttpServletRequest request, LovStdCheckPointListForm lovStdCheckPointListForm,
            HttpServletResponse response) throws IOException {
        
        LovStdCheckPointListService lovStdCheckPointListService = (LovStdCheckPointListService)getBean("lovStdCheckPointListService");
        
        LovStdCheckPointListDTO lovStdCheckPointListDTO = lovStdCheckPointListForm.getLovStdCheckPointListDTO();
        
        List resultList = lovStdCheckPointListService.findStdCheckPointAcList(lovStdCheckPointListDTO,getUser(request), lovStdCheckPointListForm);
        
        super.makeJsonResult(resultList, request, response, lovStdCheckPointListForm.getListId());
    }


}