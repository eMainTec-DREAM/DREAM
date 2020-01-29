package dream.consult.comp.tracelog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;
import dream.consult.comp.tracelog.form.ConsultCompTracelogDetailForm;
import dream.consult.comp.tracelog.service.ConsultCompTracelogDetailService;

/**
 * Screen Trace - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompTracelogDetail" name="consultCompTracelogDetailForm"
 *                input="/dream/consult/comp/tracelog/consultCompTracelogDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTracelogDetail" path="/dream/consult/comp/tracelog/consultCompTracelogDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompTracelogDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompTracelogDetailForm consultCompTracelogDetailForm = (ConsultCompTracelogDetailForm) form;
        
        switch (consultCompTracelogDetailForm.getStrutsAction())
        {
            case ConsultCompTracelogDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultCompTracelogDetailForm);
                returnActionForward = mapping.findForward("consultCompTracelogDetail");
                break;
            case ConsultCompTracelogDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, consultCompTracelogDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompTracelogDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultCompTracelogDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompTracelogDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultCompTracelogDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompTracelogDetailForm consultCompTracelogDetailForm) throws Exception 
    {   
    	ConsultCompTracelogDetailService consultCompTracelogDetailService = (ConsultCompTracelogDetailService)getBean("consultCompTracelogDetailService");
    	
    	ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO = consultCompTracelogDetailForm.getConsultCompTracelogCommonDTO(); 
    	
    	ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO = consultCompTracelogDetailService.findCompTracelogDetail(consultCompTracelogCommonDTO,getUser(request));
    	consultCompTracelogDetailForm.setConsultCompTracelogDetailDTO(consultCompTracelogDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultCompTracelogDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompTracelogDetailForm consultCompTracelogDetailForm) throws Exception
    {
    	ConsultCompTracelogDetailService consultCompTracelogDetailService = (ConsultCompTracelogDetailService)getBean("consultCompTracelogDetailService");
    	ConsultCompTracelogDetailDTO  consultCompTracelogDetailDTO = consultCompTracelogDetailForm.getConsultCompTracelogDetailDTO(); 
    	
    	consultCompTracelogDetailService.insertCompTracelogDetail(consultCompTracelogDetailDTO,getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultCompTracelogDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompTracelogDetailForm consultCompTracelogDetailForm) throws Exception
    {
    	ConsultCompTracelogDetailService consultCompTracelogDetailService = (ConsultCompTracelogDetailService)getBean("consultCompTracelogDetailService");
    	ConsultCompTracelogDetailDTO  consultCompTracelogDetailDTO = consultCompTracelogDetailForm.getConsultCompTracelogDetailDTO();
    	
    	consultCompTracelogDetailService.updateCompTracelogDetail(consultCompTracelogDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }

}