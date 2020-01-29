package dream.part.iss.wo.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.part.iss.wo.dto.LovSerialListDTO;
import dream.part.iss.wo.form.LovSerialListForm;
import dream.part.iss.wo.service.LovSerialListService;


/**
 * 순환자재 팝업
 * @author  hyosung
 * @version $Id: LovSerialListAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovSerialList" name="lovSerialListForm"
 *                input="/dream/part/iss/wo/lovSerialList.jsp" scope="request"
 *                validate="false"
 * 
 */
public class LovSerialListAction extends SuperAuthAction
{
    public static final int LOV_TASK_DEFAULT    = 1001;
    
    public static final int LOV_TASK_AC_FIND    = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovSerialListForm lovSerialListForm = (LovSerialListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovSerialListForm.getStrutsAction())
        {
            case LovSerialListAction.LOV_TASK_DEFAULT :
                returnActionForward = mapping.findForward("lovSerialList");
                break;
            case LovSerialListAction.LOV_TASK_AC_FIND :
                findSerialList(request, lovSerialListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovSerialListAction.BASE_SET_HEADER:
                setHeader(request, response, lovSerialListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward= mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovSerialListForm lovSerialListForm) throws IOException
    {
        super.setHeader(request, response, lovSerialListForm.getListId(),lovSerialListForm.getCurrentPageId()); 
    }

    /**
     * 질의 검색한다.
     * @author  hyosung
     * @version $Id: LovSerialListAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovSerialListForm
     */
    private void findSerialList(HttpServletRequest request,
            LovSerialListForm lovSerialListForm,HttpServletResponse response) throws IOException
    {
        LovSerialListService lovSerialListService = (LovSerialListService)getBean("lovSerialListService");
        
        LovSerialListDTO lovSerialListDTO = lovSerialListForm.getLovSerialListDTO();
        
        List resultList = lovSerialListService.findSerialList(lovSerialListDTO,getUser(request),lovSerialListForm);
        
        
        
        super.makeJsonResult(resultList, request, response, lovSerialListForm.getListId());
        
    }
   

}
