package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.pm.list.dto.LovProductAcListDTO;
import dream.work.pm.list.form.LovProductAcListForm;
import dream.work.pm.list.service.LovProductAcListService;

/**
 * 생산제품 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovProductAcList" name="lovProductAcListForm"
 *                input="/dream/work/pm/list/lovProductAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovProductAcList" path="/dream/work/pm/list/lovProductAcList.jsp"
 *                        redirect="false"
 */
public class LovProductAcListAction extends SuperAuthAction
{
    public static final int LOV_STD_CHECK_POINT_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovProductAcListForm lovProductAcListForm = (LovProductAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovProductAcListForm.getStrutsAction())
        {
            case LovProductAcListAction.LOV_STD_CHECK_POINT_AC_FIND :
                findProductAcAcList(request, lovProductAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovProductAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovProductAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovProductAcListForm lovProductAcListForm) throws IOException
    {
        super.setHeader(request, response, lovProductAcListForm.getListId(),lovProductAcListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovProductAcListForm
     */
    private void findProductAcAcList(HttpServletRequest request, LovProductAcListForm lovProductAcListForm,
            HttpServletResponse response) throws IOException {
        
        LovProductAcListService lovProductAcListService = (LovProductAcListService)getBean("lovProductAcListService");
        
        LovProductAcListDTO lovProductAcListDTO = lovProductAcListForm.getLovProductAcListDTO();
        
        List resultList = lovProductAcListService.findProductAcAcList(lovProductAcListDTO,getUser(request), lovProductAcListForm);
        
        super.makeJsonResult(resultList, request, response, lovProductAcListForm.getListId());
    }


}