package dream.consult.comp.wrkcal.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalLovForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalLovService;

/**
 * 근무달력 팝업
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/wrkCalListLov" name="consultCompWrkcalLovForm"
 *                input="/dream/consult/comp/wrkcal/wrkCalListLov.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultCompWrkcalLov" name="consultCompWrkcalLovForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalLov" path="/dream/consult/comp/wrkcal/consultCompWrkcalLov.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalLovAction extends AuthAction
{
    public static final int LOV_WRKCAL_DEFAULT 	= 1001;
    public static final int LOV_WRKCAL_FIND 		= 1002;
    
    public static final int LOV_WRKCAL_AC_FIND 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ConsultCompWrkcalLovForm consultCompWrkcalLovForm = (ConsultCompWrkcalLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (consultCompWrkcalLovForm.getStrutsAction())
        {
            case ConsultCompWrkcalLovAction.LOV_WRKCAL_DEFAULT :
                returnActionForward = mapping.findForward("consultCompWrkcalLov");
                break;
            case ConsultCompWrkcalLovAction.LOV_WRKCAL_FIND :
                findWrkcalList(request, consultCompWrkcalLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalLovAction.LOV_WRKCAL_AC_FIND:
                findWrkcalAcList(request, consultCompWrkcalLovForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalLovAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompWrkcalLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    private void findWrkcalAcList(HttpServletRequest request, ConsultCompWrkcalLovForm consultCompWrkcalLovForm,
            HttpServletResponse response) throws IOException 
    {
        ConsultCompWrkcalLovService consultCompWrkcalLovService = (ConsultCompWrkcalLovService)getBean("consultCompWrkcalLovService");
        List resultList = consultCompWrkcalLovService.findWrkcalAcList(consultCompWrkcalLovForm, getUser(request));

        super.makeJsonResult(resultList, request, response, consultCompWrkcalLovForm.getListId());
        
    }
    
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompWrkcalLovForm consultCompWrkcalLovForm) throws IOException
    {
        super.setHeader(request, response, consultCompWrkcalLovForm.getListId(),consultCompWrkcalLovForm.getCurrentPageId()); 
    }

    /**
     * TAPOPLINE을 검색한다.
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalLovAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompWrkcalLovForm
     */
    private void findWrkcalList(HttpServletRequest request, ConsultCompWrkcalLovForm consultCompWrkcalLovForm,HttpServletResponse response) throws IOException
    {
        ConsultCompWrkcalLovService consultCompWrkcalLovService = (ConsultCompWrkcalLovService)getBean("consultCompWrkcalLovService");
        ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO = consultCompWrkcalLovForm.getConsultCompWrkcalLovDTO();
        List resultList = consultCompWrkcalLovService.findWrkcalList(consultCompWrkcalLovDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, consultCompWrkcalLovForm.getListId());
    	
    }

}