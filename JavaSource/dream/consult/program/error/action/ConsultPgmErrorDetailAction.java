package dream.consult.program.error.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;
import dream.consult.program.error.form.ConsultPgmErrorDetailForm;
import dream.consult.program.error.service.ConsultPgmErrorDetailService;

/**
 * Error Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/consultPgmErrorDetail" name="consultPgmErrorDetailForm"
 *                input="/dream/consult/program/error/consultPgmErrorDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmErrorDetail" path="/dream/consult/program/error/consultPgmErrorDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmErrorDetailAction extends ConsultAuthAction
{
    public static final int DETAIL_INIT 		= 1001;
    /** ¼öÁ¤ */
    public static final int DETAIL_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmErrorDetailForm consultPgmErrorDetailForm = (ConsultPgmErrorDetailForm) form;
        
        switch (consultPgmErrorDetailForm.getStrutsAction())
        {
            case ConsultPgmErrorDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultPgmErrorDetailForm);
                returnActionForward = mapping.findForward("consultPgmErrorDetail");
                break;
            case ConsultPgmErrorDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultPgmErrorDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultPgmErrorDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultPgmErrorDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmErrorDetailForm consultPgmErrorDetailForm) throws Exception 
    {   
    	ConsultPgmErrorDetailService consultPgmErrorDetailService = (ConsultPgmErrorDetailService)getBean("consultPgmErrorDetailService");
    	
    	ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO = consultPgmErrorDetailForm.getConsultPgmErrorCommonDTO(); 

    	User user = getUser(request);
    	
    	ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO = consultPgmErrorDetailService.findPgmErrorDetail(consultPgmErrorCommonDTO, user);
    	consultPgmErrorDetailForm.setConsultPgmErrorDetailDTO(consultPgmErrorDetailDTO);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmErrorDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmErrorDetailForm consultPgmErrorDetailForm) throws Exception
    {
    	ConsultPgmErrorDetailService consultPgmErrorDetailService = (ConsultPgmErrorDetailService)getBean("consultPgmErrorDetailService");
    	ConsultPgmErrorDetailDTO  consultPgmErrorDetailDTO = consultPgmErrorDetailForm.getConsultPgmErrorDetailDTO();
    	
    	User user = getUser(request);
    	
    	consultPgmErrorDetailService.updatePgmErrorDetail(consultPgmErrorDetailDTO, user);
        
        setAjaxStatus(request);
    }

}