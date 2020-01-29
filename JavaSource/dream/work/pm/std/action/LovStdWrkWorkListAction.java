package dream.work.pm.std.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;
import dream.work.pm.std.form.LovStdWrkWorkListForm;
import dream.work.pm.std.service.LovStdWrkWorkListService;

/**
 * 표준점검항목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovStdWrkWorkList" name="lovStdWrkWorkListForm"
 *                input="/dream/work/pm/std/lovStdWrkWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovStdWrkWorkList" path="/dream/work/pm/std/lovStdWrkWorkList.jsp"
 *                        redirect="false"
 */
public class LovStdWrkWorkListAction extends SuperAuthAction
{
    public static final int LOV_STD_CHECK_POINT_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovStdWrkWorkListForm lovStdWrkWorkListForm = (LovStdWrkWorkListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovStdWrkWorkListForm.getStrutsAction())
        {
            case LovStdWrkWorkListAction.LOV_STD_CHECK_POINT_AC_FIND :
                findStdCheckPointAcList(request, lovStdWrkWorkListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovStdWrkWorkListAction.BASE_SET_HEADER:
                setHeader(request, response, lovStdWrkWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovStdWrkWorkListForm lovStdWrkWorkListForm) throws IOException
    {
        super.setHeader(request, response, lovStdWrkWorkListForm.getListId(),lovStdWrkWorkListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovStdWrkWorkListForm
     */
    private void findStdCheckPointAcList(HttpServletRequest request, LovStdWrkWorkListForm lovStdWrkWorkListForm,
            HttpServletResponse response) throws IOException {
        
        LovStdWrkWorkListService lovStdWrkWorkListService = (LovStdWrkWorkListService)getBean("lovStdWrkWorkListService");
        
        LovStdWrkWorkListDTO lovStdWrkWorkListDTO = lovStdWrkWorkListForm.getLovStdWrkWorkListDTO();
        
        List resultList = lovStdWrkWorkListService.findStdCheckPointAcList(lovStdWrkWorkListDTO,getUser(request), lovStdWrkWorkListForm);
        
        super.makeJsonResult(resultList, request, response, lovStdWrkWorkListForm.getListId());
    }


}