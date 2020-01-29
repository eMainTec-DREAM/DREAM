package dream.rcm.taskmap.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.rcm.taskmap.dto.RcmTaskMapListLovDTO;
import dream.rcm.taskmap.form.RcmTaskMapListLovForm;
import dream.rcm.taskmap.service.RcmTaskMapListLovService;

/**
 * Task Map 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/rcmTaskMapListLov" name="rcmTaskMapListLovForm"
 *                input="/dream/rcm/taskmap/rcmTaskMapListLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmTaskMapListLov" path="/dream/rcm/taskmap/rcmTaskMapListLov.jsp"
 *                        redirect="false"
 */
public class RcmTaskMapListLovAction extends SuperAuthAction
{
    public static final int LOV_TASKMAPLIST_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        RcmTaskMapListLovForm rcmTaskMapListLovForm = (RcmTaskMapListLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (rcmTaskMapListLovForm.getStrutsAction())
        {
            case RcmTaskMapListLovAction.LOV_TASKMAPLIST_AC_FIND :
                findTaskMapList(request, rcmTaskMapListLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmTaskMapListLovAction.BASE_SET_HEADER:
                setHeader(request, response, rcmTaskMapListLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmTaskMapListLovForm rcmTaskMapListLovForm) throws IOException
    {
        super.setHeader(request, response, rcmTaskMapListLovForm.getListId(),rcmTaskMapListLovForm.getCurrentPageId()); 
    }

    /**
     * Task Map을 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            RcmTaskMapListLovForm rcmTaskMapListLovForm,HttpServletResponse response) throws IOException
    {
        RcmTaskMapListLovService rcmTaskMapListLovService = (RcmTaskMapListLovService)getBean("rcmTaskMapListLovService");
        
        RcmTaskMapListLovDTO rcmTaskMapListLovDTO = rcmTaskMapListLovForm.getRcmTaskMapListLovDTO();
        List resultList = rcmTaskMapListLovService.findTaskMapAcList(rcmTaskMapListLovDTO,getUser(request),rcmTaskMapListLovForm );
        
        super.makeJsonResult(resultList, request, response, rcmTaskMapListLovForm.getListId());
    	
    }

}