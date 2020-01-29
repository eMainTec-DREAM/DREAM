package dream.rcm.taskmap.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;

import dream.rcm.taskmap.dto.LovTaskMapListDTO;
import dream.rcm.taskmap.form.LovTaskMapListForm;
import dream.rcm.taskmap.service.LovTaskMapListService;

/**
 * 질의 팝업
 * @author  hyosung
 * @version $Id: LovTaskMapListAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovTaskMapList" name="lovTaskMapListForm"
 *                input="/dream/rcm/taskmap/lovTaskMapList.jsp" scope="request"
 *                validate="false"
 * 
 */
public class LovTaskMapListAction extends SuperAuthAction
{
    public static final int LOV_TASK_DEFAULT    = 1001;
    
    public static final int LOV_TASK_AC_FIND    = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovTaskMapListForm lovTaskMapListForm = (LovTaskMapListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovTaskMapListForm.getStrutsAction())
        {
            case LovTaskMapListAction.LOV_TASK_DEFAULT :
                returnActionForward = mapping.findForward("lovTaskMapList");
                break;
            case LovTaskMapListAction.LOV_TASK_AC_FIND :
                findTaskMapList(request, lovTaskMapListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovTaskMapListAction.BASE_SET_HEADER:
                setHeader(request, response, lovTaskMapListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward= mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovTaskMapListForm lovTaskMapListForm) throws IOException
    {
        super.setHeader(request, response, lovTaskMapListForm.getListId(),lovTaskMapListForm.getCurrentPageId()); 
    }

    /**
     * 질의 검색한다.
     * @author  hyosung
     * @version $Id: LovTaskMapListAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            LovTaskMapListForm lovTaskMapListForm,HttpServletResponse response) throws IOException
    {
        LovTaskMapListService lovTaskMapListService = (LovTaskMapListService)getBean("lovTaskMapListService");
        
        LovTaskMapListDTO lovTaskMapListDTO = lovTaskMapListForm.getLovTaskMapListDTO();
        List resultList = lovTaskMapListService.findTaskMapList(lovTaskMapListDTO,getUser(request),lovTaskMapListForm);
        
        super.makeJsonResult(resultList, request, response, lovTaskMapListForm.getListId());
        
    }
   

}