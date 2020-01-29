package dream.consult.comp.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.struts.SuperAuthAction;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;
import dream.consult.comp.list.form.ConsultCompListLovForm;
import dream.consult.comp.list.service.ConsultCompListLovService;


/**
 * 회사 LOV
 * @author  hyosung
 * @version $Id: ConsultCompListLovAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 * @struts:action path="/consultCompListLov" name="consultCompListLovForm"
 *                input="/dream/consult/comp/list/consultCompListLov.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/compValLov" name="consultCompListLovForm"
 *                input="/dream/consult/comp/list/compValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompListLov" path="/dream/consult/comp/list/consultCompListLov.jsp"
 *                        redirect="false"
 */
public class ConsultCompListLovAction extends  SuperAuthAction
{
    public static final int LOV_COMP_DEFAULT 	       = 1001;
    public static final int LOV_COMP_FIND 		       = 1002;
    
    public static final int LOV_COMP_AC_FIND           = 1003;
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ConsultCompListLovForm consultCompListLovForm = (ConsultCompListLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (consultCompListLovForm.getStrutsAction())
        {
            case ConsultCompListLovAction.LOV_COMP_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultCompListLovAction.LOV_COMP_FIND :
                findLineList(request, consultCompListLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case ConsultCompListLovAction.LOV_COMP_AC_FIND :
                findCompAcList(request, consultCompListLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompListLovAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompListLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    /**
     * TACOMP AC LOV를 검색한다.
     * @author  hyosung
     * @version $Id: ConsultCompListLovAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompListLovForm
     */
    private void findCompAcList(HttpServletRequest request, ConsultCompListLovForm consultCompListLovForm,
            HttpServletResponse response) throws IOException 
    {
        ConsultCompListLovService consultCompListLovService = (ConsultCompListLovService)getBean("consultCompListLovService");
        
        ConsultCompListLovDTO consultCompListLovDTO= consultCompListLovForm.getConsultCompListLovDTO();
        List resultList = consultCompListLovService.findCompAcList(consultCompListLovDTO, getUser(request), consultCompListLovForm);
      
        super.makeJsonResult(resultList, request, response, consultCompListLovForm.getListId());
        
    }
    

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompListLovForm consultCompListLovForm) throws IOException
    {
        super.setHeader(request, response, consultCompListLovForm.getListId(),consultCompListLovForm.getCurrentPageId()); 
    }

    /**
     * 회사검색
     * @author  hyosung
     * @version $Id: ConsultCompListLovAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompListLovForm
     */
    private void findLineList(HttpServletRequest request,
            ConsultCompListLovForm consultCompListLovForm,HttpServletResponse response) throws IOException
    {
        ConsultCompListLovService consultCompListLovService = (ConsultCompListLovService)getBean("consultCompListLovService");
        ConsultCompListLovDTO consultCompListLovDTO = consultCompListLovForm.getConsultCompListLovDTO();
        List resultList = consultCompListLovService.findCompList(consultCompListLovDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, consultCompListLovForm.getListId());
    	
    }

}